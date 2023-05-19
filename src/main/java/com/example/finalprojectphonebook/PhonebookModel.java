package com.example.finalprojectphonebook;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PhonebookModel implements StorageModelInterface{
    //stores inputted data as Profile
    List<Profile> book = new ArrayList<>();

    @Override
    public Profile addModelEntry(String name, String phone, String pEmail, String sEmail) {
        //adds new profile when validated
        book.add(new Profile(name, phone, pEmail, sEmail));
        for(Profile profile: book){
            if(!profile.getEntered()){
                profile.setEntered(true);
                return profile;
            }
        }
        System.out.println("its sad!!\n");
        return null;
    }

    @Override
    public void deleteModelEntry(int index) {
        book.remove(index);
    }

    // edits entry
    @Override
    public void editModelEntry(String input, String newValue, int index){
        if (input == "name"){
            book.get(index).setName(newValue);
        }
        else if (input == "phone"){
            book.get(index).setPhone(newValue);
        }
        else if(input == "primary"){
            book.get(index).setPrimary(newValue);
        }else{
            book.get(index).setSecondary(newValue);
        }
    }

    // updates persistent storage (idk how to do this btw)
    @Override
    public void updateStorage() {
        //in = new FileInputStream("input.txt");
        //out = new FileOutputStream("output.txt");
    }
}