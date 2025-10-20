import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SearchEngine {

    // This is our inverted index. It maps a word to a list of files that contain it.
    private static Map<String, List<String>> invertedIndex = new HashMap<>();

    /**
     * Main method to run the search engine.
     */
    public static void main(String[] args) {
        // 1. Specify the directory to index.
        String directoryPath = "test-files";

        // 2. Build the index from the files in the directory.
        System.out.println("Building index...");
        buildIndex(directoryPath);
        System.out.println("Index built successfully.");

        // 3. Start the command-line interface for searching.
        startSearchInterface();
    }

    /**
     * Builds the inverted index from all .txt files in a given directory.
     * @param path The path to the directory.
     */
    public static void buildIndex(String path) {
        File directory = new File(path);
        File[] files = directory.listFiles(); // Get all files in the directory

        if (files == null) {
            System.out.println("Directory not found or is empty.");
            return;
        }

        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                try {
                    // Use a Scanner to read the file word by word.
                    Scanner fileScanner = new Scanner(file);
                    while (fileScanner.hasNext()) {
                        String word = fileScanner.next().toLowerCase();
                        // Remove punctuation for better matching.
                        word = word.replaceAll("[^a-zA-Z]", "");

                        // Get the current list of files for this word.
                        List<String> fileList = invertedIndex.getOrDefault(word, new ArrayList<>());

                        // Add the current file to the list if it's not already there.
                        if (!fileList.contains(file.getName())) {
                            fileList.add(file.getName());
                        }
                        // Put the updated list back into the index.
                        invertedIndex.put(word, fileList);
                    }
                    fileScanner.close();
                } catch (FileNotFoundException e) {
                    System.out.println("Error reading file: " + file.getName());
                }
            }
        }
    }

    /**
     * Starts a command-line loop to accept search queries from the user.
     */
    public static void startSearchInterface() {
        Scanner keyboardScanner = new Scanner(System.in);

        while (true) {
            System.out.print("\nEnter search term (or 'quit' to exit): ");
            String searchTerm = keyboardScanner.nextLine().toLowerCase();

            if ("quit".equals(searchTerm)) {
                System.out.println("Exiting search engine.");
                break;
            }

            // Search the index for the term.
            search(searchTerm);
        }
        keyboardScanner.close();
    }

    /**
     * Searches the inverted index for a given term and prints the results.
     * @param searchTerm The word to search for.
     */
    public static void search(String searchTerm) {
        if (invertedIndex.containsKey(searchTerm)) {
            List<String> resultFiles = invertedIndex.get(searchTerm);
            System.out.println("Found in files: " + resultFiles);
        } else {
            System.out.println("Search term not found in any file.");
        }
    }
}