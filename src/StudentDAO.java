import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

class StudentDAO {
    private static StudentDAO dao;

    private final String url = "jdbc:mysql://localhost:3306/demo";
    private final String user = "root";
    private final String pswd = "db006";

    private Connection connection;

    private Statement s;
    private PreparedStatement ps;
    private ResultSet rs;

    private StudentDAO() {
        connectDb();
    }

    public static StudentDAO getInstance() {
        if (dao == null)
            dao = new StudentDAO();
        return dao;
    }

    public void connectDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pswd);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void addStudent() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String query = "insert into stud values(?,?,?)";
        String name = "";

        int age = 0;
        int fees = 0;
        System.out.println("enter name");
        name = br.readLine();

        System.out.println("enter age");
        age = Integer.parseInt(br.readLine());

        System.out.println("enter fees");
        fees = Integer.parseInt(br.readLine());

        ps = connection.prepareStatement(query);
        ps.setString(1, name);
        ps.setInt(2, age);
        ps.setInt(3, fees);
        ps.executeUpdate();
    }

    public void deleteStudent() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String name = "";
        String query = "delete from stud where name=?";

        System.out.println("enter name");
        name = br.readLine();

        ps = connection.prepareStatement(query);
        ps.setString(1, name);
        ps.executeUpdate();
    }

    public void updateStudent() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int option = 0;

        System.out.println("choose field to update: ");
        System.out.println("1. Fees");
        System.out.println("2. Name");
        System.out.println("3. Age");

        option = Integer.parseInt(br.readLine());

        switch (option) {
            case 1:
                updateFees();
                break;
            case 2:
                updateName();
                break;
            case 3:
                updateAge();
                break;
            default:
                System.err.println("Invalid Option, try Again!");
        }
    }

    public void updateFees() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String query = "update stud set fees=? where name=?";
        String name = "";

        int fees = 0;

        System.out.println("enter name");
        name = br.readLine();

        System.out.println("enter new fee");
        fees = Integer.parseInt(br.readLine());

        ps = connection.prepareStatement(query);
        ps.setInt(1, fees);
        ps.setString(2, name);
        ps.executeUpdate();
    }

    public void updateName() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String query = "update stud set name=? where name=?";
        String name = "";

        String newName = "";

        System.out.println("enter name");
        name = br.readLine();

        System.out.println("enter new name");
        newName = br.readLine();

        ps = connection.prepareStatement(query);
        ps.setString(1, newName);
        ps.setString(2, name);
        ps.executeUpdate();
    }

    public void updateAge() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String query = "update stud set age=? where name=?";
        String name = "";

        int age = 0;

        System.out.println("enter name");
        name = br.readLine();

        System.out.println("enter new age");
        age = Integer.parseInt(br.readLine());

        ps = connection.prepareStatement(query);
        ps.setInt(1, age);
        ps.setString(2, name);
        ps.executeUpdate();
    }

    public void getFee() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String query = "select fees from stud where name='";
        String name = "";

        System.out.println("enter name");
        name = br.readLine();

        s = connection.createStatement();
        query = query + name + "'";
        rs = s.executeQuery(query);
        rs.next();
        System.out.println("******************************************************");
        System.out.println("Name : " + name);
        System.out.println("Fees   : " + rs.getInt(1));
        System.out.println("******************************************************");
    }

    public void getAllStudents() throws SQLException {
        String query = "select * from stud";
        s = connection.createStatement();
        rs = s.executeQuery(query);
        System.out.println("******************************************************");
        while (rs.next()) {
            System.out.println("Name : " + rs.getString(1));
            System.out.println("Age    : " + rs.getInt(2));
            System.out.println("Fee     : " + rs.getInt(3));
            System.out.println("******************************************************");
        }
    }
}