import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class Bomb {
    static char[][] matrix;
    static int bombs = 0;
    static int playerRow;
    static int playerColl;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());

        ArrayDeque<String> move = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(",")).forEach(move::offer);

        matrix = readMatrix(size, scanner);

        sapperPositionAndBombs(matrix);

        while (!move.isEmpty()) {
            moveSapper(move.poll(), playerRow, playerColl);

            sapperOutOfField(size, playerRow, playerColl);

            if (matrix[playerRow][playerColl] == 'e') {
                System.out.printf("END! %d bombs left on the field", bombs);
                return;
            }
            if (matrix[playerRow][playerColl] == 'B') {
                System.out.println("You found a bomb!");
                matrix[playerRow][playerColl] = '+';
                bombs--;
                if (bombs == 0) {
                    System.out.println("Congratulations! You found all bombs!");
                    return;
                }
            }
        }
        if (move.isEmpty()) {
            System.out.printf("%d bombs left on the field. Sapper position: (%d,%d)",
                    bombs, playerRow, playerColl);
        }
    }

    private static void sapperOutOfField(int size, int r, int c) {
        if (r < 0) {
            playerRow = 0;
        } else if (r > size - 1) {
            playerRow = size - 1;
        } else if (c < 0) {
            playerColl = 0;
        } else if (c > size - 1) {
            playerColl = size - 1;
        }
    }

    private static void moveSapper(String poll, int r, int c) {
        switch (poll) {
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
        playerRow = r;
        playerColl = c;
    }

    private static void sapperPositionAndBombs(char[][] matrix) {
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                if (matrix[r][c] == 's') {
                    playerRow = r;
                    playerColl = c;
                }
                if (matrix[r][c] == 'B') {
                    bombs++;
                }
            }
        }
    }

    private static char[][] readMatrix(int size, Scanner scanner) {
        char[][] matrix = new char[size][];
        for (int r = 0; r < size; r++) {
            matrix[r] = scanner.nextLine().replace(" ", "").toCharArray();
        }
        return matrix;
    }
}
