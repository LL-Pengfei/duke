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

/**
 * The Class TaskList, containing the tasklist, enabling operations to add & delete tasks in the tasklist
 */
public class TaskList {
    public static ArrayList<Task> t = new ArrayList<>();

    /**
     * Empty Constructor
     */
    public TaskList() {
    }

    /**
     * Enabling adding in new tasks of todo type
     *
     * @param cmd_2 the content of the todo task, with description of the todo task
     */
    public void todo(String cmd_2) {
        TaskList.t.add(Task.size, new Todo(cmd_2));
    }

    /**
     * Enabling the operation of marking a task done
     *
     * @param cmd_2 the # of the Task in the TaskList that the user wishes to mark as done
     */
    public void done(String cmd_2) {
        //done things
        System.out.println("Nice! I've marked this task as done:");
        System.out.print("  ");
        int num = Integer.parseInt(cmd_2); // the second token is num
        TaskList.t.get(num-1).markAsDone();
        System.out.println(TaskList.t.get(num-1).toString());
    }

    /**
     * Enabling the operation of deleting a task in the TaskList
     *
     * @param cmd_2 the # of the Task in the TaskList that the user wishes to delete
     */
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

    /**
     * Enabling the operation of finding a task in the TaskList
     *
     * @param cmd_2 the content that the users wishes to find matching the
     *              content of one/some of the tasks in the TaskList
     */
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
}
