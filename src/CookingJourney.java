import java.util.Scanner;

public class CookingJourney {
    static char[][] matrix;
    static int rowS;
    static int collS;
    static int countP = 0;
    static int rowP1;
    static int collP1;
    static int rowP2;
    static int collP2;
    static int money = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());

        matrix = readMatrix(size, scanner);

        findPillarsAndSPosition(matrix);

        String command = scanner.nextLine();
        move(command, rowS, collS);

        while (outOfShop(size, rowS, collS)) {

            if (matrix[rowS][collS] == '-') {
                command = scanner.nextLine();
                move(command, rowS, collS);

            } else if (matrix[rowS][collS] == 'P') {
                if (matrix[rowS][collS] == matrix[rowP1][collP1]) {
                    rowS = rowP2;
                    collS = collP2;
                } else {
                    rowS = rowP1;
                    collS = collP1;
                }
                matrix[rowP1][collP1] = '-';
                matrix[rowP2][collP2] = '-';
                command = scanner.nextLine();
                move(command, rowS, collS);
            } else {
                money += Character.getNumericValue(matrix[rowS][collS]);
                if (money > 50) {
                    matrix[rowS][collS] = 'S';
                    break;
                }
                matrix[rowS][collS] = '-';

                command = scanner.nextLine();
                move(command, rowS, collS);
            }
        }

        if (!outOfShop(size, rowS, collS)) {
            System.out.println("Bad news! You are out of the pastry shop.");
        } else {
            System.out.println("Good news! You succeeded in collecting enough money!");
        }
        System.out.println("Money: " + money);

        printMatrix(matrix);

    }

    private static void move(String command, int r, int c) {
        switch (command) {
            case "up":
                r -= 1;
                break;
            case "down":
                r += 1;
                break;
            case "right":
                c += 1;
                break;
            case "left":
                c -= 1;
                break;
        }
        rowS = r;
        collS = c;
    }

    private static boolean outOfShop(int size, int r, int c) {
        return r >= 0 && r < size && c >= 0 && c < size;
    }

    private static void findPillarsAndSPosition(char[][] matrix) {
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                if (matrix[r][c] == 'S') {
                    rowS = r;
                    collS = c;
                    matrix[r][c] = '-';
                }
                if (matrix[r][c] == 'P' && countP == 0) {
                    rowP1 = r;
                    collP1 = c;
                    countP++;
                } else {
                    rowP2 = r;
                    collP2 = c;
                }
            }
        }
    }

    private static void printMatrix(char[][] matrix) {
        for (char[] chars : matrix) {
            for (int c = 0; c < matrix.length; c++) {
                System.out.print(chars[c]);
            }
            System.out.println();
        }
    }

    private static char[][] readMatrix(int size, Scanner scanner) {
        char[][] matrix = new char[size][];
        for (int r = 0; r < size; r++) {
            matrix[r] = scanner.nextLine().toCharArray();
        }
        return matrix;
    }
}
