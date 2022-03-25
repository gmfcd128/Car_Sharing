package carsharing;

import java.util.List;

public interface CarDao {
    public List<Car> getCarsByCompany(Company company);
    public List<Car> getAvailableCarInCompany(Company company);
    public void createCar(Car car, Company company);
    public Car getCarById(int id);
}
