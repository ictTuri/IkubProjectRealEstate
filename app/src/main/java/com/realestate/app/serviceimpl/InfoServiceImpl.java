package com.realestate.app.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.realestate.app.converter.PropertyInfoConverter;
import com.realestate.app.dto.PropertyInfoDto;
import com.realestate.app.entity.PropertyInfoEntity;
import com.realestate.app.exceptions.MyExcMessages;
import com.realestate.app.repository.InfoRepository;
import com.realestate.app.service.InfoService;

@Service
@Transactional
public class InfoServiceImpl implements InfoService {
	
	InfoRepository infoRepo;
	
	@Autowired
	public InfoServiceImpl(InfoRepository infoRepo) {
		super();
		this.infoRepo = infoRepo;
	}

	@Override
	public List<PropertyInfoEntity> allPropertyInfos() {
		return infoRepo.getAllPropertyInfos();
	}

	@Override
	public PropertyInfoEntity propertyInfoById(int id) {
		PropertyInfoEntity propertyInfo = infoRepo.getPropertyInfoById(id);
		if (propertyInfo != null) {
			return propertyInfo;
		} else {
			throw new MyExcMessages("No such property info with given Id !");
		}
	}


	@Override
	public PropertyInfoEntity addPropertyInfo(PropertyInfoDto propertyInfo) {
		PropertyInfoEntity propertyInfoToAdd = PropertyInfoConverter.toEntity(propertyInfo);
		infoRepo.insertPropertyInfo(propertyInfoToAdd);
		return propertyInfoToAdd;
	}


	@Override
	public PropertyInfoEntity updatePropertyInfo(PropertyInfoDto propertyInfo, int id) {
		PropertyInfoEntity propertyInfoToUpdate = infoRepo.getPropertyInfoById(id);
		if (propertyInfoToUpdate != null) {
			propertyInfoToUpdate.setHasGarage(propertyInfo.isHasGarage());
			propertyInfoToUpdate.setHasElevator(propertyInfo.isHasElevator());
			propertyInfoToUpdate.setHasPool(propertyInfo.isHasPool());
			propertyInfoToUpdate.setArea(propertyInfo.getArea());
			propertyInfoToUpdate.setFloorNumber(propertyInfo.getFloorNumber());
			propertyInfoToUpdate.setNrBathrooms(propertyInfo.getNrBathrooms());
			propertyInfoToUpdate.setNrBedrooms(propertyInfo.getNrBedrooms());
			infoRepo.updatePropertyInfo(propertyInfoToUpdate);
			return propertyInfoToUpdate;
		} else {
			throw new MyExcMessages("No Property info with such Id / Please check again");
		}
	}
	
	@Override
	public void deletePropertyInfo(int id) {
		PropertyInfoEntity propertyInfoToDelete = infoRepo.getPropertyInfoById(id);
		if (propertyInfoToDelete != null) {
			if (infoRepo.existProropertyWithInfo(propertyInfoToDelete)) {
				throw new MyExcMessages("Property info exist with a property, can not delete");
			} else {
				infoRepo.deletePropertyInfo(propertyInfoToDelete);
			}
		} else {
			throw new MyExcMessages("Can not delete Property Info/ Or property info does not exist with given Id");
		}

	}

}
