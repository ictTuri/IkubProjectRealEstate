package com.realestate.app.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.realestate.app.converter.PropertyTypeConverter;
import com.realestate.app.dto.PropertyTypeDto;
import com.realestate.app.entity.PropertyTypeEntity;
import com.realestate.app.entity.enums.PropertyTypeName;
import com.realestate.app.exceptions.MyExcMessages;
import com.realestate.app.repository.TypeRepository;
import com.realestate.app.service.TypeService;

@Service
@Transactional
public class TypeServiceImpl implements TypeService {

	private static final Logger logger = LogManager.getLogger(TypeServiceImpl.class);
	
	TypeRepository typeRepo;

	public TypeServiceImpl(TypeRepository typeRepo) {
		super();
		this.typeRepo = typeRepo;
	}

	//GET ALL PROPERTIES
	@Override
	public List<PropertyTypeDto> allPropertyTypes() {
		return PropertyTypeConverter.toDto(typeRepo.getAllPropertyTypes());
	}

	// GET PROPERTY BY ID
	@Override
	public PropertyTypeDto propertyTypeById(int id) {
		PropertyTypeEntity propertyType = typeRepo.getPropertyTypeById(id);
		if (propertyType != null) {
			
			//LOGGING
			logger.info("Showing property type by id, {}",propertyType);
			
			return PropertyTypeConverter.toDto(propertyType);
		} else {
			throw new MyExcMessages("No such property type with given Id !");
		}
	}

	// INSERT NEW PROPERTY TYPE
	@Override
	public PropertyTypeDto addPropertyType(PropertyTypeDto propertyType) {
		propertyType.setPropertyTypeName(propertyType.getPropertyTypeName().toUpperCase());
		PropertyTypeEntity propertyTypeToAdd = PropertyTypeConverter.toEntity(propertyType);
		
		//LOGGING
		logger.info("Inserted new property type, {}",propertyTypeToAdd);
		
		typeRepo.insertPropertyType(propertyTypeToAdd);
		return PropertyTypeConverter.toDto(propertyTypeToAdd);
	}

	// UPDATE PROPERTY TYPE
	@Override
	public PropertyTypeDto updatePropertyType(PropertyTypeDto propertyType, int id) {
		propertyType.setPropertyTypeName(propertyType.getPropertyTypeName().toUpperCase());
		PropertyTypeEntity propertyToUpdate = typeRepo.getPropertyTypeById(id);
		if (propertyToUpdate != null) {
			if (typeRepo.existPropertyType(PropertyTypeName.valueOf(propertyType.getPropertyTypeName()), propertyType.getPropertyTypeDesc())) {
				throw new MyExcMessages("Property type already exist with name and desciption given");
			} else {
				propertyToUpdate.setPropertyTypeName(PropertyTypeName.valueOf(propertyType.getPropertyTypeName()));
				propertyToUpdate.setPropertyTypeDesc(propertyType.getPropertyTypeDesc());
				typeRepo.updatePropertyType(propertyToUpdate);
				
				//LOGGING
				logger.info("Updated property type, {}",propertyToUpdate);
				
				return PropertyTypeConverter.toDto(propertyToUpdate);
			}
		} else {
			throw new MyExcMessages("No Property type with such Id / Please check again");
		}
	}

	// DELETE PROPERTY TYPE
	@Override
	public void deletePropertyType(int id) {
		PropertyTypeEntity propertyTypeToDelete = typeRepo.getPropertyTypeById(id);
		if (propertyTypeToDelete != null) {
			if (typeRepo.existPropertyTypeInProperties(propertyTypeToDelete)) {
				throw new MyExcMessages("Property type assigned to a property / consider update / can not delete");
			} else {
				typeRepo.deletePropertyType(propertyTypeToDelete);
				
				//LOGGING
				logger.info("Deleted property type, {}",propertyTypeToDelete);
			}
		} else {
			throw new MyExcMessages("Can not delete Property Type/ Or property type does not exist with given Id");
		}
	}

}
