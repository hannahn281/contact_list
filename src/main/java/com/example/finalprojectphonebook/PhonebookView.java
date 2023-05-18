package com.example.finalprojectphonebook;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class PhonebookView extends Application implements TableViewerInterface{

    // makes controller a variable
    TableControllerInterface controller = null;

    //Sets TextFields for user to input data
    TextField inName = new TextField(),
            inPhone = new TextField(),
            inEmail = new TextField();
    Label inputValid = new Label();

    //creation of table
    TableView<Profile> bookData = new TableView<>();

    // creation of table columns
    TableColumn<Profile, String> nameClm = new TableColumn<>("Name");
    TableColumn<Profile, String> phoneClm = new TableColumn<>("Phone Number");
    TableColumn<Profile, String> emailClm = new TableColumn<>("Email");

    //Button creation
    Button add = new Button("Add");
    Button delete = new Button("Delete");
    Button quit = new Button("Quit");

    // setting stage and application
    @Override
    public void start(Stage stage) throws Exception {

        // set title of stage
        stage.setTitle("Contact Book");

        // sets up UI and action events for different buttons/columns
        this.setUpUI();

        // finish setting up UI
        //final setting pane UI and children nodes
        VBox root = new VBox();
        HBox entryPane = new HBox();
        HBox buttonPane = new HBox();
        buttonPane.getChildren().addAll(add, delete, quit);
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setSpacing(20);
        entryPane.getChildren().addAll(inName, inPhone, inEmail);
        entryPane.setAlignment(Pos.CENTER);
        root.getChildren().addAll(bookData, entryPane, buttonPane, inputValid);
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);
        Scene rootScene = new Scene(root, 1000, 650);

        rootScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("PhoneStyle.css")).toExternalForm());
        root.getStyleClass().add("root");

        nameClm.getStyleClass().add("column-title");
        phoneClm.getStyleClass().add("column-title");
        emailClm.getStyleClass().add("column-title");
        bookData.getStyleClass().add("table-view");
        add.getStyleClass().add("button");
        delete.getStyleClass().add("button");
        quit.getStyleClass().add("button");
        inputValid.getStyleClass().add("input-label");
        inName.getStyleClass().add("text-field");
        inPhone.getStyleClass().add("text-field");
        inEmail.getStyleClass().add("text-field");



        // sets controller and model
        PhonebookModel model = new PhonebookModel();
        PhonebookController controller = new PhonebookController(this, model);

        //Launches App
        stage.setScene(rootScene);
        stage.show();
    }

    // starts up the app
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void setUpUI(){

        //////* SETTING UP UI ELEMENTS *//////

        // setting prompt text for columns
        inName.setPromptText("Name");
        inPhone.setPromptText("Phone Number");
        inEmail.setPromptText("Email");

        // set table data
        bookData.setPlaceholder(new Label("No Contacts :("));
        bookData.setEditable(true);

        //creates editable name column
        nameClm.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameClm.setCellFactory(TextFieldTableCell.forTableColumn());

        // creates editable phone number column
        phoneClm.setCellValueFactory(new PropertyValueFactory<>("phone"));
        phoneClm.setCellFactory(TextFieldTableCell.forTableColumn());

        // creates editable email column
        emailClm.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailClm.setCellFactory(TextFieldTableCell.forTableColumn());

        //adds columns to bookData table
        bookData.getColumns().addAll(nameClm, phoneClm, emailClm);
        bookData.setMaxSize(500,500);
        nameClm.setPrefWidth(166.66);
        phoneClm.setPrefWidth(166.66);
        emailClm.setPrefWidth(166.66);

        //sets selection model and mode
        TableView.TableViewSelectionModel<Profile> selectionModel= bookData.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);


        //////* ADDING ACTION EVENTS *//////

        // adding action events to the add button
        add.setOnAction((actionEvent -> {
            controller.entryAdded(inName.getText(), inPhone.getText(), inEmail.getText());
        }));

        // adding action events to the delete button
        delete.setOnAction((actionEvent -> {
            controller.entryDeleted();
        }));

        // adding action events to the quit button
        quit.setOnAction((actionEvent -> {
            controller.quit();
        }));

        nameClm.setOnEditCommit(event -> {
            String newValue = event.getNewValue();
            controller.entryEdited("name", newValue);
        });

        phoneClm.setOnEditCommit(event -> {
            String newValue = event.getNewValue();
            controller.entryEdited("phone", newValue);
        });

        emailClm.setOnEditCommit(event -> {
            String newValue = event.getNewValue();
            controller.entryEdited("email", newValue);
        });
    }


    @Override
    public void setController(TableControllerInterface controller) {
        this.controller = controller;
    }


    @Override
    public void clearEntries(){
        inName.clear();
        inPhone.clear();
        inEmail.clear();
        inName.requestFocus();
    }


    @Override
    public void addViewEntry(Profile profile){
        bookData.getItems().add(profile);
    }


    @Override
    public int deleteViewEntry(){
        int row = bookData.getSelectionModel().getSelectedIndex();
        if(row >= 0) {
            bookData.getItems().remove(row);
            bookData.getSelectionModel().clearSelection();
            inName.requestFocus();
        }
        return row;
    }

    @Override
    public int editViewEntry(){
        int row = bookData.getSelectionModel().getSelectedIndex();
        return row;
    }


    @Override
    public void setInputValid(String input){
        inputValid.setText(input);
    }
}