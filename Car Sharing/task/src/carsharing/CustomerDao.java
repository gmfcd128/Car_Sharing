package carsharing;

import java.util.List;

public interface CustomerDao {
    public List<Customer> getAllCustomers();
    public void addCustomer(Customer customer);
    public void updateCustomer(Customer customer);
}
