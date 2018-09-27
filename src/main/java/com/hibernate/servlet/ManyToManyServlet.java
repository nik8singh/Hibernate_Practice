package com.hibernate.servlet;

import com.hibernate.domain.manytomany.Jewelry;
import com.hibernate.domain.manytomany.Stone;
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
import java.util.Set;

public class ManyToManyServlet extends javax.servlet.http.HttpServlet {

    public static final long serialVersionUID = 1;

    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            Stone stone1 = new Stone("Ruby");
            Stone stone2 = new Stone("Emerald");
            Stone stone3 = new Stone("Sapphire");

            session.save(stone1);
            session.save(stone2);
            session.save(stone3);

            Jewelry jewelry1 = new Jewelry("Red Green Pendent");
            Jewelry jewelry2 = new Jewelry("Green Blue Necklace");

            session.save(jewelry1);
            session.save(jewelry2);

            // now objects are persistent
            // can add jewelry to stone instead as well.
            jewelry1.getStones().add(stone1);
            jewelry1.getStones().add(stone2);

            jewelry2.getStones().add(stone2);
            jewelry2.getStones().add(stone3);

            transaction.commit();


        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        session = HibernateUtil.getSession();

        try {
            PrintWriter out = response.getWriter();

            out.println("<body text='Red'><h1>Test Output</h1><h4><a href=\"http://localhost:8080\">Go Back</a><h4/> <hr>");
            out.println("<br><br>Jewelries...<br><br>");
            List<Jewelry> jewelries = session.createQuery("from com.hibernate.domain.manytomany.Jewelry").list();
            Iterator<Jewelry> iter = jewelries.iterator();
            while (iter.hasNext()) {
                Jewelry st = iter.next();
                out.println(st.toString());
            }

            out.println("<br><br>Stones...<br><br>");
            List<Stone> stones = session.createQuery("from com.hibernate.domain.manytomany.Stone").list();

            for (Stone stone : stones) {
                out.println(stone + "<br>");
                Set<Jewelry> jewelries1 = stone.getJewelries();
                System.out.println(jewelries1);
                for (Jewelry jw : jewelries1) {
                    out.println("**********" + jw.getJewelryId() + "  " + jw.getJewelryName() + "<br>");
                }
            }

            out.println("<br><br>over...</body>");
        } catch (HibernateException e) {

            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
