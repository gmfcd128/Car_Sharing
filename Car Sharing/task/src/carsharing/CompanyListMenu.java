package carsharing;

import java.util.List;
import java.util.Scanner;

public class CompanyListMenu extends Menu{
    private List<Company> companyList;
    private Customer customer;
    public CompanyListMenu(Scanner scanner, CompanyDao companyDao, CarDao carDao, Menu previousMenu) {
        super(scanner, companyDao, carDao, null, previousMenu);
        companyList = companyDao.getAllCompanies();
        if (companyList.size() > 0) {
            this.actionList.add(new Action("Back", () -> {
            }));
            for (int i = 0; i < companyList.size(); i++) {
                //System.out.println(i + 1 + ". " + result.get(i).getName());
                this.actionList.add(new Action(companyList.get(i).getName(), () -> {

                }));
            }
        } else {
            System.out.println("The company list is empty!");
        }
    }

    public CompanyListMenu(Scanner scanner, CompanyDao companyDao, CustomerDao customerDao, CarDao carDao, Customer customer, Menu previousMenu) {
        super(scanner, companyDao, carDao, customerDao, previousMenu);
        this.customer = customer;
        companyList = companyDao.getAllCompanies();
        if (companyList.size() > 0) {
            this.actionList.add(new Action("Back", () -> {
            }));
            for (int i = 0; i < companyList.size(); i++) {
                //System.out.println(i + 1 + ". " + result.get(i).getName());
                this.actionList.add(new Action(companyList.get(i).getName(), () -> {

                }));
            }
        } else {
            System.out.println("The company list is empty!");
        }
    }

    @Override
    public void printMenu() {
        System.out.println("Choose a company:");
        super.printMenu();
    }

    @Override
    public Menu selectAction(int index) {
        if (index == 0) {
            return actionList.get(index).execute(previousMenu);
        } else if (previousMenu instanceof CustomerMenu){
            return actionList.get(index).execute(new CarListMenu(scanner, customerDao, carDao, this.customer, companyList.get(index - 1), previousMenu));
        } else {
            System.out.println("\'" + companyList.get(index - 1).getName() + "\' company:");
            return actionList.get(index).execute(new CompanyMenu(scanner, companyDao, carDao, companyList.get(index - 1), previousMenu));
        }
    }
}
