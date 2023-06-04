import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class Search implements MouseListener, FocusListener, ActionListener {
    private JButton search;
    private JButton add;
    private JButton remove;
    private JButton viewList;
    private JTextField searchField;
    private ImageIcon imageIcon;
    private JFrame frame;
    private JPanel panel;
    private JButton school;
    private JPanel panel1;
    private JScrollPane scrollPane;
    private ArrayList<Unis> topSchools;
    private JPanel panel2;
    private  JLabel searchResult;



    public Search(){

        //building frame
        frame = new JFrame("University Search");
        frame.setSize(600, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);


        //menu field initialization
        panel1 = new JPanel();
        panel2 = new JPanel();
        search = new JButton("Search");
        add = new JButton (" Add");
        remove = new JButton(" Remove");
        viewList = new JButton(" View List");
        searchField = new JTextField("Enter number");
        imageIcon = new ImageIcon("search.png.png");
        searchResult = new JLabel("");
        Image image1 = imageIcon.getImage().getScaledInstance(20, 20, 100);
        imageIcon = new ImageIcon(image1);

        //menu field button design
        menuFieldDesign();

        //menu field button size, location, & border
        panel1.setBounds(0,0,600,50);
        panel2.setBounds(0,50,600,40);
        panel1.setBorder(new EmptyBorder(5,0,5,0));

        //empty border
        emptyBorder();

        panel2.setBackground(Color.pink);
        search.setPreferredSize(new Dimension(90,30));
        search.setBounds(300,10,90,30);
        add.setPreferredSize(new Dimension(55,30));
        remove.setPreferredSize(new Dimension(80,30));
        viewList.setPreferredSize(new Dimension(85,30));
        searchField.setPreferredSize(new Dimension(150,30));

        panel1.setLayout(null);


        searchField.setBounds(75,10,120,30);
        search.setBounds(206,10,85,30);
        add.setBounds(298,10,45,30);
        remove.setBounds(349,10,75,30);
        viewList.setBounds(430,10,80,30);


        panel1.add(searchField);
        panel1.add(search);
        panel1.add(add);
        panel1.add(remove);
        panel1.add(viewList);
        panel2.add(searchResult);


        //actionListener
        searchField.addFocusListener( this);
        search.addActionListener(this);
        panel1.addMouseListener(this);

        //search panel
        panel = new JPanel(new GridLayout(98,1));
        topSchools = top98();

        for(int i= 0; i<topSchools.size(); i++){
            school = new JButton(topSchools.get(i).getName());
            school.setPreferredSize(new Dimension(400,40));
            school.setHorizontalAlignment(JButton.LEFT);
            school.setFocusable(false);
            school.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
            school.setVisible(true);
            if(i%2==0){
                school.setBackground(new Color(204, 213, 174));
            } else{
                school.setBackground(new Color(233, 237, 201));
            }
            panel.add(school);
            school.addMouseListener(this);
        }
        scrollPane = new JScrollPane(panel);
        JPanel contentPane = new JPanel(null);
        scrollPane.setBounds(75,50, 450, 440);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        contentPane.setPreferredSize(new Dimension(500,500));
        contentPane.add(scrollPane);
        contentPane.setBounds(0,70,600,500);

        frame.add(panel1, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.CENTER);
        frame.add(contentPane, BorderLayout.SOUTH);
        contentPane.setBackground(new Color(254, 250, 224));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.requestFocusInWindow();

    }

    public static void main(String[]args){
        new Search();
    }
    private ArrayList<Unis> top98(){
        ArrayList<Unis> unisArrayList = new ArrayList<Unis>();
        String fileName = "src\\top98.csv";
        BufferedReader reader  = null;
        String line = "";
        try{
            reader = new BufferedReader(new FileReader(fileName));
            while((line = reader.readLine()) != null){
                String[] college = line.split(",");
                Unis uni = new Unis(college[0], college[1]);
                unisArrayList.add(uni);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return unisArrayList;
    }

    private String findURL(String name){
        for(int i = 0; i<top98().size(); i++){
            if(top98().get(i).getName().equals(name)){
                return top98().get(i).getURL();
            }
        } return "";
    }

    public void mousePressed(MouseEvent e) {    //connect link
        if(e.getSource() == panel1){
            panel1.requestFocusInWindow();
        }

        else{
            JButton src = (JButton) e.getSource();
            try{
                Desktop.getDesktop().browse(new URL(findURL(src.getText())).toURI());
            }
            catch (Exception f)
            {}
        }
    }

    public void focusGained(FocusEvent e)
    {
        if (searchField.getText().equals("Enter number")) {
            searchField.setForeground(Color.black);
            searchField.setText("");

        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (searchField.getText().isEmpty()) {
            searchField.setText("Enter number");
            searchField.setForeground(Color.GRAY);
        }
    }








    @Override
    public void actionPerformed(ActionEvent e) {
        searchResult.setFont(new Font("Georgia", Font.BOLD|Font.ITALIC, 15));
        searchResult.setText("");
        String input = searchField.getText();
        if(!checkValid(input)){
            searchResult.setText("Invalid search");
            searchResult.setForeground(new Color(255,24,24));
        } else if ((Integer.parseInt(searchField.getText())>=0 && Integer.parseInt(searchField.getText())<=98)){
            searchResult.setForeground(new Color(0,0,0));
            for(Unis uni: topSchools){
                int num = Integer.parseInt(searchField.getText());
                if(uni.getName().indexOf(num+".")==0){
                    searchResult.setText(uni.getName());
                }
            }
        }

        searchResult.setVisible(true);
        searchResult.setBounds(100,100,100,30);
    }

    //check if search field input is valid
    private boolean checkValid(String search){
        for(Character chr: search.toCharArray()){
            int ascii = chr;
            if (ascii<47 || ascii>58){
                return false;
            }
        }
        if(Integer.parseInt(search)>98){
            return false;
        }
        return true;
    }

    //menu field button design
    private void menuFieldDesign(){
        search.setFont(new Font("Serif", Font.PLAIN, 18));
        add.setFont(new Font("Serif", Font.PLAIN, 18));
        remove.setFont(new Font("Serif", Font.PLAIN, 18));
        viewList.setFont(new Font("Serif", Font.PLAIN, 18));
        searchField.setFont(new Font("Serif", Font.PLAIN, 18));

        search.setForeground(Color.white);
        add.setForeground(Color.white);
        remove.setForeground(Color.white);
        viewList.setForeground(Color.white);
        searchField.setForeground(Color.GRAY);

        search.setBackground(new Color(212, 163, 115));
        remove.setBackground(new Color(212, 163, 115));
        viewList.setBackground(new Color(212, 163, 115));
        add.setBackground(new Color(212, 163, 115));

        search.setIcon(imageIcon);
        search.setFocusable(false);
        add.setFocusable(false);
        remove.setFocusable(false);
        viewList.setFocusable(false);
    }

    //empty border
    private void emptyBorder(){
        Border emptyBorder = BorderFactory.createEmptyBorder();
        search.setBorder(emptyBorder);
        add.setBorder(emptyBorder);
        remove.setBorder(emptyBorder);
        viewList.setBorder(emptyBorder);
        searchField.setBorder(emptyBorder);
    }








    //unused methods
    @Override
    public void mouseReleased(MouseEvent e) {

    }
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    @Override
    public void mouseExited(MouseEvent e) {

    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }
}
