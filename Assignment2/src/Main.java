

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


public class Main {


    public static void main(String[] args) {
        // Creating the instance with labeling for the frame and each button.
        JFrame myFrame = new JFrame("Assignment #2");
        JButton button1Display = new JButton("Display the text");
        JButton button2Search = new JButton("Search");
        JButton button7Exit = new JButton("Exit Program");
        JLabel labelChoose = new JLabel("Please select an option.");

        // Specifying the size of the frame.
        myFrame.setSize(400, 250);

        // Ensuring that absolute positioning is used for the buttons.
        myFrame.setLayout(null);
        myFrame.setVisible(true);

        // Setting the absolute positions for all the buttons.
        button1Display.setBounds(100, 50, 200, 40);
        button2Search.setBounds(100, 100, 200, 40);
        button7Exit.setBounds(100, 150, 200, 40);
        labelChoose.setBounds(100, 10, 200, 40);

        // Adding each button to the frame.
        myFrame.add(button1Display);
        myFrame.add(button2Search);
        myFrame.add(button7Exit);
        myFrame.add(labelChoose);

        // Close program if X in the top right corner of the frame is clicked on.
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final String states = "Alabama Alaska Arizona Arkansas California Colorado Connecticut Delaware Florida Georgia Hawaii Idaho Illinois Indiana Iowa Kansas Kentucky Louisiana Maine Maryland Massachusetts Michigan Minnesota Mississippi Missouri Montana Nebraska Nevada New Hampshire New Jersey New Mexico New York North Carolina North Dakota Ohio Oklahoma Oregon Pennsylvania Rhode Island South Carolina South Dakota Tennessee Texas Utah Vermont Virginia Washington West Virginia Wisconsin Wyoming";

        button1Display.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Make these into array list
                String[] printingStates = states.split(" ");
                List<String> printingStates2 = new ArrayList<>();
                // String Array that splits on separate criteria.
                for(int i = 0; i < printingStates.length; i++)
                {
                    // Check the string for states that contain two words. Any states that contain two words include New, North, South and West.
                    if (printingStates[i].equals("New") || printingStates[i].equals("North") || printingStates[i].equals("South") || printingStates[i].equals("West")){
                    printingStates2.add(printingStates[i] + " " + printingStates[i + 1]);
                    i++;
                    }
                    else{
                        printingStates2.add(printingStates[i]);
                    }

                }

                JTextArea textArea = new JTextArea();
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(100, 100));
                textArea.setWrapStyleWord(true);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

                for(String state: printingStates2)
                    textArea.append(state + "\n");

                JOptionPane.showMessageDialog(null, scrollPane, "List of states", JOptionPane.PLAIN_MESSAGE);

            }
        });

        button2Search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                static String getClipboard() {
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    try {
                        // Note that it is still possible for another app to modify clipboard
                        // between "if" and "return", so this could still fail:
                        if (clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)) {
                            return (String) clipboard.getData(DataFlavor.stringFlavor);
                        }
                    } catch (Exception eew) {
                        // or rethrow as your own exception type
                    }
                    return "";
                }
                    String searchString = JOptionPane.showInputDialog("Enter letters to search on? ");


                    JOptionPane.showMessageDialog(null, "Copy and Paste is not supported.");


                List<Character> searchComparison = new ArrayList<>();
                List<Integer> matchOutput = new ArrayList<>();

                JTextArea textArea = new JTextArea();
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(100, 100));
                textArea.setWrapStyleWord(true);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

                int i = 0;
                // Ensuring that we go through the entirety of states string.
                while (i < states.length()) {
                    // The loop must end since we won't be able to have a match since the characters left are too short to compare.
                    if ( (states.length() - i) < searchString.length())
                    {
                        break;
                    }
                    loadList(searchComparison, states, i, searchString.length());
                    i = checkMismatch(searchComparison, searchString, searchString.length(), i, matchOutput);
                }

                textArea.append(matchOutput + "\n");
                JOptionPane.showMessageDialog(null, scrollPane, "List of indices where matches have been made.", JOptionPane.PLAIN_MESSAGE);
            }});

        // This button exits the program when clicked.
        button7Exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        // method to load the states characters into an array starting at i and ending at searchString.length(). (pass in i so starting point is correct)

    }

    public static void loadList(List<Character> searchComparison, String states, int i, int length)
    {
        // We need to start with an empty list each time.
        searchComparison.clear();
        int m = 0;

        while (m < length) {
            //add each character from state(m) to searchComparison list (m-m).
            searchComparison.add(states.charAt(m+i));
            m++;
        }
    }

    // method checkMismatch to identify if there is a mismatch with each character of searchString starting from the right.
    public static Integer checkMismatch(List<Character> searchComparison, String searchString, int searchStringLength, int i, List<Integer> matchOutput) {
        // must check from right to left
        int k = searchStringLength - 1;
        while (k >= 0) {
            // if we are at the end of the string of characters


            //check if each character does not match
            if (searchComparison.get(k) != searchString.charAt(k))
            {
                // If we are at the end of the searchString and there is a non match then we need to move past the bad character.
                if (k>=0){
                    // Bad character has been found we must check if we need to move past it or if we have a match with it can we move to it.
                    i = checkMatchSubstring( k, searchComparison, searchString, i);
                    return i;
                }
                return i + searchStringLength;
            }

            // check if each character matches each other.
            if (searchComparison.get(k) == searchString.charAt(k)) {
                //k must be decremented so
                k--;
            }

        }
            // add index of first character where match started to searchComparison list.
            matchOutput.add(i);
            return i + searchString.length();

    }
    // The purpose is to see if the bad character matches any character in the substring or not.
    public static Integer checkMatchSubstring (int k, List<Character> searchComparison, String searchString, int i)
        {
            int j = k-1;
            // for each element of searchString we need to check against...
            while(j>=0)
            {
             if (searchComparison.get(k) == searchString.charAt(j)){
                // move i to where the bad character matches the character in searchString.
                 return i + (k-j);
                }
                j--;
            }
            // If the bad character does not exist in the substring then move past the mismatch.
            //move past bad character

                return i + (k+1);


         }



}


