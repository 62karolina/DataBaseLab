import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Main {

    private static final String url = "jdbc:mysql://localhost:3306/armzo";
    private static final String user = "root";
    private static final String password = "123";

    // JDBC variables for opening and managing connection
    private static Connection con;
    static Statement st = null;
    static Object[] names = {"Id", "Staff", "Lodger"};

    private static void SQLConnection() {
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createGUI(ResultSet rs) {

        try {
            JPanel panel = new JPanel(new FlowLayout());
            DatabaseTableModel model = new DatabaseTableModel();
            model.setDataSource(rs);
            JTable table = new JTable(model);

            panel.add(new JScrollPane(table), BorderLayout.CENTER);

            JFrame frame = new JFrame("Database Table Model");
            frame.setLocationRelativeTo(null);
            frame.setSize(500, 600);
            frame.setContentPane(panel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        SQLConnection();

        Statement st = null;
        try {


            String query = "SELECT * FROM dorm";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            createGUI(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
