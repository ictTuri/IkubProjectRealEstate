package com.realestate.app.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.realestate.app.dto.FullPropertyDto;
import com.realestate.app.entity.IssuesEntity;
import com.realestate.app.entity.LocationEntity;
import com.realestate.app.entity.PropertyEntity;
import com.realestate.app.entity.PropertyInfoEntity;
import com.realestate.app.entity.PropertyTypeEntity;
import com.realestate.app.entity.RoleEntity;
import com.realestate.app.entity.UserEntity;
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
		return propertyRepo.getPropertiesByOwner(user);
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
					return extractedUpdateProcedure(property, propertyToUpdate, propertyInfoToUpdate, role);
				} else {
					throw new MyExcMessages("No Property with such Id / Please check again");
				}
			}else {
				throw new MyExcMessages("You have no property with given id");
			}
		}
		return null;
	}

	private PropertyEntity extractedUpdateProcedure(FullPropertyDto property, PropertyEntity propertyToUpdate,
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
				propertyToUpdate.setCategory(property.getCategory());
				propertyToUpdate.setDescription(property.getDescription());
				UserEntity owner = userRepo.getUserById(property.getOwner());
				LocationEntity location = locationRepo.getLocationById(property.getLocation());
				PropertyTypeEntity propertyType = typeRepo.getPropertyTypeById(property.getPropertyType());
				propertyToUpdate.setOwner(owner);
				propertyToUpdate.setPropertyLocation(location);
				propertyToUpdate.setPropertyType(propertyType);
				propertyToUpdate.setPropertyInfo(propertyInfoToUpdate);
				propertyToUpdate.setRentingPrice(property.getRentingPrice());
				propertyToUpdate.setSellingPrice(property.getSellingPrice());
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
				if (propertyToDelete != null) {
					IssuesEntity issue = issueRepo.existIssueWithProperty(propertyToDelete);
					if (issue == null) {
						if (tradeRepo.getTradeByProperty(propertyToDelete) == null) {
							propertyRepo.deleteProperty(propertyToDelete);
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
			}else {
				throw new MyExcMessages("You have no property with given id");
			}
		}
	}

}
