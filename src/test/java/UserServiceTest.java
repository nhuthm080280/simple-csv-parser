import model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.UserService;

import java.util.List;

public class UserServiceTest {
    @Test
    void bulkImportUsersCSV_Success() {
        String testFilePath = "src/test/resources/users.csv";
        UserService userService = new UserService();
        List<User> users = userService.bulkImportUsersCSV(testFilePath);
        Assertions.assertEquals(users.size(), 3);
    }

    @Test
    void bulkImportUsersCSVFaild_InvalidId() {
        String testFilePath = "src/test/resources/invalid_id_users.csv";
        UserService userService = new UserService();
        List<User> users = userService.bulkImportUsersCSV(testFilePath);
        Assertions.assertEquals(users.size(), 0);
    }

    @Test
    void bulkImportUsersCSVFailed_InvalidHeader() {
        String testFilePath = "src/test/resources/invalid_header_users.csv";
        UserService userService = new UserService();
        List<User> users = userService.bulkImportUsersCSV(testFilePath);
        Assertions.assertEquals(users.size(), 0);
    }

    @Test
    void bulkImportUsersCSVFailed_FileNotFound() {
        String testFilePath = "src/test/resources/invalid_users.csv";
        UserService userService = new UserService();
        List<User> users = userService.bulkImportUsersCSV(testFilePath);
        Assertions.assertEquals(users.size(), 0);
    }
}
