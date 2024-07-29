package com.truetrack.sharing.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truetrack.sharing.entity.BusinessDataShareMaster;
import com.truetrack.sharing.service.BusinessDataShareMasterService;

@RestController
@RequestMapping("/api/v1/master")
public class DataShareController {

	@Autowired
	private BusinessDataShareMasterService service;

	@GetMapping
	ResponseEntity<List<BusinessDataShareMaster>> getData() {
		return ResponseEntity.ok(service.getMasterRecords());
	}
	
	@PostMapping
	ResponseEntity<BusinessDataShareMaster> addData(@RequestBody BusinessDataShareMaster master) {
		return ResponseEntity.ok(service.addMasterRecord(master));
	}

}
