package carsharing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDaoImpl implements CarDao{
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static String DB_URL = "jdbc:h2:./src/carsharing/db/data";
    Connection conn = null;
    Statement stmt = null;

    public CarDaoImpl() {
        initDatabase();
    }

    public CarDaoImpl(String dbFileName) {
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
            String sql = "CREATE TABLE IF NOT EXISTS CAR " +
                    "(ID INT PRIMARY KEY AUTO_INCREMENT, " +
                    " NAME VARCHAR(255) UNIQUE NOT NULL, " +
                    " COMPANY_ID INT NOT NULL," +
                    " FOREIGN KEY (COMPANY_ID) REFERENCES COMPANY(ID));";
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
    public List<Car> getCarsByCompany(Company company) {
        List<Car> result = new ArrayList<>();
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL);

            // STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql = "SELECT * FROM CAR WHERE COMPANY_ID=" + company.getId();
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while(rs.next()) {
                // Retrieve by column name
                int id  = rs.getInt("ID");
                String name = rs.getString("NAME");
                int companyId = rs.getInt("COMPANY_ID");
                result.add(new Car(id, name, companyId));
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
    public List<Car> getAvailableCarInCompany(Company company) {
        List<Car> result = new ArrayList<>();
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL);

            // STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql = "SELECT car.id, car.name, car.company_id " +
                    " FROM car LEFT JOIN customer " +
                    " ON car.id = customer.rented_car_id " +
                    " WHERE customer.name IS NULL AND car.company_id=" + company.getId() + ";";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while(rs.next()) {
                // Retrieve by column name
                int id  = rs.getInt("ID");
                String name = rs.getString("NAME");
                int companyId = rs.getInt("COMPANY_ID");
                result.add(new Car(id, name, companyId));
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
    public void createCar(Car car, Company company) {
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL);
            conn.setAutoCommit(true);

            //STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql = "INSERT INTO CAR (NAME, COMPANY_ID) VALUES(\'" + car.getName() + "\'," + company.getId() +");";
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
    public Car getCarById(int carId) {
        Car result = null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL);

            // STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql = "SELECT * FROM CAR WHERE ID=" + carId;
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while(rs.next()) {
                // Retrieve by column name
                int id  = rs.getInt("ID");
                String name = rs.getString("NAME");
                int companyId = rs.getInt("COMPANY_ID");
                result = new Car(id, name, companyId);
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
}
