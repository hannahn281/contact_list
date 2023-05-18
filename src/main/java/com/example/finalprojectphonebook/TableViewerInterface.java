package com.example.finalprojectphonebook;

public interface TableViewerInterface {

    public void setUpUI();

    void setController(TableControllerInterface controller);

    public void clearEntries();

    public void addViewEntry(Profile profile);

    public int deleteViewEntry();

    public int editViewEntry();

    public void setInputValid(String input);
}