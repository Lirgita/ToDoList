package org.list;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        TaskOrganizer.loadFromFile();

        int choice;

        do {
            System.out.println("\n=== TASK ORGANIZER ===");
            System.out.println("1. Add task");
            System.out.println("2. Show all tasks");
            System.out.println("3. Mark task as DONE");
            System.out.println("4. Filter by category");
            System.out.println("5. Sort by priority");
            System.out.println("6. Edit task");
            System.out.println("7. Delete task");
            System.out.println("8. Save & Exit");

            choice = TaskOrganizer.readInt(scanner, "Choose option: ");

            switch (choice) {

                case 1:
                    addTaskMenu(scanner);
                    break;

                case 2:
                    TaskOrganizer.showAllTasks();
                    break;

                case 3:
                    int index = TaskOrganizer.readInt(scanner, "Task number: ") - 1;
                    TaskOrganizer.markTaskAsDone(index);
                    break;

                case 4:
                    scanner.nextLine();
                    System.out.print("Category: ");
                    String category = scanner.nextLine();
                    TaskOrganizer.filterByCategory(category);
                    break;

                case 5:
                    TaskOrganizer.sortByPriority();
                    break;

                case 6:
                    editTaskMenu(scanner);
                    break;

                case 7:
                    int delIndex = TaskOrganizer.readInt(scanner, "Task number: ") - 1;
                    TaskOrganizer.deleteTask(delIndex);
                    break;

                case 8:
                    TaskOrganizer.saveToFile();
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option.");
            }

        } while (choice != 8);
    }

    // ---------------- ADD TASK MENU ----------------
    private static void addTaskMenu(Scanner scanner) {

        scanner.nextLine();

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Description: ");
        String description = scanner.nextLine();

        int priority = TaskOrganizer.readInt(scanner, "Priority (1-5): ");
        scanner.nextLine();

        System.out.print("Category: ");
        String category = scanner.nextLine();

        System.out.print("Due date: ");
        String dueDate = scanner.nextLine();

        System.out.print("Creation date: ");
        String creationDate = scanner.nextLine();

        int estimatedTime = TaskOrganizer.readInt(scanner, "Estimated time (hours): ");
        scanner.nextLine();

        System.out.print("Tags: ");
        String tags = scanner.nextLine();

        System.out.print("Assigned to: ");
        String assignedTo = scanner.nextLine();

        Task task = new Task(
                name,
                description,
                priority,
                category,
                dueDate,
                creationDate,
                estimatedTime,
                tags,
                assignedTo
        );

        TaskOrganizer.addTask(task);
        System.out.println("Task added.");
    }

    // ---------------- EDIT TASK MENU ----------------
    private static void editTaskMenu(Scanner scanner) {

        int taskIndex = TaskOrganizer.readInt(scanner, "Task number: ") - 1;
        scanner.nextLine();

        System.out.println("1. Edit name");
        System.out.println("2. Edit description");
        System.out.println("3. Edit category");
        System.out.println("4. Edit due date");

        int option = TaskOrganizer.readInt(scanner, "Choose field: ");
        scanner.nextLine();

        System.out.print("New value: ");
        String newValue = scanner.nextLine();

        TaskOrganizer.editTask(taskIndex, option, newValue);
    }
}
