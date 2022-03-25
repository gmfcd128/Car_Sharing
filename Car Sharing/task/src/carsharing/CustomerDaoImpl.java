package carsharing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static String DB_URL = "jdbc:h2:./src/carsharing/db/data";
    Connection conn = null;
    Statement stmt = null;

    public CustomerDaoImpl() {
        initDatabase();
    }

    public CustomerDaoImpl(String dbFileName) {
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
            String sql = "CREATE TABLE IF NOT EXISTS CUSTOMER " +
                    "(ID INT PRIMARY KEY AUTO_INCREMENT, " +
                    " NAME VARCHAR(255) UNIQUE NOT NULL, " +
                    " RENTED_CAR_ID INT, " +
                    " FOREIGN KEY (RENTED_CAR_ID) REFERENCES CAR(ID));";
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
    public List<Customer> getAllCustomers() {
        List<Customer> result = new ArrayList<>();
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL);

            // STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql = "SELECT * FROM CUSTOMER";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while(rs.next()) {
                // Retrieve by column name
                int id  = rs.getInt("ID");
                String name = rs.getString("NAME");
                int rentedCarId = rs.getInt("RENTED_CAR_ID");
                result.add(new Customer(id, name, rentedCarId));
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
    public void addCustomer(Customer customer) {
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL);
            conn.setAutoCommit(true);

            //STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql = "INSERT INTO CUSTOMER (NAME, RENTED_CAR_ID) VALUES(\'" + customer.getName() + "\', NULL);";
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
    public void updateCustomer(Customer customer) {
        String sql;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL);
            conn.setAutoCommit(true);

            //STEP 3: Execute a query
            stmt = conn.createStatement();
            if (customer.getRentedCarId() != 0) {
                sql = "UPDATE CUSTOMER SET RENTED_CAR_ID=" + customer.getRentedCarId() + "WHERE ID=" + customer.getId() + ";";
            } else {
                sql = "UPDATE CUSTOMER SET RENTED_CAR_ID=NULL WHERE ID=" + customer.getId() + ";";
            }
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


}
