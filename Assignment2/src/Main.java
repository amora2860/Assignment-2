

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

                int choice = scanner.nextInt();
                // If the user enters 1 then display states string.
                if(choice == 1) {
                    System.out.println(states);
                }

                // If the user enters 2 then ask for the searching string and begin comparing.
                if(choice == 2) {
                    System.out.println("Please enter the string of characters you would like to search for?");
                    try {
                        // Wait for user to into data.
                        scanner = new Scanner(System.in);

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
                else {
                    System.out.println("Please enter either 1, 2 or 3.");
                }
            }
            catch (Exception e){
                System.out.println("Only numbers are accepted for a menu selection.");
            }

        }

    }
    // This method begins the process of
    public static void boyerMooreAlgorithm(String searchInput, String states, int i)
    {
        // A list is needed to hold the characters being compared to.
        List<Character> patternSearchComparisonList = new ArrayList<>();

        //We need a list to contain all the matching indices.
        List<Integer> matchingIndices = new ArrayList<>();


        // Ensuring that we go through all the characters in states string.
        while (i < states.length()) {

            // Ending the loop when the searching substring is too short in length to compare too.
            if ( (states.length() - i) < searchInput.length())
            {
                break;
            }
            // Load into the patternSearchComparisonList characters from states
            loadNewCharactersIntoComparisonList(patternSearchComparisonList, states, i, searchInput.length());

            i = characterComparison(patternSearchComparisonList, searchInput, i, matchingIndices);

        }

        // Print out any results during the algorithm's operation.
        printMatchingIndicesResults(matchingIndices);

    }

    public static void loadNewCharactersIntoComparisonList(List<Character> patternSearchComparisonList, String states, int i, int searchInputLength)
    {
        // We need to start with an empty list each time this method is called.
        patternSearchComparisonList.clear();

        int m = 0;

        while (m < searchInputLength) {
            //add each character from state string starting at i and moving to the length of .
            patternSearchComparisonList.add(states.charAt(m+i));
            m++;
        }
    }

    // method checkMismatch to identify if there is a mismatch with each character of searchString starting from the right.
    public static Integer characterComparison(List<Character> searchComparison, String searchInput, int i, List<Integer> matchingIndices) {

        int k = searchInput.length() - 1;
        // We must compare the characters from right to left
        while (k >= 0) {

            //check if each character does not match
            if (searchComparison.get(k) != searchInput.charAt(k))
            {
                // If we are at the end of the searchString and there is a non-match then we need to move past the bad character.
                // Bad character has been found we must check if we need to move past it or if we have a match with it can we move to it.
                i = checkBadcCharacterMatchesAnySubstringCharacters( k, searchComparison, searchInput, i);
                return i;
            }

            // check if each character matches each other.
            if (searchComparison.get(k) == searchInput.charAt(k)) {
                //k must be decrease so we can end the loop
                k--;
            }

        }
            // add index of first character where match started to searchComparison list.
            matchingIndices.add(i);
            return i + searchInput.length();

    }
    // The purpose is to see if the bad character matches any character in the substring or not.
    public static Integer checkBadcCharacterMatchesAnySubstringCharacters (int k, List<Character> searchComparison, String searchInput, int i)
        {
            int j = k-1;
            // for each element of searchString we need to check against...
            while(j>=0)
            {
             if (searchComparison.get(k) == searchInput.charAt(j)){
                // move i to where the bad character matches the character in searchString.
                 return i + (k-j);
                }
                j--;
            }
            // If the bad character does not exist in the substring then move past the mismatch.
            return i + (k+1);

         }

    public static void printMatchingIndicesResults(List<Integer> matchingIndices){

        if (matchingIndices.isEmpty() ) {
            System.out.println("No matches were found.");
        }

        else
        {
            System.out.println("The string searched on was found on the following indices.");
            System.out.println(matchingIndices);
        }

    }

}


