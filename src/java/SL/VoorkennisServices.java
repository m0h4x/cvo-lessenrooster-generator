/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SL;

import DAL.Modulevoorkennis;
import DAL.NewHibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Wim
 */
public class VoorkennisServices {

    public static Modulevoorkennis Save(Modulevoorkennis modulevoorkennis) {
        Session session
                = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(modulevoorkennis);
        session.getTransaction().commit();
        session.close();
        return modulevoorkennis;
    }

    public static List<Modulevoorkennis> GetAllModuleVoorkennis(int Id) {
        Session session
                = NewHibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("from Modulevoorkennis modulevoorkennis where modulevoorkennis.module=" + Id);
        return q.list();
    }

    public static Modulevoorkennis getModuleVoorkennis(int Id) {
        Session session
                = NewHibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("from Modulevoorkennis modulevoorkennis where modulevoorkennis.id=" + Id);
        return (Modulevoorkennis) q.uniqueResult();
    }

    public static void deleteModuleVoorkennis(int Id) {
        Session session
                = NewHibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("from Modulevoorkennis modulevoorkennis where modulevoorkennis.id=" + Id);
        Modulevoorkennis modulevoorkennis = (Modulevoorkennis) q.uniqueResult();
        session.beginTransaction();
        session.delete(modulevoorkennis);
        session.getTransaction().commit();
    }
}
