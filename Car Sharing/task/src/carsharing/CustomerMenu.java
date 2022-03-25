package carsharing;

import java.util.Scanner;

public class CustomerMenu extends Menu {
    Customer customer;

    public CustomerMenu(Scanner scanner, CompanyDao companyDao, CarDao carDao, CustomerDao customerDao, Customer customer, Menu previousMenu) {
        super(scanner, companyDao, carDao, customerDao, previousMenu);
        this.customer = customer;
        this.actionList.add(new Action("Back", () -> {

        }));
        this.actionList.add(new Action("Rent a car", () -> {

        }));
        this.actionList.add(new Action("Return a rented car", () -> {
            if (customer.getRentedCarId() == 0) {
                System.out.println("You didn't rent a car!");
            } else {
                customer.setRentedCarId(0);
                customerDao.updateCustomer(customer);
                System.out.println("You've returned a rented car!");
            }
        }));
        this.actionList.add(new Action("My rented car", () -> {
            if (customer.getRentedCarId() == 0) {
                System.out.println("You didn't rent a car!");
            } else {
                Car rentedCar = carDao.getCarById(customer.getRentedCarId());
                System.out.println("Your rented car:");
                System.out.println(rentedCar.getName());
                System.out.println("Company:");
                System.out.println(companyDao.getCompanyById(rentedCar.getCompanyId()).getName());
                System.out.println("");
            }
        }));

    }

    @Override
    public Menu selectAction(int index) {
        if (index == 0) {
            return actionList.get(index).execute(previousMenu);
        } else if (index == 1) {
            if (customer.getRentedCarId() != 0) {
                System.out.println("You've already rented a car!");
                return actionList.get(index).execute(this);
            } else {
                return actionList.get(index).execute(new CompanyListMenu(scanner, companyDao, customerDao, carDao, this.customer, this));
            }
        } else {
            return actionList.get(index).execute(this);
        }
    }
}
