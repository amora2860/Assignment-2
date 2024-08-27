

import java.awt.event.*;
import java.awt.Dimension;
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

        // Setting the absolute positions for all of the buttons.
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
                // String Array that splits on seperate criteria.
                for(int i = 0; i < printingStates.length; i++)
                // Condition one if the word is New.
                {   // Add New and the next word into that array together.
                    switch (printingStates[i]) {
                        case "New" -> {

                            printingStates2.add(printingStates[i] + " " + printingStates[i + 1]);
                            i++;
                        }


                        // Condition two if the word is North.
                        case "North" -> {
                            // Add North and the next word into that array together.
                            printingStates2.add(printingStates[i] + " " + printingStates[i + 1]);
                            i++;
                        }

                        // Condition three if the word is South.
                        case "South" -> {
                            // Add South and the next word into that array together.
                            printingStates2.add(printingStates[i] + " " + printingStates[i + 1]);
                            i++;
                        }

                        // Condition four if the word is West.
                        case "West" -> {
                            // Add West and the next word into that array together.
                            printingStates2.add(printingStates[i] + " " + printingStates[i + 1]);
                            i++;
                        }
                        default -> printingStates2.add(printingStates[i]);
                    }



                }
                JTextArea textArea = new JTextArea();
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(100, 100));
                textArea.setWrapStyleWord(true);
                scrollPane.setVerticalScrollBarPolicy(
                        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);



                for(String state: printingStates2)
                    textArea.append(state + "\n");


                JOptionPane.showMessageDialog(null, scrollPane, "List of states", JOptionPane.PLAIN_MESSAGE);

            }
        });

        button2Search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchString = JOptionPane.showInputDialog("Enter letters to search on? ");


                List<String> searchStringIterabe = new ArrayList<>();
                List<Integer> searchComparison = new ArrayList<>();

                //take searchString and perform the bad- character search on it.
                // Use the length of searchString to dictate how long each comparison will take place on states.
                searchStringIterabe.add(searchString);

                JTextArea textArea = new JTextArea();
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(100, 100));
                textArea.setWrapStyleWord(true);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

                int i = 0;
                // Ensuring that we go through all of states string.
                while (i < states.length())
                {
                    loadList(searchStringIterabe,states,i,searchString.length());


                    // method checkMismatch to identify if there is a mismatch with each character of searchString starting from the right.
                    // public static checkMismatch(String[] searchComparison, String searchString, int searchString.length())
                    // must check front right to left
                    // k == searchString.length() -1;
                    // while (k >= 0)
                    // check if each character does not match each other. ie bad character
                    // if (searchComparison(k) != searchString(k))
                    // call checkMatchSubstring(k)
                    // break;

                    // check if each character matches each other.
                    // if(searchComparison(k) == searchString(k))
                    // if (k == 0)
                    // add index of first character where match started to searchComparison list.
                    // searchComparison.add(i)
                    // break;
                    // k must be incremented so the while loop completes.
                    // k--

                }
                // method checkMatchSubstring it's purpose is to see iffhe bad character matches any character in the substring or not.
                //   If the bad character does exist in substring then move i to where the bad char matches the character in searchString.
                // call method moveToMatch
                // for each element of searchString
                // if bad Character== searchString(i)
                // move i to where the bad character matches the character in searchString.

                //   If the bad character does not exist in the substring then move past the mismatch.
                // call method movePastMismatch
                //move past bad character
                // i = i + (k+2);
                textArea.append(searchComparison + "\n");
                JOptionPane.showMessageDialog(null, scrollPane, "List of states", JOptionPane.PLAIN_MESSAGE);
            }});

        // This button exits the program when clicked.
        button7Exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }
    // method to load the states characters into an array starting at i and ending at searchString.length(). (pass in i so starting point is correct)
    public static List<String> loadList(List<String> output, String states, int i, int length) {
        output.clear();
        int m = i;

        while (m < length) {
            //add each character from state(m) to searchComparison list (m-m).
            output.add(Character.toString(states.charAt(m)));
            m++;
        }
        return output;
    }


}


