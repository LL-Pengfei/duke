//@@author LL-Pengfei
/* Author: Zhao Pengfei (Email: zhao.pengfei@comp.nus.edu.sg)
 * Copyright © Zhao Pengfei (Email: zhao.pengfei@comp.nus.edu.sg)
 * AY19/20 CS2113T Module Project, Individual Component
 * School of Computing (SoC), National University of Singapore (NUS)
 *
 * please do not copy the codes/any of the code segments for any purposes.
 * Plagiarism is strictly prohibited under NUS rules and regulations.
 */
/* main function, support/enable the following functionalities:

1. initialize/greet/bye (Level-1) - initialize when programme starts
command: / - auto greet when programme starts
command: bye - will exit the programme

2. list (Level-2)
command: list - will return the list of the tasks stored

3. markAsDone (Level-3) - used Task class to implement
                        - allow marking as X or V
command: done int - will return "I've marked this task as done: ...

4. todo event deadline - support tracking different types of tasks (Level-4)
                       - implemented using inheritance to support
                       - multiple task types
command: todo sth - return added status + total task size (current)
command: deadline sth /by time - return added status + total task size (current)
command: event sth /at time - return added status + total task size (current)
** command has to be specific and sticks to the format specified in pt 8 **

5. Handle Errors (Level-5) - used Exceptions to handle errors
- define a class DukeException to represent exceptions specific to Duke
- currently support:
5.1 commands: done delete find todo event deadline //not followed by task number/task
    - currently can only handle empty commands, i.e. commands not followed by things that should follow
    - wrong ones still not dealt with (e.g. for deadline & event, if time format is wrong, not yet catchable)
5.2 non-sensical command/input e.g. blah -> only limited to one word command, no space
5.3 File IO Exception (persistent memory storage(pt 7)) - will catch if initial document is empty

6. Delete (Level-6)
command: delete int - return deleted status + total task size (current)

7. Save (Level-7)
- save the tasks in the hard disk automatically whenever the task list changes
- i.e. save after every command (implemented using while(true) loop and save at
- the end of completion of each command)
- also, load the data from the hard disk when Duke starts up
- file name & location hard-coded
- implemented using Serializable
ALSO, when doing Level-6, used Java Collections classes for storing data
- implemented using ArrayList<Task> to store the tasks

8. Dates and Time (Level-8)
- Deadline and Event time understood as time, not String
- support dates and time interpretations (at this moment, time only support round
- clock time, non-round clock time will round down to the round clock
- (e.g. 18:50 -> 18:00))
command: deadline(event) sth /by(at) D/M/YR TTTT (format)
sth /by D ||||| sth /at D -> space must have; must be this exact format
D/M/YR TTTT TTTT == 24 hour clock, no :
this will hence be interpreted into dates and time e.g. 2nd of December 2019, 6pm

9. Find (Level-9)
provide the capability to search for tasks that were recorded. Give users a
way to find a task by searching for a keyword
command: find sth (sth = any keyword) - return matching tasks list (number here
is not the number in the task list, but directly 1 2 3...)

9.5 A-TextUiTesting implemented, using Text UI Testing, test using the IO redirection technique
it now supports using the IO redirection technique to semi-automate testing of Duke
[automated testing of the Duke UI]
 */

import java.util.*;
import java.io.*;

public class Duke {
    private Ui ui;
    private Storage storage;

    public Duke(String filePath) throws Exception {
        ui = new Ui();
        storage = new Storage(filePath);
        storage.load();
    }

