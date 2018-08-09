public class q {
    public static void main(String[] args) {
        View view = new View();
        Commands commands = new Commands();

        view.write("Hello");
        boolean check = true;
        while (check) {
            view.write("listByParameter commands:\n" +
                    "add - add data to table\n" +
                    "clear - delete all data from table\n" +
                    "exit - exit");
            String command = view.readString();
            switch (command) {
                case "add":
                    add(view, commands);
                    break;
                case "clear":
                    clear(view, commands);
                    break;
                case "exit":
                    view.write("Bye");
                    check = false;
                    break;
                default:
                    view.write("Incorrect command");
                    break;
            }
        }
    }

    private static void clear(View view, Commands commands) {
        view.write("you trying clearing all data\n" +
                "confirm clearing (y/n)");
        String confirm = view.readString();
        if(confirm.equals("y")) {
            commands.deleteAllData();
        }else{view.write("clearing aborted");}
    }

    private static void add(View view, Commands commands) {
        try {
            view.write("You try insert data to table");
            view.write("Enter first name");
            String firstName = view.readString();
            view.write("Enter last name");
            String lastName = view.readString();
            view.write("Enter specialty");
            String specialty = view.readString();
            view.write("Enter experience");
            int experience = view.readInt();
            commands.addDeveloper(firstName, lastName, specialty, experience);
            view.write("data is saved");
        }catch (Exception e){ view.write(e.getMessage()); }
    }
}
