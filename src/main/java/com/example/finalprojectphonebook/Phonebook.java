package com.example.finalprojectphonebook;

import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Phonebook extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Contact Book");

        //stores inputted data as Profile
        List<Profile> book = new ArrayList<>();

        //Sets TextFields for user to input data
        TextField inName = new TextField(),
                inPhone = new TextField(),
                inEmail = new TextField();
        inName.setPromptText("Name");
        inPhone.setPromptText("Phone Number");
        inEmail.setPromptText("Email");
        Label inputValid = new Label();

        //Button creation
        Button add = new Button("Add");
        Button delete = new Button("Delete");

        //creation of table
        TableView<Profile> bookData = new TableView<>();
        bookData.setPlaceholder(new Label("No Contacts :("));
        bookData.setEditable(true);

        //creates columns
        //columns are editable on double click
        TableColumn<Profile, String> nameClm = new TableColumn<>("Name");
        nameClm.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameClm.setCellFactory(TextFieldTableCell.forTableColumn());
        nameClm.setOnEditCommit(event -> {
            Profile profile = event.getRowValue();
            profile.setName(event.getNewValue());
        });

        TableColumn<Profile, String> phoneClm = new TableColumn<>("Phone Number");
        phoneClm.setCellValueFactory(new PropertyValueFactory<>("phone"));
        phoneClm.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneClm.setOnEditCommit(event -> {
            Profile profile = event.getRowValue();
            profile.setPhone(event.getNewValue());
        });

        TableColumn<Profile, String> emailClm = new TableColumn<>("Email");
        emailClm.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailClm.setCellFactory(TextFieldTableCell.forTableColumn());
        emailClm.setOnEditCommit(event -> {
            Profile profile = event.getRowValue();
            profile.setEmail(event.getNewValue());
        });
        //adds columns to bookData table
        bookData.getColumns().addAll(nameClm, phoneClm, emailClm);
        bookData.setMaxSize(500,500);
        nameClm.setPrefWidth(166.66);
        phoneClm.setPrefWidth(166.66);
        emailClm.setPrefWidth(166.66);

        //sets selection model and mode
        TableView.TableViewSelectionModel<Profile> selectionModel= bookData.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);


        //setting pane UI and children nodes
        VBox root = new VBox();
        HBox entryPane = new HBox();
        HBox buttonPane = new HBox();
        buttonPane.getChildren().addAll(add, delete);
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setSpacing(20);
        entryPane.getChildren().addAll(inName, inPhone, inEmail);
        entryPane.setAlignment(Pos.CENTER);
        root.getChildren().addAll(bookData, entryPane, buttonPane, inputValid);
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);
        Scene rootScene = new Scene(root, 1000, 1000);


        //Launches App
        stage.setScene(rootScene);
        stage.show();

        //deletes row and profile from book
        delete.setOnAction(actionEvent -> {
            int row = bookData.getSelectionModel().getSelectedIndex();
            if(row >= 0){
                bookData.getItems().remove(row);
                book.remove(row);
                bookData.getSelectionModel().clearSelection();
                inName.requestFocus();
            }
        });
        //adds new profile when validated
        add.setOnAction((actionEvent -> {
            if(inName.getText().isBlank() || inPhone.getText().isBlank() || inEmail.getText().isBlank()){
                inputValid.setText("All fields must not be blank to add entry");
            }else{
            book.add(new Profile(inName.getText(), inPhone.getText(), inEmail.getText()));
            for(Profile profile: book){
                if(!profile.getEntered()){
                    bookData.getItems().add(profile);
                    profile.setEntered(true);
                }
            }
            inName.clear();
            inPhone.clear();
            inEmail.clear();
            inName.requestFocus();
        }}));


    }

    public static void main(String[] args) {
        launch();
    }
}