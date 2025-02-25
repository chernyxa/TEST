package task1;

public class CircularArrayPath {
    public static void main(String[] args) {
        // Проверка наличия аргументов
        if (args.length != 2) {
            System.out.println("Введите два аргумента: n (размер массива) и m (длина шага)");
            return;
        }

        // Парсинг аргументов
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        // Валидация входных данных
        if (n <= 0 || m <= 0) {
            System.out.println("Оба числа должны быть положительными");
            return;
        }

        // Построение пути
        StringBuilder path = new StringBuilder();
        int current = 1;

        do {
            path.append(current);                           // Добавляем начальную точку интервала
            current = (current + m - 1) % n;               // Перемещаемся на m элементов вперёд (по модулю)
            if (current == 0) current = n;                 // Если вышли за пределы, возвращаемся к n
        } while (current != 1);                            // Останавливаемся, когда вернулись в начало

        // Вывод пути
        System.out.println(path.toString());
    }
}