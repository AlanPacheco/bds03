package com.devsuperior.bds03.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.devsuperior.bds03.dto.DepartmentDTO;
import com.devsuperior.bds03.entities.Department;
import com.devsuperior.bds03.repositories.DepartmentRepository;
import com.devsuperior.bds03.services.exceptions.ResourceNotFoundException;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository repository;
	
	public List<DepartmentDTO> findAll() {
		List<Department> list = repository.findAll(Sort.by("name"));
		return list.stream().map(x -> new DepartmentDTO(x)).collect(Collectors.toList());
	}
	
	public DepartmentDTO findById(Long id) {
		Optional<Department> optional = repository.findById(id);
		Department department = optional.orElseThrow(
				()-> new ResourceNotFoundException("id not found: " + id));
		return new DepartmentDTO(department);
	}
}
