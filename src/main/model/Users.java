package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import java.util.ArrayList;
import java.util.HashSet;


// Users Class - represents a collection of Users with a list of users and a common user Feed

public class Users implements Writable {

    private final ArrayList<User> users;
    private final HashSet<String> publishedPaths;

    //// CONSTRUCTOR ////

    // EFFECTS: constructs School instance
    public Users() {
        this.users = new ArrayList<>();
        this.publishedPaths = new HashSet<>();
    }

    //// GETTERS/SETTERS ////

    // EFFECTS: returns list of User
    public ArrayList<User> getUsers() {
        return this.users;
    }

    // EFFECTS: returns Feed
    public HashSet<String> getPublishedPaths() {
        return this.publishedPaths;
    }

    // MODIFIES: this
    // EFFECTS: adds published photo
    public void addPublishedPhoto(String path) {
        publishedPaths.add(path);
        EventLog.getInstance().logEvent(new Event("User photo published: " + path));
    }

    //// JSON METHODS ////

    // EFFECTS: puts users JSON Array and feed JSON Array into a JSON with the names:
    //          "users" and "feed" respectively
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("users", usersToJson());
        json.put("feed", feedToJson());
        return json;
    }

    // EFFECTS: convert each user in the users list to a JSON object and save it in a JSON array
    private JSONArray usersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (User user : users) {
            jsonArray.put(user.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: convert each file in the feed to a JSON object and save it in a JSON array
    private JSONArray feedToJson() {
        JSONArray jsonArray = new JSONArray();
        for (String filePath : publishedPaths) {
            jsonArray.put(filePath);
        }
        return jsonArray;
    }


}
