package com.example.finalprojectphonebook;

import java.io.IOException;

public class PhonebookController implements TableControllerInterface{

    // Controller has a model and a view member
    private TableViewerInterface view;
    private StorageModelInterface model;

    // constructor to use the Phonebook Controller
    public PhonebookController(TableViewerInterface view, StorageModelInterface model){
        this.view = view;
        this.model = model;
        view.setController(this);
    }

    @Override
    // called whenever user clicks add entry
    public void entryAdded(String name, String phone, String pEmail, String sEmail) throws IOException {
        // checks if view's fields are filled. if not, then its invalid
        if(name.isBlank() || phone.isBlank()  || pEmail.isBlank()){
            view.setInputValid("All fields must not be blank to add entry");
        }
        // adds entry to view and model
        else {
            view.addViewEntry(model.addModelEntry(name, phone, pEmail, sEmail));
            view.clearEntries();
        }
    }

    // called whenever user clicks delete entry
    @Override
    public void entryDeleted() throws IOException {
        // deletes entry from view and model
        model.deleteModelEntry(view.deleteViewEntry());
    }

    // called whenever user edits entry
    @Override
    public void entryEdited(String input, String newValue) throws IOException {
        model.editModelEntry(input, newValue, (view.editViewEntry()));
    }

    // whenever user clicks to quit
    @Override
    public void quit() {
        view = null;
        model = null;
        System.exit(0);
    }

}
