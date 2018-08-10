import org.junit.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CommandsTest {
    private Commands commands = new Commands();

    @Test
    public void totalSalary() {
        commands.SessionFactoryOpen();
        ArrayList<List> list = commands.totalSalary();
        commands.SessionFactoryClosed();
        assertEquals(list.toString(),"");
    }
}