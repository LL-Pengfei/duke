//@@author LL-Pengfei
/* Author: Zhao Pengfei (Email: zhao.pengfei@comp.nus.edu.sg)
 * Copyright © Zhao Pengfei (Email: zhao.pengfei@comp.nus.edu.sg)
 * AY19/20 CS2113T Module Project, Individual Component
 * School of Computing (SoC), National University of Singapore (NUS)
 *
 * please do not copy the codes/any of the code segments for any purposes.
 * Plagiarism is strictly prohibited under NUS rules and regulations.
 */
//List.java
//deal with command List

public class List {
    public static void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Task.size; i++) {
            System.out.println(i+1 + "." + Storage.t.get(i).toString());
        }
    }
}
