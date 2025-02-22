package task4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class MinMovesToEqual {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java MinMovesToEqual <file_path>");
            return;
        }

        try {
            // Читаем файл
            List<String> lines = Files.readAllLines(Paths.get(args[0]));

            if (lines.isEmpty()) {
                System.out.println("File is empty!");
                return;
            }

            // Преобразуем строку чисел в массив (разделитель — пробел)
            int[] nums = Arrays.stream(lines.get(0).trim().split("\\s+")) // Разбиваем по пробелу
                    .mapToInt(Integer::parseInt)
                    .toArray();

            if (nums.length == 0) {
                System.out.println("No valid numbers in the file!");
                return;
            }

            // Вычисляем минимальное количество ходов
            int minMoves = calculateMinMoves(nums);

            // Выводим результат
            System.out.println(minMoves);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format in file: " + e.getMessage());
        }
    }

    private static int calculateMinMoves(int[] nums) {
        Arrays.sort(nums);
        int median = nums[nums.length / 2];
        return Arrays.stream(nums).map(n -> Math.abs(n - median)).sum();
    }
}