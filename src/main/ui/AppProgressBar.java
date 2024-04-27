package ui;

import javax.swing.*;
import java.awt.*;

// AppProgressBar extends JProgressBar with implementation details specific to this app

public class AppProgressBar extends JProgressBar {

    // EFFECTS: Creates new AppProgressBar
    public AppProgressBar(int width, int height) {
        super(JProgressBar.HORIZONTAL, 0,100);
        setPreferredSize(new Dimension(width, height));
        setValue(100);
    }
    
    // EFFECTS: Starts countdown
    public void countDown() {
        int time = 100;
        while (time > 0) {
            setValue(time);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time--;
        }
    }
}
