package com.realestate.app.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.realestate.app.converter.PropertyTypeConverter;
import com.realestate.app.dto.PropertyTypeDto;
import com.realestate.app.entity.PropertyTypeEntity;
import com.realestate.app.entity.enums.PropertyTypeNameEnum;
import com.realestate.app.exceptions.MyExcMessages;
import com.realestate.app.repository.TypeRepository;
import com.realestate.app.service.TypeService;

@Service
@Transactional
public class TypeServiceImpl implements TypeService {

	private static final Logger logger = LogManager.getLogger(TypeServiceImpl.class);
	
	TypeRepository typeRepo;

	@Autowired
	public TypeServiceImpl(TypeRepository typeRepo) {
		super();
		this.typeRepo = typeRepo;
	}

	@Override
	public List<PropertyTypeEntity> allPropertyTypes() {
		return typeRepo.getAllPropertyTypes();
	}

	@Override
	public PropertyTypeEntity propertyTypeById(int id) {
		PropertyTypeEntity propertyType = typeRepo.getPropertyTypeById(id);
		if (propertyType != null) {
			
			//LOGGING
			logger.info("Showing property type by id, {}",propertyType);
			
			return propertyType;
		} else {
			throw new MyExcMessages("No such property type with given Id !");
		}
	}

	@Override
	public PropertyTypeEntity addPropertyType(PropertyTypeDto propertyType) {
		PropertyTypeEntity propertyTypeToAdd = PropertyTypeConverter.toEntity(propertyType);
		
		//LOGGING
		logger.info("Inserted new property type, {}",propertyTypeToAdd);
		
		typeRepo.insertPropertyType(propertyTypeToAdd);
		return propertyTypeToAdd;
	}

	@Override
	public PropertyTypeEntity updatePropertyType(PropertyTypeDto propertyType, int id) {
		PropertyTypeEntity propertyToUpdate = typeRepo.getPropertyTypeById(id);
		if (propertyToUpdate != null) {
			if (typeRepo.existPropertyType(PropertyTypeNameEnum.valueOf(propertyType.getPropertyTypeName()), propertyType.getPropertyTypeDesc())) {
				throw new MyExcMessages("Property type already exist with name and desciption given");
			} else {
				propertyToUpdate.setPropertyTypeName(PropertyTypeNameEnum.valueOf(propertyType.getPropertyTypeName()));
				propertyToUpdate.setPropertyTypeDesc(propertyType.getPropertyTypeDesc());
				typeRepo.updatePropertyType(propertyToUpdate);
				
				//LOGGING
				logger.info("Updated property type, {}",propertyToUpdate);
				
				return propertyToUpdate;
			}
		} else {
			throw new MyExcMessages("No Property type with such Id / Please check again");
		}
	}

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
