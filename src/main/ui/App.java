package ui;

import model.User;
import model.Users;
import persistence.JsonReader;

import javax.swing.*;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;

// App Class contains main method and connects GUI to model

public class App {

    private static final String JSON_STORE = "data/users.json";
    private Users users;
    private User currentUser;
    private final Scanner input;
    private final JsonReader jsonReader;

    //// CONSTRUCTORS ////

    // EFFECTS: instantiates a new App
    public App() {
        this.input = new Scanner(System.in);
        this.users = new Users();
        this.jsonReader = new JsonReader(JSON_STORE);
    }

    //// GETTERS/SETTERS ////

    // MODIFIES: this
    // EFFECTS: sets current user
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    // EFFECTS: gets current user
    public User getCurrentUser() {
        return currentUser;
    }

    // MODIFIES: this, user
    // EFFECTS: loads a JSON if required and logs a user in if account exists otherwise creates 
    //          a new account
    public void login() {
        String name;
        int result = JOptionPane.showConfirmDialog(null, "Would you like to load your profile?",
                "Welcome!", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            loadUsers();
            name = JOptionPane.showInputDialog("Please enter your name:");
            for (User user : users.getUsers()) {
                if (user.getUserName().equals(name)) {
                    setCurrentUser(user);
                    new AppFrame("TickTickBoom", currentUser, users);
                    return;
                }
            }
            User newUser = new User(name);
            setCurrentUser(newUser);
            users.getUsers().add(newUser);
            JOptionPane.showMessageDialog(null,
                    String.format("You do not have a profile %s, so we have made you one!", name));
            addPhotoDirectory();
        } else {
            newUser();
        }
        new AppFrame("TickTickBoom", currentUser, users);
    }

    // MODIFIES: this
    // EFFECTS: Requests a user to navigate to their file directory
    private void addPhotoDirectory() {
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("."));
            chooser.setDialogTitle("Choose your photo directory:");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int result = chooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    currentUser.setDirectoryPath(chooser.getSelectedFile().getPath());
                    File dir = new File(chooser.getSelectedFile().getPath());
                    File[] directoryListing = dir.listFiles();
                    if (directoryListing != null) {
                        for (File file : directoryListing) {
                            currentUser.getFilePaths().add(file.getPath());
                        }
                    }
                } catch (Exception e) {
                    //noinspection ImplicitArrayToString
                    System.out.println(e.getStackTrace());
                }
            }
        } catch (Exception e) {
            //noinspection ImplicitArrayToString
            System.out.println(e.getStackTrace());
        }
    }

    // MODIFIES: this, users
    // EFFECTS: creates a new user
    private void newUser() {
        String name = JOptionPane.showInputDialog("Please enter a name for your user profile:");
        User newUser = new User(name);
        setCurrentUser(newUser);
        users.getUsers().add(newUser);
        addPhotoDirectory();
    }
    
    //// MAIN JSON METHODS ////

    // MODIFIES: this
    // EFFECTS: loads users from JSON file
    private void loadUsers() {
        try {
            users = jsonReader.read();
            System.out.println("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //// MAIN RUNNABLE METHOD ////

    // EFFECTS: main method to run App
    public static void main(String[] args) {
        App app = new App();
        app.login();
    }
}
