import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class NotebookStore {
    public static void main(String[] args) {
        Set<Notebook> notebooks = getNotebooks();

        // Запрашиваем критерии фильтрации
        Map<String, Object> filters = new HashMap<>();


        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();

            int choice = scanner.nextInt();
            scanner.nextLine(); // очищаем буфер после nextInt

            execChoice(scanner, choice, filters);
            

            // Фильтруем ноутбуки
            Set<Notebook> filteredNotebooks = filterNotebooks(notebooks, filters);

            // Выводим отфильтрованные ноутбуки
            System.out.println("");
            System.out.println("Отфильтрованные ноутбуки:");
            showSet(filteredNotebooks);
        }
    }

    private static void showSet(Set<Notebook> notebooks)
    {
        if (notebooks.isEmpty()) {
            System.out.println("Ноутбуков, соотвествующих критерию не найдено\n");
        }
        for (Notebook notebook : notebooks) {
            StringBuilder str = new StringBuilder();
            str.append("Бренд: ");
            str.append(notebook.getBrand());
            str.append("; ОЗУ: ");
            str.append(notebook.getRam());
            str.append("; ЖД: ");
            str.append(notebook.getStorage());
            str.append("; ОС: ");
            str.append(notebook.getOs());
            str.append("; Цвет: ");
            str.append(notebook.getColor());
            System.out.println(str);
        }
        System.out.println("");
    }

    private static void execChoice(Scanner scanner, int choice, Map<String, Object> filters)
    {
        switch (choice) {
            case 0:
                System.out.println("До свидания!");
                System.exit(0);
                break;
            case 1:
                System.out.print("Введите минимальный объем ОЗУ: ");
                int minRam = scanner.nextInt();
                filters.put("ram", minRam);
                break;
            case 2:
                System.out.print("Введите минимальный объем ЖД: ");
                int minStorage = scanner.nextInt();
                filters.put("storage", minStorage);
                break;
            case 3:
                System.out.print("Введите требуемую операционную систему: ");
                String os = scanner.nextLine();
                filters.put("os", os);
                break;
            case 4:
                System.out.print("Введите требуемый цвет: ");
                String color = scanner.nextLine();
                filters.put("color", color);
                break;
            default:
                System.out.println("Некорректный выбор.");
                return;
        }
    }

    private static void printMenu()
    {
        System.out.println("Введите цифру, соответствующую необходимому критерию:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");
        System.out.println("0 - Выход");
    }

    private static Set<Notebook> getNotebooks()
    {
        Set<Notebook> notebooks = new HashSet<>();
        notebooks.add(new Notebook("HP", 8, 256, "Windows", "Silver"));
        notebooks.add(new Notebook("Dell", 16, 512, "Linux", "Black"));
        notebooks.add(new Notebook("Lenovo", 8, 512, "Windows", "Gray"));
        notebooks.add(new Notebook("Apple", 16, 512, "macOS", "Silver"));
        notebooks.add(new Notebook("Acer", 12, 256, "Windows", "Blue"));

        return notebooks;
    }

    private static Set<Notebook> filterNotebooks(Set<Notebook> notebooks, Map<String, Object> filters) {
        Set<Notebook> result = new HashSet<>();

        for (Notebook notebook : notebooks) {
            boolean match = true;

            for (Map.Entry<String, Object> entry : filters.entrySet()) {
                switch (entry.getKey()) {
                    case "ram":
                        match = notebook.getRam() >= (int) entry.getValue();
                        break;
                    case "storage":
                        match = notebook.getStorage() >= (int) entry.getValue();
                        break;
                    case "os":
                        match = notebook.getOs().equalsIgnoreCase((String) entry.getValue());
                        break;
                    case "color":
                        match = notebook.getColor().equalsIgnoreCase((String) entry.getValue());
                        break;
                }

                if (!match) {
                    break;
                }
            }

            if (match) {
                result.add(notebook);
            }
        }

        return result;
    }
}
