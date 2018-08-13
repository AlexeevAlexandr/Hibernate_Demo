import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class CommandsTest {
    private Commands commands = new Commands();

    @Before
    public void setUp(){
        commands.SessionFactoryOpen();
    }

    @After
    public void tearDown(){
        commands.SessionFactoryClosed();
    }

    @Test
    public void ifSessionFactory(){
        assertTrue(commands.sessionFactory.isOpen());
    }

    @Test
    public void deleteTest() {
        commands.deleteAllData();
        assertEquals(commands.string,"Delete from table is successful");
    }
}