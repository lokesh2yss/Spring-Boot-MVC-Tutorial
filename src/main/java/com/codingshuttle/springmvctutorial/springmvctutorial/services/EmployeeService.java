package com.codingshuttle.springmvctutorial.springmvctutorial.services;

import com.codingshuttle.springmvctutorial.springmvctutorial.dto.EmployeeDTO;
import com.codingshuttle.springmvctutorial.springmvctutorial.entities.EmployeeEntity;
import com.codingshuttle.springmvctutorial.springmvctutorial.exceptions.ResourceNotFoundException;
import com.codingshuttle.springmvctutorial.springmvctutorial.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper mapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    public Optional<EmployeeDTO> getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId).map(employeeEntity -> mapper.map(employeeEntity, EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map(employeeEntity -> mapper.map(employeeEntity, EmployeeDTO.class))
                .collect(Collectors.toList());


    }

    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        EmployeeEntity toCreateEmployee = mapper.map(employeeDTO, EmployeeEntity.class);
        EmployeeEntity createdEmployeeEntity = employeeRepository.save(toCreateEmployee);
        return mapper.map(createdEmployeeEntity, EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployeeById(Long employeeId, EmployeeDTO employeeDTO) {
        existsByEmployeeId(employeeId);
        EmployeeEntity employeeEntity = mapper.map(employeeDTO, EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity updatedEmployeeEntity = employeeRepository.save(employeeEntity);
        return mapper.map(updatedEmployeeEntity, EmployeeDTO.class);
    }

    public void existsByEmployeeId(Long employeeId) {
        boolean exists = employeeRepository.existsById(employeeId);
        if(!exists) throw new ResourceNotFoundException("Employee doesn't exist by id="+employeeId);
    }

    public boolean deleteEmployeeById(Long employeeId) {
        existsByEmployeeId(employeeId);
        employeeRepository.deleteById(employeeId);
        return true;
    }

    public EmployeeDTO partiallyUpdateEmployeeById(Long employeeId, HashMap<String, Object> updates) {
        existsByEmployeeId(employeeId);
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
        updates.forEach((field, value) -> {
                Field fieldToUpdate = ReflectionUtils.findRequiredField(EmployeeEntity.class, field);
                fieldToUpdate.setAccessible(true);
                ReflectionUtils.setField(fieldToUpdate, employeeEntity, value);
        });
        EmployeeEntity updatedEmployeeEntity = employeeRepository.save(employeeEntity);
        return mapper.map(updatedEmployeeEntity, EmployeeDTO.class);

    }
}
