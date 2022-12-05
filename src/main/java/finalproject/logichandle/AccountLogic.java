package finalproject.logichandle;


import finalproject.entity.Account;
import finalproject.view.View;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

import static finalproject.main.Main.accounts;


public class AccountLogic extends View {
    public void register(Scanner sc) {
        Account account = new Account();
        System.out.println("Enter your email: ");
        emailRegister(sc, account);
        usernameRegister(sc, account);
        passwordRegister(sc, account);
        accounts.add(account);
        System.out.println("Registration successful, please login.");
    }

    private void emailRegister(Scanner sc, Account account) {
        String email;
        String regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        boolean flag = true;
        do {
            email = sc.nextLine();
            if (checkSpace(email)) {
                System.out.println("Email must not contain spaces");
                continue;
            }
            if (patternMatches(email, regexPattern)) {
                System.out.println("You need to enter the correct email format.");
                continue;
            }
            if (accounts.size() == 0) {
                break;
            } else {
                flag = checkEmailExisted(email);
            }
        } while (flag);
        account.setEmail(email);
    }

    private void usernameRegister(Scanner sc, Account account) {
        System.out.println("Enter username: ");
        String user;
        boolean flag = true;
        do {
            user = sc.nextLine();
            if (accounts.size() == 0) {
                break;
            }
            if (checkSpace(user)) {
                System.out.println("Username must not contain spaces.");
                continue;
            }
            if (checkUserExisted(user)){
                continue;
            }
            flag = false;
        } while (flag);
        account.setUsername(user);
    }

    private boolean checkUserExisted(String user) {
        boolean flag =  false;
        for (Account i : accounts) {
            if (user.equals(i.getUsername())) {
                System.out.println("This username is already in use.");
                flag = true;
            }
        }
        return flag;
    }

