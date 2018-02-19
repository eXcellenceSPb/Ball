package Balls;

import javax.swing.JFrame;
import java.sql.*;

public class GameStart {
    private static final String url = "jdbc:mysql://localhost:3306/balls";
    private static final String login = "root";
    private static final String Pass = "";

    public static Connection connection;
    public static Statement statement;
    public static ResultSet resultSet;


    public static void main(String[] args) throws SQLException, InterruptedException {

        String query = "SELECT * FROM `balls`";
        try{
            connection = DriverManager.getConnection(url,login,Pass);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
//            if (resultSet.next()){
//                int count = 0;
//                count = resultSet.getInt(1);
//                String count1 = resultSet.getString(2);
//                System.out.println("Id: "+count + "; Результат: "+count1+";");
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        GamePanel panel = new GamePanel();
        JFrame startFrame = new JFrame("Bubble");
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        startFrame.setContentPane(panel);
        startFrame.pack();
        startFrame.setLocationRelativeTo(null);
        startFrame.setVisible(true);
        panel.start();
    }
}
