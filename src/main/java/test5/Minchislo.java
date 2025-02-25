package test5;

import java.io.*;
import java.util.*;

public class Minchislo {
    public static void main(String[] args) throws IOException {
        // Чтение входных данных
        BufferedReader br = new BufferedReader(new FileReader("INPUT.TXT"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        br.close();

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        // Получаем все перестановки для a и b без ведущих нулей
        List<Integer> aPerms = getValidPermutations(a);
        List<Integer> bPerms = getValidPermutations(b);

        // Проверяем сумму всех возможных перестановок
        for (int x : aPerms) {
            for (int y : bPerms) {
                if (x + y == c) {
                    // Записываем результат
                    BufferedWriter bw = new BufferedWriter(new FileWriter("OUTPUT.TXT"));
                    bw.write("YES\n" + x + " " + y);
                    bw.close();
                    return;
                }
            }
        }

        // Если не нашли подходящей пары
        BufferedWriter bw = new BufferedWriter(new FileWriter("OUTPUT.TXT"));
        bw.write("NO");
        bw.close();
    }

    // Метод для генерации всех перестановок числа, исключая ведущие нули
    private static List<Integer> getValidPermutations(int num) {
        char[] digits = String.valueOf(num).toCharArray();
        List<Integer> result = new ArrayList<>();
        permute(digits, 0, result);
        Collections.sort(result); // Сортируем, чтобы минимальное число шло первым
        return result;
    }

    // Рекурсивный метод для генерации перестановок
    private static void permute(char[] arr, int index, List<Integer> result) {
        if (index == arr.length) {
            if (arr[0] != '0') { // Исключаем числа с ведущими нулями
                result.add(Integer.parseInt(new String(arr)));
            }
            return;
        }
        Set<Character> used = new HashSet<>();
        for (int i = index; i < arr.length; i++) {
            if (used.add(arr[i])) { // Пропускаем дубликаты
                swap(arr, i, index);
                permute(arr, index + 1, result);
                swap(arr, i, index); // Возвращаем назад
            }
        }
    }

    // Метод для перестановки символов в массиве
    private static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}