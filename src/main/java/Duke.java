//@@author LL-Pengfei
/* Author: Zhao Pengfei (Email: zhao.pengfei@comp.nus.edu.sg)
 * Copyright © Zhao Pengfei (Email: zhao.pengfei@comp.nus.edu.sg)
 * AY19/20 CS2113T Module Project, Individual Component
 * School of Computing (SoC), National University of Singapore (NUS)
 *
 * please do not copy the codes/any of the code segments for any purposes.
 * Plagiarism is strictly prohibited under NUS rules and regulations.
 */

//import TaskPackage.*;
/*import StoragePackage.*;
import ParserPackage.*;
import UiPackage.*;
import DukeExceptionPackage.*;
 */

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    //there is a bit confusion with naming here, due to cumulative work done...
    //Task is the subclass for Deadline Event Todo
    //Tasklist is the class extracting out codes dealing with the list when attempting to make the code more oop
    //will probably refactorise in the future

    public Duke(String filePath) throws Exception {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
        parser = new Parser();
        storage.load();
    }

    public void run() throws Exception {
        while(true) {
            String cmd = Storage.br.readLine();
            if (cmd.equals("bye")) {
                ui.bye();
                break;
            } else if (cmd.equals("list")) {
                ui.list();
            } else {
                String[] token = cmd.split(" ", 2);
                String cmd_1 = token[0];

                //handling errors here //should prob catch the case when rubbish input got space in b/w also
                //the variables errorMessage here better be put in the dukeException class, but time limited,
                //refactorise later; also refer to the webpage when refactorising
                if (token.length < 2) {
                    if (cmd_1.equals("done") || cmd_1.equals("delete") || cmd_1.equals("find") || cmd_1.equals("todo")
                            || cmd_1.equals("event") || cmd_1.equals("deadline")) {
                        //incomplete input
                        try {
                            String errorMessage = ":( OOPS!!! The description of a " + cmd_1 + " cannot be empty.";
                            throw new DukeException(errorMessage);
                        } catch (DukeException e) {
                            System.out.println(e.getMessage());
                            continue;
                        }
                    } else {
                        //non-sensical inputs, e.g. blah
                        try {
                            String errorMessage = ":( OOPS!!! I'm sorry, but I don't know what that means :-(";
                            throw new DukeException(errorMessage);
                        } catch (DukeException e) {
                            System.out.println(e.getMessage());
                            continue;
                        }
                    }
                }

                String cmd_2 = token[1];
                if (cmd_1.equals("done")) {
                    tasks.done(cmd_2);
                } else if (cmd_1.equals("delete")) {
                    tasks.delete(cmd_2);
                } else if (cmd_1.equals("find")){
                    tasks.find(cmd_2);
                } else {
                    ui.addTask_pre();
                    if (cmd_1.equals("todo")) {
                        tasks.todo(cmd_2);
                    } else {
                        parser.event_deadline_parser(cmd_1, cmd_2);
                    }
                    ui.addTask_post();
                }
            }
            storage.unload();
        }
    }

    public static void main(String[] args) throws Exception {
        new Duke("C:\\Users\\LL\\2113t\\duke\\src\\main\\java\\duke.txt").run();
    }
}


/* main function, support/enable the following functionalities:

1. initialize/greet/bye (Level-1) - initialize when programme starts
command: / - auto greet when programme starts
command: bye - will exit the programme

2. list (Level-2)
command: list - will return the list of the tasks stored

3. markAsDone (Level-3) - used Task class to implement
                        - allow marking as X or V
command: done int - will return "I've marked this task as done: ...

4. todo event deadline - support tracking different types of tasks (Level-4)
                       - implemented using inheritance to support
                       - multiple task types
command: todo sth - return added status + total task size (current)
command: deadline sth /by time - return added status + total task size (current)
command: event sth /at time - return added status + total task size (current)
** command has to be specific and sticks to the format specified in pt 8 **

5. Handle Errors (Level-5) - used Exceptions to handle errors
- define a class DukeException to represent exceptions specific to Duke
- currently support:
5.1 commands: done delete find todo event deadline //not followed by task number/task
    - currently can only handle empty commands, i.e. commands not followed by things that should follow
    - wrong ones still not dealt with (e.g. for deadline & event, if time format is wrong, not yet catchable)
5.2 non-sensical command/input e.g. blah -> only limited to one word command, no space
5.3 File IO Exception (persistent memory storage(pt 7)) - will catch if initial document is empty

6. Delete (Level-6)
command: delete int - return deleted status + total task size (current)

7. Save (Level-7)
- save the tasks in the hard disk automatically whenever the task list changes
- i.e. save after every command (implemented using while(true) loop and save at
- the end of completion of each command)
- also, load the data from the hard disk when Duke starts up
- file name & location hard-coded
- implemented using Serializable
ALSO, when doing Level-6, used Java Collections classes for storing data
- implemented using ArrayList<Task> to store the tasks

8. Dates and Time (Level-8)
- Deadline and Event time understood as time, not String
- support dates and time interpretations (at this moment, time only support round
- clock time, non-round clock time will round down to the round clock
- (e.g. 18:50 -> 18:00))
command: deadline(event) sth /by(at) D/M/YR TTTT (format)
sth /by D ||||| sth /at D -> space must have; must be this exact format
D/M/YR TTTT TTTT == 24 hour clock, no :
this will hence be interpreted into dates and time e.g. 2nd of December 2019, 6pm

9. Find (Level-9)
provide the capability to search for tasks that were recorded. Give users a
way to find a task by searching for a keyword
command: find sth (sth = any keyword) - return matching tasks list (number here
is not the number in the task list, but directly 1 2 3...)

9.5.1 A-TextUiTesting implemented, using Text UI Testing, test using the IO redirection technique
it now supports using the IO redirection technique to semi-automate testing of Duke
[automated testing of the Duke UI]
//need to update 9.5.1 after completing more oop section

9.5.2 A-MoreOOP implemented
Code refactored to extract out closely related code as classes
extracted out Ui Storage Parser Tasklist
 */