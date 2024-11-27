package org.example.asstest;

public class Auth {
    private static User currentUser;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        Auth.currentUser = currentUser;
    }

    public static boolean login(String username, String password) {
        if (username.equals("admin") && password.equals("admin")) {
            setCurrentUser(new User(1, "admin", "admin", "admin"));
            return true;
        }
        return false;
    }

    public static void logout() {
        setCurrentUser(null);
    }
}