    private void passwordRegister(Scanner sc, Account account) {
        System.out.println("Enter password: ");
        String password;
        String regexPattern = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[.,-_;]).{8,15})";
        boolean flag = true;
        do {
            password = sc.nextLine();
            if (checkSpace(password)) {
                System.out.println("Password must not contain spaces.");
                continue;
            }
            if (patternMatches(password, regexPattern)) {
                System.out.println("Password must contain from 8-15 characters, at least 1 uppercase character, 1 special character (. , - _ ;)");
                continue;
            }
            flag = false;
        } while (flag);
        account.setPassword(password);
    }

    private boolean checkSpace(String string) {
        return string.contains(" ");
    }

    public boolean patternMatches(String string, String regexPattern) {
        return !Pattern.compile(regexPattern)
                .matcher(string)
                .find();
    }

    public void changeUsername(Scanner sc, Account account) {
        checkPassword(sc, account);
        System.out.println("Enter new username: ");
        String newUsername;
        boolean flag = true;
        do {
            int count = 0;
            newUsername = sc.nextLine();
            if(checkSpace(newUsername)) {
                System.out.println("Username must not contain spaces.");
                continue;
            }
            for (Account i : accounts) {
                if (newUsername.equals(i.getUsername())) {
                    if (newUsername.equals(account.getUsername())) {
                        System.out.println("The new username cannot be the same as the old username.");
                        count++;
                        continue;
                    }
                    System.out.println("This username is already in use.");
                    count++;
                    break;
                }
            }
            if (count == 0) {
                flag = false;
            }
        } while (flag);
        account.setUsername(newUsername);
        System.out.println("Change username successfully");
    }

    public void changeEmail(Scanner sc, Account account) {
        checkPassword(sc, account);
        System.out.println("Enter new email: ");
        String newEmail;
        String regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        boolean flag = true;
        do {
            int count = 0;
            newEmail = sc.nextLine();
            if(checkSpace(newEmail)) {
                System.out.println("Email must not contain spaces.");
                continue;
            }
            for (Account i : accounts) {
                if (newEmail.equals(i.getEmail())) {
                    if (newEmail.equals(account.getEmail())) {
                        System.out.println("The new email cannot be the same as the old email.");
                        count++;
                        continue;
                    }
                    System.out.println("E-mail is being used.");
                    count++;
                    break;
                }
            }
            if (patternMatches(newEmail, regexPattern)) {
                System.out.println("You need to enter the correct email format.");
                count++;
            }
            if (count == 0) {
                flag = false;
            }
        } while (flag);
        account.setEmail(newEmail);
        System.out.println("Change email successfully");
    }

    public void changePassword(Scanner sc, Account account) {
        checkPassword(sc, account);
        System.out.println("Enter new password: ");
        String newPassword;
        String regexPattern = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[.,-_;]).{8,15})";
        boolean flag = true;
        do {
            newPassword = sc.nextLine();
            if(checkSpace(newPassword)) {
                System.out.println("Email must not contain spaces.");
                continue;
            }
            if (newPassword.equals(account.getPassword())) {
                System.out.println("The new password cannot be the same as the old password.");
                continue;
            }
            if (patternMatches(newPassword, regexPattern)) {
                System.out.println("Password must contain from 8-15 characters, at least 1 uppercase character, 1 special character (. , - _ ;)");
                continue;
            }
            flag = false;
        } while (flag);
        account.setPassword(newPassword);
        System.out.println("Change password successfully");
    }

    private void checkPassword(Scanner sc, Account account) {
        System.out.println("Re-enter your old password");
        String oldPass;
        boolean flag = true;
        do {
            oldPass = sc.nextLine();
            if (oldPass.equals(account.getPassword())) {
                flag = false;
            } else {
                System.out.println("Incorrect password");
            }
        } while (flag);
    }

    private boolean checkEmailExisted(String email) {
        boolean flag =  false;
        for (Account i : accounts) {
            if (email.equals(i.getEmail())) {
                System.out.println("E-mail is being used.");
                flag = true;
            }
        }
        return flag;
    }


    public void logIn(Scanner sc) throws IOException {
        Account account = userLogIn(sc);
        passwordLogIn(sc, account);
    }

    public Account userLogIn(Scanner sc) {
        System.out.println("Enter username: ");
        String user;
        Account account = null;
        boolean flag = true;
        do {
            user = sc.nextLine();
            for (Account i : accounts) {
                if (user.equals(i.getUsername())) {
                    account = i;
                    flag = false;
                }
            }
            if (flag) {
                System.out.println("Check the username again.");
            }
        } while (flag);
        return account;
    }

    public void passwordLogIn(Scanner sc, Account account) throws IOException {
        System.out.println("Enter password: ");
        String password = sc.nextLine();
        if (password.equals(account.getPassword())) {
            QuesAnsLogic quesAnsLogic = new QuesAnsLogic();
            GeneralLogic generalLogic = new GeneralLogic();
            while (true) {
                showMenu();
                generalLogic.chooseMenu(sc, quesAnsLogic, account);
            }
        } else {
            System.out.println("Password is not correct");
            System.out.println("1. Re-login");
            System.out.println("2. Forgot password");
            System.out.print("Choose: ");
            int choice = checkNumberException(sc, 1, 2);
            if (choice == 1) {
                logIn(sc);
            }
            if (choice == 2) {
                checkEmailForgetMode(sc, account);
            }
        }

    }

    private void checkEmailForgetMode(Scanner sc, Account account) {
        boolean flag = true ;
        do {
            System.out.println("Re-enter your registered email: ");
            String email = sc.nextLine();
            if (email.equals(account.getEmail())) {
                changePasswordForgetMode(sc,account);
                flag = false;
            }
            if (flag) {
                System.out.println("The email you just entered does not match the registered email");
            }
        } while (flag);
    }

    private void changePasswordForgetMode(Scanner sc, Account account) {
        System.out.println("Enter new password: ");
        String newPassword;
        String regexPattern = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[.,-_;]).{8,15})";
        boolean flag = true;
        do {
            newPassword = sc.nextLine();
            if(checkSpace(newPassword)) {
                System.out.println("Email must not contain spaces.");
                continue;
            }
            if (newPassword.equals(account.getPassword())) {
                System.out.println("The new password cannot be the same as the old password.");
                continue;
            }
            if (patternMatches(newPassword, regexPattern)) {
                System.out.println("Password must contain from 8-15 characters, at least 1 uppercase character, 1 special character (. , - _ ;)");
                continue;
            }
            flag = false;
        } while (flag);
        account.setPassword(newPassword);
        System.out.println("Change password successfully");
    }
}
