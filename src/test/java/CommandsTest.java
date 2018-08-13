import org.junit.*;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class CommandsTest {
    private Commands commands = new Commands();

    @Before
    public void setUp(){
        commands.SessionFactoryOpen();
        commands.deleteAllData();
    }

    @After
    public void tearDown(){
        commands.SessionFactoryClosed();
    }

    @Test
    public void ifSessionFactoryOpen(){
        assertTrue(commands.sessionFactory.isOpen());
    }

    @Test
    public void ifSessionFactoryClosed(){
        commands.SessionFactoryClosed();
        assertTrue(commands.sessionFactory.isClosed());
    }

    @Test
    public void deleteTest() {
        commands.deleteAllData();
        assertEquals(commands.message,"Delete from table is successful");
    }

    @Test
    public void addDeveloperTest(){
        String message = "Developer:\n" +
                "id: 46\n" +
                "First Name: firstName\n" +
                "Last Name: lastName\n" +
                "Specialty: specialty\n" +
                "Experience: 1\n" +
                "Salary: 1000";
        commands.addDeveloper("firstName", "lastName", "specialty", 1, 1000);
        commands.listDevelopers();
        String incomeMessage = commands.developers.toString();
        assertEquals(commands.message, "List of all Developers:");
        assertEquals(incomeMessage.substring(19, incomeMessage.length()-2), message.substring(18, message.length()));
    }

    @Test
    public void updateDeveloperTest(){
        String message = "First Name: firstName\n" +
                "Last Name: lastName\n" +
                "Specialty: specialty\n" +
                "Experience: 2\n" +
                "Salary: 1000";
        Integer developerId = commands.addDeveloper("firstName", "lastName", "specialty", 1, 1000);
        commands.updateDeveloper(developerId, 2);
        commands.listDevelopers();
        String incomeMessage = commands.developers.toString();
        assertEquals(commands.message, "List of all Developers:");
        assertEquals(incomeMessage.substring(19, incomeMessage.length()-2), message);
    }

    @Test
    public void removeDeveloperTest(){
        String message = "First Name: firstName\n" +
                "Last Name: lastName\n" +
                "Specialty: specialty\n" +
                "Experience: 1\n" +
                "Salary: 1000";
        commands.addDeveloper("firstName", "lastName", "specialty", 1, 1000);
        Integer developerId = commands.addDeveloper("firstName", "lastName", "specialty", 1, 1000);
        commands.removeDeveloper(developerId);
        commands.listDevelopers();
        String incomeMessage = commands.developers.toString();
        assertEquals(commands.message, "List of all Developers:");
        assertEquals(incomeMessage.substring(19, incomeMessage.length()-2), message);
    }

    @Test
    public void ListByParameterTest(){
        String message = "First Name: Developer2\n" +
                "Last Name: lastName2\n" +
                "Specialty: specialty2\n" +
                "Experience: 2\n" +
                "Salary: 2000";
        commands.addDeveloper("Developer1", "lastName1", "specialty1", 1, 1000);
        commands.addDeveloper("Developer2", "lastName2", "specialty2", 2, 2000);
        commands.listByParameter();
        String incomeMessage = commands.developers.toString();
        assertEquals(commands.message, "List by parameter");
        assertEquals(incomeMessage.substring(19, incomeMessage.length()-2), message);
    }

}