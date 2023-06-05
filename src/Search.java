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
    private JFrame listFrame;
    private User user1;

    private JScrollPane listScroll;

    public Search(User validUser){

        user1 = validUser;
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

        frameTopDesign();         //menu field design

        panel1.add(searchField);
        panel1.add(search);
        panel1.add(add);
        panel1.add(remove);
        panel1.add(viewList);
        panel2.add(searchResult);

        //actionListener
        searchField.addFocusListener( this);
        add.addActionListener(this);
        search.addActionListener(this);
        remove.addActionListener(this);
        panel1.addMouseListener(this);
        viewList.addActionListener(this);

        buildScrollPanel();       //build the scroll panel with college list

        scrollPane = new JScrollPane(panel);
        JPanel contentPane = new JPanel(null);
        scrollPane.setBounds(75,20, 450, 450);
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



    private String findURL(String name){
        for(int i = 0; i<topSchools.size(); i++){
            if(topSchools.get(i).getName().equals(name)){
                return topSchools.get(i).getURL();
            }
        } return "";
    }

    public void mousePressed(MouseEvent e) {    //connect link
        if(e.getSource() == panel1){
            panel1.requestFocusInWindow();
        } else{
            JButton src = (JButton) e.getSource();
            try{
                Desktop.getDesktop().browse(new URL(findURL(src.getText())).toURI());
            }
            catch (Exception f)
            {}
        }
    }

    public void focusGained(FocusEvent e) {             //enter number vanishes
        if (searchField.getText().equals("Enter number")) {
            searchField.setForeground(Color.black);
            searchField.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {               //enter number appears
        if(searchField.getText().isEmpty()) {
            searchField.setText("Enter number");
            searchField.setForeground(Color.GRAY);
        }
    }








    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==search){
            actionForSearchButton();
        } else if(e.getSource()==add){
            actionForAddButton();
        } else if(e.getSource()==remove){
            actionForRemoveButton();
        } else if (e.getSource()==viewList){
            actionForViewList();
        }
    }

    //actionPerformed for search button
    private void actionForSearchButton(){
        searchResult.setFont(new Font("Georgia", Font.ITALIC, 15));
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
    }

    //actionPerformed for add button
    private void actionForAddButton(){
        searchResult.setFont(new Font("Georgia", Font.ITALIC, 15));
        searchResult.setText("");
        String input = searchField.getText();
        if(!checkValid(input)){
            searchResult.setText("Invalid search");
            searchResult.setForeground(new Color(255,24,24));
        } else if ((Integer.parseInt(searchField.getText())>=0 && Integer.parseInt(searchField.getText())<=98)){
            for(Unis uni: topSchools){
                int num = Integer.parseInt(searchField.getText());
                if(user1.checkDuplicates(uni)>-1){
                    searchResult.setForeground(new Color(255,24,24));
                    searchResult.setText("University has already been added!!!");
                } else if(uni.getName().indexOf(num+".")==0 && user1.checkDuplicates(uni)==-1){
                    searchResult.setForeground(new Color(40, 100, 10));
                    searchResult.setText("University added successfully!!!");
                    System.out.println(uni.getName());
                    user1.addUni(uni);
                    break;
                }
            }
        }
    }

    //actionPerformed for remove button
    private void actionForRemoveButton(){
        searchResult.setFont(new Font("Georgia", Font.ITALIC, 15));
        searchResult.setText("");
        String input = searchField.getText();
        if(!checkValid(input)){
            searchResult.setText("Invalid search");
            searchResult.setForeground(new Color(255,24,24));
        } else if ((Integer.parseInt(searchField.getText())>=0 && Integer.parseInt(searchField.getText())<=98)){
            for(int i=0; i<topSchools.size();i++){
                Unis uni = topSchools.get(i);
                int num = Integer.parseInt(searchField.getText());
                if(uni.getName().indexOf(num+".")==0 && user1.checkDuplicates(uni)>-1){
                    searchResult.setForeground(new Color(40, 100, 10));
                    searchResult.setText("University removed successfully!!!");
                    System.out.println(uni.getName());
                    user1.removeUni(uni);
                    break;
                } else{
                    searchResult.setForeground(new Color(255,24,24));
                    searchResult.setText("List doesn't contain university!!!");
                }
            }
        }
    }

    //actionPerformed for viewList button
    private void actionForViewList(){
        if(listFrame != null) {
            listFrame.dispose();
        }
        listFrame = new JFrame();
        listFrame.setTitle(user1.getName() + "'s College List");
        listFrame.setSize(400, 400);
        listFrame.setLocationRelativeTo(frame);
        listFrame.setResizable(false);
        listFrame.setLayout(null);

        JButton empty = new JButton("No school has been added yet!!!");
        empty.setPreferredSize(new Dimension(700,25));
        empty.setHorizontalAlignment(JButton.LEFT);
        empty.setFocusable(false);
        empty.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        JPanel viewListPanel = new JPanel(new GridLayout(8, 1));
        if(user1.uniList().size()>8){
            viewListPanel = new JPanel(new GridLayout(user1.uniList().size(),1));
        }
        viewListPanel.add(empty);


        for(int i = 0; i<user1.uniList().size(); i++){
            viewListPanel.remove(empty);
            JButton interestSchool = new JButton(user1.uniList().get(i).getName());
            interestSchool.setPreferredSize(new Dimension(500,25));
            interestSchool.setHorizontalAlignment(JButton.LEFT);
            interestSchool.setFocusable(false);
            interestSchool.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
            interestSchool.setVisible(true);
            viewListPanel.add(interestSchool);
            interestSchool.addMouseListener(this);
        }
        listScroll = new JScrollPane(viewListPanel);
        listScroll.setBounds(0,0,360,355);
        listScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        listScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);


        JPanel listContentPane = new JPanel(null);
        listContentPane.setBounds(05,05,380,350);
        listContentPane.add(listScroll);
        listFrame.add(listContentPane);
        listFrame.setVisible(true);
        listFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
    private void frameTopDesign(){

        //panel setup
        panel1.setLayout(null);
        panel1.setBounds(0,0,600,45);
        panel2.setBounds(0,45,600,35);
        panel1.setBorder(new EmptyBorder(5,0,5,0));

        //font type and size
        search.setFont(new Font("Serif", Font.PLAIN, 18));
        add.setFont(new Font("Serif", Font.PLAIN, 18));
        remove.setFont(new Font("Serif", Font.PLAIN, 18));
        viewList.setFont(new Font("Serif", Font.PLAIN, 18));
        searchField.setFont(new Font("Serif", Font.PLAIN, 18));

        //font color
        search.setForeground(Color.white);
        add.setForeground(Color.white);
        remove.setForeground(Color.white);
        viewList.setForeground(Color.white);
        searchField.setForeground(Color.GRAY);

        //button background color
        search.setBackground(new Color(212, 163, 115));
        remove.setBackground(new Color(212, 163, 115));
        viewList.setBackground(new Color(212, 163, 115));
        add.setBackground(new Color(212, 163, 115));

        //set focusable to false;
        search.setIcon(imageIcon);
        search.setFocusable(false);
        add.setFocusable(false);
        remove.setFocusable(false);
        viewList.setFocusable(false);

        //empty border
        Border emptyBorder = BorderFactory.createEmptyBorder();
        search.setBorder(emptyBorder);
        add.setBorder(emptyBorder);
        remove.setBorder(emptyBorder);
        viewList.setBorder(emptyBorder);
        searchField.setBorder(emptyBorder);

        //set bounds
        searchField.setBounds(75,10,110,30);
        search.setBounds(222,10,85,30);
        add.setBounds(313,10,40,30);
        remove.setBounds(359,10,75,30);
        viewList.setBounds(440,10,80,30);
    }

    //build the school list from csv file
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

    //build the scroll panel with college list
    private void buildScrollPanel(){
        panel = new JPanel(new GridLayout(98,1));
        topSchools = top98();

        for(int i= 0; i<topSchools.size(); i++){
            school = new JButton(topSchools.get(i).getName());
            school.setPreferredSize(new Dimension(700,40));
            school.setHorizontalAlignment(JButton.LEFT);
            school.setFocusable(false);
            school.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
            school.setVisible(true);
            if(i%2==0){
                school.setBackground(new Color(204, 213, 174));
            } else{
                school.setBackground(new Color(233, 237, 201));
            }
            panel.add(school);
            school.addMouseListener(this);
        }
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