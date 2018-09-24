package com.hibernate.servlet;

import com.hibernate.domain.Address;
import com.hibernate.domain.Student;
import com.hibernate.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

public class OneToOneServlet extends javax.servlet.http.HttpServlet {

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {

            PrintWriter out = response.getWriter();

            transaction = session.beginTransaction();
            Address address1 = new Address("Street1", "Essex", "VT", "05452");
            Address address2 = new Address("Street2", "Burlington", "VT", "05404");
            Student student1 = new Student("Ram", address1);
            Student student2 = new Student("Sham", address2);

            session.save(address1);
            session.save(address2);
            session.save(student1);
            session.save(student2);

            transaction.commit();

            out.println("<body text='Red'><h1>Test Output</h1><hr>");
            List<Student> students = session.createQuery("from com.hibernate.domain.Student").list();
            Iterator<Student> iter = students.iterator();
            while (iter.hasNext()) {
                Student st = iter.next();
                out.println(st.toString());
                out.println("<br>");
            }
            out.println("over...</body>");
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }


}
