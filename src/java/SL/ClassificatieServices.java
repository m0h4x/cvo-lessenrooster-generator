/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SL;

import DAL.Classificatie;
import DAL.NewHibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Wim
 */
public class ClassificatieServices {

    public static Classificatie Save(Classificatie classificatie) {
        Session session
                = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(classificatie);
        session.getTransaction().commit();
        session.close();
        return classificatie;
    }

    public static Classificatie getClassificatie(String classificatie) {
        Session session
                = NewHibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("from Classificatie classificatie where classificatie.code='" + classificatie + "'");
        return (Classificatie) q.uniqueResult();
    }

    public static List<Classificatie> GetAllClassificaties() {
        Session session
                = NewHibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("from Classificatie");
        return q.list();
    }

    public static Classificatie getClassificatie(int Id) {
        Session session
                = NewHibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("from Classificatie classificatie where classificatie.id=" + Id);
        return (Classificatie) q.uniqueResult();
    }
}
