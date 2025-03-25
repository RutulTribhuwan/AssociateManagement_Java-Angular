package com.example.Developer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Developer.model.Associate;
import com.example.Developer.repository.AssociateRepository;

@Service
public class AssociateService {

	
	@Autowired
	private AssociateRepository associateRepository;
	
	public Associate saveAssociate(Associate associate) {
		return associateRepository.save(associate);
	}
	
	public List<Associate> getAllAssociates(){
		return associateRepository.findAll();
	}
	
	public Optional<Associate> getAssociateById(Long id){
		return associateRepository.findById(id);
	}
	
	public void deleteAssociate(Long id) {
		associateRepository.deleteById(id);
	}
}