    public void run() throws Exception {
        while(true) {
            String cmd = Storage.br.readLine();
            if (cmd.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (cmd.equals("list")) {
                List.list();
            } else {
                //doing things, or done things
                String[] token = cmd.split(" ", 2);
                String cmd_1 = token[0];

                //handling errors here
                //the variables errorMessage here better be put in the dukeException class,
                //but time limited, refactorise later
                //also refer to the webpage when refactorising
                //should prob catch the case when rubbish input got space in b/w
                if (token.length < 2) {
                    if (cmd_1.equals("done") || cmd_1.equals("delete") || cmd_1.equals("find") || cmd_1.equals("todo")
                            || cmd_1.equals("event") || cmd_1.equals("deadline")) {
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
                    System.out.print("  ");
                    int num = Integer.parseInt(cmd_2); // the second token is num
                    Storage.t.get(num-1).markAsDone();
                    System.out.println(Storage.t.get(num-1).toString());
                } else if (cmd_1.equals("delete")) {
                    //delete things
                    System.out.println("Noted. I've removed this task:");
                    System.out.print("  ");
                    int num = Integer.parseInt(cmd_2);
                    System.out.println(Storage.t.get(num-1).toString());
                    Storage.t.remove(num-1);
                    Task.size--;
                    System.out.println("Now you have " + Task.size + " tasks in the list.");
                } else if (cmd_1.equals("find")){
                    //add functionality to allow users to find task by
                    //searching for a keyword
                    System.out.println("Here are the matching tasks in your list:");
                    int temp_counter = 0;
                    for (int i = 0; i < Task.size; i++) {
                        String temp_str = Storage.t.get(i).toString();
                        if (temp_str.contains(cmd_2)) {
                            System.out.println(++temp_counter + "." + temp_str);
                        }
                    }
                } else {
                    //doing things
                    System.out.println("Got it. I've added this task:");
                    System.out.print("  ");
                    if (cmd_1.equals("todo")) {
                        Storage.t.add(Task.size, new Todo(cmd_2));
                    } else {
                        //event & deadline
                        //need string parsing again
                        String[] cmd_2_token = cmd_2.split("/", 2);
                        String cmd_2_1 = cmd_2_token[0];
                        String cmd_2_2 = cmd_2_token[1];
                        String[] cmd_2_2_token = cmd_2_2.split(" ", 2);
                        String cmd_2_2_2 = cmd_2_2_token[1];

                        //cmd_2_2_2 is the dates and times
                        //level 8: have to parse it further
                        String[] cmd_2_2_2_token = cmd_2_2_2.split(" ");
                        String cmd_2_2_2_1 = cmd_2_2_2_token[0]; //dates
                        String cmd_2_2_2_2 = cmd_2_2_2_token[1]; //timing
                        String[] cmd_2_2_2_1_token = cmd_2_2_2_1.split("/");
                        String cmd_2_2_2_1_day = cmd_2_2_2_1_token[0];
                        String cmd_2_2_2_1_month = cmd_2_2_2_1_token[1];
                        String cmd_2_2_2_1_year = cmd_2_2_2_1_token[2];

                        //parse it
                        String task_year_str = cmd_2_2_2_1_year; //i know it is redundant logically,
                        // but it looks nice (better readability)
                        int task_month_int = Integer.parseInt(cmd_2_2_2_1_month);
                        String task_month_str =  Storage.month_name[task_month_int-1];
                        String task_day_str;
                        if (cmd_2_2_2_1_day.equals("1") || cmd_2_2_2_1_day.equals("21") || cmd_2_2_2_1_day.equals("31")) {
                            task_day_str = cmd_2_2_2_1_day + "st";
                        } else if (cmd_2_2_2_1_day.equals("2") || cmd_2_2_2_1_day.equals("22")) {
                            task_day_str = cmd_2_2_2_1_day + "nd";
                        } else if (cmd_2_2_2_1_day.equals("3") || cmd_2_2_2_1_day.equals("23")) {
                            task_day_str = cmd_2_2_2_1_day + "rd";
                        } else {
                            task_day_str = cmd_2_2_2_1_day + "th";
                        }

                        //okay... date parsing complete, now do the timing parsing
                        int task_time_int = Integer.parseInt(cmd_2_2_2_2) / 100;
                        //note: can only take round clock at this moment
                        //examples given thus far seems only concerned with round clock...
                        String task_time_str;
                        if (task_time_int == 12) {
                            task_time_str = "12noon";
                        } else if (task_time_int == 0) {
                            task_time_str = "12midnight";
                        } else if (task_time_int > 12) {
                            task_time_str = Integer.toString(task_time_int-12) + "pm";
                        } else {
                            task_time_str = Integer.toString(task_time_int) + "am";
                        }
                        //parsing done

                        if (cmd_1.equals("event")) {
                            Storage.t.add(Task.size, new Event(cmd_2_1, task_day_str, task_month_str, task_year_str, task_time_str));
                        } else { //deadline
                            Storage.t.add(Task.size, new Deadline(cmd_2_1, task_day_str, task_month_str, task_year_str, task_time_str));
                        }
                    }
                    System.out.println(Storage.t.get(Task.size-1).toString());
                    System.out.println("Now you have " + Task.size + " tasks in the list.");
                }
            }
            storage.reload();
        }
    }

    public static void main(String[] args) throws Exception {
        new Duke("C:\\Users\\LL\\2113t\\duke\\src\\main\\java\\duke.txt").run();
    }
}