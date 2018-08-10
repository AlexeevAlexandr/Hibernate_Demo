public class DeveloperRunner {
    public static void main(String[] args) {
        Commands commands = new Commands();
        try {
            commands.SessionFactoryOpen();
            //clear table
            commands.deleteAllData();
            //add to table
            Integer developerId1 = commands.addDeveloper("Developer1", "Developer1", "Java Developer", 2, 2000);
            Integer developerId2 = commands.addDeveloper("Developer2", "Developer2", "C++ Developer", 2, 1800);
            commands.addDeveloper("Developer6", "Developer6", "Java Team Lead", 6, 2500);
            commands.addDeveloper("Developer4", "Developer4", "Java Junior", 1, 800);
            commands.addDeveloper("Developer3", "Developer3", "Java Team Lead", 8, 2300);
            commands.addDeveloper("Developer5", "Developer5", "Java Team Lead", 4, 2100);
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
            //view total salary
            commands.totalSalary();
        }catch (Exception e){
            System.out.println("Exception in class DeveloperRunner: " + e.getMessage());
        }finally {
            commands.SessionFactoryClosed();
        }
    }

}