package task1;

import java.util.Scanner;

public class CircularArrayPath {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = scanner.nextInt();
        System.out.print("Enter m: ");
        int m = scanner.nextInt();
        scanner.close();

        StringBuilder path = new StringBuilder();
        int start = 0;

        boolean[] visited = new boolean[n];

        while (!visited[start]) {
            path.append(start + 1);
            visited[start] = true;
            start = (start + m) % n;
        }

        System.out.println(path.toString());
    }
}