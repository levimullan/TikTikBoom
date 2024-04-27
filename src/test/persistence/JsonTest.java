package persistence;

import model.User;
import model.Users;

public class JsonTest {

    public Users newGeneralUsers() {
        Users users = new Users();
        users.getPublishedPaths().add("testPublishedFilePath1");
        users.getPublishedPaths().add("testPublishedFilePath2");

        User user1 = new User("Levi");
        User user2 = new User("Raven");
        users.getUsers().add(user1);
        users.getUsers().add(user2);
        user1.setDirectoryPath("testDirectoryPathUser1");
        user2.setDirectoryPath("testDirectoryPathUser2");
        user1.getFilePaths().add("testFilePathUser1");
        user2.getFilePaths().add("testFilePathUser2");

        return users;

    }
}
