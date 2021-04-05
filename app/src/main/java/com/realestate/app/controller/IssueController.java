package com.realestate.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.realestate.app.converter.IssuesConverter;
import com.realestate.app.dto.IssueDtoForUpdate;
import com.realestate.app.dto.IssuesDto;
import com.realestate.app.dto.IssuesDtoForCreate;
import com.realestate.app.service.IssueService;

@RestController
@RequestMapping("/api/v1")
public class IssueController {

	IssueService issueService;

	@Autowired
	public IssueController(IssueService issueService) {
		super();
		this.issueService = issueService;
	}

	// -----------------------------
	// GET ROUTES STARTS HERE
	// -----------------------------
	@GetMapping("/issues")
	public ResponseEntity<List<IssuesDto>> showAllIssues() {
		// show all issues on database
		return new ResponseEntity<>(IssuesConverter.toDto(issueService.allIssues()), HttpStatus.OK);
	}

	@GetMapping("/issues/{id}")
	public ResponseEntity<IssuesDto> showIssueById(@PathVariable("id") int id) {
		// show issue by id
		return new ResponseEntity<>(IssuesConverter.toDto(issueService.issuesById(id)), HttpStatus.FOUND);
	}

	// -----------------------------
	// POST ROUTES STARTS HERE
	// -----------------------------
	@PostMapping("/issues/new")
	public ResponseEntity<IssuesDto> addPropertyInfo(@Valid @RequestBody IssuesDtoForCreate issue) {
		// return the added issue formated by converter
		return new ResponseEntity<>(IssuesConverter.toDto(issueService.addIssues(issue)), HttpStatus.CREATED);
	}

	// -----------------------------
	// PUT ROUTES STARTS HERE
	// -----------------------------
	@PutMapping("/issues/edit/{id}")
	public ResponseEntity<IssuesDto> updateIssue(@Valid @RequestBody IssueDtoForUpdate issue,
			@PathVariable("id") int id) {
		// return the updated issue if process complete
		return new ResponseEntity<>(IssuesConverter.toDto(issueService.updateIssues(issue, id)), HttpStatus.OK);
	}

	// -----------------------------
	// DELETE ROUTES STARTS HERE
	// -----------------------------
	@DeleteMapping("/issues/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteIssue(@PathVariable("id") int id) {
		// delete issue if needed to
		issueService.deleteIssue(id);
	}

}
