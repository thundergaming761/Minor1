import java.util.Random;

public class markov {
    public static void main(String[] args) {
        int numTosses = 100;  // Number of coin toss

        // Transition matrix: [Heads, Tails]
        double[][] transitionMatrix = {
            //H     T
            {0.47, 0.53},  // H
            {0.54, 0.46}   // T
        };

        // Initial state: Start with "Heads"
        int currentState = 0;  // 0 represents "Heads," 1 represents "Tails"

        // Simulate coin tosses
        Random random = new Random();
        for (int toss = 1; toss <= numTosses; toss++) {
            // Perform the next toss based on the transition probabilities
            double randomValue = random.nextDouble();
            if (randomValue < transitionMatrix[currentState][0]) {
                // Transition to "Heads"
                System.out.println("Toss " + toss + ": Heads");
                currentState = 0;
            } else {
                // Transition to "Tails"
                System.out.println("Toss " + toss + ": Tails");
                currentState = 1;
            }
        }
    }
}

