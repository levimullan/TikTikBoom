package persistence;

import model.Users;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            Users users = new Users();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyUsers() {
        try {
            Users users = new Users();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyUsers.json");
            writer.open();
            writer.write(users);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyUsers.json");
            users = reader.read();
            assertTrue(users.getUsers().isEmpty());
            assertTrue(users.getPublishedPaths().isEmpty());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Users users = newGeneralUsers();
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralUsers.json");
            writer.open();
            writer.write(users);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWriterGeneralUsers.json");
            Users testUsers = reader.read();
            assertNotNull(testUsers);
        } catch (FileNotFoundException e) {
            fail("File not found exception.");
        } catch (IOException e) {
            fail("Couldn't read from file.");
        }
    }
}
