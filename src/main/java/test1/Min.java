package test1;

import java.io.*;  // Импортируем необходимые классы для работы с файлами

public class Min {
    public static void main(String[] args) {
        try {
            // Создаем объект BufferedReader для чтения из файла "INPUT.TXT"
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));

            // Читаем первую строку из файла и удаляем лишние пробелы.
            // Первая строка содержит первое натуральное число.
            String num1 = reader.readLine().trim();

            // Читаем вторую строку из файла, содержащую второе натуральное число.
            String num2 = reader.readLine().trim();

            // Закрываем reader, так как данные считаны.
            reader.close();

            // Вызываем метод mergeNumbers для объединения цифр двух чисел в наименьшее возможное число.
            String mergedNumber = mergeNumbers(num1, num2);

            // Создаем BufferedWriter для записи результата в файл "OUTPUT.TXT"
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

            // Записываем полученное число в файл
            writer.write(mergedNumber);

            // Закрываем writer, чтобы сохранить изменения в файле
            writer.close();
        } catch (IOException e) {
            // Если возникнет ошибка при работе с файлами, выводим трассировку ошибки
            e.printStackTrace();
        }
    }

    /**
     * Метод mergeNumbers объединяет цифры двух строк таким образом, чтобы полученное число было наименьшим.
     * При этом сохраняется порядок следования цифр в каждой исходной строке.
     *
     * Алгоритм:
     * Пока в обеих строках есть оставшиеся цифры, сравниваем подстроки (оставшиеся части) обеих строк.
     * Если оставшаяся часть строки a лексикографически меньше или равна оставшейся части строки b,
     * то добавляем следующую цифру из a, иначе – из b.
     * После чего, если одна из строк закончилась, добавляем оставшиеся цифры из другой строки.
     *
     * @param a первая строка с цифрами
     * @param b вторая строка с цифрами
     * @return объединенная строка, представляющая наименьшее возможное число
     */
    public static String mergeNumbers(String a, String b) {
        StringBuilder result = new StringBuilder(); // Для накопления результата
        int i = 0, j = 0;  // Индексы для строк a и b

        // Пока не исчерпаны цифры в обеих строках
        while (i < a.length() && j < b.length()) {
            // Сравниваем оставшиеся части строк (a.substring(i) и b.substring(j))
            // Лексикографическое сравнение гарантирует, что выбранная цифра ведет к минимальному числу.
            if (a.substring(i).compareTo(b.substring(j)) <= 0) {
                // Если оставшаяся часть a меньше или равна оставшейся части b, берем текущую цифру из a.
                result.append(a.charAt(i));
                i++;  // Переходим к следующей цифре в строке a.
            } else {
                // Иначе берем текущую цифру из b.
                result.append(b.charAt(j));
                j++;  // Переходим к следующей цифре в строке b.
            }
        }

        // Если в строке a остались цифры, добавляем их к результату.
        while (i < a.length()) {
            result.append(a.charAt(i));
            i++;
        }

        // Если в строке b остались цифры, добавляем их к результату.
        while (j < b.length()) {
            result.append(b.charAt(j));
            j++;
        }

        // Возвращаем сформированное число в виде строки.
        return result.toString();
    }
}