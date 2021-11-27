package service;

import model.User;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserService {
    public static final String ID = "ID";
    public static final String FIRSTNAME = "FirstName";
    public static final String LASTNAME = "LastName";
    public static final String EMAIL = "Email";
    public static final String PHONE = "Phone";
    public static final String COUNTRY = "Country";
    public static final Integer NUMBER_COLUMN = 5;


    public List<User> bulkImportUsersCSV(String filePath) {
        System.out.println("Start import user");
        List<User> users = new ArrayList<>();
        try {
            Path path = Paths.get(filePath);
            Reader reader = Files.newBufferedReader(path, Charset.forName("UTF-8"));
            SimpleCSVReader simpleCSVReader = new SimpleCSVReader(reader);
            List<List<String>> records = simpleCSVReader.readRecords();
            if (isNotValidHeader(records.get(0))) {
                System.out.println("Invalid header");
                return users;
            }

            if (records.isEmpty()) {
                System.out.println("No record found");
                return users;
            }

            for (int i = 1; i < records.size(); i++) {
                User user = new User();
                user.setId(UUID.fromString(records.get(i).get(0)));
                user.setFirstName(records.get(i).get(1));
                user.setLastName(records.get(i).get(2));
                user.setEmail(records.get(i).get(3));
                user.setPhoneNumber(records.get(i).get(4));
                user.setCountry(records.get(i).get(5));
                users.add(user);
            }
        } catch (IOException | IndexOutOfBoundsException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Total imported user: " + users.size());
        return users;
    }

    private boolean isNotValidHeader(List<String> header) {
        if (header.size() < NUMBER_COLUMN) {
            return false;
        }
        return !header.get(0).equals(ID) ||
            !header.get(1).equals(FIRSTNAME) ||
            !header.get(2).equals(LASTNAME) ||
            !header.get(3).equals(EMAIL) ||
            !header.get(4).equals(PHONE) ||
            !header.get(5).equals(COUNTRY);
    }

}
