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

import com.realestate.app.dto.IssueForUpdateDto;
import com.realestate.app.dto.IssuesDto;
import com.realestate.app.dto.IssuesForCreateDto;
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
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/issues")
	public ResponseEntity<List<IssuesDto>> showAllIssues() {
		// show all issues on database
		return new ResponseEntity<>(issueService.allIssues(), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/issues/{id}")
	public ResponseEntity<IssuesDto> showIssueById(@PathVariable("id") int id) {
		// show issue by id
		return new ResponseEntity<>(issueService.issuesById(id), HttpStatus.FOUND);
	}

	// -----------------------------
	// POST ROUTES STARTS HERE
	// -----------------------------
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/issues")
	public ResponseEntity<IssuesDto> addPropertyInfo(@Valid @RequestBody IssuesForCreateDto issue) {
		// return the added issue formated by converter
		return new ResponseEntity<>(issueService.addIssues(issue), HttpStatus.CREATED);
	}

	// -----------------------------
	// PUT ROUTES STARTS HERE
	// -----------------------------
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/issues/{id}")
	public ResponseEntity<IssuesDto> updateIssue(@Valid @RequestBody IssueForUpdateDto issue,
			@PathVariable("id") int id) {
		// return the updated issue if process complete
		return new ResponseEntity<>(issueService.updateIssues(issue, id), HttpStatus.OK);
	}

	// -----------------------------
	// DELETE ROUTES STARTS HERE
	// -----------------------------
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/issues/{id}")
	public ResponseEntity<Void> deleteIssue(@PathVariable("id") int id) {
		// delete issue if needed to
		issueService.deleteIssue(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
