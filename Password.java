import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner; 
import java.io.File;
import java.io.FileNotFoundException;

public class Password{
	
	private static String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static String lowerCase = "abcdefghijklmnopqrstuvwxyz";
	private static String numbers = "1234567890";
	private static String specChars = "~!@#$%&()-+=<>^";
	private static ArrayList<String> wordbank = new ArrayList<String>();
	
	private String upperDecision;
	private String lowerDecision;
	private String numberDecision;
	private String specialDecision;
	private int length;
	
	private static boolean continueInput(String input) {
		try {
			if (!input.equals("Y") && !input.equals("N")) {
				throw new IncorrectInputException("Please either enter Y or N");
			}
		} catch (IncorrectInputException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	private static boolean continueLengthInput(String input) {
		try {
			if (Integer.parseInt(input) < 1 || Integer.parseInt(input) > 15) {
				throw new IncorrectInputException("Please either enter a number between 1-15");
			}
		} catch (IncorrectInputException e1) {
			System.out.println(e1.getMessage());
			return false;
		}
		return true;
	}

	private static String getPasswordParameters(Scanner scan, String message) {
		System.out.println(message);
		String parameter = scan.nextLine();
		if(!message.contains("long")) {
	    	while(!continueInput(parameter)) {
		    	parameter = scan.nextLine();
		    }
	    }else {
	    	while(!continueLengthInput(parameter)) {
		    	parameter = scan.nextLine();
		    }
	    }

		return parameter;
	}
	
	public Password(Scanner scan) {
		upperDecision = getPasswordParameters(scan, "Do you want uppercase letters? (Y/N)");
		lowerDecision = getPasswordParameters(scan, "Do you want lowercase letters? (Y/N)");
		numberDecision = getPasswordParameters(scan, "Do you want numbers? (Y/N)");
		specialDecision = getPasswordParameters(scan, "Do you want special characters? (Y/N)");
		this.length = Integer.parseInt(getPasswordParameters(scan, "How long do you want your password? (1-10)"));
	}
	
	public String createPassword() {
		String password = "";
		String passwordRepo = null;
		Random rand = new Random();
		
		if(upperDecision.equals("Y")) {
			passwordRepo = upperCase;
		} 
		if(lowerDecision.equals("Y")) {
			passwordRepo = passwordRepo + lowerCase;
		}
		if(numberDecision.equals("Y")) {
			passwordRepo = passwordRepo + numbers;
		}
		if(specialDecision.equals("Y")) {
			passwordRepo = passwordRepo + specChars;
		}
		
		int repoLength = passwordRepo.length()-1;
		for(int i = 0; i < length; i++) {
			password = password + passwordRepo.charAt(rand.nextInt(repoLength));
		}
		
		return password;
	}
	
	public String replacePassword(Scanner scan, String purpose, String oldPassword) {
		String replacePassword = getPasswordParameters(scan, "Do you want to replace the password for " + purpose + "? (Y/N)");
		if(replacePassword.equals("Y")) {
			String newPassword = this.createPassword();
			return newPassword;
		} else {
			return oldPassword;
		}
	}
	
	public String addWord(Scanner scan, String oldPassword) {
		String addWord = getPasswordParameters(scan, "Do you want to add a random four letter word into your password? (Y/N)");
		File myObj = new File("wordbank.txt");
		Random rand = new Random();
		try {
			Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        wordbank.add(data);
		      }
		      myReader.close();
		}catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		if(addWord.equals("Y")) {
			oldPassword = wordbank.get(rand.nextInt(wordbank.size())) + oldPassword;
			return oldPassword;
		} else {
			return oldPassword;
		}
	}
			
}