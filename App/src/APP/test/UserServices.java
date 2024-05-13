package APP.test;

import APP.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserServices {
    private List<User> users = new ArrayList<>();

    public void registerUser(String username, String password) {
        users.add(new User(username, password));
        System.out.println(users);
    }

    public User getUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public boolean validateLogin(String username, String password) {
        User user = getUser(username);
        return user != null && user.getPassword().equals(password);
    }
}
