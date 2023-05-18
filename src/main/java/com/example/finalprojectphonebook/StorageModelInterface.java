package com.example.finalprojectphonebook;

public interface StorageModelInterface {
    // adds entry
    Profile addModelEntry(String entry1, String entry2, String entry3);
    // deletes entry
    void deleteModelEntry(int index);

    // edits entry
    void editModelEntry(String input, String newValue, int index);

    // updates persistent storage
    void updateStorage();
}
