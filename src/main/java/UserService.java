import java.util.ArrayList;

public class UserService {

    private ArrayList<User> users = new ArrayList<>();

    public String addUser(User user)  throws Exception {
        if(user.getName().isEmpty()) throw new Exception("Please provide user's name!");
        if(user.getLastName().isEmpty()) throw new Exception("Please provide user's last name!");
        if (user.getGender() == null) throw new Exception ("Please provide user's gender");
        if (user.getPhoneNumber().isEmpty()) throw new Exception ("Please provide user's phone number");
        if (user.getAddress().isEmpty()) throw new Exception ("Please provide user's address");
        if (user.getEmail().isEmpty()) throw new Exception ("Please provide user's email");

        this.users.add(user);
        return "User " + user.getName() + " " + user.getLastName() + " was registered. User will receive his/her card in 3 to 5 working days.";
    }
    public User signIn(String name, String password) {
        for (User user : this.users) {
            if (user.getName().equals(name) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public String checkCurrentPassword(String password, User currentUser) {
        if (currentUser.getPassword().equals(password)) {
            return "Password match";
        }
        return "Incorrect current password";
    }
}

