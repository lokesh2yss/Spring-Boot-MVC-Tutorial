package com.codingshuttle.springmvctutorial.springmvctutorial.repositories;

import com.codingshuttle.springmvctutorial.springmvctutorial.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
}
