package com.realestate.app.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.realestate.app.exceptions.MyExcMessages;
import com.realestate.app.filter.PropertyFilter;
import com.realestate.app.repository.InfoRepository;
import com.realestate.app.repository.IssueRepository;
import com.realestate.app.repository.LocationRepository;
import com.realestate.app.repository.PropertyRepository;
import com.realestate.app.repository.TradeRepository;
import com.realestate.app.repository.TypeRepository;
import com.realestate.app.repository.UserRepository;
import com.realestate.app.service.PropertyService;

@Service
@Transactional
public class PropertyServiceImpl implements PropertyService {

	IssueRepository issueRepo;
	PropertyRepository propertyRepo;
	UserRepository userRepo;
	LocationRepository locationRepo;
	TradeRepository tradeRepo;
	TypeRepository typeRepo;
	InfoRepository infoRepo;

	private static final Logger logger = LogManager.getLogger(PropertyServiceImpl.class);

	@Autowired
	public PropertyServiceImpl(PropertyRepository propertyRepo, UserRepository userRepo,
			LocationRepository locationRepo, TradeRepository tradeRepo, IssueRepository issueRepo,
			TypeRepository typeRepo, InfoRepository infoRepo) {
		super();
		this.propertyRepo = propertyRepo;
		this.userRepo = userRepo;
		this.locationRepo = locationRepo;
		this.tradeRepo = tradeRepo;
		this.issueRepo = issueRepo;
		this.typeRepo = typeRepo;
		this.infoRepo = infoRepo;
	}

	@Override
	public List<PropertyEntity> getAllProperties(PropertyFilter filter) {
		logger.info("Filtring Properties with filter {}", filter);
		return propertyRepo.getAllProperties(filter);
	}

	@Override
	public PropertyEntity propertyById(int id) {
		PropertyEntity property = propertyRepo.getPropertiesById(id);
		if (property != null) {
			return property;
		} else {
			throw new MyExcMessages("No such property with given Id !");
		}
	}

	@Override
	public UserEntity propertyOwner(int id) {
		return propertyRepo.getPropertyOwner(id);
	}

	// CREATE NEW PROPERTY
	@Override
	public PropertyEntity addProperty(FullPropertyDto property) {
		RoleEntity role = userRepo.getRoleById(2);
		if (userRepo.existUserById(property.getOwner(), role)) {
			if (locationRepo.existLocation(property.getLocation())) {
				PropertyInfoEntity propertyInfoToAdd = FullPropertyConverter.toInfoEntityForCreate(property);
				if (typeRepo.existPropertyType(property.getPropertyType())) {
					UserEntity owner = userRepo.getUserById(property.getOwner());
					LocationEntity location = locationRepo.getLocationById(property.getLocation());
					PropertyTypeEntity propertyType = typeRepo.getPropertyTypeById(property.getPropertyType());
					PropertyEntity propertyToAdd = FullPropertyConverter.toEntityForCreate(property, owner,
							propertyType, location, propertyInfoToAdd);
					propertyRepo.insertProperties(propertyToAdd);
					return propertyToAdd;
				} else {
					throw new MyExcMessages("No property type with given Id !");
				}
			} else {
				throw new MyExcMessages("No location with given Id !");
			}
		} else {
			throw new MyExcMessages("No owner with given Id !");
		}
	}

	// UPDATE EXISTING PROPERTY
	@Override
	public PropertyEntity updateProperty(FullPropertyDto property, int id) {
		PropertyEntity propertyToUpdate = propertyRepo.getPropertiesById(id);
		if (propertyToUpdate != null) {
			PropertyInfoEntity propertyInfoToUpdate = infoRepo.getPropertyInfoById(propertyToUpdate.getPropertyInfo().getPropertyInfoId());
			RoleEntity role = userRepo.getRoleById(2);
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
			throw new MyExcMessages("No Property with such Id / Please check again");
		
	}

	// DELETE PROPERTY BY ID
	@Override
	public PropertyEntity deleteProperty(int id) {
		PropertyEntity propertyToDelete = propertyRepo.getPropertiesById(id);
		if (propertyToDelete != null) {
			IssuesEntity issue = issueRepo.existIssueWithProperty(propertyToDelete);
			if (issue == null) {
				if (tradeRepo.getTradeByProperty(propertyToDelete) == null) {
					propertyRepo.deleteProperty(propertyToDelete);
					infoRepo.deletePropertyInfo(propertyToDelete.getPropertyInfo());
					return propertyToDelete;
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
