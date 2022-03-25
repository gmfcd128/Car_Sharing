package carsharing;


import java.util.Scanner;

public class MainMenu extends Menu {
    public MainMenu(Scanner scanner, CompanyDao companyDao, CarDao carDao, CustomerDao customerDao, Menu previousMenu) {
        super(scanner, companyDao, carDao, customerDao, previousMenu);
        this.actionList.add(new Action("Exit", () -> {
        }));
        this.actionList.add(new Action("Log in as a manager", () -> {

        }));
        this.actionList.add(new Action("Log in as a customer", () -> {

        }));
        this.actionList.add(new Action("Create a customer", () -> {
            System.out.println("Enter the customer name:");
            String customerName = scanner.nextLine();
            customerDao.addCustomer(new Customer(customerName));
            System.out.println("The customer was added!");
        }));
    }

    @Override
    public void printMenu() {
        super.printMenu();
    }

    @Override
    public Menu selectAction(int index) {
        if (index == 0) {
            return actionList.get(index).execute(null);
        } else if (index == 1) {
            return actionList.get(index).execute(new ManagerMenu(scanner, companyDao, carDao, this));
        } else if (index == 2) {
            if (customerDao.getAllCustomers().size() == 0) {
                System.out.println("The customer list is empty!");
                return actionList.get(index).execute(this);
            } else {
                return actionList.get(index).execute(new CustomerListMenu(scanner, companyDao, carDao, customerDao, this));
            }
        } else if (index == 3) {
            return actionList.get(index).execute(this);
        }
        return actionList.get(index).execute(null);
    }
}
