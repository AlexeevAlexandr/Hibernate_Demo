import java.util.Scanner;

public class View {

    void write(String data){
        System.out.println(data);
    }

    String readString(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    int readInt(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
