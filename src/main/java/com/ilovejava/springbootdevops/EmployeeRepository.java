package com.ilovejava.springbootdevops;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String>{

    public List<Object[]> findEmployeesByNameAndAge(String name, String age);
}
