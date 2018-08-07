import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DeveloperRunner {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        sessionFactory = new Configuration().configure().buildSessionFactory();

        DeveloperRunner developerRunner = new DeveloperRunner();

        System.out.println("Adding Developer's records to the database");
        Integer developerId1 = developerRunner.addDeveloper("Proselyte", "Developer", "Java Developer", 2);
        Integer developerId2 = developerRunner.addDeveloper("Some", "Developer", "C++ Developer", 2);
        Integer developerId3 = developerRunner.addDeveloper("Peter", "Team Lead", "Java Team Lead", 6);

        System.out.println("List of Developers:");
        developerRunner.listDevelopers();

        System.out.println("Removing Some Developer and updating Proselyte Developer's experience:");
        developerRunner.removeDeveloper(developerId2);
        developerRunner.updateDeveloper(developerId1, 3);

        System.out.println("Final list of Developers:");
        developerRunner.listDevelopers();
        sessionFactory.close();
    }
    void session(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    Integer addDeveloper(String firstName, String lastName, String specialty, int experience) {
        Session session = sessionFactory.openSession();
        Transaction transaction;
        Integer developerId;

        transaction = session.beginTransaction();
        Developer developer = new Developer(firstName, lastName, specialty, experience);
        developerId = (Integer) session.save(developer);
        transaction.commit();
        session.close();
        return developerId;
    }

    void listDevelopers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        List developers = session.createQuery("FROM Developer").list();
        for (Object developer : developers) {
            System.out.println(developer);
            System.out.println("\n================\n");
        }
        session.close();
    }

    private void updateDeveloper(int developerId, int experience) {
        Session session = sessionFactory.openSession();
        Transaction transaction;

        transaction = session.beginTransaction();
        Developer developer = session.get(Developer.class, developerId);
        developer.setExperience(experience);
        session.update(developer);
        transaction.commit();
        session.close();
    }

    private void removeDeveloper(int developerId) {
        Session session = sessionFactory.openSession();
        Transaction transaction;

        transaction = session.beginTransaction();
        Developer developer = session.get(Developer.class, developerId);
        session.delete(developer);
        transaction.commit();
        session.close();
    }

}