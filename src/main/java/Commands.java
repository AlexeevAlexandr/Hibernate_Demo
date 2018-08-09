import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.transaction.Transactional;
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
            System.out.println("Exception in add block: " + e.getMessage());
        }
        return 0;
    }

    void listDevelopers() {
        try(SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession())
        {
            List developers = session.createQuery("FROM Developer").list();
            for (Object developer : developers) {
                System.out.println(developer);
                System.out.println("================");
            }
        }catch (Exception e) {
            System.out.println("Exception in listByParameter block: " + e.getMessage());
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
            System.out.println("Exception in update block: " + e.getMessage());
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
            System.out.println("Exception in remove block: " + e.getMessage());
        }
    }

    void deleteAllData(){
        try(SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession())
        {
            Transaction transaction = session.beginTransaction();
            session.createQuery("DELETE FROM Developer").executeUpdate();
            transaction.commit();
            System.out.println("Delete from table is successful");
        }catch (Exception e){
            System.out.println("Exception in deleteAllData block: " + e.getMessage());
        }
    }

    void listByParameter(){
        try(SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession())
        {
            Query query = session.createQuery("FROM Developer D WHERE D.firstName = 'Developer2'");
            List developers = query.list();
            System.out.println(developers);
        }catch (Exception e){
            System.out.println("Exception in listByParameter block: " + e.getMessage());
        }
    }

    /**
     * sorting of list;
     * (experience > 3) - points that list will be shown with experience more than 3;
     * (ORDER BY D.experience DESC) - sorting of list (DESC - by descending; ASC - by ascending);
     */
    void sortList(){
        try(SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession())
        {
            Query query = session.createQuery(
                    "FROM Developer D WHERE experience > 3 ORDER BY D.experience DESC");
            List developers = query.list();
            System.out.println(developers);
        }catch (Exception e){
            System.out.println("Exception in sortList block: " + e.getMessage());
        }
    }
}
