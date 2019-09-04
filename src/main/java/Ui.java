//@@author LL-Pengfei
/* Author: Zhao Pengfei (Email: zhao.pengfei@comp.nus.edu.sg)
 * Copyright © Zhao Pengfei (Email: zhao.pengfei@comp.nus.edu.sg)
 * AY19/20 CS2113T Module Project, Individual Component
 * School of Computing (SoC), National University of Singapore (NUS)
 *
 * please do not copy the codes/any of the code segments for any purposes.
 * Plagiarism is strictly prohibited under NUS rules and regulations.
 */
//Ui.java
//deal with user interactions

//package UiPackage;
//import TaskPackage.*;

public class Ui {
    public Ui() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Task.size; i++) {
            System.out.println(i+1 + "." + TaskList.t.get(i).toString());
        }
    }

    public void addTask_pre() {
        System.out.println("Got it. I've added this task:");
        System.out.print("  ");
    }

    public void addTask_post() {
        System.out.println(TaskList.t.get(Task.size-1).toString());
        System.out.println("Now you have " + Task.size + " tasks in the list.");
    }
}
