//@@author LL-Pengfei
/* Author: Zhao Pengfei (Email: zhao.pengfei@comp.nus.edu.sg)
 * Copyright © Zhao Pengfei (Email: zhao.pengfei@comp.nus.edu.sg)
 * AY19/20 CS2113T Module Project, Individual Component
 * School of Computing (SoC), National University of Singapore (NUS)
 *
 * please do not copy the codes/any of the code segments for any purposes.
 * Plagiarism is strictly prohibited under NUS rules and regulations.
 */
//Deadline.java
//support Deadline of Task, subclass of Task


public class Deadline extends Task {
    protected String by_day;
    protected String by_month;
    protected String by_year;
    protected String by_time;

    public Deadline(String description, String by_day, String by_month, String by_year, String by_time) {
        super(description);
        this.by_day = by_day;
        this.by_month = by_month;
        this.by_year = by_year;
        this.by_time = by_time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by_day + " of " + by_month + " " + by_year + ", " +  by_time + ")";
    }
}