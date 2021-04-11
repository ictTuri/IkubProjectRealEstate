package com.realestate.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.realestate.app.converter.FullPropertyConverter;
import com.realestate.app.converter.UserConverter;
import com.realestate.app.dto.FullPropertyDto;
import com.realestate.app.dto.PropertyDto;
import com.realestate.app.dto.UserDto;
import com.realestate.app.entity.IssuesEntity;
import com.realestate.app.entity.LocationEntity;
import com.realestate.app.entity.PropertyEntity;
import com.realestate.app.entity.PropertyInfoEntity;
import com.realestate.app.entity.PropertyTypeEntity;
import com.realestate.app.entity.RoleEntity;
import com.realestate.app.entity.UserEntity;
import com.realestate.app.entity.enums.PropertyCategory;
import com.realestate.app.exceptions.MyExcMessages;
import com.realestate.app.filter.PropertyFilter;
import com.realestate.app.repository.impl.InfoRepositoryImpl;
import com.realestate.app.repository.impl.IssueRepositoryImpl;
import com.realestate.app.repository.impl.LocationRepositoryImpl;
import com.realestate.app.repository.impl.PropertyRepositoryImpl;
import com.realestate.app.repository.impl.TradeRepositoryImpl;
import com.realestate.app.repository.impl.TypeRepositoryImpl;
import com.realestate.app.repository.impl.UserRepositoryImpl;
import com.realestate.app.service.PropertyService;

@Service
@Transactional
public class PropertyServiceImpl implements PropertyService {

	private static final Logger logger = LogManager.getLogger(PropertyServiceImpl.class);
	
	IssueRepositoryImpl issueRepo;
	PropertyRepositoryImpl propertyRepo;
	UserRepositoryImpl userRepo;
	LocationRepositoryImpl locationRepo;
	TradeRepositoryImpl tradeRepo;
	TypeRepositoryImpl typeRepo;
	InfoRepositoryImpl infoRepo;

	@Autowired
	public PropertyServiceImpl(PropertyRepositoryImpl propertyRepo, UserRepositoryImpl userRepo,
			LocationRepositoryImpl locationRepo, TradeRepositoryImpl tradeRepo, IssueRepositoryImpl issueRepo,
			TypeRepositoryImpl typeRepo, InfoRepositoryImpl infoRepo) {
		super();
		this.propertyRepo = propertyRepo;
		this.userRepo = userRepo;
		this.locationRepo = locationRepo;
		this.tradeRepo = tradeRepo;
		this.issueRepo = issueRepo;
		this.typeRepo = typeRepo;
		this.infoRepo = infoRepo;
	}

	// GET ALL PROPERTIES
	@Override
	public List<PropertyDto> getAllProperties(PropertyFilter filter) {
		
		//LOGGING
		logger.info("Filtring Properties with filter {}", filter);
		
		filter.setCategory(filter.getCategory().toUpperCase());
		List<PropertyDto> toReturn= new ArrayList<>();
		propertyRepo.getAllProperties(filter).forEach(entity -> toReturn.add(FullPropertyConverter.singleToDto(entity)));
		return toReturn;
	}

	// GET PROPERTY BY ID
	@Override
	public PropertyDto propertyById(int id) {
		PropertyEntity property = propertyRepo.getPropertiesById(id);
		if (property != null) {
			
			//LOGGING
			logger.info("Showing Properties by id, property: {}", property);
			
			return FullPropertyConverter.singleToDto(property);
		} else {
			throw new MyExcMessages("No such property with given Id !");
		}
	}

	@Override
	public UserDto propertyOwner(int id) {
		
		//LOGGING
		logger.info("Showing Properties Owner");
		
		return UserConverter.toDto(propertyRepo.getPropertyOwner(id));
	}

	// CREATE NEW PROPERTY
	@Override
	public PropertyDto addProperty(FullPropertyDto property) {
		property.setCategory(property.getCategory().toUpperCase());
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
					
					//LOGGING
					logger.info("Inserting new Property, property: {}", propertyToAdd);
					
					propertyRepo.insertProperties(propertyToAdd);
					return FullPropertyConverter.singleToDto(propertyToAdd);
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
	public PropertyDto updateProperty(FullPropertyDto property, int id) {
		property.setCategory(property.getCategory().toUpperCase());
		PropertyEntity propertyToUpdate = propertyRepo.getPropertiesById(id);
		if (propertyToUpdate != null) {
			PropertyInfoEntity propertyInfoToUpdate = infoRepo.getPropertyInfoById(propertyToUpdate.getPropertyInfo().getPropertyInfoId());
			RoleEntity role = userRepo.getRoleById(2);
			if (userRepo.existUserById(property.getOwner(), role)) {
				if (locationRepo.existLocation(property.getLocation())) {
					propertyInfoToUpdate.setHasGarage(property.getPropertyInfo().isHasGarage());
					propertyInfoToUpdate.setHasElevator(property.getPropertyInfo().isHasElevator());
					propertyInfoToUpdate.setHasPool(property.getPropertyInfo().isHasPool());
					propertyInfoToUpdate.setArea(property.getPropertyInfo().getArea());
					propertyInfoToUpdate.setFloorNumber(property.getPropertyInfo().getFloorNumber());
					propertyInfoToUpdate.setNrBathrooms(property.getPropertyInfo().getNrBathrooms());
					propertyInfoToUpdate.setNrBedrooms(property.getPropertyInfo().getNrBedrooms());
					infoRepo.updatePropertyInfo(propertyInfoToUpdate);
					propertyToUpdate.setCategory(PropertyCategory.valueOf(property.getCategory()));
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
					
					//LOGGING
					logger.info("Updating Property, property: {}", propertyToUpdate);
					
					propertyRepo.updateProperty(propertyToUpdate);
					return FullPropertyConverter.singleToDto(propertyToUpdate);

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
	public void deleteProperty(int id) {
		PropertyEntity propertyToDelete = propertyRepo.getPropertiesById(id);
		if (propertyToDelete != null) {
			IssuesEntity issue = issueRepo.existIssueWithProperty(propertyToDelete);
			if (issue == null) {
				if (tradeRepo.getTradeByProperty(propertyToDelete) == null) {
					propertyRepo.deleteProperty(propertyToDelete);
					infoRepo.deletePropertyInfo(propertyToDelete.getPropertyInfo());
					
					//LOGGING
					logger.info("Delete of Property, property: {}", propertyToDelete);
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
