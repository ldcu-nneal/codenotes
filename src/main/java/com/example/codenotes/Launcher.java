package com.example.codenotes;

/**
 * Launcher class for Code Notes application.
 * This class is used as the entry point for the application,
 * especially when building with Maven or creating executable JARs.
 */
public class Launcher {

    /**
     * Main method that launches the JavaFX application.
     * This approach helps avoid module system issues when packaging the application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // Delegate to the main method in CodeNotesApp
        CodeNotesApp.main(args);
    }
}