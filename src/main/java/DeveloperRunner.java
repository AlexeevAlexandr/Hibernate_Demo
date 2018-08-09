public class DeveloperRunner {
    public static void main(String[] args) {
        Commands commands = new Commands();
        //clear table
        commands.deleteAllData();
        //add to table
        Integer developerId1 = commands.addDeveloper("Developer1", "Developer1", "Java Developer", 2);
        Integer developerId2 = commands.addDeveloper("Developer2", "Developer2", "C++ Developer", 2);
        commands.addDeveloper("Developer6", "Developer6", "Java Team Lead", 6);
        commands.addDeveloper("Developer4", "Developer4", "Java Junior", 1);
        commands.addDeveloper("Developer3", "Developer3", "Java Team Lead", 8);
        commands.addDeveloper("Developer5", "Developer5", "Java Team Lead", 4);
        //view list
        commands.listDevelopers();
        //view list by parameter
        commands.listByParameter();
        //remove and update
        commands.removeDeveloper(developerId2);
        commands.updateDeveloper(developerId1, 3);

        commands.listDevelopers();
        //view sorted list
        commands.sortList();
        //view group list
        commands.groupList();
    }

}