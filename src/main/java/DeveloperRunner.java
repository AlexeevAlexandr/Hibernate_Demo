public class DeveloperRunner {

    public static void main(String[] args) {
        Commands commands = new Commands();
        commands.deleteAllData();

        System.out.println("Adding Developer's records to the database");
        Integer developerId1 = commands.addDeveloper("Proselyte", "Developer", "Java Developer", 2);
        Integer developerId2 = commands.addDeveloper("Some", "Developer", "C++ Developer", 2);
        commands.addDeveloper("Peter", "Team Lead", "Java Team Lead", 6);

        System.out.println("List of Developers:");
        commands.listDevelopers();

        System.out.println("Removing Some Developer and updating Proselyte Developer's experience:");
        commands.removeDeveloper(developerId2);
        commands.updateDeveloper(developerId1, 3);

        System.out.println("Final list of Developers:");
        commands.listDevelopers();
    }

}