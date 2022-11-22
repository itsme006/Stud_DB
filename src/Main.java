import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static StudentDAO dao = StudentDAO.getInstance();

    public static void main(String[] args) throws Exception {
        while (true) {
            performOperation();
        }
    }

    public static void operation() {
        System.out.println("Select Operation:");
        System.out.println("1. Insert Student");
        System.out.println("2. Delete Student");
        System.out.println("3. Update Student");
        System.out.println("4. Get Fee of Student");
        System.out.println("5. All Students");
        System.out.println("6. Exit");
    }

    public static int chooseOperation() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        operation();
        try {
            int option = Integer.parseInt(br.readLine());
            return option;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void performOperation() throws Exception {
        int option = chooseOperation();
        switch (option) {
            case 1:
                dao.addStudent();
                System.err.println("******************************************************");
                break;
            case 2:
                dao.deleteStudent();
                System.err.println("******************************************************");
                break;
            case 3:
                dao.updateStudent();
                System.err.println("******************************************************");
                break;
            case 4:
                dao.getFee();
                System.err.println("******************************************************");
                break;
            case 5:
                dao.getAllStudents();
                System.err.println("******************************************************");
                break;
            case 6:
                System.exit(0);
            default:
                System.err.println("Invalid Option, try Again!");
                System.err.println("******************************************************");
        }
    }
}