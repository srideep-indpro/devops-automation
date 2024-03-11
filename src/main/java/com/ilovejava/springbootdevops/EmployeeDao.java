package com.ilovejava.springbootdevops;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class EmployeeDao {

    @Autowired
    EntityManager em;

    // constructor

    List<Employee> findEmployeesByName(String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);

        Root<Employee> employee = cq.from(Employee.class);
        Predicate authorNamePredicate = cb.equal(employee.get("name"), name);
//        Predicate titlePredicate = cb.like(book.get("title"), "%" + title + "%");
        cq.where(authorNamePredicate);

        TypedQuery<Employee> query = em.createQuery(cq);
        return query.getResultList();
    }
}
