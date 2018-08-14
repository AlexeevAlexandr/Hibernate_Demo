public class ManualTest {
    public static void main(String[] args) {
        Commands commands = new Commands();

        commands.SessionFactoryOpen();
        System.out.println(commands.message.equals("id is reset")? "OK" : "False");
        commands.deleteAllData();
        System.out.println(commands.message.equals("Delete from table is successful")? "OK" : "False");

        commands.dropCountID();
        commands.SessionFactoryClosed();
    }
}
