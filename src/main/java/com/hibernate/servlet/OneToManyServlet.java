package com.hibernate.servlet;

import com.hibernate.domain.onetomany.Employee;
import com.hibernate.domain.onetomany.Phone;
import com.hibernate.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class OneToManyServlet extends javax.servlet.http.HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            PrintWriter out = response.getWriter();

            transaction = session.beginTransaction();

            Set<Phone> phoneNumbers = new HashSet<Phone>();
            Phone phone1 = new Phone("home", "8027773992");
            Phone phone2 = new Phone("mobile", "7017995166");
            phoneNumbers.add(phone1);
            phoneNumbers.add(phone2);

            Set<Phone> phoneNumbers2 = new HashSet<Phone>();
            Phone phone3 = new Phone("home", "7777773992");
            Phone phone4 = new Phone("mobile", "8888885166");
            phoneNumbers2.add(phone3);
            phoneNumbers2.add(phone4);

            Employee employee = new Employee("John", phoneNumbers);
            Employee employee2 = new Employee("Alex", phoneNumbers2);

            // for bidirectional - student should be mapped to phone object
            phone1.setEmployee(employee);
            phone2.setEmployee(employee);
            phone3.setEmployee(employee2);
            phone4.setEmployee(employee2);

            session.save(employee);
            session.save(employee2);

            transaction.commit();

            out.println("<body text='Red'><h1>Test Output</h1><h4><a href=\"http://localhost:8080\">Go Back</a><h4/> <hr>");

            out.println("<br><br>Getting all Employees<br>");

            List<Employee> employees = session.createQuery("from Employee").list();
            Iterator<Employee> iter = employees.iterator();
            while (iter.hasNext()) {
                Employee st = iter.next();
                out.println(st.toString());
            }

            out.println("<br><br>Getting employee object and displaying student information and also getting collection of phones from the employee..<br>");
            Employee emp = (Employee) session.load(Employee.class, 1);

            out.println("<br> " + emp.getEmployeeId() + "  " + emp.getEmployeeName());
            Set<Phone> phones = emp.getEmployeePhoneNumbers();
            Iterator<Phone> iter1 = phones.iterator();
            while (iter1.hasNext()) {
                Phone phone = iter1.next();
                out.println("<br>" + phone.getPhoneId() + "  " + phone.getPhoneType() + "  " + phone.getPhoneNumber());
            }

            out.println("<br><br>Getting phone object and displaying phone information and also getting student through phone object...<br>");

            Phone ph = (Phone) session.load(Phone.class, 3); // new Long(3) means the third record
            out.println("<br>" + ph.getPhoneId() + "  " + ph.getPhoneType() + "  " + ph.getPhoneNumber() + " belongs to " + ph.getEmployee().getEmployeeId() + "  " + ph.getEmployee().getEmployeeName());
            out.println("<br><br>over...</body>");


        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
