package Client;

import javax.swing.*;

public class ClientGUI extends JFrame {



    private JLabel laptopTextField;
    private JSlider slider1;
    private JPanel mainPanel;

    public ClientGUI () {
        setTitle("Client GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setSize(900,800);
        setVisible(true);
    }


}
