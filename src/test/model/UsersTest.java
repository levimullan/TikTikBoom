package model;


// UsersTest Class - tests to ensure proper functionality of Users class

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UsersTest {

    Users testUsers;

    // EFFECTS: creates a testUsers instance prior to running each test
    @BeforeEach
    void setup() {
        testUsers = new Users();
    }

    // EFFECTS: test to ensure correct functionality of Users constructor
    @Test
    void constructorTest() {
        assertTrue(testUsers.getUsers().isEmpty());
        assertTrue(testUsers.getPublishedPaths().isEmpty());
    }

    // EFFECTS: test to ensure correct functionality of getUsers() method
    @Test
    void getUsersEmptyTest() {
        assertTrue(testUsers.getUsers().isEmpty());
    }

    // EFFECTS: test to ensure correct UserTest constructor functionality
    @Test
    void getUsersNotEmptyTest() {
        User user1 = new User("Harry");
        User user2 = new User("Ron");
        testUsers.getUsers().add(user1);
        testUsers.getUsers().add(user2);
        assertEquals(2, testUsers.getUsers().size());
    }

    // EFFECTS: test to ensure correct functionality of getPublishedPaths() method
    @Test
    void getPublishedPathsEmptyTest() {
        assertTrue(testUsers.getPublishedPaths().isEmpty());
    }

    // EFFECTS: test to ensure correct functionality of getPublishedPaths() method
    @Test
    void getPublishedPathsNotEmptyTest() {
        assertTrue(testUsers.getPublishedPaths().isEmpty());
        testUsers.getPublishedPaths().add("TestFilePath1");
        testUsers.getPublishedPaths().add("TestFilePath2");
        assertEquals(2, testUsers.getPublishedPaths().size());
    }
    
    // EFFECTS: test to ensure correct functionality of toJson() method
    @Test
    public void toJsonTest() {
        JSONObject jsonTestSchool = testUsers.toJson();
        assertTrue(jsonTestSchool.has("users"));
        assertTrue(jsonTestSchool.has("feed"));
    }


}
