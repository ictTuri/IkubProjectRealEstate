package com.realestate.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.realestate.app.converter.LocationConverter;
import com.realestate.app.converter.PropertyConverter;
import com.realestate.app.dto.LocationDto;
import com.realestate.app.dto.PropertyDtoForCreate;
import com.realestate.app.entity.LocationEntity;
import com.realestate.app.entity.PropertyEntity;
import com.realestate.app.entity.PropertyInfoEntity;
import com.realestate.app.entity.PropertyTypeEntity;
import com.realestate.app.entity.RoleEntity;
import com.realestate.app.entity.UserEntity;
import com.realestate.app.exceptions.MyExcMessages;
import com.realestate.app.repository.LocationRepository;
import com.realestate.app.repository.PropertyRepository;
import com.realestate.app.repository.TradeRepository;
import com.realestate.app.repository.UserRepository;

@Service
@Transactional
public class PropertyServiceImpl implements PropertyService {
	
	PropertyRepository propertyRepo;
	UserRepository userRepo;
	LocationRepository locationRepo;
	TradeRepository tradeRepo;
	@Autowired
	public PropertyServiceImpl(PropertyRepository propertyRepo, UserRepository userRepo,
			LocationRepository locationRepo, TradeRepository tradeRepo) {
		super();
		this.propertyRepo = propertyRepo;
		this.userRepo = userRepo;
		this.locationRepo = locationRepo;
		this.tradeRepo = tradeRepo;
	}


	
	@Override
	public List<PropertyEntity> allProperties() {
		return propertyRepo.getAllProperties();
	}

	@Override
	public List<LocationEntity> allLocations() {
		return locationRepo.getAllLocations();
	}


	@Override
	public PropertyEntity addProperty(PropertyDtoForCreate property) {
		if(property != null) {
			if(property.getCategory() == null || property.getDescription() == null) {
				throw new MyExcMessages("Please fill category and description !");
			}else {
				return propertyValidations(property);
			}
		}else {
			throw new MyExcMessages("Please fill the property data to proceed !");
		}
	}

	//Validations for the property extracted
	private PropertyEntity propertyValidations(PropertyDtoForCreate property) {
		RoleEntity role = userRepo.getRoleById(2);
		if(userRepo.existUserById(property.getOwner(),role)) {
			if(locationRepo.existLocation(property.getPropertyLocation())) {
				if(propertyRepo.existPropertyInfo(property.getPropertyInfo())) {
					if(propertyRepo.existPropertyType(property.getPropertyType())) {
						UserEntity owner = userRepo.getUserById(property.getOwner());
						LocationEntity location = locationRepo.getLocationById(property.getPropertyLocation());
						PropertyInfoEntity propertyInfo = propertyRepo.getPropertyInfoById(property.getPropertyInfo());
						PropertyTypeEntity propertyType = propertyRepo.getPropertyTypeById(property.getPropertyType());
						PropertyEntity propertyToAdd = PropertyConverter.toEntityForCreate(property, owner, propertyType, location, propertyInfo);
						propertyRepo.insertProperties(propertyToAdd);
						return propertyToAdd;
					}else {
						throw new MyExcMessages("No property type with given Id !");
					}
				}else {
					throw new MyExcMessages("No property info with given Id !");
				}
			}else {
				throw new MyExcMessages("No location with given Id !");
			}
		}else {
			throw new MyExcMessages("No owner with given Id !");
		}
	}


	@Override
	public LocationEntity addLocation(LocationDto location) {
		if(location != null) {
			if(location.getCityName() !=null && location.getStreetName() != null && location.getZipCode() > 999) {
				LocationEntity locationToAdd = LocationConverter.toEntity(location);
				locationRepo.insertLocation(locationToAdd);
				return locationToAdd;
			}else {
				throw new MyExcMessages("Please fill City, Street and zip data to proceed !");
			}
		}else {
			throw new MyExcMessages("Please fill the location data to proceed !");
		}
	}

	@Override
	public PropertyEntity updateProperty(PropertyDtoForCreate property, int id) {
		PropertyEntity propertyToUpdate = propertyRepo.getPropertiesById(id);
		if(propertyToUpdate != null) {
			RoleEntity role = userRepo.getRoleById(2);
			if(userRepo.existUserById(property.getOwner(),role)) {
				if(locationRepo.existLocation(property.getPropertyLocation())) {
					return propertyUpdateValidation(property, id, propertyToUpdate);
				}else {
					throw new MyExcMessages("Updated Location does not exist !");
				}
			}else {
				throw new MyExcMessages("Updated Owner does not exist !");
			}
		}else
			throw new MyExcMessages("No Property with such Id / Please check again");
	}


	//Validations for the updated property extracted
	private PropertyEntity propertyUpdateValidation(PropertyDtoForCreate property, int id,
			PropertyEntity propertyToUpdate) {
		if(propertyRepo.existPropertyInfo(property.getPropertyInfo())) {
			if(propertyRepo.existPropertyInfoAnotherProperty(id, property.getPropertyInfo())) {
				if(property.getCategory()!=null&&property.getDescription()!=null&&(property.getRentingPrice()>0 || property.getSellingPrice()>0)) {
					propertyToUpdate.setCategory(property.getCategory());
					propertyToUpdate.setDescription(property.getDescription());
					UserEntity owner = userRepo.getUserById(property.getOwner());
					LocationEntity location = locationRepo.getLocationById(property.getPropertyLocation());
					PropertyInfoEntity propertyInfo = propertyRepo.getPropertyInfoById(property.getPropertyInfo());
					PropertyTypeEntity propertyType = propertyRepo.getPropertyTypeById(property.getPropertyType());
					propertyToUpdate.setOwner(owner);
					propertyToUpdate.setPropertyInfo(propertyInfo);
					propertyToUpdate.setPropertyLocation(location);
					propertyToUpdate.setPropertyType(propertyType);
					propertyToUpdate.setRentingPrice(property.getRentingPrice());
					propertyToUpdate.setSellingPrice(property.getSellingPrice());
					propertyRepo.updateProperty(propertyToUpdate);
					return propertyToUpdate;
				}else {
					throw new MyExcMessages("Please fill all data fpr property !");
				}
			}else {
				throw new MyExcMessages("Info Id belong to another property !");
			}
		}else {
			throw new MyExcMessages("Updated Property Info does not exist !");
		}
	}

	@Override
	public PropertyEntity deleteProperty(int id) {
		PropertyEntity propertyToDelete = propertyRepo.getPropertiesById(id);
		if(propertyToDelete!=null) {
			if(tradeRepo.getTradeById(id)!=null) {
				propertyRepo.deleteProperty(propertyToDelete);
				return propertyToDelete;
			}else {
				throw new MyExcMessages("Property is Rented or Bought. Can not delete");
			}
		}else {
			throw new MyExcMessages("Can not delete Property / Or property does not exist with given Id");
		}
	}

}
