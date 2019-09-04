//@@author LL-Pengfei
/* Author: Zhao Pengfei (Email: zhao.pengfei@comp.nus.edu.sg)
 * Copyright © Zhao Pengfei (Email: zhao.pengfei@comp.nus.edu.sg)
 * AY19/20 CS2113T Module Project, Individual Component
 * School of Computing (SoC), National University of Singapore (NUS)
 *
 * please do not copy the codes/any of the code segments for any purposes.
 * Plagiarism is strictly prohibited under NUS rules and regulations.
 */
//Parser.java
//deal with making sense of the user command

public class Parser {
    public void event_deadline_parser(String cmd_1, String cmd_2) {
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
        String task_month_str =  Storage.month_name[task_month_int-1];
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
            TaskList.t.add(Task.size, new Event(cmd_2_1, task_day_str, task_month_str, task_year_str, task_time_str));
        } else { //deadline
            TaskList.t.add(Task.size, new Deadline(cmd_2_1, task_day_str, task_month_str, task_year_str, task_time_str));
        }
    }
}
