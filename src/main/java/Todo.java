//@@author LL-Pengfei
/* Author: Zhao Pengfei (Email: zhao.pengfei@comp.nus.edu.sg)
 * Copyright © Zhao Pengfei (Email: zhao.pengfei@comp.nus.edu.sg)
 * AY19/20 CS2113T Module Project, Individual Component
 * School of Computing (SoC), National University of Singapore (NUS)
 *
 * please do not copy the codes/any of the code segments for any purposes.
 * Plagiarism is strictly prohibited under NUS rules and regulations.
 */

//Todo.java
//support Todo of Task, subclass of Task

/**
 * The Class Todo, sub-class of Class Task
 * It provides and enables the functionalities related to the Todo type of Task.
 */
public class Todo extends Task {
    /**
     * The constructor for the Class Todo, initialize the parameters of Todo for description
     *
     * @param description The Todo task name
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Cast all the information pertaining to a Todo instance to one singular string
     *
     * @return The string containing all the information associated with an Todo instance.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}