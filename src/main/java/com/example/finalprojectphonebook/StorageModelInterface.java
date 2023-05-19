package com.example.finalprojectphonebook;

import java.io.IOException;

public interface StorageModelInterface {
    // adds entry
    Profile addModelEntry(String entry1, String entry2, String entry3, String entry4, Boolean onLoad) throws IOException;
    // deletes entry
    void deleteModelEntry(int index) throws IOException;

    // edits entry
    void editModelEntry(String input, String newValue, int index) throws IOException;

    // updates persistent storage
    void updateStorage() throws IOException;
}
