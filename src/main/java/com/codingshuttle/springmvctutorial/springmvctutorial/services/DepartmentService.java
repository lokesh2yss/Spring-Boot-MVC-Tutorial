package com.codingshuttle.springmvctutorial.springmvctutorial.services;

import com.codingshuttle.springmvctutorial.springmvctutorial.dto.DepartmentDTO;
import com.codingshuttle.springmvctutorial.springmvctutorial.entities.DepartmentEntity;
import com.codingshuttle.springmvctutorial.springmvctutorial.exceptions.ResourceNotFoundException;
import com.codingshuttle.springmvctutorial.springmvctutorial.repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final ModelMapper mapper;

    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper mapper) {
        this.departmentRepository = departmentRepository;
        this.mapper = mapper;
    }
    public void existsByDepartmentId(Long departmentId) {
        boolean exists = departmentRepository.existsById(departmentId);
        if(!exists) throw new ResourceNotFoundException("Department doesn't exist with id="+departmentId);
    }

    public Optional<DepartmentDTO> getDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId).map(departmentEntity -> mapper.map(departmentEntity, DepartmentDTO.class));
    }

    public List<DepartmentDTO> getAllDepartments() {
        List<DepartmentEntity> entities = departmentRepository.findAll();
        return entities
                .stream()
                .map(entity -> mapper.map(entity, DepartmentDTO.class))
                .collect(Collectors.toList());
    }

    public DepartmentDTO createNewDepartment(DepartmentDTO inputDepartment) {
        DepartmentEntity toSaveEntity = mapper.map(inputDepartment, DepartmentEntity.class);
        DepartmentEntity savedEntity = departmentRepository.save(toSaveEntity);
        return mapper.map(savedEntity, DepartmentDTO.class);
    }

    public DepartmentDTO updateDepartmentById(DepartmentDTO departmentDTO, Long departmentId) {
        existsByDepartmentId(departmentId);
        DepartmentEntity toUpdateEntity = mapper.map(departmentDTO, DepartmentEntity.class);
        toUpdateEntity.setId(departmentId);
        DepartmentEntity updatedEntity = departmentRepository.save(toUpdateEntity);
        return mapper.map(updatedEntity, DepartmentDTO.class);
    }

    public void deleteDepartmentById(Long departmentId) {
        existsByDepartmentId(departmentId);
        departmentRepository.deleteById(departmentId);
    }

    public DepartmentDTO partiallyUpdateDepartmentById(Long departmentId, HashMap<String, Object> updates) {
        existsByDepartmentId(departmentId);
        DepartmentEntity departmentEntity = departmentRepository.findById(departmentId).get();
        updates.forEach((field, value) -> {
            Field fieldToUpdate = ReflectionUtils.findRequiredField(DepartmentEntity.class, field);
            fieldToUpdate.setAccessible(true);
            ReflectionUtils.setField(fieldToUpdate, departmentEntity, value);
        });
        DepartmentEntity updatedEntity = departmentRepository.save(departmentEntity);
        return mapper.map(updatedEntity, DepartmentDTO.class);
    }
}
