# Command-Line Search Engine

A simple but powerful command-line tool built in Java to index text files within a directory and perform high-speed keyword searches. This project was built to demonstrate core computer science fundamentals, particularly the use of efficient data structures for information retrieval.

## Features
- Indexes all `.txt` files in a specified directory.
- Builds an inverted index using a HashMap for fast lookups.
- Provides an interactive command-line interface for searching.
- Achieves O(1) time complexity for search queries.

## How to Run
1. Clone the repository.
2. Compile the `SearchEngine.java` file: `javac src/SearchEngine.java`
3. Run the compiled code from the project's root directory: `java src.SearchEngine`
4. The program will index the files in the `test-files` directory and prompt for a search term.