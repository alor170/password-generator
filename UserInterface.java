import java.util.Scanner;  // Import the Scanner class

class Main {
public static void main(String[] args) {
    Scanner parameters = new Scanner(System.in);  // Create a Scanner object
    
    System.out.println("What site/account is this for?");
    String purpose = parameters.nextLine();
    
    Password password1 = new Password(parameters);
    String password = password1.createPassword();
    System.out.println("The password for " + purpose + " is: " + password);
    
    password = password1.replacePassword(parameters, purpose, password);
    System.out.println("The password for " + purpose + " is: " + password);
    
    password = password1.addWord(parameters, password);
    System.out.println("The password for " + purpose + " is: " + password);
    
    parameters.close();
  }
}
