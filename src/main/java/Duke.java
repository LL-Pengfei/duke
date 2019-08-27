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

        //Level 3 Mark as Done
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        String[] task = new String[105]; //redundancy
        int[] task_state = new int[105];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = 0;

        while(true) {
            String cmd = br.readLine();
            //if (cmd == null) break;
            if (cmd.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (cmd.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= size; i++) {
                    System.out.print(i + ".[");
                    if(task_state[i] == 1) {
                        System.out.print("\u2713"); //done
                    } else {
                        System.out.print("\u2718");
                    }
                    System.out.println("] " + task[i]);
                }
            } else {
                //doing things, or done things
                //done noted as 1
                String[] token = cmd.split(" ");
                String cmd_1 = token[0];
                if (cmd_1.equals("done")) {
                    //done things
                    System.out.println("Nice! I've marked this task as done:");
                    int num = Integer.parseInt(token[1]); // the second token is num
                    System.out.println("  [" + "\u2713" + "] " + task[num]);
                    task_state[num] = 1; //marked as done
                } else {
                    //doing things
                    System.out.println("added: " + cmd);
                    size++;
                    task[size] = cmd;
                    task_state[size] = 0; //undone
                }

            }
        }
        //Level 3 ends
    }
}
// Level 2 Add, List
        /*
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
        */
//Level 2 ends

//Level 1 Greet Echo Exit
        /*
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
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