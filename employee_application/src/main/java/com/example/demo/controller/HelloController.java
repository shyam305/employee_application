package com.example.demo.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.Beans.Employee;
import com.example.demo.exception.EmployeeNotFoundException;

@RestController
@RequestMapping("/users")
public class HelloController {
	
	Map<String, Employee> allEmployees = new HashMap<>();

	@GetMapping
	public Collection<Employee> getHelloWorld() {
		return allEmployees.values();
	}

	@PostMapping
	public String insertEmployee(@RequestBody Employee emp) {
		System.out.println("POST MAPPING");
		allEmployees.put(emp.getEmpId(), emp);
		return "User added successfully";
	}

	@PutMapping("/{empId}")
	public ResponseEntity<Object> updateEmployee(@PathVariable String empId, @RequestBody Employee emp) {
		if (allEmployees.containsKey(empId)) {
			allEmployees.put(empId, emp);
			return new ResponseEntity<>("User updated Successfully", HttpStatus.OK);
		} else {
			throw new EmployeeNotFoundException();
		}
	}

	@DeleteMapping("/{empId}")
	public String deleteEmployee(@PathVariable String empId, @RequestBody Employee emp) {
		if (allEmployees.containsKey(empId)) {
			allEmployees.remove(empId);
			return "User deleted successfully";
		} else {
			return "User not found";
		}
	}

}
