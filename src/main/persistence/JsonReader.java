package persistence;

/*
    JsonReader class is used to read a Json file saved
    at a specified directory and convert it into a java file

    Main uses fields and methods from the JsonSerializationDemo
    by Felix Grund and  Paul Carter
    Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */

import model.Event;
import model.EventLog;
import model.User;
import model.Users;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Stream;

public class JsonReader {

    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads Users JSONObject from file and returns it as an instantiation of the Users class;
    // throws IOException if an error occurs reading data from file
    public Users read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        EventLog.getInstance().logEvent(new Event("User loaded state from " + source));
        return parseUsers(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses Users from JSON object and returns it
    private Users parseUsers(JSONObject jsonObject) {
        Users users = new Users();
        addUsers(users.getUsers(), jsonObject);
        addFilesPaths(users.getPublishedPaths(), jsonObject, "feed");
        return users;
    }

    // EFFECTS: parses chambers from JSON object and adds them to chambers (school field)
    private void addUsers(ArrayList<User> users, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("users");
        for (Object json : jsonArray) {
            JSONObject nextUser = (JSONObject) json;
            addUser(users, nextUser);
        }
    }

    // EFFECTS: adds User to list of Users
    private void addUser(ArrayList<User> users, JSONObject jsonObject) {
        User user = new User(jsonObject.getString("userName"));
        user.setDirectoryPath(jsonObject.getString("path"));
        addFilesPaths(user.getFilePaths(), jsonObject, "filePaths");
        users.add(user);
    }

    // EFFECTS: parses files from JSON object and adds them to ArrayList of unpublished files
    private void addFilesPaths(HashSet<String> filePaths, JSONObject jsonObject, String key) {
        JSONArray jsonArray = jsonObject.getJSONArray(key);
        for (Object path : jsonArray) {
            String filePath = path.toString();
            filePaths.add(filePath);
        }
    }
}
