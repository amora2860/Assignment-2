
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) {

        // This string of characters is not meant to be changed hence the final declaration.
        final String states = "Alabama Alaska Arizona Arkansas California Colorado Connecticut Delaware Florida Georgia Hawaii Idaho Illinois Indiana Iowa Kansas Kentucky Louisiana Maine Maryland Massachusetts Michigan Minnesota Mississippi Missouri Montana Nebraska Nevada New Hampshire New Jersey New Mexico New York North Carolina North Dakota Ohio Oklahoma Oregon Pennsylvania Rhode Island South Carolina South Dakota Tennessee Texas Utah Vermont Virginia Washington West Virginia Wisconsin Wyoming";

        // This variable keeps track of where we are in states string.
        int i = 0;

        // Must ensure the menu always comes up after option 1 or 2 is selected.
        while (true) {

            System.out.println("Please enter 1, 2 or 3 to select an option below.");
            System.out.println("1 Display the text");
            System.out.println("2 Search");
            System.out.println("3 Exit program");

            try {
                // Wait for user to into data.
                Scanner scanner = new Scanner(System.in);

                // Take in the information entered.
                int choice = scanner.nextInt();

                // If the user enters 1 then display states string.
                if(choice == 1) {
                    System.out.println(states);
                }

                // If the user enters 2 then ask for the searching string and begin comparing.
                if(choice == 2) {
                    System.out.println("Please enter the string of characters you would like to search for.");
                    try {
                        // Wait for user to into data.
                        scanner = new Scanner(System.in);

                        // Take in the information entered.
                        String searchInput = scanner.nextLine();

                        boyerMooreAlgorithm(searchInput, states, i);
                    }
                    catch (Exception e){
                        System.out.println("Please enter a valid string of character.");
                    }
                }
                // If the user enters the number 3 end the program.
                if(choice == 3) {
                    System.out.println("Ending Program.");
                    System.exit(0);
                }
                // If the user inputs any other number they need to be informed what the options are.
                if (choice > 3 || choice < 1 ) {
                    System.out.println("Please only enter either 1, 2 or 3.");
                }
            }
            catch (Exception e){
                System.out.println("Only whole numbers are accepted for a menu selection.");
            }

        }

    }
    // This method begins the process of comparing the searchInput and patternSearchComparisonList.
    public static void boyerMooreAlgorithm(String searchInput, String states, int i)
    {
        // A list is needed to hold the characters being compared to.
        List<Character> patternSearchComparisonList = new ArrayList<>();

        // We need a list to contain all the matching indices.
        List<Integer> matchingIndices = new ArrayList<>();


        // Ensuring that we go through all the characters in states string.
        while (i < states.length()) {

            // We must end the loop when what is left of states string is smaller than searchInput.
            if ( (states.length() - i) < searchInput.length())
            {
                break;
            }
            // Load characters from states string into the patternSearchComparisonList for comparison.
            loadNewCharactersIntoComparisonList(patternSearchComparisonList, states, i, searchInput.length());

            // Compare both strings for a bad character.
            i = characterComparison(patternSearchComparisonList, searchInput, i, matchingIndices);

        }

        // Print out any results during the algorithm's operation.
        printMatchingIndicesResults(matchingIndices);

    }

    public static void loadNewCharactersIntoComparisonList(List<Character> patternSearchComparisonList, String states, int i, int searchInputLength)
    {
        // We need to start with an empty list each time this method is called.
        patternSearchComparisonList.clear();

        // m must always start at 0.
        int m = 0;

        // Add each character from state string starting at i and moving to the right until we loaded the exact length of searchInput.
        while (m < searchInputLength) {

            patternSearchComparisonList.add(states.charAt(m+i));
            m++;
        }
    }

    // We are checking for character matches on both strings from right to left.
    public static Integer characterComparison(List<Character> searchComparison, String searchInput, int i, List<Integer> matchingIndices) {

        // k is the local value that will keep track of what index we are at with when comparing characters.
        int k = searchInput.length() - 1;

        // We must compare the characters from right to left.
        while (k >= 0) {

            // Check if each character does not match which means a bad character was found.
            if (searchComparison.get(k) != searchInput.charAt(k))
            {

                // Since a bad character has been found we must check if we need to move past it or if we have a match within it, and we can move the searchComparison to it.
                i = checkMatchesWithOtherSubstringCharacters( k, searchComparison, searchInput, i);

                // Return i so that loadNewCharactersIntoComparisonList will move further down states string.
                return i;
            }

            // Check if each character matches each other.
            if (searchComparison.get(k) == searchInput.charAt(k)) {
                // k must be decreased, so we can end the loop.
                k--;
            }

        }
            // Since we have made it out of the loop we know that both searchInput and searchComparison match.
            matchingIndices.add(i);

            // Move past the match to see if any other matches are in the states string.
            return i + searchInput.length();

    }
    // We need to check if the bad character matches any character in the substring after where the bad character was found.
    public static Integer checkMatchesWithOtherSubstringCharacters (int k, List<Character> searchComparison, String searchInput, int i)
        {
            // j allows for a way to keep track of the index values less than k as we go through the loop.
            int j = k-1;

            // We are checking for a match at each character to the left of searchComparison at k.
            while(j>=0)
            {
             if (searchComparison.get(k) == searchInput.charAt(j)){
                // Move i to where the bad character matches the character in searchString and end the loop.
                 return i + (k-j);
                }

             // If there is no match then continue to the next character to see if a match exists.
                j--;
            }
            // If the bad character does not match any characters in the substring then move past the bad character.
            return i + (k+1);

         }

    public static void printMatchingIndicesResults(List<Integer> matchingIndices){

        // If no matching indices have been found inform the user.
        if (matchingIndices.isEmpty() ) {

            System.out.println("No matches were found.");
        }

        // Print out all the matching indices.
        else
        {
            System.out.println("The string searched matches at the following indices.");
            System.out.println(matchingIndices);
        }

    }

}


