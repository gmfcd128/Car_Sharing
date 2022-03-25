package carsharing;

import java.util.List;
import java.util.Scanner;

public class CarListMenu extends Menu{
    List<Car> availableCars;

    public CarListMenu(Scanner scanner, CustomerDao customerDao, CarDao carDao, Customer customer, Company company, Menu previousMenu) {
        super(scanner, null, carDao, customerDao, previousMenu);
        availableCars = carDao.getAvailableCarInCompany(company);
        if (availableCars.size() > 0) {
            this.actionList.add(new Action("Back", () -> {
            }));
            for (int i = 0; i < availableCars.size(); i++) {
                //System.out.println(i + 1 + ". " + result.get(i).getName());
                int finalI = i;
                this.actionList.add(new Action(availableCars.get(i).getName(), () -> {
                    customer.setRentedCarId(availableCars.get(finalI).getId());
                    customerDao.updateCustomer(customer);
                    System.out.println("You rented \'" + availableCars.get(finalI).getName() + "\'");
                }));
            }
        } else {
            System.out.println("No available cars in the " + company.getName()  + " company");
        }

    }

    @Override
    public void printMenu() {
        System.out.println("Choose a car:");
        super.printMenu();
    }

    @Override
    public Menu selectAction(int index) {
        return actionList.get(index).execute(previousMenu);
    }
}
