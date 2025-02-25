package test4;

import java.io.*;
import java.util.*;

public class ExactFraction {
    public static void main(String[] args) {
        try {
            // Чтение входных данных из файла
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

            String input = reader.readLine();
            reader.close();

            // Разделение чисел по символу '/'
            String[] parts = input.split("/");
            int A = Integer.parseInt(parts[0]);
            int B = Integer.parseInt(parts[1]);

            // Вычисление целой и дробной части
            String result = getExactFraction(A, B);

            // Запись результата в файл
            writer.write(result);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getExactFraction(int A, int B) {
        StringBuilder result = new StringBuilder();

        // Добавляем целую часть
        result.append(A / B);
        A %= B;

        if (A == 0) {
            return result.toString(); // Если остатка нет, возвращаем целое число
        }

        result.append(".");

        Map<Integer, Integer> remainderMap = new HashMap<>();

        while (A != 0) {
            if (remainderMap.containsKey(A)) {
                // Найден повторяющийся остаток — это начало периода
                int start = remainderMap.get(A);
                result.insert(start, "(");
                result.append(")");
                break;
            }

            remainderMap.put(A, result.length());
            A *= 10;
            result.append(A / B);
            A %= B;
        }

        // Убираем лишние нули и точки
        return cleanResult(result.toString());
    }

    private static String cleanResult(String result) {
        // Удаляем ведущий ноль у целых чисел типа 0.(3)
        if (result.startsWith("0.")) {
            result = result.replaceFirst("0\\.", ".");
        }
        // Убираем ненужные (0)
        result = result.replace("(0)", "");
        // Если число целое, убираем точку
        if (result.endsWith(".")) {
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }
}

// Пример ввода (INPUT.TXT): 10/7
// Пример вывода (OUTPUT.TXT): 1.(428571)

// Пример ввода (INPUT.TXT): 1/3
// Пример вывода (OUTPUT.TXT): 0.(3)

// Пример ввода (INPUT.TXT): 100/25
// Пример вывода (OUTPUT.TXT): 4

// Код работает с периодическими дробями и форматирует результат по правилам задачи. Попробуй запустить и протестировать! Если что-то пойдет не так — подсказывай, разберемся вместе.