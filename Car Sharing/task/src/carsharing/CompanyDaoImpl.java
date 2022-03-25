package carsharing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDaoImpl implements CompanyDao {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static String DB_URL = "jdbc:h2:./src/carsharing/db/data";
    Connection conn = null;
    Statement stmt = null;

    public CompanyDaoImpl() {
        initDatabase();
    }

    public CompanyDaoImpl(String dbFileName) {
        DB_URL = "jdbc:h2:./src/carsharing/db/" + dbFileName;
        initDatabase();
    }

    private void initDatabase() {
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL);
            conn.setAutoCommit(true);

            //STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS COMPANY " +
                    "(ID INT PRIMARY KEY AUTO_INCREMENT, " +
                    " NAME VARCHAR(255) UNIQUE NOT NULL);";
            stmt.executeUpdate(sql);
            sql = "ALTER TABLE company ALTER COLUMN id RESTART WITH 1;";
            stmt.executeUpdate(sql);
            // STEP 4: Clean-up environment
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            } // nothing we can do
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } //end finally try
        } //end try
    }

    @Override
    public List<Company> getAllCompanies() {
        List<Company> result = new ArrayList<>();
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL);

            // STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql = "SELECT * FROM COMPANY";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while(rs.next()) {
                // Retrieve by column name
                int id  = rs.getInt("ID");
                String name = rs.getString("NAME");
                result.add(new Company(id, name));
            }
            // STEP 5: Clean-up environment
            rs.close();
        } catch(SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try
        return result;
    }

    @Override
    public Company getCompanyById(int companyId) {
        Company result = null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL);

            // STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql = "SELECT * FROM COMPANY WHERE ID=" + companyId + ";";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while(rs.next()) {
                // Retrieve by column name
                int id  = rs.getInt("ID");
                String name = rs.getString("NAME");
                result = new Company(id, name);
            }
            // STEP 5: Clean-up environment
            rs.close();
        } catch(SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try
        return result;
    }

    @Override
    public void addCompany(Company company) {
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL);
            conn.setAutoCommit(true);

            //STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql = "INSERT INTO COMPANY (NAME) VALUES(\'" + company.getName() + "\');";
            stmt.executeUpdate(sql);

            // STEP 4: Clean-up environment
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            } // nothing we can do
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } //end finally try
        } //end try
    }

    @Override
    public void updateCompany(Company company) {

    }

    @Override
    public void deleteCompany(Company company) {

    }
}
