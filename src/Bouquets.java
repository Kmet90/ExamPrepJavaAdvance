import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Bouquets {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> tulips = new ArrayDeque<>();

        Arrays.stream(scanner.nextLine().split(", "))
                .map(Integer::parseInt)
                .forEach(tulips::push);

        ArrayDeque<Integer> daffodils = Arrays.stream(scanner.nextLine().split(", "))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        int sumBouquets = 0;
        int storeFlowers = 0;

        while (!tulips.isEmpty() && !daffodils.isEmpty()) {
            int numbersTulips = tulips.pop();
            int numbersDaffodils = daffodils.poll();
            int bouquet = numbersTulips + numbersDaffodils;

            if (bouquet == 15) {
                sumBouquets++;
            } else if (bouquet < 15) {
                storeFlowers += bouquet;
            } else {
                while (bouquet > 15) {
                    numbersTulips -= 2;
                    bouquet = numbersTulips + numbersDaffodils;
                }
                if (bouquet == 15) {
                    sumBouquets++;
                } else {
                    storeFlowers += bouquet;
                }
            }
        }
        int moreBouquets = storeFlowers / 15;
        sumBouquets = sumBouquets + moreBouquets;

        if (sumBouquets >= 5) {
            System.out.printf("You made it! You go to the competition with %d bouquets!", sumBouquets);
        } else {
            System.out.printf("You failed... You need more %d bouquets.", 5 - sumBouquets);
        }

    }
}
