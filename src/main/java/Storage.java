//@@author LL-Pengfei
/* Author: Zhao Pengfei (Email: zhao.pengfei@comp.nus.edu.sg)
 * Copyright © Zhao Pengfei (Email: zhao.pengfei@comp.nus.edu.sg)
 * AY19/20 CS2113T Module Project, Individual Component
 * School of Computing (SoC), National University of Singapore (NUS)
 *
 * please do not copy the codes/any of the code segments for any purposes.
 * Plagiarism is strictly prohibited under NUS rules and regulations.
 */
//Storage.java
//deal with loading tasks from the file and saving tasks in the file

import java.util.*;
import java.io.*;

public class Storage {
    public static ArrayList<Task> t = new ArrayList<>(); //make it global
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public String cmd;
    public String filePath;

    public static String[] month_name = new String[15]; //redundancy

    public Storage(String filePath) throws Exception {
        month_name[0] = "January";
        month_name[1] = "February";
        month_name[2] = "March";
        month_name[3] = "April";
        month_name[4] = "May";
        month_name[5] = "June";
        month_name[6] = "July";
        month_name[7] = "August";
        month_name[8] = "September";
        month_name[9] = "October";
        month_name[10] = "November";
        month_name[11] = "December";

        this.filePath = filePath;
        //file io
        try {
            FileInputStream file = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(file);
            Task.size = 0;
            while(file.available() > 0) {
                t.add(Task.size++, (Task)in.readObject());
            }
            in.close();
            file.close();
        } catch (IOException e) {
            System.out.println("OOPS... IOException is caught.");
        } catch (ClassNotFoundException e) {
            System.out.println("OOPS... ClassNotFoundException is caught.");
        }
    }

    public void wrapUp() throws Exception {
        //file io
        try {
            FileOutputStream new_file = new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(new_file);
            for (int i = 0; i < Task.size; i++) {
                out.writeObject(Storage.t.get(i));
            }
            out.close();
            new_file.close();
        } catch (IOException e) {
            System.out.println("OOPS... IOException is caught.");
        }
    }
}
