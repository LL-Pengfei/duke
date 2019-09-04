//@@author LL-Pengfei
/* Author: Zhao Pengfei (Email: zhao.pengfei@comp.nus.edu.sg)
 * Copyright © Zhao Pengfei (Email: zhao.pengfei@comp.nus.edu.sg)
 * AY19/20 CS2113T Module Project, Individual Component
 * School of Computing (SoC), National University of Singapore (NUS)
 *
 * please do not copy the codes/any of the code segments for any purposes.
 * Plagiarism is strictly prohibited under NUS rules and regulations.
 */
//TaskList.java
//contain the tasklist, has operations to add/delete tasks in the list

import java.util.*;
import java.io.*;

public class TaskList {
    public static ArrayList<Task> t = new ArrayList<>();

    public TaskList() {
    }

    public void delete(String cmd_2) {
        //delete things
        System.out.println("Noted. I've removed this task:");
        System.out.print("  ");
        int num = Integer.parseInt(cmd_2);
        System.out.println(TaskList.t.get(num-1).toString());
        TaskList.t.remove(num-1);
        Task.size--;
        System.out.println("Now you have " + Task.size + " tasks in the list.");
    }

    public void find(String cmd_2) {
        //add functionality to allow users to find task by
        //searching for a keyword
        System.out.println("Here are the matching tasks in your list:");
        int temp_counter = 0;
        for (int i = 0; i < Task.size; i++) {
            String temp_str = TaskList.t.get(i).toString();
            if (temp_str.contains(cmd_2)) {
                System.out.println(++temp_counter + "." + temp_str);
            }
        }
    }

    public void todo(String cmd_2) {
        TaskList.t.add(Task.size, new Todo(cmd_2));
    }

    public void done(String cmd_2) {
        //done things
        System.out.println("Nice! I've marked this task as done:");
        System.out.print("  ");
        int num = Integer.parseInt(cmd_2); // the second token is num
        TaskList.t.get(num-1).markAsDone();
        System.out.println(TaskList.t.get(num-1).toString());
    }
}
