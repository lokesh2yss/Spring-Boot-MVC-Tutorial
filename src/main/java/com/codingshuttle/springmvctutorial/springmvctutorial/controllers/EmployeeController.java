package com.codingshuttle.springmvctutorial.springmvctutorial.controllers;

import com.codingshuttle.springmvctutorial.springmvctutorial.dto.EmployeeDTO;
import com.codingshuttle.springmvctutorial.springmvctutorial.exceptions.ResourceNotFoundException;
import com.codingshuttle.springmvctutorial.springmvctutorial.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService service) {
        this.employeeService = service;
    }

    @GetMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long employeeId) {
        Optional<EmployeeDTO> employeeDTOOptional = employeeService.getEmployeeById(employeeId);
        return employeeDTOOptional.map(ResponseEntity::ok).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id="+employeeId));
    }
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {
        EmployeeDTO createdEmployeeDTO = employeeService.createNewEmployee(employeeDTO);
        return new ResponseEntity<>(createdEmployeeDTO, HttpStatus.CREATED);
    }
    @PutMapping(path="/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody @Valid EmployeeDTO employeeDTO, @PathVariable Long employeeId) {
        return ResponseEntity.ok(employeeService.updateEmployeeById(employeeId, employeeDTO));
    }

    @DeleteMapping(path="/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId) {
        boolean gotDeleted = employeeService.deleteEmployeeById(employeeId);
        if(gotDeleted) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.notFound().build();
    }
    @PatchMapping(path="/{employeeId}")
    public ResponseEntity<EmployeeDTO> partiallyUpdateEmployeeById(@PathVariable Long employeeId, @RequestBody HashMap<String, Object> updates) {
        return ResponseEntity.ok(employeeService.partiallyUpdateEmployeeById(employeeId, updates));
    }

}
