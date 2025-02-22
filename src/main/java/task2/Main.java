package task2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Укажите пути к двум файлам в аргументах программы!");
            return;
        }

        String circleFilePath = args[0];
        String pointsFilePath = args[1];

        try {
            // Считываем параметры окружности
            File circleFile = new File(circleFilePath);
            Scanner circleScanner = new Scanner(circleFile);

            double centerX = circleScanner.nextDouble();
            double centerY = circleScanner.nextDouble();
            double radius = circleScanner.nextDouble();
            circleScanner.close();

            // Считываем координаты точек
            File pointsFile = new File(pointsFilePath);
            Scanner pointsScanner = new Scanner(pointsFile);

            while (pointsScanner.hasNextDouble()) {
                double pointX = pointsScanner.nextDouble();
                double pointY = pointsScanner.nextDouble();

                // Вычисляем положение точки относительно окружности
                int position = checkPointPosition(centerX, centerY, radius, pointX, pointY);
                System.out.println(position);
            }
            pointsScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + e.getMessage());
        }
    }

    /**
     * Проверка положения точки относительно окружности
     */
    public static int checkPointPosition(double cx, double cy, double r, double px, double py) {
        double distanceSquared = Math.pow(px - cx, 2) + Math.pow(py - cy, 2);
        double radiusSquared = Math.pow(r, 2);

        if (distanceSquared == radiusSquared) {
            return 0; // Точка на окружности
        } else if (distanceSquared < radiusSquared) {
            return 1; // Точка внутри окружности
        } else {
            return 2; // Точка снаружи окружности
        }
    }
}