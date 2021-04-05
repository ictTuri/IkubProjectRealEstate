package com.realestate.app.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.realestate.app.converter.PropertyConverter;
import com.realestate.app.dto.PropertyDtoForCreate;
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
	public List<PropertyEntity> allProperties() {
		return propertyRepo.getAllProperties();
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
		return null;
	}

	// CREATE NEW PROPERTY
	@Override
	public PropertyEntity addProperty(PropertyDtoForCreate property) {
		RoleEntity role = userRepo.getRoleById(2);
		if (userRepo.existUserById(property.getOwner(), role)) {
			if (locationRepo.existLocation(property.getPropertyLocation())) {
				if (propertyRepo.existPropertyInfo(property.getPropertyInfo())) {
					if (typeRepo.existPropertyType(property.getPropertyType())) {
						UserEntity owner = userRepo.getUserById(property.getOwner());
						LocationEntity location = locationRepo.getLocationById(property.getPropertyLocation());
						PropertyInfoEntity propertyInfo = infoRepo.getPropertyInfoById(property.getPropertyInfo());
						PropertyTypeEntity propertyType = typeRepo.getPropertyTypeById(property.getPropertyType());
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

	
	// UPDATE EXISTING PROPERTY
	@Override
	public PropertyEntity updateProperty(PropertyDtoForCreate property, int id) {
		PropertyEntity propertyToUpdate = propertyRepo.getPropertiesById(id);
		if (propertyToUpdate != null) {
			RoleEntity role = userRepo.getRoleById(2);
			if (userRepo.existUserById(property.getOwner(), role)) {
				if (locationRepo.existLocation(property.getPropertyLocation())) {
					return extractedUpdatePropertyValidation(property, id, propertyToUpdate);
				} else {
					throw new MyExcMessages("Updated Location does not exist !");
				}
			} else {
				throw new MyExcMessages("Updated Owner does not exist !");
			}
		} else
			throw new MyExcMessages("No Property with such Id / Please check again");
	}

	// PROPERTY UPDATE VALIDATIONS
	private PropertyEntity extractedUpdatePropertyValidation(PropertyDtoForCreate property, int id,
			PropertyEntity propertyToUpdate) {
		if (propertyRepo.existPropertyInfo(property.getPropertyInfo())) {
			if (propertyRepo.existPropertyInfoAnotherProperty(id, property.getPropertyInfo())) {
				propertyToUpdate.setCategory(property.getCategory());
				propertyToUpdate.setDescription(property.getDescription());
				UserEntity owner = userRepo.getUserById(property.getOwner());
				LocationEntity location = locationRepo.getLocationById(property.getPropertyLocation());
				PropertyInfoEntity propertyInfo = infoRepo.getPropertyInfoById(property.getPropertyInfo());
				PropertyTypeEntity propertyType = typeRepo.getPropertyTypeById(property.getPropertyType());
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

	// DELETE PROPERTY BY ID
	@Override
	public PropertyEntity deleteProperty(int id) {
		PropertyEntity propertyToDelete = propertyRepo.getPropertiesById(id);
		if (propertyToDelete != null) {
			IssuesEntity issue = issueRepo.existIssueWithProperty(propertyToDelete);
			if(issue==null) {
				if (tradeRepo.getTradeByProperty(propertyToDelete) == null) {
					propertyRepo.deleteProperty(propertyToDelete);
					return propertyToDelete;
				} else {
					throw new MyExcMessages("Property is Rented or Bought. Can not delete");
				}
			}else {
				throw new MyExcMessages("Property is linked with a issue !");
			}	
		} else {
			throw new MyExcMessages("Can not delete Property / Or property does not exist with given Id");
		}
	}

}
