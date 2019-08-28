import java.util.*;
import java.io.*;

public class Duke {
    public static void list(Task[] t) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Task.size; i++) {
            System.out.print(i+1 + ".[");
            System.out.print(t[i].getStatusIcon());
            System.out.println("] " + t[i].description);
        }
    }

    public static void initialize() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void main(String[] args) throws Exception {
        initialize();
        Task[] t = new Task[105]; //redundancy
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            String cmd = br.readLine();
            //if (cmd == null) break;
            if (cmd.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (cmd.equals("list")) {
                list(t);
            } else {
                //doing things, or done things
                //done noted as 1
                String[] token = cmd.split(" ");
                String cmd_1 = token[0];
                if (cmd_1.equals("done")) {
                    //done things
                    System.out.println("Nice! I've marked this task as done:");
                    int num = Integer.parseInt(token[1]); // the second token is num
                    t[num-1].markAsDone();
                    System.out.println("  [" + t[num-1].getStatusIcon() + "] " + t[num-1].description);
                } else {
                    //doing things
                    System.out.println("added: " + cmd);
                    t[Task.size] = new Task(cmd);
                }
            }
        }
    }
}