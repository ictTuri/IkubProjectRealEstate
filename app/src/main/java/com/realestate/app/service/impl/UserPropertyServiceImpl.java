package com.realestate.app.service.impl;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.realestate.app.converter.FullPropertyConverter;
import com.realestate.app.dto.FullPropertyDto;
import com.realestate.app.entity.IssuesEntity;
import com.realestate.app.entity.LocationEntity;
import com.realestate.app.entity.PropertyEntity;
import com.realestate.app.entity.PropertyInfoEntity;
import com.realestate.app.entity.PropertyTypeEntity;
import com.realestate.app.entity.RoleEntity;
import com.realestate.app.entity.UserEntity;
import com.realestate.app.entity.enums.PropertyCategoryEnum;
import com.realestate.app.exceptions.MyExcMessages;
import com.realestate.app.repository.InfoRepository;
import com.realestate.app.repository.IssueRepository;
import com.realestate.app.repository.LocationRepository;
import com.realestate.app.repository.PropertyRepository;
import com.realestate.app.repository.TradeRepository;
import com.realestate.app.repository.TypeRepository;
import com.realestate.app.repository.UserRepository;
import com.realestate.app.security.UserPrincipal;
import com.realestate.app.service.UserPropertyService;

@Service
@Transactional
public class UserPropertyServiceImpl implements UserPropertyService {

	private static final Logger logger = LogManager.getLogger(UserPropertyServiceImpl.class);
	
	TradeRepository tradeRepo;
	UserRepository userRepo;
	PropertyRepository propertyRepo;
	LocationRepository locationRepo;
	InfoRepository infoRepo;
	TypeRepository typeRepo;
	IssueRepository issueRepo;
	
	@Autowired
	public UserPropertyServiceImpl(UserRepository userRepo, PropertyRepository propertyRepo,
			LocationRepository locationRepo, InfoRepository infoRepo, TypeRepository typeRepo,
			TradeRepository tradeRepo, IssueRepository issueRepo) {
		super();
		this.userRepo = userRepo;
		this.propertyRepo = propertyRepo;
		this.locationRepo = locationRepo;
		this.infoRepo = infoRepo;
		this.typeRepo = typeRepo;
		this.tradeRepo = tradeRepo;
		this.issueRepo = issueRepo;
	}

	@Override
	public Iterable<PropertyEntity> showAllMyProperties() {
		UserPrincipal thisUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserEntity user = userRepo.getUserByUsername(thisUser.getUsername());
		
		//LOGGING
		logger.info("Showing all properties of Owner: {}", thisUser);
		
		return propertyRepo.getPropertiesByOwner(user);
	}
	
	@Override
	public PropertyEntity insertMyProperty(@Valid FullPropertyDto property) {
		UserPrincipal thisUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserEntity user = userRepo.getUserByUsername(thisUser.getUsername());
		if (locationRepo.existLocation(property.getLocation())) {
			PropertyInfoEntity propertyInfoToAdd = FullPropertyConverter.toInfoEntityForCreate(property);
			if (typeRepo.existPropertyType(property.getPropertyType())) {
				LocationEntity location = locationRepo.getLocationById(property.getLocation());
				PropertyTypeEntity propertyType = typeRepo.getPropertyTypeById(property.getPropertyType());
				PropertyEntity propertyToAdd = FullPropertyConverter.toEntityForCreate(property, user,
						propertyType, location, propertyInfoToAdd);
				
				//LOGGING
				logger.info("Inserting new Property, property: {}", propertyToAdd);
				
				propertyRepo.insertProperties(propertyToAdd);
				return propertyToAdd;
			} else {
				throw new MyExcMessages("No property type with given Id !");
			}
		} else {
			throw new MyExcMessages("No location with given Id !");
		}
	}

