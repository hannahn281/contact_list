package com.example.finalprojectphonebook;

import java.io.IOException;

public interface TableControllerInterface {

    // when a string is entered, sends to model
    void entryAdded(String entry1, String entry2, String entry3, String entry4) throws IOException;

    // when a string is entered, sends to model
    void entryDeleted() throws IOException;

    // when a string is entered, sends to model
    void entryEdited(String input, String newValue) throws IOException;

    // when user wants to quit
    void quit();
}