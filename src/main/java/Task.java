//@@author LL-Pengfei
/* Author: Zhao Pengfei (Email: zhao.pengfei@comp.nus.edu.sg)
 * Copyright © Zhao Pengfei (Email: zhao.pengfei@comp.nus.edu.sg)
 * AY19/20 CS2113T Module Project, Individual Component
 * School of Computing (SoC), National University of Singapore (NUS)
 *
 * please do not copy the codes/any of the code segments for any purposes.
 * Plagiarism is strictly prohibited under NUS rules and regulations.
 */

import java.io.Serializable;

public class Task implements Serializable {
    protected String description;
    protected boolean isDone;
    public static int size = 0; // actually no need to specially
                                // keep a static variable to trace size...
                                // since arraylist has been implemented and can
                                // access the size using size() method...

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        size++;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }
}