import java.util.*;
import java.io.*;

public class Duke {
    public static void main(String[] args) {
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
        while(true) {
            Scanner sc = new Scanner(System.in);
            String cmd = sc.next();
            if (cmd.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println(cmd);
            }
        }
        //Level 1 ends

    }
}