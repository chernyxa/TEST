package task4;

import java.io.*;
import java.util.*;

public class MinMovesToEqual {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Передайте имя файла в качестве аргумента!");
            return;
        }

        String fileName = args[0];

        try {

            List<Integer> nums = readNumbersFromFile(fileName);

            if (nums.isEmpty()) {
                System.out.println("Файл пустой или содержит некорректные данные.");
                return;
            }


            int minMoves = calculateMinMoves(nums);
            System.out.println(minMoves);

        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }


    private static List<Integer> readNumbersFromFile(String fileName) throws IOException {
        List<Integer> nums = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;


        while ((line = reader.readLine()) != null) {
            try {
                // Преобразуем строку в число и добавляем в список
                nums.add(Integer.parseInt(line.trim()));
            } catch (NumberFormatException e) {
                System.out.println("Пропущено некорректное значение: " + line);
            }
        }
        reader.close();
        return nums;
    }


    private static int calculateMinMoves(List<Integer> nums) {

        Collections.sort(nums);


        int median = nums.get(nums.size() / 2);


        int moves = 0;
        for (int num : nums) {
            moves += Math.abs(num - median);
        }
        return moves;
    }
}