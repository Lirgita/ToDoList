package org.list;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;


public class TaskOrganizer {

    // 2D array to store tasks
    // rows = tasks, columns = task attributes
    private static String[][] tasks = new String[100][10];
    private static int taskCount = 0;

    // Column index mapping:
    // 0 - name
    // 1 - description
    // 2 - priority
    // 3 - status
    // 4 - category
    // 5 - dueDate
    // 6 - creationDate
    // 7 - estimatedTime
    // 8 - tags
    // 9 - assignedTo

    public static void addTask(Task task) {
        for(int col = 0; col<10;col++){
            if(col==0){
                tasks[taskCount][col]=task.getName();
            }else if(col==1){
                tasks[taskCount][col]=task.getDescription();
            }else if(col==2){
                tasks[taskCount][col] = task.getPriority() + "";

            }else if(col==3){
                tasks[taskCount][col] = task.getStatus();
            }else if(col==4){
                tasks[taskCount][col] = task.getCategory();
            }else if(col==5){
                tasks[taskCount][col] = task.getDueDate();
            }else if(col==6){
                tasks[taskCount][col] = task.getCreationDate();
            }else if(col==7){
                tasks[taskCount][col] = task.getEstimatedTime() + "";
            }else if(col==8){
                tasks[taskCount][col]=task.getTags();
            }else if(col==9){
                tasks[taskCount][col]=task.getAssignedTo();
            }
        }
        taskCount++;
    }
    //Displays all tasks stored in the array

    public static void showAllTasks() {
        if(taskCount==0){
            System.out.println("No tasks");
            return;
        }
        for(int i = 0;i<taskCount;i++){
            System.out.println("----- Task " + (i + 1) + " -----");
            System.out.println("Name: " + tasks[i][0]);
            System.out.println("Description: " + tasks[i][1]);
            System.out.println("Priority: " + tasks[i][2]);
            System.out.println("Status: " + tasks[i][3]);
            System.out.println("Category: " + tasks[i][4]);
            System.out.println("Due Date: " + tasks[i][5]);
            System.out.println("Creation Date: " + tasks[i][6]);
            System.out.println("Estimated Time: " + tasks[i][7]);
            System.out.println("Tags: " + tasks[i][8]);
            System.out.println("Assigned To: " + tasks[i][9]);

        }

    }

    //Marks a task as DONE using its index.
    public static void markTaskAsDone(int index) {

        switch (index) {

            // invalid negative index
            case -1:
                System.out.println("Invalid task number.");
                break;

            default:
                if (index >= taskCount) {
                    System.out.println("Invalid task number.");
                } else {
                    tasks[index][3] = "DONE";
                    System.out.println("Task marked as DONE.");
                }
                break;
        }
    }
    public static void filterByCategory(String category) {

        int count = 0;

        for (int i = 0; i < taskCount; i++) {

            if (tasks[i][4].equalsIgnoreCase(category)) {

                System.out.println("----- Task " + (i + 1) + " -----");
                System.out.println("Name: " + tasks[i][0]);
                System.out.println("Description: " + tasks[i][1]);
                System.out.println("Priority: " + tasks[i][2]);
                System.out.println("Status: " + tasks[i][3]);
                System.out.println("Category: " + tasks[i][4]);
                System.out.println("Due Date: " + tasks[i][5]);
                System.out.println("Assigned To: " + tasks[i][9]);

                count++;
            }
        }

        if (count == 0) {
            System.out.println("No tasks found for this category.");
        }
    }


    //Sorts tasks by priority (ascending).
    //Uses a temporary variable to swap rows
    public static void sortByPriority() {

        for (int i = 0; i < taskCount; i++) {

            for (int j = i + 1; j < taskCount; j++) {

                int priority1 = Integer.parseInt(tasks[i][2]);
                int priority2 = Integer.parseInt(tasks[j][2]);

                if (priority1 > priority2) {

                    // temporary variable
                    String[] temp = tasks[i];
                    tasks[i] = tasks[j];
                    tasks[j] = temp;
                }
            }
        }

        System.out.println("Tasks sorted by priority.");
    }
    public static void editTask(int taskIndex, int option, String newValue) {

        if (taskIndex < 0 || taskIndex >= taskCount) {
            System.out.println("Wrong task number.");
            return;
        }

        switch (option) {

            case 1:
                tasks[taskIndex][0] = newValue;
                break;

            case 2:
                tasks[taskIndex][1] = newValue;
                break;

            case 3:
                tasks[taskIndex][4] = newValue;
                break;

            case 4:
                tasks[taskIndex][5] = newValue;
                break;

            default:
                System.out.println("Wrong option.");
                return;
        }

        System.out.println("Task updated.");
    }

    public static void deleteTask(int taskIndex) {

        if (taskIndex < 0 || taskIndex >= taskCount) {
            System.out.println("Invalid task number.");
            return;
        }

        for (int i = taskIndex; i < taskCount - 1; i++) {

            String[] temp = tasks[i];
            tasks[i] = tasks[i + 1];
            tasks[i + 1] = temp;
        }

        tasks[taskCount - 1] = null;
        taskCount--;

        System.out.println("Task deleted.");
    }

    public static void saveToFile() {

        try {
            FileWriter writer = new FileWriter("tasks.txt");

            for (int i = 0; i < taskCount; i++) {

                for (int j = 0; j < 10; j++) {
                    writer.write(tasks[i][j]);
                    if (j < 9) {
                        writer.write(",");
                    }
                }
                writer.write("\n");
            }

            writer.close();
            System.out.println("Tasks saved.");

        } catch (Exception e) {
            System.out.println("Error saving tasks.");
        }
    }





    public static int readInt(Scanner scanner, String message) {

        int number = 0;
        boolean valid = false;

        while (!valid) {
            try {
                System.out.print(message);
                number = scanner.nextInt();
                valid = true;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
        return number;
    }


    public static void loadFromFile() {

        try {
            File file = new File("tasks.txt");

            if (!file.exists()) {
                return;
            }

            Scanner fileScanner = new Scanner(file);
            taskCount = 0;

            while (fileScanner.hasNextLine()) {

                String line = fileScanner.nextLine();
                String[] parts = line.split(",");

                for (int j = 0; j < 10; j++) {
                    tasks[taskCount][j] = parts[j];
                }
                taskCount++;
            }

            fileScanner.close();

        } catch (Exception e) {
            System.out.println("Error loading tasks.");
        }
    }











}
