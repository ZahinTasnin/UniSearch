import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Timer;

public class Login {
    private JFrame frame;
    private JPanel panel;
    private JButton logIn;
    private JLabel user;
    private JTextField userName;
    private JButton signUp;
    private JLabel password;
    private JPasswordField passwordField;
    private static ArrayList<User> userList;
    private JPanel panel1;

    public Login(){

        userList = new ArrayList<User>();
        //frame
        frame = new JFrame();
        frame.setTitle("UniSearch");
        frame.setSize(600,400);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        logIn = new JButton("LOGIN");
        signUp = new JButton("SIGNUP");
        user = new JLabel("USERNAME:");
        password = new JLabel("PASSWORD:");
        userName = new JTextField();
        passwordField = new JPasswordField();

        user.setFont(new Font("Serif", Font.BOLD, 23));
        password.setFont(new Font("Serif", Font.BOLD, 23));
        logIn.setFont(new Font("Serif", Font.ITALIC|Font.BOLD, 18));
        logIn.setForeground(Color.WHITE);
        logIn.setBackground(Color.BLUE);
        signUp.setFont(new Font("Serif", Font.ITALIC|Font.BOLD, 18));
        signUp.setForeground(Color.WHITE);
        signUp.setBackground(Color.BLUE);
        userName.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 15));


        panel = new JPanel();
        panel.setLayout(null);
        panel.add(user);
        panel.add(password);
        panel.add(userName);
        panel.add(passwordField);
        panel.add(logIn);
        panel.add(signUp);
        panel.setBackground(new Color(232,244,250));


        signUp.addActionListener(this::actionPerformed);
        signUp.setFocusable(false);
        logIn.addActionListener(this::actionPerformed);
        logIn.setFocusable(false);

        //size
        user.setBounds(20, 20, 200, 30);
        password.setBounds(20, 50, 200, 30);
        passwordField.setBounds(120, 50, 300, 35);
        userName.setBounds(120, 20, 300, 35);
        logIn.setBounds(20, 20, 100, 30);
        signUp.setBounds(20, 20, 120, 30);

        //location
        user.setLocation(50,100);
        password.setLocation(50, 200);
        userName.setLocation(220, 100);
        passwordField.setLocation(220, 200);
        logIn.setLocation(100, 300);
        signUp.setLocation(350, 300);

        frame.add(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        signUp.setVisible(false);
        logIn.setVisible(false);
        JButton src = (JButton) event.getSource();
        JLabel success = new JLabel("");
        success.setBounds(50, 20, 1000, 80);
        success.setVisible(true);
        panel.add(success);
        if (src.getText().equals("SIGNUP")) {
            String name = userName.getText();
            String password = String.valueOf(passwordField);
            boolean add = true;
            for (User user2 : userList) {
                if (user2.getName().equals(name)) {
                    add = false;
                }
            }
            if (add) {
                userList.add(new User(name, password));
                success.setText("   S U C C E S S F U L");
                ImageIcon image = new ImageIcon("smiley.png");
                Image image1 = image.getImage().getScaledInstance(100, 60, 1000);
                image = new ImageIcon(image1);
                success.setIcon(image);
                success.setHorizontalTextPosition(JLabel.RIGHT);
                success.setForeground(new Color(40, 100, 10));
                success.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 25));
                Thread thread = new Thread(() -> {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    success.setVisible(false);
                    signUp.setVisible(true);
                    logIn.setVisible(true);
                });
                thread.start();
                userName.setText("");
                passwordField.setText("");

            } else {
                success.setText("   U N S U C C E S S F U L");
                ImageIcon image = new ImageIcon("sad.png");
                Image image1 = image.getImage().getScaledInstance(60, 60, 1000);
                image = new ImageIcon(image1);
                success.setIcon(image);

                success.setForeground(new Color(250, 30, 50));
                success.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 25));

                Thread thread = new Thread(() -> {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    success.setVisible(false);
                    signUp.setVisible(true);
                    logIn.setVisible(true);
                });
                thread.start();
                userName.setText("");
                passwordField.setText("");
            }
        } else if (src.getText().equals("LOGIN")) {
            logIn.setVisible(false);
            String name = (String) userName.getText();
            String password = String.valueOf(passwordField);
            System.out.println(name + password);
            boolean add = false;
            for (User user2 : userList) {
                if (user2.getName().equals(name) && user2.getPassword().equals(password)) {
                    add = true;
                }
            }
            if (add==false) {
                success.setText("   U N S U C C E S S F U L");
                ImageIcon image = new ImageIcon("sad.png");
                Image image1 = image.getImage().getScaledInstance(60, 60, 1000);
                image = new ImageIcon(image1);
                success.setIcon(image);

                success.setForeground(new Color(250, 30, 50));
                success.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 30));

                Thread thread = new Thread(() -> {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    success.setVisible(false);
                    logIn.setVisible(true);
                    signUp.setVisible(true);
                });
                thread.start();
                userName.setText("");
                passwordField.setText("");
            } else{
                success.setText("Successful. LOADING........");
                success.setForeground(new Color(40, 100, 10));
                success.setFont(new Font("Serif", Font.BOLD, 20));
                Thread thread = new Thread(() -> {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    new Search();
                    success.setVisible(false);
                    logIn.setVisible(true);
                    signUp.setVisible(true);
                });
                thread.start();
            }
        }


    }
    public static void main(String[]args){
        new Login();
    }

}
