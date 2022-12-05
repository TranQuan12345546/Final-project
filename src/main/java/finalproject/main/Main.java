package finalproject.main;


import finalproject.entity.*;
import finalproject.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<QuesAnsDetail> quesAnsDetailArrayList = new ArrayList<>();
    public static ArrayList<Account> accounts = new ArrayList<>();
    public static boolean mode = true;
    public static void main(String[] args) {
        try {
            ExcelWriterAccount excelWriterAccount = new ExcelWriterAccount();
            accounts = (ArrayList<Account>) excelWriterAccount.readExcel(accounts);
            ExcelWriterJava excelWriterJava = new ExcelWriterJava();
            quesAnsDetailArrayList = (ArrayList<QuesAnsDetail>) excelWriterJava.readExcel(quesAnsDetailArrayList);
            Scanner sc = new Scanner(System.in);
            View view = new View();
            view.mainMenu(sc);
        } catch (IOException e) {
            System.out.println("You need to close all excel table first.");
        }
    }
}
