import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UserController {
private double balance;
Scanner scanner = new Scanner(System.in);
private final UserService userService;
private User currentUser;

    public UserController() {
        this.userService = new UserService();
    }

    public void signUp(){
        try {
        balance = 0.00;
        User user = this.collectUserInfo();
        String result = this.userService.addUser(user);
        displayMessage(result);
        }catch (Exception exception) {
            displayMessage("Error: " + exception.getMessage());
        }
    }

    private User collectUserInfo() {
        User user = new User();
        user.setName(getInfo("Please enter user's first name: "));
        user.setLastName(getInfo("Please enter user's last name: "));
        Gender gender = Gender.valueOf(this.getInfo("Please choose user's gender(MALE,FEMALE): ").toUpperCase());
        user.setGender(gender);
        user.setPhoneNumber(getInfo("Please enter user's phone number: "));
        user.setAddress(getInfo("Please enter user's complete address: "));
        user.setEmail(getInfo("Please enter user's email: "));
        user.setAccountNumber(getInfo("Please enter user's account number"));
        String password;
        do {
            password = getInfo("Please enter user's password. " +
                    "\nPasswords must have at least 8 characters and contain at least 1 uppercase letter, 1 lowercase letter and 1 number.");
        } while (!Pattern.matches("(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}", password));
            user.setPassword(password);
            displayMessage("Password set successfully.");

        return user;
    }

    public void login(){
        String inputFirstName = getInfo("Please enter user's first name: ");
        String inputPassword = getInfo("Please enter user's password");

        currentUser = userService.signIn(inputFirstName, inputPassword);
        if(currentUser != null){
        displayMessage("Welcome! You have successfully logged in.");
            MenuController menuController = new MenuController();
            menuController.loginMenu(currentUser);
        }else{
            displayMessage("Invalid data");
        }
    }

    public void checkBalance(){
        displayMessage("Balance is: " + balance + " EUR");
    }

    public void deposit(){
        double depositAmount = Double.parseDouble(getInfo("Please enter amount you want to deposit: "));
        balance += depositAmount;
        displayMessage("User's current balance is: " + balance + " EUR");
    }

    public void withdrawal() {
        double withdrawAmount = Double.parseDouble(getInfo("Please enter amount you want to withdraw: "));
        if (withdrawAmount > balance){
            System.out.println("Insufficient balance");
        }else{
        balance -= withdrawAmount;
        displayMessage("User's current balance is: " + balance + " EUR");
        }
    }

    public void changeUserPassword(User currentUser){
        String newPassword;

        String inputPassword = getInfo("Please enter user's current password: ");
        String result = this.userService.checkCurrentPassword(inputPassword,currentUser);
        this.displayMessage(result);
        if (result.equals("Password match")){
            do {
            newPassword= getInfo("Please enter new password. " +
                    "\nPasswords must have at least 8 characters and contain at least 1 uppercase letter, 1 lowercase letter and 1 number.");
            } while (!Pattern.matches("(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}", newPassword));

            currentUser.setPassword(newPassword);
            displayMessage("Password set successfully.");
        }
    }


    public void logout(){
        displayMessage("Thank you for using our services.");
        System.exit(0);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public String getInfo(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }
}
