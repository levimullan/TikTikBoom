package ui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

// AppWindowListener implements WindowListener and overrides it's methods with implementation specific to this app

public class AppWindowListener implements WindowListener {

    private final AppFrame parent;

    // EFFECTS: Creates new AppWindowListener
    public AppWindowListener(AppFrame parent) {
        super();
        this.parent = parent;
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    // EFFECTS: Prompts the user to save when window is closing
    @Override
    public void windowClosing(WindowEvent e) {
        parent.savePrompt();

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
