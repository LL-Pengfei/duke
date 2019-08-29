import java.util.*;
import java.io.*;

public class Duke {
    public static void list(Task[] t) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Task.size; i++) {
            System.out.println(i+1 + "." + t[i].toString());
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
            if (cmd.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (cmd.equals("list")) {
                list(t);
            } else {

                //doing things, or done things
                //done noted as 1
                String[] token = cmd.split(" ", 2);
                String cmd_1 = token[0];

                //handling errors here
                //the variables errorMessage here better be put in the dukeException class,
                //but time limited today, refactorise later
                //also refer to the webpage just found out when refactorising
                //should prob catch the case when rubbish input got space in b/w
                if (token.length < 2) {
                    if (cmd_1.equals("done") || cmd_1.equals("todo") || cmd_1.equals("event") || cmd_1.equals("deadline")) {
                        //incomplete input
                        try {
                            String errorMessage = ":( OOPS!!! The description of a " + cmd_1 + " cannot be empty.";
                            throw new DukeException(errorMessage);
                        } catch (DukeException e) {
                            System.out.println(e.getMessage());
                            continue;
                        }
                    } else {
                        //non-sensical inputs, e.g. blah
                        try {
                            String errorMessage = ":( OOPS!!! I'm sorry, but I don't know what that means :-(";
                            throw new DukeException(errorMessage);
                        } catch (DukeException e) {
                            System.out.println(e.getMessage());
                            continue;
                        }
                    }
                }

                String cmd_2 = token[1];
                if (cmd_1.equals("done")) {
                    //done things
                    System.out.println("Nice! I've marked this task as done:");
                    int num = Integer.parseInt(cmd_2); // the second token is num
                    t[num-1].markAsDone();
                    System.out.println(t[num-1].toString());
                } else {
                    //doing things
                    System.out.println("Got it. I've added this task:");
                    System.out.print("  ");
                    if (cmd_1.equals("todo")) {
                        t[Task.size] = new Todo(cmd_2);
                    } else {
                        //event & deadline
                        //need string parsing again
                        //System.out.println("debugggg");
                        String[] cmd_2_token = cmd_2.split("/");
                        String cmd_2_1 = cmd_2_token[0];
                        String cmd_2_2 = cmd_2_token[1];
                        String[] cmd_2_2_token = cmd_2_2.split(" ", 2);
                        String cmd_2_2_2 = cmd_2_2_token[1];
                        if (cmd_1.equals("event")) {
                            t[Task.size] = new Event(cmd_2_1, cmd_2_2_2);
                        } else { //deadline
                            t[Task.size] = new Deadline(cmd_2_1, cmd_2_2_2);
                        }
                    }
                    // System.out.println(Task.size);
                    System.out.println(t[Task.size-1].toString());
                    System.out.println("Now you have " + Task.size + " tasks in the list.");
                }
            }
        }
    }
}
//Task[] tasks = new Task[100];
//task[0] = new Deadline("return book", "Monday");