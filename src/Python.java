import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class Python {
    static int pythonLength = 1;
    static char[][] matrix;
    static int food = 0;
    static int rowPython;
    static int collPython;
    static int size;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        size = Integer.parseInt(scanner.nextLine());

        ArrayDeque<String> commands = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(", ")).forEach(commands::offer);

        matrix = readMatrix(size, scanner);
        findPythonPositionAndFoodCount(matrix);


        while (!commands.isEmpty()) {
            pythonMove(rowPython, collPython, commands.pop());
            isOut(size, rowPython, collPython);
            if (matrix[rowPython][collPython] == 'f') {
                food--;
                pythonLength++;
            } else if (matrix[rowPython][collPython] == 'e') {
                pythonLength = -1;
            }
            if (food == 0 || pythonLength == -1) {
                break;
            }
        }
        if (food == 0) {
            System.out.println("You win! Final python length is " + pythonLength);
        } else if (food > 0 && pythonLength != -1) {
            System.out.println("You lose! There is still " + food + " food to be eaten.");
        } else {
            System.out.println("You lose! Killed by an enemy!");
        }
    }

    private static void isOut(int size, int r, int c) {
        if (r < 0) {
            r = size - 1;
        } else if (r > size - 1) {
            r = 0;
        } else if (c < 0) {
            c = size - 1;
        } else if (c > size - 1) {
            c = 0;
        }
        rowPython = r;
        collPython = c;
    }

    private static void pythonMove(int r, int c, String command) {
        switch (command) {
            case "left":
                c--;
                break;
            case "right":
                c++;
                break;
            case "up":
                r--;
                break;
            case "down":
                r++;
                break;
        }
        rowPython = r;
        collPython = c;
    }

    private static void findPythonPositionAndFoodCount(char[][] matrix) {
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix.length; c++) {
                if (matrix[r][c] == 's') {
                    rowPython = r;
                    collPython = c;
                }
                if (matrix[r][c] == 'f') {
                    food++;
                }
            }
        }
    }

    private static char[][] readMatrix(int size, Scanner scanner) {
        char[][] m = new char[size][];
        for (int r = 0; r < size; r++) {
            m[r] = scanner.nextLine().replace(" ", "").toCharArray();
        }
        return m;
    }
}
