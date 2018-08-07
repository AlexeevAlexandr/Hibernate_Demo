public class DeveloperRunner {
    private Commands commands = new Commands();

    public static void main(String[] args) {
        DeveloperRunner developerRunner = new DeveloperRunner();

        System.out.println("Adding Developer's records to the database");
        Integer developerId1 = developerRunner.commands.addDeveloper("Proselyte", "Developer", "Java Developer", 2);
        Integer developerId2 = developerRunner.commands.addDeveloper("Some", "Developer", "C++ Developer", 2);
        Integer developerId3 = developerRunner.commands.addDeveloper("Peter", "Team Lead", "Java Team Lead", 6);

        System.out.println("List of Developers:");
        developerRunner.commands.listDevelopers();

        System.out.println("Removing Some Developer and updating Proselyte Developer's experience:");
        developerRunner.commands.removeDeveloper(developerId2);
        developerRunner.commands.updateDeveloper(developerId1, 3);

        System.out.println("Final list of Developers:");
        developerRunner.commands.listDevelopers();
    }
}