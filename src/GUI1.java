import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class GUI1 implements ActionListener, MouseListener {
    private JFrame frame;
    private JPanel panel;
    private JButton addButton;
    private JButton removeButton;
    private JButton viewListButton;
    private JLabel label;



    public GUI1() throws URISyntaxException {

        frame = new JFrame();
        frame.setTitle("College List");
        panel = new JPanel();
        label = new JLabel("click");
        label.addMouseListener(this);



        addButton = new JButton("ADD");
        removeButton = new JButton("REMOVE");
        viewListButton = new JButton("VIEW_LIST");
        panel.add(addButton);
        panel.add(removeButton);
        panel.add(viewListButton);
        panel.add(label);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(panel);
        frame.setVisible(true);
        frame.setSize(500, 500);
    }


    public static void main (String[] args) throws URISyntaxException {
        new GUI1();
    }

    @Override
    public void actionPerformed(ActionEvent e){

        JButton src = (JButton) e.getSource();
        if(src.getText().equals("click")){
            try{
                Desktop.getDesktop().browse(new URL("https://www.google.com").toURI());
            }
            catch (Exception f)
            {}
        }
    }
    public void mouseListener(MouseEvent e){
        JLabel src = (JLabel) e.getSource();
        if(src.getText().equals("click")){
            try{
                Desktop.getDesktop().browse(new URL("https://www.google.com").toURI());
            }
            catch (Exception f)
            {}
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        JLabel src = (JLabel) e.getSource();
        if(src.getText().equals("click")){
            try{
                Desktop.getDesktop().browse(new URL("https://www.google.com").toURI());
            }
            catch (Exception f)
            {}
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}


