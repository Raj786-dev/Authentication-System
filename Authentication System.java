import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class User {
    String username;
    String password;
    String role;

    User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}

public class AuthSystem {
    // In-memory "database" of users
    private static final Map<String, User> users = new HashMap<>();

    static {
        // Predefined users
        users.put("admin", new User("admin", "admin123", "Admin"));
        users.put("raj", new User("raj", "raj123", "User"));
        users.put("guest", new User("guest", "guest", "Guest"));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("==== Login System ====");
        System.out.print("Enter username: ");
        String username = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        User user = authenticate(username, password);

        if (user != null) {
            System.out.println("\n✅ Login successful! Welcome " + user.username + " (" + user.role + ")");
            showMenu(user.role, sc);
        } else {
            System.out.println("\n❌ Invalid username or password!");
        }

        sc.close();
    }

    private static User authenticate(String username, String password) {
        User user = users.get(username);
        if (user != null && user.password.equals(password)) {
            return user;
        }
        return null;
    }

    private static void showMenu(String role, Scanner sc) {
        System.out.println("\n=== Menu ===");

        switch (role) {
            case "Admin":
                System.out.println("1. View Users");
                System.out.println("2. Manage System");
                System.out.println("3. Logout");
                break;

            case "User":
                System.out.println("1. View Profile");
                System.out.println("2. Change Password");
                System.out.println("3. Logout");
                break;

            case "Guest":
                System.out.println("1. Browse Content");
                System.out.println("2. Logout");
                break;

            default:
                System.out.println("No permissions assigned.");
        }
    }
}
