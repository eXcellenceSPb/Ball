package Balls;

import Balls.Game.GamePanel;

import javax.swing.JFrame;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class GameStart {

    private static String URL;// = "jdbc:mysql://localhost:3306/balls";
    private static String Login;// = "root";
    private static String Pass;// = "";

    public static Connection connection;
    public static Statement statement;
//    public static ResultSet resultSet;


    public static void main(String[] args) throws IOException {
        Properties props = new Properties();
        props.load(new FileInputStream(new File("src/config.ini")));
        URL = props.getProperty("URL");
        Login = props.getProperty("Login");
        Pass = props.getProperty("Pass");
//        String query = "SELECT * FROM `balls`";
        try{
            connection = DriverManager.getConnection(URL,Login,Pass);
            statement = connection.createStatement();
//            resultSet = statement.executeQuery(query);
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
