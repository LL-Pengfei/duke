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

/**
 * The Class Deadline, sub-class of Class Task
 * It provides and enables the functionalities related to the Deadline type of Task.
 */
public class Deadline extends Task {
    protected String by_day;
    protected String by_month;
    protected String by_year;
    protected String by_time;

    /**
     * The constructor for the Class Deadline, initialize the parameters of Deadline for description and time of the deadline
     *
     * @param description The deadline name
     * @param by_day The day of the month of the year of the deadline
     * @param by_month The month of the year of the deadline
     * @param by_year The year of the deadline
     * @param by_time The specific timing of the deadline
     */
    public Deadline(String description, String by_day, String by_month, String by_year, String by_time) {
        super(description);
        this.by_day = by_day;
        this.by_month = by_month;
        this.by_year = by_year;
        this.by_time = by_time;
    }

    /**
     * Cast all the information pertaining to a Deadline instance to one singular string
     *
     * @return The string containing all the information associated with an Deadline instance.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by_day + " of " + by_month + " " + by_year + ", " +  by_time + ")";
    }
}