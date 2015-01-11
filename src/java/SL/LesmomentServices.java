/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SL;

import DAL.Lesmoment;
import DAL.NewHibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Wim
 */
public class LesmomentServices {

    public static List<Lesmoment> GetAllLesmomenten(int Id) {
        Session session
                = NewHibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("from Lesmoment lesmoment where lesmoment.module=" + Id);
        return q.list();
    }

    public static Lesmoment getLesmoment(int Id) {
        Session session
                = NewHibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("from Lesmoment lesmoment where lesmoment.id=" + Id);
        return (Lesmoment) q.uniqueResult();
    }

    public static Lesmoment Save(Lesmoment lesmoment) {
        Session session
                = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(lesmoment);
        session.getTransaction().commit();
        session.close();
        return lesmoment;
    }

    public static void deleteLesmoment(int Id) {
        Session session
                = NewHibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("from Lesmoment lesmoment where lesmoment.id=" + Id);
        Lesmoment lesmoment = (Lesmoment) q.uniqueResult();
        session.beginTransaction();
        session.delete(lesmoment);
        session.getTransaction().commit();
    }
}
