package com.truetrack.sharing.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truetrack.sharing.dtos.BusinessDataShareDetailDTO;
import com.truetrack.sharing.dtos.BusinessDataShareDetailInternal;
import com.truetrack.sharing.entity.BusinessDataShareDetail;
import com.truetrack.sharing.service.BusinessDataShareDetailService;

@RestController
@RequestMapping("/api/v1/details")
public class DataShareDetailsController {

	@Autowired
	private BusinessDataShareDetailService service;

	@GetMapping("{id}")
	public ResponseEntity<BusinessDataShareDetailDTO> getDetails(@PathVariable Integer id) {
		return ResponseEntity.ok(service.getDetails(id));
	}

	@PostMapping
	public ResponseEntity<BusinessDataShareDetailInternal> addDetails(@RequestBody BusinessDataShareDetail detail) {
		return ResponseEntity.ok(service.addDetail(detail));
	}

}
