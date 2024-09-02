import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // The string of state names is final because it should not be altered
        final String states = "Alabama Alaska Arizona Arkansas California Colorado Connecticut Delaware Florida Georgia Hawaii Idaho Illinois Indiana Iowa Kansas Kentucky Louisiana Maine Maryland Massachusetts Michigan Minnesota Mississippi Missouri Montana Nebraska Nevada New Hampshire New Jersey New Mexico New York North Carolina North Dakota Ohio Oklahoma Oregon Pennsylvania Rhode Island South Carolina South Dakota Tennessee Texas Utah Vermont Virginia Washington West Virginia Wisconsin Wyoming";

        // Must ensure the menu always comes up after option 1 or 2 is selected
        while (true) {

            System.out.println("Please enter 1, 2, or 3 to select an option below.");
            System.out.println("1 Display the text");
            System.out.println("2 Search");
            System.out.println("3 Exit program");

            try {

                Scanner scanner = new Scanner(System.in);
                int choice = scanner.nextInt();

                // Display the text of state names
                if (choice == 1) {
                    System.out.println(states);
                }

                // Search for a pattern in the text
                if (choice == 2) {
                    System.out.println("Please enter the string of characters you would like to search for:");
                    scanner.nextLine(); // Consume newline
                    String searchInput = scanner.nextLine();

                    // Convert both strings to lower case for case-insensitive search
                    boyerMooreAlgorithm(searchInput.toLowerCase(), states.toLowerCase());
                }

                // Exit the program
                if (choice == 3) {
                    System.out.println("Ending Program.");
                    System.exit(0);
                }

                // Handle invalid menu selection
                if (choice > 3 || choice < 1) {
                    System.out.println("Please only enter either 1, 2, or 3.");
                }
                // Handle any characters entered other than whole numbers
            } catch (Exception e) {
                System.out.println("Only whole numbers are accepted for menu selection.");
            }
        }
    }

    // Perform Boyer-Moore algorithm using the bad character rule
    // Summary: Compare the searchInputs characters with the states string from right to left
    // If a match is made then continue to the next character
    // If a mismatch is found then a bad character has been identified and must be evaluated by badCharacterComparison
    // The results of badCharacterComparison will move i to the bad character or past it
    public static void boyerMooreAlgorithm(String searchInput, String states) {
        List<Integer> matchingIndices = new ArrayList<>();
        int i = 0;
        // Ensure we have enough characters to compare as we move through state's string
        while (i <= states.length() - searchInput.length()) {
            int shift = searchInput.length() - 1;

            // Compare the pattern and the text from right to left
            while (shift >= 0 && searchInput.charAt(shift) == states.charAt(i + shift)) {
                shift--;
            }
            // If a match has been found, record it and move past
            if (shift < 0) {
                matchingIndices.add(i);
                i += searchInput.length();

            } else {
                // Bad character rule: shift the pattern so that the bad character aligns with the match in the pattern
                // If a match is not found move past the bad character
                i += Math.max(1, shift - badCharacterComparison(searchInput, states.charAt(i + shift)));
            }
        }
        // Print out results
        printMatchingIndicesResults(matchingIndices);
    }

    // Check if the bad character matches with any characters in pattern
    public static int badCharacterComparison(String pattern, char badChar) {
        for (int i = pattern.length() - 2; i >= 0; i--) {
            if (pattern.charAt(i) == badChar) {
                // If a match is found, move i to that position
                return i;
            }
        }
        // If the character is not matched, return -1
        return -1;
    }

    // Print out the results of the search
    public static void printMatchingIndicesResults(List<Integer> matchingIndices) {
        if (matchingIndices.isEmpty()) {
            System.out.println("No matches were found.");
        } else {
            System.out.println("The string searched matches at the following indices:");
            System.out.println(matchingIndices);
        }
    }
}
