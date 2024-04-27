package ui;

import javax.swing.*;
import java.awt.*;

// AppButton Class extends JButton with dimensions specific to this apps requirements

public class AppButton extends JButton {

    // EFFECTS: creates new instance of AppButton
    public AppButton(int width, int height, String buttonText) {
        super(buttonText);
        setPreferredSize(new Dimension(width, height));
    }
}
