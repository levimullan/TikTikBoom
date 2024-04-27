package model;


// UserTest Class - tests to ensure proper functionality of User class

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    User testUser;

    // MODIFIES: this
    // EFFECTS: sets up the state of this class prior to running each test
    @BeforeEach
    public void setup() {
        testUser = new User("Levi");
    }

    // EFFECTS: test to ensure correct UserTest constructor functionality
    @Test
    public void ConstructorTest() {
        assertEquals("Levi", testUser.getUserName());
        assertNull(testUser.getUserPhotoDirectoryPath());
        assertTrue(testUser.getFilePaths().isEmpty());
    }

    // EFFECTS: test to ensure correct functionality of the getFilePaths() method
    @Test
    public void getFilePaths() {
        testUser.getFilePaths().add("path1");
        testUser.getFilePaths().add("path2");
        testUser.getFilePaths().add("path3");
        testUser.getFilePaths().add("path4");
        testUser.getFilePaths().add("path5");
        assertEquals(5, testUser.getFilePaths().size());
    }
}
