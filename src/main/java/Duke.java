//@@author LL-Pengfei
/* Author: Zhao Pengfei (Email: zhao.pengfei@comp.nus.edu.sg)
 * Copyright © Zhao Pengfei (Email: zhao.pengfei@comp.nus.edu.sg)
 * AY19/20 CS2113T Module Project, Individual Component
 * School of Computing (SoC), National University of Singapore (NUS)
 *
 * please do not copy the codes/any of the code segments for any purposes.
 * Plagiarism is strictly prohibited under NUS rules and regulations.
 */

import java.util.*;
import java.io.*;

public class Duke {
    public static ArrayList<Task> t = new ArrayList<>(); //make it global

    public static void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Task.size; i++) {
            System.out.println(i+1 + "." + t.get(i).toString());
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
        //Task[] t = new Task[105]; //redundancy
        String[] month_name = new String[15]; //redundancy
        month_name[0] = "January";
        month_name[1] = "February";
        month_name[2] = "March";
        month_name[3] = "April";
        month_name[4] = "May";
        month_name[5] = "June";
        month_name[6] = "July";
        month_name[7] = "August";
        month_name[8] = "September";
        month_name[9] = "October";
        month_name[10] = "November";
        month_name[11] = "December";

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //file io
        try {
            FileInputStream file = new FileInputStream("C:\\Users\\LL\\2113t\\duke\\src\\main\\java\\duke.txt");
            ObjectInputStream in = new ObjectInputStream(file);
            Task.size = 0;
            while(file.available() > 0) {
                t.add(Task.size++, (Task)in.readObject());
            }
            in.close();
            file.close();
        } catch (IOException e) {
            System.out.println("OOPS... IOException is caught.");
        } catch (ClassNotFoundException e) {
            System.out.println("OOPS... ClassNotFoundException is caught.");
        }

        while(true) {
            String cmd = br.readLine();
            if (cmd.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (cmd.equals("list")) {
                list();
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
                    t.get(num-1).markAsDone();
                    System.out.println(t.get(num-1).toString());
                } else {
                    //doing things
                    System.out.println("Got it. I've added this task:");
                    System.out.print("  ");
                    if (cmd_1.equals("todo")) {
                        t.add(Task.size, new Todo(cmd_2));
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
                        String task_month_str =  month_name[task_month_int-1];
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
                            t.add(Task.size, new Event(cmd_2_1, task_day_str, task_month_str, task_year_str, task_time_str));
                        } else { //deadline
                            t.add(Task.size, new Deadline(cmd_2_1, task_day_str, task_month_str, task_year_str, task_time_str));
                        }
                    }
                    System.out.println(t.get(Task.size-1).toString());
                    System.out.println("Now you have " + Task.size + " tasks in the list.");
                }
            }

            //file io
            try {
                FileOutputStream new_file = new FileOutputStream("C:\\Users\\LL\\2113t\\duke\\src\\main\\java\\duke.txt");
                ObjectOutputStream out = new ObjectOutputStream(new_file);
                for (int i = 0; i < Task.size; i++) {
                    out.writeObject(t.get(i));
                }
                out.close();
                new_file.close();
            } catch (IOException e) {
                System.out.println("OOPS... IOException is caught.");
            }
        }
    }
}