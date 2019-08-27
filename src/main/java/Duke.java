import java.util.*;
import java.io.*;

public class Duke {
    public static void main(String[] args) throws Exception {
        //Level 0 - skeleton code
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        //Level 0 ends

        //Level 1 Greet Echo Exit
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        /*
        Scanner sc = new Scanner(System.in);
        while(true) {
            String cmd = sc.next();
            if (cmd.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println(cmd);
            }
        }
        */
        //Level 1 ends

        //Level 2 Add, List
        String[] task = new String[105]; //redundancy
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = 0;

        while(true) {
            String cmd = br.readLine();
            if (cmd.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (cmd.equals("list")) {
                for (int i = 1; i <= size; i++) {
                    System.out.println(i +". " + task[i]);
                }
            } else {
                System.out.println("added: " + cmd);
                size++;
                task[size] = cmd;
            }

        }
        //Level 2 ends
    }
}