import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

class Commands {
    Integer addDeveloper(String firstName, String lastName, String specialty, int experience) {
        Integer developerId;
        try(SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession())
        {
            Transaction transaction;
            transaction = session.beginTransaction();
            Developer developer = new Developer(firstName, lastName, specialty, experience);
            developerId = (Integer) session.save(developer);
            transaction.commit();
            return developerId;
        }catch (Exception e){
            System.out.println("Exception in add block");
        }
        return 0;
    }

    void listDevelopers() {
        try(SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession())
        {
            Transaction transaction = session.beginTransaction();
            List developers = session.createQuery("FROM Developer").list();
            for (Object developer : developers) {
                System.out.println(developer);
                System.out.println("\n================\n");
            }
        }catch (Exception e) {
            System.out.println("Exception in list block");
        }
    }

    void updateDeveloper(int developerId, int experience) {
        try(SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession())
        {
            Transaction transaction = session.beginTransaction();
            Developer developer = session.get(Developer.class, developerId);
            developer.setExperience(experience);
            session.update(developer);
            transaction.commit();
        }catch (Exception e){
            System.out.println("Exception in update block");
        }
    }

    void removeDeveloper(int developerId) {
        try(SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession())
        {
            Transaction transaction = session.beginTransaction();
            Developer developer = session.get(Developer.class, developerId);
            session.delete(developer);
            transaction.commit();
        }catch (Exception e){
            System.out.println("Exception in remove block");
        }
    }

    void deleteAllData(){
        try(SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession())
        {
            session.createQuery("DELETE FROM Developer").executeUpdate();
        }catch (Exception e){
            System.out.println("Exception in deleteAllData block " + e.getMessage());
        }
    }
}
