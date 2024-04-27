package persistence;

import org.json.JSONObject;

/* Interface of Writable to prescribe necessary Json
writable methods required by each class to be written as a Json
(Users, User)

Main uses fields and methods from the JsonSerializationDemo
by Felix Grund and  Paul Carter
Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */
public interface Writable {

    // EFFECTS: returns instantiation of class that implements this interface as JSON object
    JSONObject toJson();

}
