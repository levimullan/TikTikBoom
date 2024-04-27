package persistence;

import static org.junit.jupiter.api.Assertions.*;

import model.Users;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/*
    Tests the Json Reader class
    Class uses fields and methods from the JsonSerializationDemo
    by Felix Grund and  Paul Carter
    Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
*/

public class JsonReaderTest extends JsonTest{

    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Users users = reader.read();
            fail("IO Exception expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReaderEmptyUsers() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyUsers.json");
        try {
            Users users = reader.read();
            assertTrue(users.getUsers().isEmpty());
            assertTrue(users.getPublishedPaths().isEmpty());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderGeneralUsers() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralUsers.json");
        try {
            Users users = reader.read();
            assertEquals(2, users.getUsers().size());
            assertEquals(7, users.getPublishedPaths().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
