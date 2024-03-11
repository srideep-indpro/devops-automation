package com.ilovejava.springbootdevops;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
        empList.add(new Employee("1","Amit","30","8998","10000"));
        empList.add(new Employee("2","Sumit","31","8890","20000")) ;

        List<Employee> employees = repository.saveAll(empList);
        if(employees.size()>0){
            return "Saved successfully";
        }

        return "error while saving";

    }

    @GetMapping("/getEmployeeByName")
    public ResponseEntity<?> getEmpByName(@RequestParam(name="name") String name){
        List<?> employeesByName = empDao.findEmployeesByName(name);

        return ResponseEntity.ok(employeesByName);
    }
}
