package auth;

import model.User;

public class UserSession {
    private static User loggedInUser;

    public static void setLoggedInUser(User user) {
        loggedInUser = user;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static String getRole() {
        return loggedInUser != null ? loggedInUser.getRole() : null;
    }
}
