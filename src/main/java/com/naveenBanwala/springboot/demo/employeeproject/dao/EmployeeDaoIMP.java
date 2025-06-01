package com.naveenBanwala.springboot.demo.employeeproject.dao;

import com.naveenBanwala.springboot.demo.employeeproject.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDaoIMP implements EmployeeDao {

    //Define Field of Entity Manager  and this is a Bean provided by
    //Spring Framework
    private EntityManager entityManager;

    //Setup for Constructor Injection
    @Autowired
    public EmployeeDaoIMP(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
     public List<Employee> findAll(){
        //Create a query
        TypedQuery<Employee> query = entityManager.createQuery("From Employee", Employee.class);

        //Execute the query anf d get the result
        List<Employee> employees = query.getResultList();

        //return the list
       return employees;
    }

    @Override
    public Employee findById(int id) {
        Employee employee = entityManager.find(Employee.class, id);
        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        entityManager.merge(employee);
        return employee;
    }

    @Override
    public void deleteById(int id) {
        Employee employee = entityManager.find(Employee.class, id);
        entityManager.remove(employee);
    }
    @Override
    public Employee save(Employee employee){
        //Use merge instead of presist becoz it wiorks for POST/PUT/PATCH
     entityManager.merge(employee);
        return employee;
    }
}
