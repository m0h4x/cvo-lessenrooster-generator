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
import DAL.Module;

/**
 *
 * @author Wim
 */
public class ModuleServices {

    public static List<Module> GetAllModules() {
        Session session
                = NewHibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("from Module");
        return q.list();
    }

    public static List<Module> GetAllModulesMetClassificatie(Classificatie c) {
        Session session
                = NewHibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("from Module where classificatie.code='"+ c.getCode() + "'");
        return q.list();
    }

    public static List<Module> GetAllUniqueModules() {
        Session session
                = NewHibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("from Module group by classificatie order by Naam");
        return q.list();
    }

    public static Module getModule(int Id) {
        Session session
                = NewHibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("from Module module where module.id=" + Id);
        return (Module) q.uniqueResult();
    }

    public static void deleteModule(int Id) {
        Session session
                = NewHibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("from Module module where module.id=" + Id);
        Module module = (Module) q.uniqueResult();
        session.beginTransaction();
        session.delete(module);
        session.getTransaction().commit();
    }

    public static Module Save(Module module) {
        Session session
                = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(module);
        session.getTransaction().commit();
        session.close();
        return module;
    }
}
