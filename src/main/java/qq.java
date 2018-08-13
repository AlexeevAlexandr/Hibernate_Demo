public class qq {
    public static void main(String[] args) {
        Commands commands = new Commands();

        commands.SessionFactoryOpen();
        commands.deleteAllData();
        System.out.println(commands.string.equals("Delete from table is successful")? "OK" : "Error");
        commands.SessionFactoryClosed();
    }
}
