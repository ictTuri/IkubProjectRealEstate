package com.realestate.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.realestate.app.converter.IssuesConverter;
import com.realestate.app.dto.IssueDtoForUpdate;
import com.realestate.app.dto.IssuesDto;
import com.realestate.app.service.UserIssueService;

@RestController
@RequestMapping("/api/v1/issue")
public class UserIssueController {
	
	UserIssueService userIssueService;
	
	@Autowired
	public UserIssueController(UserIssueService userIssueService) {
		super();
		this.userIssueService = userIssueService;
	}
	
	@PreAuthorize("hasAnyRole('OWNER','CLIENT')")
	@GetMapping("/myissues")
	public ResponseEntity<List<IssuesDto>> showMyIssues() {
		// show all issues on database
		return new ResponseEntity<>(IssuesConverter.toDto(userIssueService.allMyIssues()), HttpStatus.OK);
	} 
	
	@PreAuthorize("hasAnyRole('OWNER','CLIENT')")
	@PutMapping("/myissues/{id}")
	public ResponseEntity<IssuesDto> updateIssueById(@Valid @RequestBody IssueDtoForUpdate issue, @PathVariable int id){
		return new ResponseEntity<>(IssuesConverter.toDto(userIssueService.updateMyIssue(issue, id)), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('OWNER','CLIENT')")
	@DeleteMapping("/myissues/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteMyIssue(@PathVariable int id) {
		userIssueService.deleteMyIssue(id);
	}
}
