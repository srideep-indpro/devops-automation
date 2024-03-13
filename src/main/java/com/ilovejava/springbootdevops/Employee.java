package com.ilovejava.springbootdevops;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {

    @Id
    private String id;
    private String name;
    private String age;
    private String contactNo;
    private String salary;

    public Employee() {
    }

//    public Employee(String id, String name, String age, String contactNo, String salary) {
//        this.id = id;
//        this.name = name;
//        this.age = age;
//        this.contactNo = contactNo;
//        this.salary = salary;
//    }

    public Employee(String id, String... args){

        this.id = id;

        if(args.length > 0){
            this.name = args[0];
        }
        if(args.length > 1){
            this.age = args[1];
        }
        if(args.length > 2){
            this.contactNo = args[2];
        }
        if(args.length > 3){
            this.salary = args[3];
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
