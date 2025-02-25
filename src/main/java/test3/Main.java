package test3;

import java.io.*;  // Импортируем все необходимые классы для работы с вводом/выводом (работа с файлами).

public class Main {  // Главный класс программы, который содержит метод main.

    public static void main(String[] args) {  // Точка входа в программу. Метод main, где начинается выполнение.

        try {  // Начинаем блок обработки исключений. Если будет ошибка при чтении файла, программа не завершится аварийно.

            // Чтение входных данных из файла input.txt
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            // Создаем объект BufferedReader для чтения данных из файла "input.txt". FileReader читает данные,
            // а BufferedReader добавляет буферизацию для ускоренной работы с текстовыми данными.

            // Чтение первой строки файла (координаты цели)
            String[] targetPosition = reader.readLine().split(" ");
            // Считываем строку с координатами цели и разбиваем ее на две части по пробелу. Например, "1 1" будет разделено на ["1", "1"].

            // Чтение второй строки файла (скорость цели)
            String[] targetVelocity = reader.readLine().split(" ");
            // Считываем строку с компонентами скорости цели и разбиваем на части по пробелу, например, "1 1" — на ["1", "1"].

            // Чтение третьей строки файла (параметры ракеты)
            String[] rocketData = reader.readLine().split(" ");
            // Считываем строку с параметрами ракеты (макс. скорость, время и требуемое расстояние), делим на массив строк.

            // Преобразование данных в целые числа
            int x0 = Integer.parseInt(targetPosition[0]);
            int y0 = Integer.parseInt(targetPosition[1]);
            int Vx = Integer.parseInt(targetVelocity[0]);
            int Vy = Integer.parseInt(targetVelocity[1]);
            int V = Integer.parseInt(rocketData[0]);
            int t = Integer.parseInt(rocketData[1]);
            int d = Integer.parseInt(rocketData[2]);
            // Преобразуем строковые значения в целые числа с помощью Integer.parseInt() для дальнейших расчетов.
            // x0, y0 — начальные координаты цели, Vx, Vy — скорость цели, V — максимальная скорость ракеты,
            // t — время, d — требуемое расстояние от ракеты до цели.

            // Вычисление координат цели через время t
            int targetX = x0 + Vx * t;
            int targetY = y0 + Vy * t;
            // Вычисляем новые координаты цели через время t. Она движется с постоянной скоростью по прямой.

            // Вычисление расстояния между ракетой (начало координат) и целью в момент времени t
            double distance = Math.sqrt(targetX * targetX + targetY * targetY);
            // Рассчитываем расстояние между началом координат (местоположение ракеты) и новой позицией цели.
            // Используем формулу Евклидова расстояния: √(x² + y²).

            // Проверка, может ли ракета попасть в точку на расстоянии d
            if (distance <= V * t && distance == d) {
                // Если ракета может двигаться на расстояние, меньшее или равное максимальной дистанции (V * t), и
                // расстояние до цели равно заданному d, то выводим "YES".
                System.out.println("YES");
            } else {
                // В противном случае, если ракета не может попасть в эту точку, выводим "NO".
                System.out.println("NO");
            }

            reader.close();  // Закрываем BufferedReader после завершения чтения файла.
        } catch (IOException e) {  // Обработка ошибок ввода/вывода.
            e.printStackTrace();  // Если возникла ошибка при работе с файлом, выводим трассировку стека.
        }
    }
}

/*
import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Чтение входных данных из файла
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));

            // Чтение данных
            String[] targetPosition = reader.readLine().split(" ");
            String[] targetVelocity = reader.readLine().split(" ");
            String[] rocketData = reader.readLine().split(" ");

            int x0 = Integer.parseInt(targetPosition[0]);
            int y0 = Integer.parseInt(targetPosition[1]);
            int Vx = Integer.parseInt(targetVelocity[0]);
            int Vy = Integer.parseInt(targetVelocity[1]);
            int V = Integer.parseInt(rocketData[0]);
            int t = Integer.parseInt(rocketData[1]);
            int d = Integer.parseInt(rocketData[2]);

            // Позиция цели через время t
            int xt = x0 + Vx * t;
            int yt = y0 + Vy * t;

            // Расстояние от РК-2000 до цели через время t
            double distanceToTarget = Math.sqrt(xt * xt + yt * yt);

            // Расстояние, которое РК-2000 может пройти за время t
            double maxDistanceRK2000 = V * t;

            // Запись результата в output.txt
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

            // Проверяем, может ли РК-2000 оказаться на расстоянии d от цели
            if (Math.abs(distanceToTarget - d) <= maxDistanceRK2000) {
                writer.write("YES");
            } else {
                writer.write("NO");
            }

            // Закрытие файлов
            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
*/