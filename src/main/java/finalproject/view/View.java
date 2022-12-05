package finalproject.view;

import finalproject.logichandle.AccountLogic;
import java.io.IOException;
import java.util.Scanner;

import static finalproject.main.Main.mode;


public class View {
    public void mainMenu(Scanner sc) throws IOException {
        AccountLogic accountLogic = new AccountLogic();
        while (true) {
            showMenuLogin();
            int choose = checkNumberException(sc, 1, 2);
            if (choose == 1) {
                accountLogic.register(sc);
            }
            if (choose == 2) {
                accountLogic.logIn(sc);
            }
        }
    }

    public void showMenuLogin() {
        System.out.println("Welcome to the program, please choose: ");
        System.out.println("1. Register");
        System.out.println("2. Log in");
        System.out.print("Choose: ");
    }



    public void LoginView() {
        System.out.println("1. Change username.");
        System.out.println("2. Change email.");
        System.out.println("3. Change password.");
        System.out.println("4. Log out.");
        System.out.println("5. Return to main menu");
        System.out.print("Choose: ");
    }

    public void showMenu() {
        if (mode) {
            System.out.println("-------JAVA INTERVIEW QUESTION PROGRAM--------");
        } else {
            System.out.println("-----------ENGLISH QUESTION PROGRAM-----------");
        }

        System.out.println("1. Add Question and Answer.");
        System.out.println("2. Show already existing Question and Answer.");
        System.out.println("3. Edit Question and Answer.");
        System.out.println("4. Search question.");
        System.out.println("5. Practice.");
        System.out.println("6. Quiz.");
        System.out.println("7. Account setting.");
        System.out.println("8. Change mode.");
        System.out.println("9. Learning Status.");
        System.out.println("10. Exit.");
        System.out.print("Choose: ");
    }


    public void menuStatus() {
        System.out.println("1. View Learning status");
        System.out.println("2. Change status");
        System.out.print("Choose: ");
    }

    public void viewTopic() {
        System.out.println("Choose Topic: ");
        System.out.println("1. Java Core");
        System.out.println("2. OOP - Object Oriented");
        System.out.println("3. Java Threads");
        System.out.println("4. Java Collections");
        System.out.println("5. Exception");
        System.out.print("Choose: ");
    }

    public void showQuiz(String[] answers) {
        System.out.println("Choose 1 of the following answers");
        System.out.println("1. " + answers[0]);
        System.out.println("2. " + answers[1]);
        System.out.println("3. " + answers[2]);
        System.out.println("4. " + answers[3]);
        System.out.print("Your answer is: ");
    }

    public void quizForeword() {
        System.out.println("------------Welcome to Quiz Test-----------");
        System.out.println("Think carefully before choosing the answer!");
    }

    public void askForNextQuestion() {
        System.out.println("Would you like to try another question?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.print("Choose: ");
    }

    protected static void editQuestion() {
        System.out.println("Do you want to edit Question or Answer?");
        System.out.println("1. Question");
        System.out.println("2. Answer");
        System.out.println("Choose: ");
    }

    public int checkNumberException(Scanner sc, int firstNumber, int lastNumber) {
        int choose = -1;
        do {
            try {
                choose = Integer.parseInt(sc.nextLine());
                if (choose < firstNumber || choose > lastNumber) {
                    System.out.println("Invalid selection, please choose again");
                }
            } catch (Exception e ) {
                System.out.println("You need to enter a number.");
            }
        } while (choose < firstNumber || choose > lastNumber);
        return choose;
    }

    public void viewPractice() {
        System.out.println("Choose Topic to Practice: ");
        System.out.println("1. Java Core");
        System.out.println("2. OOP - Object Oriented");
        System.out.println("3. Java Threads");
        System.out.println("4. Java Collections");
        System.out.println("5. Exception");
        System.out.println("6. All topic");
        System.out.println("Choose: ");
    }
}