	@Override
	public PropertyEntity updateMyProperty(FullPropertyDto property, int id) {
		UserPrincipal thisUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserEntity user = userRepo.getUserByUsername(thisUser.getUsername());
		List<PropertyEntity> list = (List<PropertyEntity>) propertyRepo.getPropertiesByOwner(user);
		for (PropertyEntity pe : list) {
			if (pe.getPropertiesId() == id) {
				PropertyEntity propertyToUpdate = propertyRepo.getPropertiesById(id);
				if (propertyToUpdate != null) {
					PropertyInfoEntity propertyInfoToUpdate = infoRepo
							.getPropertyInfoById(propertyToUpdate.getPropertyInfo().getPropertyInfoId());
					RoleEntity role = userRepo.getRoleById(2);
					return extractedUpdateProcedure(property,user, propertyToUpdate, propertyInfoToUpdate, role);
				} else {
					throw new MyExcMessages("No Property with such Id / Please check again");
				}
			}else {
				throw new MyExcMessages("You have no property with given id");
			}
		}
		return null;
	}

	private PropertyEntity extractedUpdateProcedure(FullPropertyDto property,UserEntity user, PropertyEntity propertyToUpdate,
			PropertyInfoEntity propertyInfoToUpdate, RoleEntity role) {
		if (userRepo.existUserById(property.getOwner(), role)) {
			if (locationRepo.existLocation(property.getLocation())) {
				propertyInfoToUpdate.setHasGarage(property.isHasGarage());
				propertyInfoToUpdate.setHasElevator(property.isHasElevator());
				propertyInfoToUpdate.setHasPool(property.isHasPool());
				propertyInfoToUpdate.setArea(property.getArea());
				propertyInfoToUpdate.setFloorNumber(property.getFloorNumber());
				propertyInfoToUpdate.setNrBathrooms(property.getNrBathrooms());
				propertyInfoToUpdate.setNrBedrooms(property.getNrBedrooms());
				infoRepo.updatePropertyInfo(propertyInfoToUpdate);
				propertyToUpdate.setCategory(PropertyCategoryEnum.valueOf(property.getCategory()));
				propertyToUpdate.setDescription(property.getDescription());
				LocationEntity location = locationRepo.getLocationById(property.getLocation());
				PropertyTypeEntity propertyType = typeRepo.getPropertyTypeById(property.getPropertyType());
				propertyToUpdate.setOwner(user);
				propertyToUpdate.setPropertyLocation(location);
				propertyToUpdate.setPropertyType(propertyType);
				propertyToUpdate.setPropertyInfo(propertyInfoToUpdate);
				propertyToUpdate.setRentingPrice(property.getRentingPrice());
				propertyToUpdate.setSellingPrice(property.getSellingPrice());
				
				//LOGGING
				logger.info("Owner updateing property {} and property info: {}", propertyToUpdate,propertyInfoToUpdate);
				
				propertyRepo.updateProperty(propertyToUpdate);
				return propertyToUpdate;

			} else {
				throw new MyExcMessages("Updated Location does not exist !");
			}
		} else {
			throw new MyExcMessages("Updated Owner does not exist !");
		}
	}

	@Override
	public void deleteMyProperty(int id) {
		UserPrincipal thisUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserEntity user = userRepo.getUserByUsername(thisUser.getUsername());
		List<PropertyEntity> list = (List<PropertyEntity>) propertyRepo.getPropertiesByOwner(user);
		for(PropertyEntity pe: list) {
			if(pe.getPropertiesId() == id) {
				PropertyEntity propertyToDelete = propertyRepo.getPropertiesById(id);
				extractedPropertyDeletionValidation(propertyToDelete);
			}else {
				throw new MyExcMessages("You have no property with given id");
			}
		}
	}

	private void extractedPropertyDeletionValidation(PropertyEntity propertyToDelete) {
		if (propertyToDelete != null) {
			IssuesEntity issue = issueRepo.existIssueWithProperty(propertyToDelete);
			if (issue == null) {
				if (tradeRepo.getTradeByProperty(propertyToDelete) == null) {
					propertyRepo.deleteProperty(propertyToDelete);
					
					//LOGGING
					logger.info("Owner Deleting property: {}", propertyToDelete);
					
					infoRepo.deletePropertyInfo(propertyToDelete.getPropertyInfo());
				} else {
					throw new MyExcMessages("Property is Rented or Bought. Can not delete");
				}
			} else {
				throw new MyExcMessages("Property is linked with a issue !");
			}
		} else {
			throw new MyExcMessages("Can not delete Property / Or property does not exist with given Id");
		}
	}

}
