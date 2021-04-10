import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * This object is used to compute the most common first character of any given
 * web page.
 */
public class MostCommonFirstCharacter {

    // Variables

    // Our selected url
    private String selectedURL;

    // Constructors

    /**
     * Used to compute the most common word of any url based text document
     * 
     * @param selectedURL Text document url
     */
    public MostCommonFirstCharacter(String selectedURL) {
        this.selectedURL = selectedURL;
    }

    /**
     * With no URL we will compute the originally given URL
     */
    public MostCommonFirstCharacter() {
        selectedURL = "https://www.gutenberg.org/cache/epub/29364/pg29364.txt";
    }

    // Methods

    /**
     * Return the most common first character in the given document
     * 
     * @return Most Common First Character, as a string so we can also parse errors
     */
    public String getCharacter() {

        // Create an empty has map for the
        Map<Character, Integer> characterFrequencies = new HashMap<>();

        // Used to parse out any errors in a try catch
        try {
            // Get all the characters and frequencies in the url and return their map
            characterFrequencies = importAndCleanLines();

        } catch (Exception e) {
            // If this fails we return the given letter
            return "Failed to import from URL, see below for the error specification: \n \n " + e.toString();
        }

        // Check we actually have values in our frequency map
        if (characterFrequencies.size() == 0) {
            return "No characters found in document please check that you inputted a valid URL";
        }

        // Create empty starting values for the iterator
        String mostCommonCharacter = "";
        int mostCommonCharacterValue = 0;

        // Now we check through all the characters freuquency to find the greatest value
        // (or values) using an iterator with a type cast
        Iterator<Map.Entry<Character, Integer>> charValues = characterFrequencies.entrySet().iterator();
        while (charValues.hasNext()) {

            // Get next entry, we don't need to check this cast as we already cast it
            Map.Entry<Character, Integer> checkingEntry = charValues.next();

            // Check if the value is greater than the current highest
            if (checkingEntry.getValue() > mostCommonCharacterValue) {

                // If so we update the current highest value and character
                mostCommonCharacter = checkingEntry.getKey().toString();
                mostCommonCharacterValue = (int) checkingEntry.getValue();

            } else if (checkingEntry.getValue() == mostCommonCharacterValue) {

                // If it is equal we simply add the character to the output string, not using a
                // string builder as this is such a rare occurance
                mostCommonCharacter += " and " + checkingEntry.getKey().toString();
            }
        }

        return mostCommonCharacter;
    }

    /**
     * We clean the lines of text from any grammer and punctuation then return a
     * hash map of their first characters (Key) and it's frequency (Value) using
     * characters to save memory
     * 
     * @return Has Map of first characters with their frequency
     * @throws IOException When file is incorrect or stream fails to find a letter
     */
    private Map<Character, Integer> importAndCleanLines() throws IOException {

        // Create our output hash map
        Map<Character, Integer> outputStrings = new HashMap<>();

        // Create URL from our link
        URL url = new URL(this.selectedURL);

        // Open this as an input stream
        InputStream inputStream = url.openStream();

        // Create a buffered reader in a try-with-resources statement
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String currentLine;
            // Loop through the lines and output
            // their words first characters too our hashmap
            while ((currentLine = reader.readLine()) != null) {

                // We now need to split up the line
                String[] textInLine = currentLine.split(" ");

                // Then we add the first letter in the word too our dictionary
                for (String rawWord : textInLine) {

                    // First we replace anything that isn't a letter with nothing (removing them)
                    rawWord = rawWord.replaceAll("[^a-zA-Z]", "");

                    // Make sure this isn't a blank space once cleaned
                    if (rawWord.length() > 0) {

                        // Set our key as our first character in lower case
                        char key = Character.toLowerCase(rawWord.charAt(0));

                        // Check the key is a character (although we cleaned it this is in case there is
                        // a corrupt character)
                        if (Character.isLetter(key)) {

                            if (outputStrings.containsKey(key)) {
                                // If the character is in our dictionary add one too the frequency
                                outputStrings.put(key, outputStrings.get(key) + 1);
                            } else {
                                // If not create a new key and add this character
                                outputStrings.put(key, 1);
                            }
                        } else {
                            // Throw errors for corrupt character that wasn't cleaned
                            throw new IOException(
                                    "Invalid Character in text document please check for a corrupt file as the character, "
                                            + key + " was parsed even though it isn't a letter");
                        }
                    }
                }
            }
        }

        // Return the created hash map
        return outputStrings;
    }
}
