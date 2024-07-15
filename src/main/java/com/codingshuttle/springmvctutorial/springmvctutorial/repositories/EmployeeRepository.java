package com.codingshuttle.springmvctutorial.springmvctutorial.repositories;

import com.codingshuttle.springmvctutorial.springmvctutorial.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long > {
}
