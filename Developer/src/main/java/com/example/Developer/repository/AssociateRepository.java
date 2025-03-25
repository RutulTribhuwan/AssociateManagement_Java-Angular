package com.example.Developer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Developer.model.Associate;

@Repository
public interface AssociateRepository extends JpaRepository<Associate,Long>{

}
