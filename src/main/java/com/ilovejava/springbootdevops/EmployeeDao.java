package com.ilovejava.springbootdevops;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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


    List<?> findEmployeesByName1(List<String> attributeList) {
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
        return resultList;
    }

}
