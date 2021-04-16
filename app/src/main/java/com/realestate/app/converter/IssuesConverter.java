package com.realestate.app.converter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.realestate.app.dto.ClientIssueForCreateDto;
import com.realestate.app.dto.IssuesDto;
import com.realestate.app.dto.IssuesForCreateDto;
import com.realestate.app.entity.IssuesEntity;
import com.realestate.app.entity.PropertyEntity;
import com.realestate.app.entity.UserEntity;

public class IssuesConverter {
	
	private IssuesConverter() {
	}

	public static IssuesDto toDto(IssuesEntity issue) {
		IssuesDto issueToReturn = new IssuesDto();
		issueToReturn.setCategory(issue.getCategory());
		issueToReturn.setCreatedDate(issue.getCreatedDate());
		issueToReturn.setResoulutionStatus(issue.getResoulutionStatus());
		issueToReturn.setDescription(issue.getDescription());
		return issueToReturn;
	}

	public static List<IssuesDto> toDto(List<IssuesEntity> issues) {
		List<IssuesDto> toReturn = new ArrayList<>();
		for(IssuesEntity ue : issues) {
			toReturn.add(toDto(ue));
		}
		return toReturn;
	}
	
	public static IssuesEntity toEntity(IssuesDto issue) {
		IssuesEntity issueToReturn = new IssuesEntity();
		issueToReturn.setCategory(issue.getCategory());
		issueToReturn.setCreatedDate(LocalDateTime.now());
		issueToReturn.setResoulutionStatus(issue.getResoulutionStatus());
		issueToReturn.setDescription(issue.getDescription());
		return issueToReturn;
	}
	
	public static IssuesEntity toEntityForCreate(IssuesForCreateDto dto, UserEntity client, PropertyEntity property) {
		IssuesEntity toReturn=new IssuesEntity();
		toReturn.setCategory(dto.getCategory());
		toReturn.setClient(client);
		toReturn.setCreatedDate(LocalDateTime.now());
		toReturn.setDescription(dto.getDescription());
		toReturn.setProperty(property);
		toReturn.setIssueId(null);
		return toReturn;
	}

	public static IssuesEntity toClientEntityForCreate(ClientIssueForCreateDto issue, UserEntity user,
			PropertyEntity property) {
		IssuesEntity toReturn=new IssuesEntity();
		toReturn.setCategory(issue.getCategory());
		toReturn.setClient(user);
		toReturn.setCreatedDate(LocalDateTime.now());
		toReturn.setDescription(issue.getDescription());
		toReturn.setProperty(property);
		toReturn.setIssueId(null);
		return toReturn;
	}
	
}
