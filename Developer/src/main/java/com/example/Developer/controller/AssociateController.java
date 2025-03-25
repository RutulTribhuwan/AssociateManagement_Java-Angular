package com.example.Developer.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Developer.model.Associate;
import com.example.Developer.service.AssociateService;

@CrossOrigin(origins = "http://localhost:60596")
@RestController
@RequestMapping("api/v2/associates")
public class AssociateController {

	@Autowired
	private AssociateService associateService;
	
	
	@PostMapping
	public Associate createAssociate(@RequestBody Associate associate) {
		return associateService.saveAssociate(associate);
	}
	
	@GetMapping
	public List<Associate> getAllAssociates(){
		return associateService.getAllAssociates();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Associate> getAssociateById(@PathVariable Long id){
		return associateService.getAssociateById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Associate> updateAssociate(@PathVariable Long id,@RequestBody Associate associateDetails){
		return associateService.getAssociateById(id)
				.map(associate->{
					associate.setName(associateDetails.getName());
					associate.setDepartment(associateDetails.getDepartment());
					Associate updatedassociate=associateService.saveAssociate(associate);
					return ResponseEntity.ok(updatedassociate);
					})
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteAssociate(@PathVariable Long id){
		return associateService.getAssociateById(id)
				.map(associate ->{
					associateService.deleteAssociate(id);
					return ResponseEntity.noContent().build();
				})
				.orElse(ResponseEntity.notFound().build());
	}
	
}
