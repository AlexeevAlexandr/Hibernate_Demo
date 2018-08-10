import org.junit.*;

class Test {
    Commands commands = new Commands();

    @BeforeClass
    void SessionFactoryOpen() {
        commands.SessionFactoryOpen();
    }

    @AfterClass
    void SessionFactoryClosed() {
        commands.SessionFactoryClosed();
    }

    @Test
    void clearTest(){

    }
}
