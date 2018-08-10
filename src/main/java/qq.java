public class qq {
    public static void main(String[] args) {
        Commands commands = new Commands();

        commands.SessionFactoryOpen();

        commands.deleteAllData();

        commands.SessionFactoryClosed();
    }
}
