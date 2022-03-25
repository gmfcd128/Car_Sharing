package carsharing;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Menu {
    protected Scanner scanner;
    protected CompanyDao companyDao;
    protected CarDao carDao;
    protected CustomerDao customerDao;
    protected Menu previousMenu;
    protected List<Action> actionList;
    public Menu(Scanner scanner, CompanyDao companyDao, CarDao carDao,CustomerDao customerDao,  Menu previousMenu) {
        this.scanner = scanner;
        this.companyDao = companyDao;
        this.carDao = carDao;
        this.customerDao = customerDao;
        this.previousMenu = previousMenu;
        this.actionList = new ArrayList<>();
    }
    public void printMenu() {
        for (int i = 1; i < actionList.size(); i++) {
            System.out.println(Integer.toString(i) + ". " + actionList.get(i).getLabel());
        }
        System.out.println(Integer.toString(0) + ". " + actionList.get(0).getLabel());
    }
    public abstract Menu selectAction(int index);
}
