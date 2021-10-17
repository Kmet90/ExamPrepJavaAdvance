import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class MagicBox {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> firstBoxQueue = new ArrayDeque<>();

        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .forEach(firstBoxQueue::offer);

        ArrayDeque<Integer> secondBoxStack = new ArrayDeque<>();

        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .forEach(secondBoxStack::push);

        int sum = 0;

        while (!firstBoxQueue.isEmpty() && !secondBoxStack.isEmpty()) {

            if ((firstBoxQueue.peek() + secondBoxStack.peek()) % 2 == 0) {
                sum += firstBoxQueue.poll() + secondBoxStack.pop();

            } else {
                firstBoxQueue.offer(secondBoxStack.pop());
            }
        }
        if (firstBoxQueue.isEmpty()) {
            System.out.println("First magic box is empty.");
        }
        if (secondBoxStack.isEmpty()) {
            System.out.println("Second magic box is empty.");
        }
        if (sum >= 90) {
            System.out.printf("Wow, your prey was epic! Value: %d", sum);
        } else {
            System.out.printf("Poor prey... Value: %d", sum);
        }

    }
}
