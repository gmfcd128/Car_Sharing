package carsharing;

import java.util.List;
import java.util.Scanner;

public class CustomerListMenu extends Menu{
    private List<Customer> customerList;

    @Override
    public void printMenu() {
        System.out.println("Choose a customer:");
        super.printMenu();
    }

    public CustomerListMenu(Scanner scanner, CompanyDao companyDao, CarDao carDao, CustomerDao customerDao, Menu previousMenu) {
        super(scanner, companyDao, carDao, customerDao, previousMenu);
        this.actionList.add(new Action("Back", () -> {
        }));
        customerList = customerDao.getAllCustomers();
        if (customerList.size() > 0) {
            for (int i = 0; i < customerList.size(); i++) {
                //System.out.println(i + 1 + ". " + result.get(i).getName());
                this.actionList.add(new Action(customerList.get(i).getName(), () -> {

                }));
            }
        } else {
            System.out.println("The customer list is empty!");
        }
    }

    @Override
    public Menu selectAction(int index) {
        if (index == 0) {
            return actionList.get(index).execute(previousMenu);
        } else {
            return actionList.get(index).execute(new CustomerMenu(scanner, companyDao, carDao,  customerDao, customerList.get(index - 1), previousMenu));
        }
    }
}
