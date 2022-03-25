package carsharing;

import java.util.List;
import java.util.Scanner;

public class ManagerMenu extends Menu {
    public ManagerMenu(Scanner scanner, CompanyDao companyDao, CarDao carDao, Menu previousMenu) {
        super(scanner, companyDao, carDao, null, previousMenu);
        this.actionList.add(new Action("Back", () -> {
        }));
        this.actionList.add(new Action("Company list", () -> {

        }));
        this.actionList.add(new Action("Create a company", () -> {
            System.out.println("Enter the company name:");
            String companyName = scanner.nextLine();
            companyDao.addCompany(new Company(companyName));
            System.out.println("The company was created!");
            System.out.println("");
        }));
    }

    @Override
    public void printMenu() {
        super.printMenu();
    }

    @Override
    public Menu selectAction(int index) {
        if (index == 0) {
            return actionList.get(index).execute(previousMenu);
        } else if (index == 1){
            if (companyDao.getAllCompanies().size() > 0) {
                return actionList.get(index).execute(new CompanyListMenu(scanner, companyDao, carDao,  this));
            } else {
                System.out.println("The company list is empty!");
                return actionList.get(index).execute(this);
            }
        } else {
            return actionList.get(index).execute(this);
        }
    }
}
