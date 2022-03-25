package carsharing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    static CompanyDaoImpl companyDao;
    static CarDaoImpl carDao;
    static CustomerDaoImpl customerDao;
    static Scanner scanner;

    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-databaseFileName")) {
                companyDao = new CompanyDaoImpl(args[i + 1]);
                carDao = new CarDaoImpl(args[i + 1]);
                customerDao = new CustomerDaoImpl(args[i + 1]);
            }
        }
        if (companyDao == null) {
            companyDao = new CompanyDaoImpl();
        }
        if (carDao == null) {
            carDao = new CarDaoImpl();
        }
        scanner = new Scanner(System.in);
        Menu menu = new MainMenu(scanner, companyDao, carDao, customerDao, null);
        while (menu != null) {
            menu.printMenu();
            int option = Integer.parseInt(scanner.nextLine());
            System.out.println("");
            menu = menu.selectAction(option);
        }


    }
}