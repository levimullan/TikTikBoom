package model;


import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.HashSet;

// User Class - a User with a userName, directory path, and set of image files

public class User implements Writable {

    private final String userName;
    private String userPhotoDirectoryPath;
    private final HashSet<String> filePaths;

    //// CONSTRUCTOR ////

    // EFFECTS: Instantiates a User
    public User(String userName) {
        this.userName = userName;
        this.userPhotoDirectoryPath = null;
        this.filePaths = new HashSet<>();
    }

    //// GETTERS/SETTERS ////

    // EFFECTS: returns user name
    public String getUserName() {
        return userName;
    }

    // EFFECTS: returns HashSet of user files;
    public HashSet<String> getFilePaths() {
        return filePaths;
    }

    // EFFECTS: returns path to users file directory
    public String getUserPhotoDirectoryPath() {
        EventLog.getInstance().logEvent(new Event("File added to: " + userPhotoDirectoryPath));
        return userPhotoDirectoryPath;
    }

    public void setDirectoryPath(String path) {
        this.userPhotoDirectoryPath = path;
        EventLog.getInstance().logEvent(new Event("User directory set to: " + userPhotoDirectoryPath));
    }

    public void addfilePath(String path) {
        this.filePaths.add(path);
        EventLog.getInstance().logEvent(new Event("File added with path: " + path));
    }



    //// JSON METHODS ////

    // EFFECTS: create and return a JSONObject for this user (name, and file paths)
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("userName", userName);
        json.put("path", userPhotoDirectoryPath);
        json.put("filePaths", filesToJson());
        return json;
    }


    // EFFECTS: convert each file path in list of files to a JSON and save it in a JSON array
    public JSONArray filesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (String filePath : filePaths) {
            jsonArray.put(filePath);
        }
        return jsonArray;
    }

}
