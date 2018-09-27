package com.hibernate.servlet;

import com.hibernate.domain.manytoone.Category;
import com.hibernate.domain.manytoone.Food;
import com.hibernate.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

public class ManyToOneServlet extends javax.servlet.http.HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            PrintWriter out = response.getWriter();
            transaction = session.beginTransaction();

            Category category1 = new Category("Fruit");
            Category category2 = new Category("Meat");
            Food food1 = new Food("Banana", category1);
            Food food2 = new Food("Apple", category1);
            Food food3 = new Food("Pear", category1);
            Food food4 = new Food("Chicken", category2);
            Food food5 = new Food("Beef", category2);
            Food food6 = new Food("Lamb", category2);
            session.save(food1);
            session.save(food2);
            session.save(food3);
            session.save(food4);
            session.save(food5);
            session.save(food6);

            transaction.commit();

            out.println("<body text='Red'><h1>Test Output</h1><h4><a href=\"http://localhost:8080\">Go Back</a><h4/> <hr>");
            List<Food> foods = session.createQuery("from com.hibernate.domain.manytoone.Food").list();
            Iterator<Food> iter = foods.iterator();
            while (iter.hasNext()) {
                Food st = iter.next();
                out.println(st.toString());
            }
            out.println("<br><br>over...</body>");
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
