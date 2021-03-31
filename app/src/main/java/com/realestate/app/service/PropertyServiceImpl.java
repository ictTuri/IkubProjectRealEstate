package com.realestate.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.realestate.app.converter.LocationConverter;
import com.realestate.app.converter.PropertyConverter;
import com.realestate.app.converter.PropertyInfoConverter;
import com.realestate.app.converter.PropertyTypeConverter;
import com.realestate.app.dto.LocationDto;
import com.realestate.app.dto.PropertyDtoForCreate;
import com.realestate.app.dto.PropertyInfoDto;
import com.realestate.app.dto.PropertyTypeDto;
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

	// DISPLAY ALL PROPERTIES
	@Override
	public List<PropertyEntity> allProperties() {
		return propertyRepo.getAllProperties();
	}

	// DISPLAY PROPERTY BY ID
	@Override
	public PropertyEntity propertyById(int id) {
		PropertyEntity property = propertyRepo.getPropertiesById(id);
		if (property != null) {
			return property;
		} else {
			throw new MyExcMessages("No such property with given Id !");
		}
	}

	// DISPLAY ALL LOCATIONS
	@Override
	public List<LocationEntity> allLocations() {
		return locationRepo.getAllLocations();
	}

	// DISPLAY LOCATION BY ID
	@Override
	public LocationEntity locationById(int id) {
		LocationEntity location = locationRepo.getLocationById(id);
		if (location != null) {
			return location;
		} else {
			throw new MyExcMessages("No such location with given Id !");
		}
	}

	// DISPLAY ALL PROPERTY TYPES
	@Override
	public List<PropertyTypeEntity> allPropertyTypes() {
		return propertyRepo.getAllPropertyTypes();
	}

	// DISPLAY PROPERTY TYPE BY ID
	@Override
	public PropertyTypeEntity propertyTypeById(int id) {
		PropertyTypeEntity propertyType = propertyRepo.getPropertyTypeById(id);
		if (propertyType != null) {
			return propertyType;
		} else {
			throw new MyExcMessages("No such property type with given Id !");
		}
	}

	// DISPLAY ALL PROPERTY INFOS
	@Override
	public List<PropertyInfoEntity> allPropertyInfos() {
		return propertyRepo.getAllPropertyInfos();
	}

	// DISPLAY PROPERTY INFO BY ID
	@Override
	public PropertyInfoEntity propertyInfoById(int id) {
		PropertyInfoEntity propertyInfo = propertyRepo.getPropertyInfoById(id);
		if (propertyInfo != null) {
			return propertyInfo;
		} else {
			throw new MyExcMessages("No such property info with given Id !");
		}
	}

	// PROPERTY INSERT
	@Override
	public PropertyEntity addProperty(PropertyDtoForCreate property) {
		RoleEntity role = userRepo.getRoleById(2);
		if (userRepo.existUserById(property.getOwner(), role)) {
			if (locationRepo.existLocation(property.getPropertyLocation())) {
				if (propertyRepo.existPropertyInfo(property.getPropertyInfo())) {
					if (propertyRepo.existPropertyType(property.getPropertyType())) {
						UserEntity owner = userRepo.getUserById(property.getOwner());
						LocationEntity location = locationRepo.getLocationById(property.getPropertyLocation());
						PropertyInfoEntity propertyInfo = propertyRepo.getPropertyInfoById(property.getPropertyInfo());
						PropertyTypeEntity propertyType = propertyRepo.getPropertyTypeById(property.getPropertyType());
						PropertyEntity propertyToAdd = PropertyConverter.toEntityForCreate(property, owner,
								propertyType, location, propertyInfo);
						propertyRepo.insertProperties(propertyToAdd);
						return propertyToAdd;
					} else {
						throw new MyExcMessages("No property type with given Id !");
					}
				} else {
					throw new MyExcMessages("No property info with given Id !");
				}
			} else {
				throw new MyExcMessages("No location with given Id !");
			}
		} else {
			throw new MyExcMessages("No owner with given Id !");
		}
	}

	// LOCATION INSERT
	@Override
	public LocationEntity addLocation(LocationDto location) {
		LocationEntity locationToAdd = LocationConverter.toEntity(location);
		locationRepo.insertLocation(locationToAdd);
		return locationToAdd;
	}

	// PROPERTY TYPE INSERT
	@Override
	public PropertyTypeEntity addPropertyType(PropertyTypeDto propertyType) {
		PropertyTypeEntity propertyTypeToAdd = PropertyTypeConverter.toEntity(propertyType);
		propertyRepo.insertPropertyType(propertyTypeToAdd);
		return propertyTypeToAdd;
	}

	// PROPERTY INFO INSERT
	@Override
	public PropertyInfoEntity addPropertyInfo(PropertyInfoDto propertyInfo) {
		PropertyInfoEntity propertyInfoToAdd = PropertyInfoConverter.toEntity(propertyInfo);
		propertyRepo.insertPropertyInfo(propertyInfoToAdd);
		return propertyInfoToAdd;
	}

	// PROPERTY UPDATE
	@Override
	public PropertyEntity updateProperty(PropertyDtoForCreate property, int id) {
		PropertyEntity propertyToUpdate = propertyRepo.getPropertiesById(id);
		if (propertyToUpdate != null) {
			RoleEntity role = userRepo.getRoleById(2);
			if (userRepo.existUserById(property.getOwner(), role)) {
				if (locationRepo.existLocation(property.getPropertyLocation())) {
					return propertyUpdateValidation(property, id, propertyToUpdate);
				} else {
					throw new MyExcMessages("Updated Location does not exist !");
				}
			} else {
				throw new MyExcMessages("Updated Owner does not exist !");
			}
		} else
			throw new MyExcMessages("No Property with such Id / Please check again");
	}

	// Validations for the updated property extracted
	private PropertyEntity propertyUpdateValidation(PropertyDtoForCreate property, int id,
			PropertyEntity propertyToUpdate) {
		if (propertyRepo.existPropertyInfo(property.getPropertyInfo())) {
			if (propertyRepo.existPropertyInfoAnotherProperty(id, property.getPropertyInfo())) {
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
			} else {
				throw new MyExcMessages("Info Id belong to another property !");
			}
		} else {
			throw new MyExcMessages("Updated Property Info does not exist !");
		}
	}

	// LOCATION UPDATE
	@Override
	public LocationEntity updateLocation(LocationDto location, int id) {
		LocationEntity locationToUpdate = locationRepo.getLocationById(id);
		if (locationToUpdate != null) {
			locationToUpdate.setCityName(location.getCityName());
			locationToUpdate.setStreetName(location.getStreetName());
			locationToUpdate.setDescription(location.getDescription());
			locationToUpdate.setZipCode(location.getZipCode());
			locationRepo.updateLocation(locationToUpdate);
			return locationToUpdate;
		} else {
			throw new MyExcMessages("No Location with such Id / Please check again");
		}
	}

	// PROPERTY TYPE UPDATE
	@Override
	public PropertyTypeEntity updatePropertyType(PropertyTypeDto propertyType, int id) {
		PropertyTypeEntity propertyToUpdate = propertyRepo.getPropertyTypeById(id);
		if (propertyToUpdate != null) {
			if (propertyRepo.existPropertyType(propertyType.getPropertyTypeName(),
					propertyType.getPropertyTypeDesc())) {
				throw new MyExcMessages("Property type already exist with name and desciption given");
			} else {
				propertyToUpdate.setPropertyTypeName(propertyType.getPropertyTypeName());
				propertyToUpdate.setPropertyTypeDesc(propertyType.getPropertyTypeDesc());
				propertyRepo.updatePropertyType(propertyToUpdate);
				return propertyToUpdate;
			}
		} else {
			throw new MyExcMessages("No Property type with such Id / Please check again");
		}
	}

	// PROPERTY INFO UPDATE
	@Override
	public PropertyInfoEntity updatePropertyInfo(PropertyInfoDto propertyInfo, int id) {
		PropertyInfoEntity propertyInfoToUpdate = propertyRepo.getPropertyInfoById(id);
		if (propertyInfoToUpdate != null) {
			propertyInfoToUpdate.setHasGarage(propertyInfo.isHasGarage());
			propertyInfoToUpdate.setHasElevator(propertyInfo.isHasElevator());
			propertyInfoToUpdate.setHasPool(propertyInfo.isHasPool());
			propertyInfoToUpdate.setArea(propertyInfo.getArea());
			propertyInfoToUpdate.setFloorNumber(propertyInfo.getFloorNumber());
			propertyInfoToUpdate.setNrBathrooms(propertyInfo.getNrBathrooms());
			propertyInfoToUpdate.setNrBedrooms(propertyInfo.getNrBedrooms());
			propertyRepo.updatePropertyInfo(propertyInfoToUpdate);
			return propertyInfoToUpdate;
		} else {
			throw new MyExcMessages("No Property info with such Id / Please check again");
		}
	}

	// PROPERTY DELETION
	@Override
	public PropertyEntity deleteProperty(int id) {
		PropertyEntity propertyToDelete = propertyRepo.getPropertiesById(id);
		if (propertyToDelete != null) {
			if (tradeRepo.getTradeById(id) != null) {
				propertyRepo.deleteProperty(propertyToDelete);
				return propertyToDelete;
			} else {
				throw new MyExcMessages("Property is Rented or Bought. Can not delete");
			}
		} else {
			throw new MyExcMessages("Can not delete Property / Or property does not exist with given Id");
		}
	}

	// LOCATION DELETION
	@Override
	public void deleteLocation(int id) {
		LocationEntity locationToDelete = locationRepo.getLocationById(id);
		if (locationToDelete != null) {
			if (propertyRepo.existLocationInProperty(locationToDelete)) {
				throw new MyExcMessages("Location attached to a property, can not delete");
			} else {
				locationRepo.deleteLocation(locationToDelete);
			}
		} else {
			throw new MyExcMessages("No locatin on given id or already deleted");
		}
	}

	// PROPERTY TYPE DELETION
	@Override
	public void deletePropertyType(int id) {
		PropertyTypeEntity propertyTypeToDelete = propertyRepo.getPropertyTypeById(id);
		if (propertyTypeToDelete != null) {
			if (propertyRepo.existPropertyTypeInProperties(propertyTypeToDelete)) {
				throw new MyExcMessages("Property type assigned to a property / consider update / can not delete");
			} else {
				propertyRepo.deletePropertyType(propertyTypeToDelete);
			}
		} else {
			throw new MyExcMessages("Can not delete Property Type/ Or property type does not exist with given Id");
		}
	}

	// PROPERTY INFO DELETION
	@Override
	public void deletePropertyInfo(int id) {
		PropertyInfoEntity propertyInfoToDelete = propertyRepo.getPropertyInfoById(id);
		if (propertyInfoToDelete != null) {
			if (propertyRepo.existRropertyWithInfo(propertyInfoToDelete)) {
				throw new MyExcMessages("Property info exist with a property, can not delete");
			} else {
				propertyRepo.deletePropertyInfo(propertyInfoToDelete);
			}
		} else {
			throw new MyExcMessages("Can not delete Property Info/ Or property info does not exist with given Id");
		}
	}

}
