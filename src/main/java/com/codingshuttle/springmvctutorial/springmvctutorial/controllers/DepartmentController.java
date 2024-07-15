package com.codingshuttle.springmvctutorial.springmvctutorial.controllers;

import com.codingshuttle.springmvctutorial.springmvctutorial.dto.DepartmentDTO;
import com.codingshuttle.springmvctutorial.springmvctutorial.entities.DepartmentEntity;
import com.codingshuttle.springmvctutorial.springmvctutorial.exceptions.ResourceNotFoundException;
import com.codingshuttle.springmvctutorial.springmvctutorial.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(path="/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path="/{departmentId}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long departmentId) {
        Optional<DepartmentDTO> departmentDTOOptional = departmentService.getDepartmentById(departmentId);
        return departmentDTOOptional.map(ResponseEntity::ok).orElseThrow(() -> new ResourceNotFoundException("Department not found with id="+departmentId));
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> createNewDepartment(@RequestBody @Valid DepartmentDTO inputDepartment) {
        DepartmentDTO createdDepartment = departmentService.createNewDepartment(inputDepartment);
        return new ResponseEntity<>(createdDepartment, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDTO> updateDepartmentById(@RequestBody @Valid DepartmentDTO departmentDTO, @PathVariable Long departmentId) {
        return ResponseEntity.ok(departmentService.updateDepartmentById(departmentDTO, departmentId));
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<Boolean> deleteDepartmentById(@PathVariable Long departmentId) {
        departmentService.deleteDepartmentById(departmentId);
        return ResponseEntity.ok(true);
    }

    @PatchMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDTO> partiallyUpdateDepartmentById(@PathVariable Long departmentId, @RequestBody HashMap<String, Object> updates) {
        return ResponseEntity.ok(departmentService.partiallyUpdateDepartmentById(departmentId, updates));
    }

}
