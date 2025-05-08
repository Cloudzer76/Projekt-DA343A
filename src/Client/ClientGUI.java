package Client;

import javax.swing.*;

public class ClientGUI extends JFrame {



    private JLabel JlabelAppliance;
    private JSlider JSliderWatt;
    private JPanel mainPanel;


    public ClientGUI (Appliances appliances) {
        setTitle("Client GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setSize(900,800);
        setVisible(true);

        JlabelAppliance = new JLabel(appliances.getApplianceName());

        JSliderWatt = new JSlider(appliances.getApplianceWatt());




    }


}
