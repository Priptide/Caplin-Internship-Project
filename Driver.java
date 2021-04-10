/**
 * Driver class used to run the program
 */
public class Driver {
    public static void main(String[] args) {

        // Create a new object using our given URL
        MostCommonFirstCharacter mostCommonWord = new MostCommonFirstCharacter(
                "https://www.gutenberg.org/cache/epub/29364/pg29364.txt");

        // Output the character found or give the error message if found
        System.out.println("Most common first character: \n" + mostCommonWord.getCharacter());
    }
}
