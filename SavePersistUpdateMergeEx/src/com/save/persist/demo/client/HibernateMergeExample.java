package com.save.persist.demo.client;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.save.persist.demo.beans.Employee;
import com.save.persist.demo.util.HibernateUtil;

public class HibernateMergeExample {

  public static void main(String[] args) {

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    // save example - with transaction
    Session session1 = sessionFactory.openSession();
    Transaction tx1 = session1.beginTransaction();
    Employee emp1 = HibernateSaveExample.getTestEmployee(8);
    long id1 = (Long) session1.save(emp1);
    System.out.println("2. Employee save called with transaction, id=" + id1);
    System.out.println("3. Before committing save transaction");
    tx1.commit();
    System.out.println("4. After committing save transaction");
    System.out.println("*****");
    session1.close();

    // Prep Work
    Session session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();
    Employee emp = (Employee) session.load(Employee.class, new Long(id1));
    System.out.println("Employee object loaded. " + emp);
    tx.commit();

    // merge example - data already present in tables
    emp.setSalary(25000);
    Transaction tx8 = session.beginTransaction();
    Employee emp4 = (Employee) session.merge(emp);
    System.out.println(emp4 == emp); // returns false
    emp.setName("Test");
    emp4.setName("Kumar");
    System.out.println("15. Before committing merge transaction");
    tx8.commit();
    System.out.println("16. After committing merge transaction");

    // Close resources
    sessionFactory.close();

  }
}
