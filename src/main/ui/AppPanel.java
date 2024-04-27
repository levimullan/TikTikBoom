package ui;

import model.User;
import model.Users;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashSet;

// AppPanel Class expends JPanel with specific implementation details for this app

public class AppPanel extends JPanel {

    private final ArrayList<PhotoLabel> photoLabels;
    private final ArrayList<PhotoLabel> publishedLabels;
    private final User currentUser;
    private final Users users;

    //// CONSTRUCTOR ////

    // EFFECTS: instantiates an AppPanel
    public AppPanel(User user, Users users) {
        super();
        this.currentUser = user;
        this.users = users;
        this.photoLabels = new ArrayList<>();
        this.publishedLabels = new ArrayList<>();
        setPublishedPhotos();
        setBorder(new EmptyBorder(3, 3, 3, 3));
        createPhotoLabels(this);
        addPhotosToPanel();
    }

    // METHODS //

    // MODIFIES: this
    // EFFECTS: instantiates a photo label for each file of the current users list of paths and adds it to
    //          the list of photoLabels
    public void createPhotoLabels(AppPanel parent) {
        for (String filePath : currentUser.getFilePaths()) {
            PhotoLabel pl = new PhotoLabel(96, 100, filePath, this);
            photoLabels.add(pl);
        }
    }

    // EFFECTS: adds photo label to this panel
    public void addPhotosToPanel() {
        this.removeAll();
        this.setBorder(new EmptyBorder(3, 3, 3, 3));
        this.setLayout(new GridLayout(photoLabels.size() / 3, 3, 3, 3));
        for (PhotoLabel label : photoLabels) {
            this.add(label);
        }
    }

    // EFFECTS: retrieves 15 random photos from the list of photo labels and adds them
    //          to the list of random Photos then adds them to this panel
    public void addRandomPhotosToPanel() {
        this.removeAll();
        this.setBorder(new EmptyBorder(3, 3, 3, 3));
        this.setLayout(new GridLayout(5, 3, 3, 3));
        HashSet<PhotoLabel> randomPhotos = new HashSet<>();
        while (randomPhotos.size() < 15) {
            int random = (int) (Math.random() * (photoLabels.size() - 1));
            PhotoLabel label = photoLabels.get(random);
            randomPhotos.add(label);
        }
        for (PhotoLabel label : randomPhotos) {
            add(label);
        }
    }

    // EFFECTS: adds each published photo labels in the feed to this panel
    public void addFeedPhotosToPanel() {
        this.removeAll();
        this.setLayout(new GridLayout(publishedLabels.size(), 1, 3, 3));
        int parentWidth = getParent().getWidth();
        for (PhotoLabel pl : publishedLabels) {
            int labelWidth = pl.getXDim();
            int labelHeight = pl.getYDim();
            int scaleFactor = parentWidth / labelWidth;
            pl.setPreferredSize(new Dimension(labelWidth * scaleFactor - 10, labelHeight * scaleFactor));
            ImageIcon imageIcon = new ImageIcon(pl.getFilePath());
            int iconWidth = imageIcon.getIconWidth();
            int iconHeight = imageIcon.getIconHeight();
            int heightScaleFactor = iconWidth / parentWidth;
            try {
                imageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(parentWidth,
                        iconHeight / heightScaleFactor, Image.SCALE_FAST));
                pl.setIcon(imageIcon);
                this.add(pl);
            } catch (ArithmeticException e) {
                //Do nothing
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Creates a set of PhotoLabels for each file path in the users set of published file paths
    private void setPublishedPhotos() {
        for (String filePath : users.getPublishedPaths()) {
            PhotoLabel pl = new PhotoLabel(96, 100, filePath, this);
            publishedLabels.add(pl);
        }
    }

    // MODIFIES: this
    // EFFECTS: moves photo from personal album to published album
    public void publishPhoto(PhotoLabel label) {
        users.addPublishedPhoto(label.getFilePath());
        currentUser.getFilePaths().remove(label.getFilePath());
        publishedLabels.add(label);
        photoLabels.remove(label);
    }

    // MODIFIES: this
    // EFFECTS: Using the JFileChooser, opens a new directory navigation frame and allows user to select an
    //          image file to add to the user's photo directory
    public void addFileToDirectory() {
        try {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files",
                    "png", "jpeg");
            chooser.setFileFilter(filter);
            chooser.setCurrentDirectory(new File("."));
            int result = chooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                try {
                    Path sourcePath = file.toPath();
                    Path destinationPath = new File(currentUser.getUserPhotoDirectoryPath(),
                            file.getName()).toPath();
                    Path path = Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                    currentUser.getFilePaths().add(path.toString());
                    PhotoLabel pl = new PhotoLabel(96, 100, path.toString(), this);
                    photoLabels.add(pl);
                    addPhotosToPanel();
                } catch (Exception e) {
                    // do nothing
                }
            }
        } catch (Exception e) {
            // do nothing;
        }
    }

    // EFFECTS: returns list of published Photos
    public ArrayList<PhotoLabel> getPublishedLabels() {
        return publishedLabels;
    }
}

