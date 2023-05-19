package com.example.finalprojectphonebook;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class PhonebookModel implements StorageModelInterface{
    //stores inputted data as Profile
    List<Profile> book = new ArrayList<>();

    @Override
    public Profile addModelEntry(String name, String phone, String pEmail, String sEmail) throws IOException{
        //adds new profile when validated
        book.add(new Profile(name, phone, pEmail, sEmail));
        for(Profile profile: book){
            if(!profile.getEntered()){
                profile.setEntered(true);
                updateStorage();
                return profile;
            }
        }
        updateStorage();
        return null;
    }

    @Override
    public void deleteModelEntry(int index) throws IOException{
        book.remove(index);
        updateStorage();
    }

    // edits entry
    @Override
    public void editModelEntry(String input, String newValue, int index) throws IOException{
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
        updateStorage();
    }

    // updates persistent storage (idk how to do this btw)
    @Override
    public void updateStorage() throws IOException {

        // sets up directory and file
        File dir = new File("src/main/resources/com/example/finalprojectphonebook");
        File file = new File(dir, "ProfileData.txt");

        file.setReadable(true); //read
        file.setWritable(true); //write
        file.setExecutable(true); //execute

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))){
            for (Profile profile : book) {
                writer.write(profile.getName() + "," + profile.getPhone() + "," + profile.getPrimary() + "," + profile.getSecondary() + "\n");
            }
            writer.close();
        }
        System.out.println("Contents of: "+ file.getAbsolutePath());
        System.out.println(Files.readString(file.toPath()));
    }

    public void loadStorage() throws IOException{
        // finds up directory and file
        File dir = new File("src/main/resources/com/example/finalprojectphonebook");
        File file = new File(dir, "ProfileData.txt");
        System.out.println("Contents of woohoo: "+ file.getAbsolutePath());
        System.out.println(Files.readString(file.toPath()));

        file.setReadable(true); //read
        file.setExecutable(true); //execute

        try {
            Reader reader = new BufferedReader(new FileReader(file));
            String line = ((BufferedReader) reader).readLine();

            while (file.canRead()) {
                while (line != null) {
                    String[] properties = (line.split(","));

                    book.add(new Profile(properties[0], properties[1], properties[2], properties[3]));

                    // read next line
                    line = ((BufferedReader) reader).readLine();
                }
            }
            reader.close();
        }

        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}