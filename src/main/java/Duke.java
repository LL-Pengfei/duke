import java.util.*;
import java.io.*;

public class Duke {
    public static void main(String[] args) throws Exception {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        String[] task = new String[105]; //redundancy
        int[] task_state = new int[105];
        int[] task_new_state = new int[105]; //1 ToDOs|2 Deadlines|3 Events
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = 0;

        while(true) {
            String cmd = br.readLine();

            //System.out.println("debug ***" + cmd); if (true) break; //debug
            //debug
            //System.out.println("debug: " + cmd_1 + " " + cmd_2);
            //if (cmd == null) break;

            if (cmd.equals("todo") || cmd.equals("event") || cmd.equals("deadline")) {
                System.out.println(":( OOPS!!! The description of a " + cmd + "cannot be empty.");
                continue;
            } else if (cmd.equals("blah")) {
                System.out.println(":( OOPS!!! I'm sorry, but I don't know what that means :-(");
                continue;
            } else if (cmd.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (cmd.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                //System.out.println("debug ***"); if (true) break;
                for (int i = 1; i <= size; i++) {
                    System.out.print(i + ".[");
                    if (task_new_state[i] == 1) {
                        System.out.print("T");
                    } else if (task_new_state[i] == 2) {
                        System.out.print("D");
                    } else { // task_new_state[i] == 3
                        System.out.print("E");
                    }

                    System.out.print("][");
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
                String[] token = cmd.split(" ", 2);
                String cmd_1 = token[0];
                String cmd_2 = token[1];

                if (cmd_1.equals("done")) {
                    //done things
                    System.out.println("Nice! I've marked this task as done:");
                    int num = Integer.parseInt(token[1]); // the second token is num
                    System.out.print("  [");
                    if (task_new_state[num] == 1) {
                        System.out.print("T");
                    } else if (task_new_state[num] == 2) {
                        System.out.print("D");
                    } else { // task_new_state[num] == 3
                        System.out.print("E");
                    }
                    System.out.println("][" + "\u2713" + "] " + task[num]);
                    task_state[num] = 1; //marked as done
                } else {
                    //doing things
                    System.out.println("Got it. I've added this task:");
                    size++;

                    System.out.print("  [");
                    if (cmd_1.equals("todo")) {
                        task_state[size] = 0; //undone
                        task_new_state[size] = 1;
                        task[size] = cmd_2;
                        System.out.println("T][" + "\u2718" + "] " + cmd_2);
                        System.out.println("Now you have " + size + " tasks in the list.");
                    } else {
                        String[] cmd_temp = cmd_2.split("/");
                        String cmd_temp_1 = cmd_temp[0];
                        String cmd_temp_2 = cmd_temp[1];
                        String reorg_cmd = cmd_temp_1 + "(" + cmd_temp_2 + ")"; //the correct task name

                        task[size] = reorg_cmd;
                        task_state[size] = 0; //undone

                        if (cmd_1.equals("deadline")) {
                            System.out.print("D");
                            task_new_state[size] = 2;
                        } else { //event
                            System.out.print("E");
                            task_new_state[size] = 3;
                        }
                        System.out.println("][" + "\u2718" + "] " + reorg_cmd);
                        System.out.println("Now you have " + size + " tasks in the list.");
                    }
                    //debug
                    //System.out.println("debug: " + cmd_1);
                }
            }
        }
        //Level 4 ends
    }
}
/*
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    //...
}
Task t = new Taks("read book");
t.markAsDone()
*/

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

         PrintWriter pw = new PrintWriter(System.out);
         pw.printf(
         pw.close();

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