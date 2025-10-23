package src;
// import java.io.*;
import java.io.*;
import java.util.*;
// 12341
public class Main {
    private static final String FILE_NAME = "tasks.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = loadTasks();

        System.out.println("=== Приложение: Список дел ===");
        System.out.println("Команды: add, list, delete, done, clear, exit");

        while (true) {
            System.out.print("> ");
            if (!scanner.hasNextLine()) {
                System.out.println("\n⚠️ Ввод недоступен. Завершение программы.");
                break;
            }

            String command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "add":
                    System.out.print("Введите задачу: ");
                    if (!scanner.hasNextLine()) break;
                    String text = scanner.nextLine();
                    tasks.add(new Task(text, false));
                    saveTasks(tasks);
                    System.out.println("✅ Задача добавлена!");
                    break;

                case "list":
                    if (tasks.isEmpty()) {
                        System.out.println("Список задач пуст.");
                    } else {
                        System.out.println("Ваши задачи:");
                        for (int i = 0; i < tasks.size(); i++) {
                            Task t = tasks.get(i);
                            System.out.printf("%d. [%s] %s%n", i + 1, t.isDone() ? "x" : " ", t.getText());
                        }
                    }
                    break;

                case "done":
                    System.out.print("Введите номер выполненной задачи: ");
                    if (!scanner.hasNextLine()) break;
                    try {
                        int index = Integer.parseInt(scanner.nextLine()) - 1;
                        if (index >= 0 && index < tasks.size()) {
                            tasks.get(index).setDone(true);
                            saveTasks(tasks);
                            System.out.println("🎯 Задача отмечена как выполненная!");
                        } else {
                            System.out.println("❌ Неверный номер задачи.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Введите число!");
                    }
                    break;

                case "delete":
                    System.out.print("Введите номер задачи для удаления: ");
                    if (!scanner.hasNextLine()) break;
                    try {
                        int index = Integer.parseInt(scanner.nextLine()) - 1;
                        if (index >= 0 && index < tasks.size()) {
                            System.out.println("🗑 Удалено: " + tasks.remove(index).getText());
                            saveTasks(tasks);
                        } else {
                            System.out.println("❌ Неверный номер задачи.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Введите число!");
                    }
                    break;

                case "clear":
                    tasks.clear();
                    saveTasks(tasks);
                    System.out.println("🧹 Все задачи удалены!");
                    break;

                case "exit":
                    System.out.println("👋 До свидания!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Неизвестная команда. Попробуйте: add, list, delete, done, clear, exit");
            }
        }
    }


    private static void saveTasks(List<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Task t : tasks) {
                writer.write((t.isDone() ? "1" : "0") + ";" + t.getText());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("⚠️ Ошибка при сохранении задач: " + e.getMessage());
        }
    }

    private static List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return tasks;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";", 2);
                if (parts.length == 2) {
                    boolean done = parts[0].equals("1");
                    tasks.add(new Task(parts[1], done));
                }
            }
        } catch (IOException e) {
            System.out.println("⚠️ Ошибка при загрузке задач: " + e.getMessage());
        }
        return tasks;
    }
}
