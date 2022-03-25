package carsharing;

import java.util.List;

public interface CompanyDao {
    public List<Company> getAllCompanies();
    public Company getCompanyById(int companyId);
    public void addCompany(Company company);
    public void updateCompany(Company company);
    public void deleteCompany(Company company);
}
