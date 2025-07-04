package com.naveenBanwala.springboot.demo.employeeproject.service;

import com.naveenBanwala.springboot.demo.employeeproject.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findById(int id);

    void deleteById(int id);

    Employee update(Employee employee);

    Employee save(Employee employee);
}
