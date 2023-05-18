package com.example.finalprojectphonebook;

public interface TableControllerInterface {

    // when a string is entered, sends to model
    void entryAdded(String entry1, String entry2, String entry3);

    // when a string is entered, sends to model
    void entryDeleted();

    // when a string is entered, sends to model
    void entryEdited(String input, String newValue);

    // when user wants to quit
    void quit();
}