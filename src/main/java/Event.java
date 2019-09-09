//@@author LL-Pengfei
/* Author: Zhao Pengfei (Email: zhao.pengfei@comp.nus.edu.sg)
 * Copyright © Zhao Pengfei (Email: zhao.pengfei@comp.nus.edu.sg)
 * AY19/20 CS2113T Module Project, Individual Component
 * School of Computing (SoC), National University of Singapore (NUS)
 *
 * please do not copy the codes/any of the code segments for any purposes.
 * Plagiarism is strictly prohibited under NUS rules and regulations.
 */

//Event.java
//support Event of Task, subclass of Task
//tutorial01 note: better to use getters and setters to allow modification of at_day month year time inside class event
//a good practice to do that for oop & se

/**
 * The Class Event, sub-class of Class Task
 * It provides and enables the functionalities related to the Event type of Task.
 */
public class Event extends Task {
    protected String at_day;
    protected String at_month;
    protected String at_year;
    protected String at_time;

    /**
     * The constructor for the Class Event, initialize the parameters of Event for description and time of the event
     *
     * @param description The event name
     * @param at_day The day of the month of the year of the event
     * @param at_month The month of the year of the event
     * @param at_year The year of the event
     * @param at_time The specific timing of the event
     */
    public Event(String description, String at_day, String at_month, String at_year, String at_time) {
        super(description);
        this.at_day = at_day;
        this.at_month = at_month;
        this.at_year = at_year;
        this.at_time = at_time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at_day + " of " + at_month + " " + at_year + ", " +  at_time + ")";
    }
}