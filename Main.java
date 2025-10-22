import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> tasks = new ArrayList<>();

        System.out.println("=== Приложение: Список дел ===");
        System.out.println("Команды: add, list, delete, exit");

        while (true) {
            System.out.print("> ");
            String command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "add":
                    System.out.print("Введите задачу: ");
                    String task = scanner.nextLine();
                    tasks.add(task);
                    System.out.println("✅ Задача добавлена!");
                    break;

                case "list":
                    if (tasks.isEmpty()) {
                        System.out.println("Список задач пуст.");
                    } else {
                        System.out.println("Ваши задачи:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + ". " + tasks.get(i));
                        }
                    }
                    break;

                case "delete":
                    System.out.print("Введите номер задачи для удаления: ");
                    try {
                        int index = Integer.parseInt(scanner.nextLine()) - 1;
                        if (index >= 0 && index < tasks.size()) {
                            System.out.println("🗑 Удалено: " + tasks.remove(index));
                        } else {
                            System.out.println("❌ Неверный номер задачи.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Введите число!");
                    }
                    break;

                case "exit":
                    System.out.println("👋 До свидания!");
                    return;

                default:
                    System.out.println("Неизвестная команда. Попробуйте: add, list, delete, exit");
            }
        }
    }
}



