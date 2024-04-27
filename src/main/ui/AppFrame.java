package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

import model.Event;
import model.EventLog;
import model.User;
import model.Users;
import persistence.JsonWriter;

// AppFrame extends JFrame with implemention details specific to this app

class AppFrame extends JFrame {

    private static final int WIDTH = 300;
    private static final int HEIGHT = 600;

    private static final String JSON_STORE = "data/users.json";
    private AppPanel photoPanel;
    private MenuPanel menuPanel;
    private final User currentUser;
    private final Users users;
    private final JsonWriter jsonWriter;
    private GridBagConstraints gc;
    private GridBagLayout layout;

    //// CONSTRUCTOR ////

    // EFFECTS: Instantiates a new AppFrame
    public AppFrame(String frameName, User user, Users users) {
        super(frameName);
        this.currentUser = user;
        this.users = users;
        this.jsonWriter = new JsonWriter(JSON_STORE);
        this.addWindowListener(new AppWindowListener(this));
        layout = new GridBagLayout();
        gc = new GridBagConstraints();
        getContentPane().setLayout(layout);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        generateJScrollAndAppPanel();
        generateProgressBar();
        generateMenuPanel();
        pack();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Generates the menu panel
    public void generateMenuPanel() {
        gc.gridx = 0;
        gc.gridy = 2;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;
        menuPanel = new MenuPanel(WIDTH);
        add(menuPanel, gc);
        setMenuPanelButtonListeners();
    }

    // EFFECTS: Sets the button listeners for the menu panel buttons
    public void setMenuPanelButtonListeners() {
        menuPanel.setButtonListener(new ButtonListener() {
            @Override
            public void addPhotos(int num) {
                if (num == 0) {
                    photoPanel.addFileToDirectory();
                } else if (num == 1) {
                    photoPanel.addPhotosToPanel();
                } else if (num == 2) {
                    photoPanel.addRandomPhotosToPanel();
                } else if (num == 3) {
                    if (photoPanel.getPublishedLabels() != null) {
                        photoPanel.addFeedPhotosToPanel();
                    } else {
                        String message = "You haven't chosen a photo of the day yet.";
                        JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: Generates the JProgressBar
    public void generateProgressBar() {
        gc.gridx = 0;
        gc.gridy = 1;
        AppProgressBar timer = new AppProgressBar(WIDTH - 6, 12);
        add(timer, gc);
    }

    // MODIFIES: this
    // EFFECTS: Generates the JScrollPane and the underlying AppPanel
    public void generateJScrollAndAppPanel() {
        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;
        photoPanel = new AppPanel(currentUser, this.users);
        JScrollPane scrollPane = new JScrollPane(photoPanel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(WIDTH, HEIGHT - 90));
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(2, 0));
        scrollPane.setBorder(null);
        add(scrollPane, gc);
    }

    // EFFECTS: Prompts the user to save the application state
    public void savePrompt() {
        int result = JOptionPane.showConfirmDialog(null, "Would you like to save your profile?",
                "Welcome!", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            saveUsers();
        }
        printLog();
        System.exit(0);
    }

    // EFFECTS: Saves the Users to a JSON file
    public void saveUsers() {
        try {
            jsonWriter.open();
            jsonWriter.write(users);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file to " + JSON_STORE);
        }
    }

    // EFFECTS: Prints log to console
    public void printLog() {
        EventLog log = EventLog.getInstance();
        Iterator<Event> events = log.iterator();
        for (Iterator<Event> it = events; it.hasNext(); ) {
            Event e = it.next();
            System.out.println(e);
        }
    }
}
