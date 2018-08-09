public class DeveloperRunner {

    public static void main(String[] args) {
        Commands commands = new Commands();
        commands.deleteAllData();

        System.out.println("Adding Developer's records to the database");
        Integer developerId1 = commands.addDeveloper("Developer1", "Developer1", "Java Developer", 2);
        Integer developerId2 = commands.addDeveloper("Developer2", "Developer2", "C++ Developer", 2);
        commands.addDeveloper("Developer6", "Developer6", "Java Team Lead", 6);
        commands.addDeveloper("Developer4", "Developer4", "Java Junior", 1);
        commands.addDeveloper("Developer3", "Developer3", "Java Team Lead", 8);
        commands.addDeveloper("Developer5", "Developer5", "Java Team Lead", 4);

        System.out.println("List of all Developers:");
        commands.listDevelopers();

        System.out.println("The Developer who will be deleted");
        commands.listByParameter();

        System.out.println("Removing Some Developer and updating Proselyte Developer's experience:");
        commands.removeDeveloper(developerId2);
        commands.updateDeveloper(developerId1, 3);

        System.out.println("Final listByParameter of Developers:");
        commands.listDevelopers();

        System.out.println("Sorted list");
        commands.sortList();
    }

}