import java.util.Scanner;

public class MenuController {
    private Scanner scanner = new Scanner(System.in);
    private final UserController userController;

    public MenuController() {
        this.userController = new UserController();
    }

    public void startMenu(){
        String userChoice = this.getInfo("""
                Welcome to Bank
                please choose an option
                    1. Log in
                    2. Register
                """);
        switch (userChoice) {
            case "1":
                userController.login();
                break;
            case "2":
                userController.signUp();
                break;
            default:
                displayMessage("Please choose an option from the menu");
                break;
        }this.startMenu();
    }
    public void loginMenu(User currentUser){
        String userChoice = this.getInfo("""
                Please choose an option
                    1. Check Balance
                    2. Deposit
                    3. Withdrawal
                    4. Change user's password
                    5. Log out
                """);
        switch (userChoice) {
            case "1":
                userController.checkBalance();
                break;
            case "2":
                userController.deposit();
                break;
            case "3":
                userController.withdrawal();
                break;
            case "4":
                userController.changeUserPassword(currentUser);
                break;
            case "5":
                userController.logout();
                break;
            default:
                displayMessage("Please choose an option from the menu");
                break;
        }this.loginMenu(currentUser);
    }

     public void displayMessage(String message) {
            System.out.println(message);
        }

     public String getInfo(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }
}
