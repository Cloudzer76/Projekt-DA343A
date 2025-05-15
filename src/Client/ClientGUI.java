package Client;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import se.mau.DA343A.VT25.projekt.Buffer;

/* 1. Crete a client application with a simple GUI represents a single electrical appliance.
 2. Implement the client GUI yourself, using SWING. It must include:
(a) A javax.swing.JSlider to control the power consumption level.
(b) A label showing the name of the appliance
 4.For each running client, the (fake) energy usage of the appliance can be
adjusted with a slider in the GUI.
 6. Use a javax.swing.event.ChangeListener callback to monitor the JSlider in the GUI for changes.
7. When the power consumption is changed, the new consumption value is added to a Bu\er<T>
(from the JAR) */

public class ClientGUI extends JFrame implements ChangeListener {
    //Alla fönster stängs ner när man stänger ett fönster



    Buffer buffer;
    private JLabel jlabelAppliance;
    private JSlider jSliderWatt;
    private JPanel mainPanel;
    private JLabel powerLabel;


    public ClientGUI (Appliances appliances) {
        buffer = new Buffer();


        setTitle("Client GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,200);

        jlabelAppliance = new JLabel(appliances.getApplianceName());

        jSliderWatt = new JSlider(JSlider.HORIZONTAL, 0, appliances.getApplianceWatt(), 0);
        jSliderWatt.setPaintTrack(true);
        jSliderWatt.setPaintTicks(true);
        jSliderWatt.setPaintLabels(true);


        jSliderWatt.setMaximum(appliances.getApplianceWatt());
        jSliderWatt.setMinimum(0);


        powerLabel = new JLabel();
        powerLabel.setText("Current power " + jSliderWatt.getValue());
        jSliderWatt.addChangeListener(this);

        mainPanel = new JPanel();

        mainPanel.add(jlabelAppliance);
        mainPanel.add(jSliderWatt);
        mainPanel.add(powerLabel);

        setContentPane(mainPanel);
        setVisible(true);




    }


    @Override
    public void stateChanged(ChangeEvent e) {  // Use a javax.swing.event.ChangeListener callback to monitor the JSlider in the GUI for changes
                                               //When the power consumption is changed,
                                              // the new consumption value is added to a Buffer<T>(from the JAR).

        powerLabel.setText("Current power " + jSliderWatt.getValue());


        buffer.put(jSliderWatt.getValue());

    }
}
