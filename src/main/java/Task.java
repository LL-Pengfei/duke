//@@author LL-Pengfei
/* Author: Zhao Pengfei (Email: zhao.pengfei@comp.nus.edu.sg)
 * Copyright © Zhao Pengfei (Email: zhao.pengfei@comp.nus.edu.sg)
 * AY19/20 CS2113T Module Project, Individual Component
 * School of Computing (SoC), National University of Singapore (NUS)
 *
 * please do not copy the codes/any of the code segments for any purposes.
 * Plagiarism is strictly prohibited under NUS rules and regulations.
 */

//Task.java
//baseclass of Deadline Event & Todo, branching out to form different subclasses
//of Task; implemented Serializable to support persistent storage (save tasks in
// hard disk, load data from hard disk when Duke starts up)

import java.io.Serializable;

/**
 * The Class Task, base-class of Class Event, Deadline and Todo
 * It provides and enables the functionalities related to the Task.
 */
public class Task implements Serializable {
    protected String description;
    protected boolean isDone;
    public static int size = 0; // actually no need to specially
                                // keep a static variable to trace size...
                                // since arraylist has been implemented and can
                                // access the size using size() method...
    /**
     * The constructor for the Class Task, initialize the parameters of Task for description and time of the Task,
     * i.e. the name of the Task and the state whether the Task has been done (initialize to not done yet, i.e. isDone == false)
     * @param description The Task name
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        size++;
    }

    /**
     * Cast all the information pertaining to a Task instance to one singular string
     *
     * @return The string containing all the information associated with an Task instance.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Mark the Task as Done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Get the symbol related to the status of a Task instance as to whether is has been done or not.
     * If the Task has been done, return a tick, otherwise return a cross.
     *
     * @return A tick if the Task instance has been marked as done, or a cross if the Task has not been done yet
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }
}