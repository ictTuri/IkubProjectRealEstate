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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realestate.app.dto.ClientIssueForCreateDto;
import com.realestate.app.dto.IssueForUpdateDto;
import com.realestate.app.dto.IssuesDto;
import com.realestate.app.service.UserIssueService;

@RestController
@RequestMapping("/api/v1/issue/myissues")
public class UserIssueController {
	
	UserIssueService userIssueService;
	
	@Autowired
	public UserIssueController(UserIssueService userIssueService) {
		super();
		this.userIssueService = userIssueService;
	}
	
	@PreAuthorize("hasAnyRole('OWNER','CLIENT')")
	@GetMapping()
	public ResponseEntity<List<IssuesDto>> showMyIssues() {
		// show all issues on database
		return new ResponseEntity<>(userIssueService.allMyIssues(), HttpStatus.OK);
	} 
	
	@PreAuthorize("hasAnyRole('CLIENT')")
	@PostMapping()
	public ResponseEntity<IssuesDto> insertIssueByClient(@Valid @RequestBody ClientIssueForCreateDto issue){
		// insert issue by client
		return new ResponseEntity<>(userIssueService.insertMyIssue(issue), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('OWNER','CLIENT')")
	@PutMapping("/{id}")
	public ResponseEntity<IssuesDto> updateIssueById(@Valid @RequestBody IssueForUpdateDto issue, @PathVariable int id){
		// update issue by client or owner
		return new ResponseEntity<>(userIssueService.updateMyIssue(issue, id), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('OWNER','CLIENT')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteMyIssue(@PathVariable int id) {
		// delete issue and return no content
		userIssueService.deleteMyIssue(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
