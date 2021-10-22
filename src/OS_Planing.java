import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class OS_Planing {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> tasks = new ArrayDeque<>();

        Arrays.stream(scanner.nextLine().split(", "))
                .mapToInt(Integer::parseInt)
                .forEach(tasks::push);

        ArrayDeque<Integer> threads = new ArrayDeque<>();

        Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .forEach(threads::offer);

        int needToKillTask = Integer.parseInt(scanner.nextLine());

        while (!(needToKillTask == tasks.peek())) {
            if (threads.peek() >= tasks.peek()) {
                threads.poll();
                tasks.pop();
            }else {
                threads.poll();
            }


        }
        System.out.printf("Thread with value %d killed task %d%n", threads.peek(), needToKillTask);
        for (Integer thread : threads) {
            System.out.print(thread + " ");
        }
    }
}
