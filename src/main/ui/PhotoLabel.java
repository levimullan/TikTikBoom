package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// PhotoLabel extends JLabel with implementation specific to how labels should be displayed and their custom 
// behaviours for proper functionality of this app

public class PhotoLabel extends JLabel {

    private final AppPanel parent;
    private final Dimension labelDim;
    private final String filePath;
    private final ImageIcon imageIcon;
    private MouseListener mouseListener;
    private final int horizontal;
    private final int vertical;

    // EFFECTS: Creates a new PhotoLabel 
    public PhotoLabel(int width, int height, String filePath, AppPanel parent) {
        this.parent = parent;
        this.filePath = filePath;
        this.horizontal = width;
        this.vertical = height;
        this.labelDim = new Dimension(width, height);
        this.setPreferredSize(labelDim);
        ImageIcon imageIcon = new ImageIcon(filePath);
        this.imageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_FAST));
        this.setIcon(this.imageIcon);
        generateLabelMouseListener();
        addMouseListener(mouseListener);
    }

    // MODIFIES: this
    // EFFECTS: Creates a new mouse listener with specific mouse actions and assigns it to a class field
    private void generateLabelMouseListener() {
        this.mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                PhotoLabel pressed = (PhotoLabel) e.getSource();
                pressed.setBorder(new LineBorder(Color.GREEN, 2));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                PhotoLabel pressed = (PhotoLabel) e.getSource();
                parent.publishPhoto(pressed);
                //parent.removeAll();
                parent.addFeedPhotosToPanel();
                pressed.setBorder(new EmptyBorder(0,0,0,0));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
    }

    public String getFilePath() {
        return filePath;
    }

    public int getXDim() {
        return horizontal;
    }

    public int getYDim() {
        return vertical;
    }
}
