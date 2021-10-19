import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CookingJourney1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());
        char[][] matrix = inputMatrix(scanner, size);
        int[] start = getStartPosition(matrix);
        matrix[start[0]][start[1]] = '-';
        List<int[]> pillars = new ArrayList<>();
        getPillars(matrix, pillars);
        int money = 0;
        if (pillars.size() == 2) {
            int[] pillar1 = pillars.get(0);
            int[] pillar2 = pillars.get(1);
            String command = scanner.nextLine();
            move(command, start);
            while (isInBounds(size, start[0], start[1])) {
                if (Arrays.equals(start, pillar1)) {
                    matrix[pillar1[0]][pillar1[1]] = '-';
                    start = pillar2;
                    pillar1[0] = pillar1[1] = size + 1;
                    pillar2 = pillar1;
                    matrix[start[0]][start[1]] = '-';
                } else if (Arrays.equals(start, pillar2)) {
                    matrix[pillar2[0]][pillar2[1]] = '-';
                    start = pillar1;
                    pillar1[0] = pillar1[1] = size + 1;
                    pillar2 = pillar1;
                    matrix[start[0]][start[1]] = '-';
                } else if (matrix[start[0]][start[1]] > 47 && matrix[start[0]][start[1]] < 58) {
                    money += matrix[start[0]][start[1]] - 48;
                    matrix[start[0]][start[1]] = '-';
                    if (money >= 50) {
                        matrix[start[0]][start[1]] = 'S';
                        break;
                    }
                }
                command = scanner.nextLine();
                move(command, start);
            }
        } else {
            String command = scanner.nextLine();
            move(command, start);
            while (isInBounds(size, start[0], start[1])) {
                if (matrix[start[0]][start[1]] > 47 && matrix[start[0]][start[1]] < 58) {
                    money += matrix[start[0]][start[1]] - 48;
                    matrix[start[0]][start[1]] = '-';
                    if (money >= 50) {
                        matrix[start[0]][start[1]] = 'S';
                        break;
                    }
                }
                command = scanner.nextLine();
                move(command, start);
            }
        }
        if (!isInBounds(size, start[0], start[1])) System.out.println("Bad news! You are out of the pastry shop.");
        else System.out.println("Good news! You succeeded in collecting enough money!");
        System.out.printf("Money: %d%n", money);
        printMatrix(matrix);
    }

    private static void printMatrix(char[][] matrix) {
        for (char[] chars : matrix) {
            for (int col = 0; col < matrix.length; col++) {
                System.out.printf("%c", chars[col]);
            }
            System.out.println();
        }
    }

    private static void move(String command, int[] start) {
        switch (command) {
            case "up":
                start[0]--;
                break;
            case "down":
                start[0]++;
                break;
            case "right":
                start[1]++;
                break;
            case "left":
                start[1]--;
                break;
        }
    }

    private static boolean isInBounds(int size, int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size;
    }

    private static int[] getStartPosition(char[][] matrix) {
        int[] start = new int[2];
        for (int row = 0; row < matrix.length; row++)
            for (int col = 0; col < matrix.length; col++) {
                if (matrix[row][col] == 'S') {
                    start[0] = row;
                    start[1] = col;
                    break;
                }
            }
        return start;
    }

    private static void getPillars(char[][] matrix, List<int[]> pillars) {
        for (int row = 0; row < matrix.length; row++)
            for (int col = 0; col < matrix.length; col++) {
                if (matrix[row][col] == 'O') {
                    int[] pillar = {row, col};
                    pillars.add(pillar);
                }
            }
    }

    private static char[][] inputMatrix(Scanner scanner, int size) {
        char[][] matrix = new char[size][size];
        for (int row = 0; row < size; row++) {
            String[] line = scanner.nextLine().split("");
            for (int col = 0; col < size; col++) {
                matrix[row][col] = line[col].charAt(0);
            }
        }
        return matrix;
    }
}
