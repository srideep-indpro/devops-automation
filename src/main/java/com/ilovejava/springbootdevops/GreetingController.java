package com.ilovejava.springbootdevops;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class GreetingController {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private EmployeeDao empDao;

    @GetMapping("/greeting")
    public String greet(){
        return "I'm on EKS now";
    }


    @PostMapping("/add")
    public String addEmployees(){
        List<Employee> empList = new ArrayList<>();
        empList.add(new Employee("1","Srideep","30","9933012997","100000"));
        empList.add(new Employee("2","Sreetama","31","977579300","15000")) ;
        empList.add(new Employee("3","Eshan","14","8509024040","10000"));


        List<Employee> employees = repository.saveAll(empList);
        if(employees.size()>0){
            return "Saved successfully";
        }

        return "error while saving";

    }

    @GetMapping("/getEmployeeByName")
    public ResponseEntity<?> getEmpByName(@RequestParam(name="attributes") List<String> attributeList){
        EmployeeDynamicObject employeesByName = empDao.findEmployeesByAttributes(attributeList);

        return ResponseEntity.ok(employeesByName);
    }
}
