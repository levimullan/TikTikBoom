package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// MenuPanel expends JPanel and implements ActionListener with implementation specific to this app.
// It houses the menu buttons.

public class MenuPanel extends JPanel implements ActionListener {

    private static final Color APPLE_BLUE = new Color(12, 135, 247);

    private JButton addPhotoButton;
    private JButton homeButton;
    private JButton gameButton;
    private JButton feedButton;
    private ButtonListener buttonListener;

    // EFFECTS: Creates a new MenuPanel with a specified width
    public MenuPanel(int width) {
        super();
        setPreferredSize(new Dimension(width,50));
        setBorder(new EmptyBorder(0, 3,3,3));
        setLayout(new GridLayout(1, 3, 0, 3));
        addButtons();
        setButtonSizes();
        setButtonGraphics();
        add(addPhotoButton);
        add(homeButton);
        add(gameButton);
        add(feedButton);
        addPhotoButton.addActionListener(this);
        homeButton.addActionListener(this);
        gameButton.addActionListener(this);
        feedButton.addActionListener(this);
    }

    // EFFECTS: Sets the colour and alignment specifications for each button in the menu
    private void setButtonGraphics() {
        addPhotoButton.setForeground(APPLE_BLUE);
        addPhotoButton.setOpaque(true);
        addPhotoButton.setText("+");
        addPhotoButton.setHorizontalAlignment(SwingConstants.CENTER);
        homeButton.setForeground(APPLE_BLUE);
        homeButton.setOpaque(true);
        homeButton.setText("PHOTOS");
        homeButton.setHorizontalAlignment(SwingConstants.CENTER);
        gameButton.setForeground(APPLE_BLUE);
        gameButton.setOpaque(true);
        gameButton.setText("GAME");
        gameButton.setHorizontalAlignment(SwingConstants.CENTER);
        feedButton.setForeground(APPLE_BLUE);
        feedButton.setOpaque(true);
        feedButton.setText("FEED");
        feedButton.setHorizontalAlignment(SwingConstants.CENTER);
    }

    // EFFECTS: Sets the size specifications for the buttons in the menu
    private void setButtonSizes() {
        addPhotoButton.setPreferredSize(new Dimension(100, 50));
        homeButton.setPreferredSize(new Dimension(100, 50));
        gameButton.setPreferredSize(new Dimension(100, 50));
        feedButton.setPreferredSize(new Dimension(100, 50));
    }

    // EFFECTS: Adds the buttons to the menu 
    private void addButtons() {
        addPhotoButton = new JButton();
        homeButton = new JButton();
        gameButton = new JButton();
        feedButton = new JButton();
    }

    // MODIFIES: this
    // EFFECTS: Stores the button listener in class field to be used on the buttons
    public void setButtonListener(ButtonListener listener) {
        this.buttonListener = listener;
    }

    // EFFECTS: Sets the events to occur for each of the buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        if (clicked == addPhotoButton) {
            if (buttonListener != null) {
                buttonListener.addPhotos(0);
            }
        } else if (clicked == homeButton) {
            if (buttonListener != null) {
                buttonListener.addPhotos(1);
            }
        } else if (clicked == gameButton) {
            if (buttonListener != null) {
                buttonListener.addPhotos(2);
            }
        } else if (clicked == feedButton) {
            if (buttonListener != null) {
                buttonListener.addPhotos(3);
            }
        }
    }
}
