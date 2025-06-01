package com.naveenBanwala.springboot.demo.employeeproject.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
//import com.naveenBanwala.springboot.demo.employeeproject.dao.EmployeeDao;
import com.naveenBanwala.springboot.demo.employeeproject.entity.Employee;
import com.naveenBanwala.springboot.demo.employeeproject.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//import static org.apache.tomcat.jni.SSLConf.apply;

@RestController
@RequestMapping("/api")
public class RestControllerForEmployee {

    private EmployeeService employeeService;

    // inject ObjectMapper(class of Jackson)
    // used for convert Json <-> java Objects

    private ObjectMapper objectMapper;

    //Quick and dirty inject employee Dao(use constructor injection)
    public RestControllerForEmployee(EmployeeService employeeService , ObjectMapper objectMapper) {
        this.employeeService = employeeService;
        this.objectMapper = objectMapper;
    }

    //expose "/employee" and return a list of employees
    @GetMapping("/employees")
    public List<Employee> findAll() {

        return employeeService.findAll();
    }

    //Add mapping for Get api/employee/{employeeId}
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {
         Employee theemployee=employeeService.findById(employeeId);
         if(theemployee==null) {
             throw new RuntimeException("Employee id not found" + employeeId);
         }
         return theemployee;
    }

    //Add mapping for Post - add new employees
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {

        employee.setId(0);
        Employee theemployee = employeeService.save(employee);

        return theemployee;
    }

    @PutMapping("/employess")
    public Employee updateEmployee(@RequestBody Employee employee) {

        Employee theemployee =employeeService.save(employee);
        return theemployee;
    }

    //Add patch mapping for partial updates
    @PatchMapping("/employees/{employeeId}")
    public Employee updatePartialEmployee(@RequestBody Map<String,Object> patchPayLoad, @PathVariable int employeeId) {
             Employee tempEmployee=employeeService.findById(employeeId);

             if(tempEmployee==null) {
                 throw new RuntimeException("Employee id not found" + employeeId);

             }
             //throw exception if request body contains key
             if(patchPayLoad.containsKey("id")) {
                 throw new RuntimeException("Employee id  is not allowed in Request Body" + employeeId);
             }
           Employee patchedEmployee = apply(patchPayLoad,tempEmployee);

             Employee dbEmployee=employeeService.save(patchedEmployee);

             return dbEmployee;

    }
      // Here we use patchpayload for partial json which is come from the user for updates
    // and we have also Employee object json so we merge both and return their json value
    //for further updates
    private Employee apply(Map<String,Object> patchPayLoad, Employee tempEmployee) {

        // Convert Employee object to  a Json Object node
        ObjectNode employeeNode = objectMapper.convertValue(tempEmployee , ObjectNode.class);

        //Convert patchPayload object to json object node
        ObjectNode patchNode = objectMapper.convertValue(patchPayLoad,ObjectNode.class);


        //Merge the patch updates into the Employee node
        employeeNode.setAll(patchNode);

        return objectMapper.convertValue(employeeNode,Employee.class);

    }

    @DeleteMapping("employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {

        Employee tempEmployee=employeeService.findById(employeeId);
        if(tempEmployee==null) {
            throw new RuntimeException("Employee id not found" + employeeId);

        }
        employeeService.deleteById(employeeId);

        return "delete the employee " + employeeId;
    }



}
