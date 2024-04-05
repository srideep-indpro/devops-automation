package com.ilovejava.springbootdevops;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.*;

@Repository
public class EmployeeDao {

    @Autowired
    EntityManager em;

    // constructor

    List<Employee> findEmployeesByName(List<String> attributeList) {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);

        Root<Employee> employee = cq.from(Employee.class);

        Selection[] selections = new Selection[]{employee.get("age"), employee.get("name")};

        cq.multiselect(selections);
//        cq.multiselect(employee.get("age"), employee.get("name"));
        List<Employee> resultList = em.createQuery(cq).getResultList();

//        Predicate authorNamePredicate = cb.equal(employee.get("name"), name);
//        Predicate titlePredicate = cb.like(book.get("title"), "%" + title + "%");
//        cq.where(authorNamePredicate);

//        TypedQuery<Employee> query = em.createQuery(cq)
//                .setHint("jakarta.persistence.fetchgraph", entityGraph);
//        return query.getResultList();
        return em.createQuery(cq).getResultList();
    }


    EmployeeDynamicObject findEmployeesByName1(List<String> attributeList) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
        CriteriaQuery<Object> cq = cb.createQuery();
        Root<Employee> root = cq.from(Employee.class);

        List<Selection<?>> s = new ArrayList<>();
        attributeList.forEach(att -> {
            Path<Object> objectPath = root.get(att);
            s.add(objectPath);
        });
        cq.multiselect(s);

//        List<Object[]> resultList = em.createQuery(cq).getResultList();
        List<Object> resultList = em.createQuery(cq).getResultList();
        List<Map<String, Object>> historyMapList = new ArrayList<>();
//        List<EmployeeDynamicObject> dynamicObjects = new ArrayList<>();

        for(int i=0; i<resultList.size();i++){
            Object[] obj = (Object[]) resultList.get(i);
            Map<String,Object> historyMap = new HashMap<>();
            for(int j=0;j<obj.length;j++){
                historyMap.put(attributeList.get(j), obj[j]);

//                EmployeeDynamicObject dObj = new EmployeeDynamicObject();
//                dObj.setEmployeeDetails(historyMap);
//                dynamicObjects.add(dObj);
            }
            historyMapList.add(historyMap);
            historyMap = new HashMap<>();
        }
        EmployeeDynamicObject dObj = new EmployeeDynamicObject();
        dObj.setEmployeeDetails(historyMapList);
        return dObj;
//        return resultList;
    }

}
