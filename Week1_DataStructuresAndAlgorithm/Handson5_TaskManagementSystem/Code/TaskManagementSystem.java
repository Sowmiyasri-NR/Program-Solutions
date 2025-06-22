import java.util.Scanner;

class Task {
    int taskId;
    String taskName;
    String status;

    Task(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }

    void display() {
        System.out.println("Task ID: " + taskId + ", Name: " + taskName + ", Status: " + status);
    }
}

class TaskNode {
    Task task;
    TaskNode next;

    TaskNode(Task task) {
        this.task = task;
        this.next = null;
    }
}

public class TaskManagementSystem {
    private TaskNode head;

    public void addTask(Task task) {
        TaskNode newNode = new TaskNode(task);
        if (head == null) {
            head = newNode;
        } else {
            TaskNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        System.out.println("Task added successfully.\n");
    }

    public void searchTask(int id) {
        TaskNode current = head;
        while (current != null) {
            if (current.task.taskId == id) {
                current.task.display();
                return;
            }
            current = current.next;
        }
        System.out.println("Task not found.\n");
    }

    public void traverseTasks() {
        if (head == null) {
            System.out.println("No tasks available.\n");
            return;
        }
        TaskNode current = head;
        while (current != null) {
            current.task.display();
            current = current.next;
        }
    }

    public void deleteTask(int id) {
        if (head == null) {
            System.out.println("No tasks to delete.\n");
            return;
        }

        if (head.task.taskId == id) {
            head = head.next;
            System.out.println("Task deleted.\n");
            return;
        }

        TaskNode current = head;
        while (current.next != null && current.next.task.taskId != id) {
            current = current.next;
        }

        if (current.next == null) {
            System.out.println("Task not found.\n");
        } else {
            current.next = current.next.next;
            System.out.println("Task deleted.\n");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManagementSystem system = new TaskManagementSystem();

        while (true) {
            System.out.println("--- Task Management Menu ---");
            System.out.println("1. Add Task");
            System.out.println("2. Search Task");
            System.out.println("3. Delete Task");
            System.out.println("4. Display All Tasks");
            System.out.println("5. Exit");
            System.out.print("Choose an option (1-5): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Task ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Task Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Task Status: ");
                    String status = scanner.nextLine();
                    system.addTask(new Task(id, name, status));
                    break;

                case 2:
                    System.out.print("Enter Task ID to search: ");
                    int searchId = scanner.nextInt();
                    scanner.nextLine();
                    system.searchTask(searchId);
                    break;

                case 3:
                    System.out.print("Enter Task ID to delete: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();
                    system.deleteTask(deleteId);
                    break;

                case 4:
                    System.out.println("\n--- All Tasks ---");
                    system.traverseTasks();
                    break;

                case 5:
                    System.out.println("Exiting Task Management System.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        }
    }
}
