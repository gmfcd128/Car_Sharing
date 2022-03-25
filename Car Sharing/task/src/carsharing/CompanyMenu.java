package carsharing;

import java.util.List;
import java.util.Scanner;

public class CompanyMenu extends Menu{
    private Company company;
    public CompanyMenu(Scanner scanner, CompanyDao companyDao, CarDao carDao, Company company, Menu previousMenu) {
        super(scanner, companyDao, carDao, null, previousMenu);
        this.company = company;
        this.actionList.add(new Action("Back", () -> {

        }));
        this.actionList.add(new Action("Car list", () -> {
            List<Car> carList = carDao.getCarsByCompany(this.company);
            if (carList.size() > 0) {
                System.out.println("Car list:");
                for (int i = 0; i < carList.size(); i++) {
                    System.out.println(i + 1 + ". " + carList.get(i).getName());
                }
            } else {
                System.out.println("The car list is empty!");

            }
            System.out.println("");
        }));
        this.actionList.add(new Action("Create a car", () -> {
            System.out.println("Enter the car name:");
            String carName = scanner.nextLine();
            carDao.createCar(new Car(carName), this.company);
            System.out.println("The car was added!");
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
        } else {
            return actionList.get(index).execute(this);
        }
    }
}
