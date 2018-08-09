import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;

import java.util.ArrayList;
import java.util.List;

class Commands {
    private Transaction transaction = null;
    private SessionFactory sessionFactory;

    void SessionFactoryOpen(){
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    void SessionFactoryClosed(){
        sessionFactory.close();
    }

    Integer addDeveloper(String firstName, String lastName, String specialty, int experience, int salary) {
        try (Session session = sessionFactory.openSession())
        {
            System.out.println("Adding Developer's records to the database");
            transaction = session.beginTransaction();
            Developer developer = new Developer(firstName, lastName, specialty, experience, salary);
            Integer developerId = (Integer) session.save(developer);
            transaction.commit();
            return developerId;
        } catch (Exception e) {
            if(transaction != null){
                transaction.rollback();
            }
            System.out.println("Exception in add block: " + e.getMessage());
        }
        return 0;
    }

    void listDevelopers() {
        try (Session session = sessionFactory.openSession())
        {
            List developers = session.createQuery("FROM Developer").list();
            System.out.println("List of all Developers:");
            for (Object developer : developers) {
                System.out.println(developer);
                System.out.println("================");
            }
        } catch (Exception e) {
            System.out.println("Exception in listByParameter block: " + e.getMessage());
        }
    }

    void updateDeveloper(int developerId, int experience) {
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Developer developer = session.get(Developer.class, developerId);
            developer.setExperience(experience);
            session.update(developer);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null){
                transaction.rollback();
            }
            System.out.println("Exception in update block: " + e.getMessage());
        }
    }

    void removeDeveloper(int developerId) {
        try (Session session = sessionFactory.openSession())
        {
            transaction = session.beginTransaction();
            Developer developer = session.get(Developer.class, developerId);
            session.delete(developer);
            transaction.commit();
            System.out.println("Removing Some Developer and updating Proselyte Developer's experience:");
        } catch (Exception e) {
            if(transaction != null){
                transaction.rollback();
            }
            System.out.println("Exception in remove block: " + e.getMessage());
        }
    }

    void deleteAllData() {
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM Developer").executeUpdate();
            transaction.commit();
            System.out.println("Delete from table is successful");
        } catch (Exception e) {
            if(transaction != null){
                transaction.rollback();
            }
            System.out.println("Exception in deleteAllData block: " + e.getMessage());
        }
    }

    void listByParameter() {
        try (Session session = sessionFactory.openSession())
        {
            System.out.println("The Developer who will be deleted");
            List developers = session.createQuery("FROM Developer D WHERE D.firstName = 'Developer2'").list();
            System.out.println(developers);
        } catch (Exception e) {
            System.out.println("Exception in listByParameter block: " + e.getMessage());
        }
    }

    /**
     * sorting of list;
     * (experience > 3) - points that list will be shown with experience more than 3;
     * (ORDER BY D.experience DESC) - sorting of list (DESC - by descending; ASC - by ascending);
     */
    void sortList() {
        try (Session session = sessionFactory.openSession())
        {
            List developers = session.createQuery(
                    "FROM Developer D WHERE experience > 3 ORDER BY D.experience DESC").list();
            System.out.println("Sorted list");
            for(Object developer : developers) {
                System.out.println(developer);
            }
        } catch (Exception e) {
            System.out.println("Exception in sortList block: " + e.getMessage());
        }
    }

    void groupList() {
        try (Session session = sessionFactory.openSession())
        {
            List developers = session.createQuery("FROM Developer D GROUP BY D.specialty").list();
            System.out.println("grouping list");
            for (Object developer : developers) {
                System.out.println(developer);
            }
        } catch (Exception e) {
            System.out.println("Exception in groupList: " + e.getMessage());
        }
    }

    public void totalSalary() {
        try (Session session = sessionFactory.openSession())
        {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Developer.class);
            List sum = criteria.setProjection(Projections.sum("salary")).list();
            List max = criteria.setProjection(Projections.max("salary")).list();
            List min = criteria.setProjection(Projections.min("salary")).list();
            System.out.println("Total salary of all developers: " + sum);
            System.out.println("Min salary: " + max);
            System.out.println("Max salary: " + min);
            transaction.commit();
        }catch (Exception e) {
            if(transaction != null){
                transaction.rollback();
            }
            System.out.println("Exception in deleteAllData block: " + e.getMessage());
        }
    }
}
