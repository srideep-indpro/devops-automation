package com.ilovejava.springbootdevops;


import java.util.List;
import java.util.Map;

public class EmployeeDynamicObject {

    private List<Map<String, Object>> employeeDetails;

    public List<Map<String, Object>> getEmployeeDetails() {
        return employeeDetails;
    }

    public void setEmployeeDetails(List<Map<String, Object>> employeeDetails) {
        this.employeeDetails = employeeDetails;
    }
}
