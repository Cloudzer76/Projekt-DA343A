package Client;

import se.mau.DA343A.VT25.projekt.Buffer;

/*3. It needs to be possible to start multiple clients simultaneously, representing di\erent
household appliances (with di\erent names). It is allowed if multiple clients are run as either:
(a) separate processes
(b) within the same process.
 5. Each client has a power consumption (adjustable in the GUI), a maximum power consumption
(for the GUI), and a name.*/

public class ClientStarter {

    public static void main (String [] args) {    //Gör på bättre sätt

        Appliances laptop = new Appliances("Laptop" , 30);
        Appliances ledlight = new Appliances("LED light bulb", 15);
        Appliances lightBulb = new Appliances("Incandescent Bulb", 40);
        Appliances deskcomp = new Appliances("Desktop computer", 200);
        Appliances tv = new Appliances("TV (LED)" , 150);
        Appliances refrigerator = new Appliances("Refrigerator", 250);
        Appliances microwave= new Appliances("Microwave Oven", 1000);


        ClientGUI guiLaptop = new ClientGUI(laptop);
        ClientGUI guiLED = new ClientGUI(ledlight);
        ClientGUI guiLight = new ClientGUI(lightBulb);
        ClientGUI guiDesk = new ClientGUI(deskcomp);
        ClientGUI guiTV = new ClientGUI(tv);
        ClientGUI guiRef = new ClientGUI(refrigerator);
        ClientGUI guiMicro = new ClientGUI(microwave);


        Client clientLaptop = new Client(laptop, guiLaptop.buffer);
        Client clientLED = new Client(ledlight, guiLED.buffer);
        Client clientLight = new Client(lightBulb, guiLight.buffer);
        Client clientDesk = new Client(deskcomp, guiDesk.buffer);
        Client clientTv = new Client(tv, guiTV.buffer);
        Client clientRef  = new Client(refrigerator, guiRef.buffer);
        Client clientMicro = new Client(microwave, guiMicro.buffer);


        Thread laptopThread = new Thread(clientLaptop);
        laptopThread.start();

        Thread ledThread = new Thread(clientLED);
        ledThread.start();

        Thread lightThread = new Thread(clientLight);
        lightThread.start();

        Thread deskThread = new Thread(clientDesk);
        deskThread.start();

        Thread tvThread = new Thread(clientTv);
        tvThread.start();

        Thread refThread = new Thread(clientRef);
        refThread.start();

        Thread microThread = new Thread(clientMicro);
        microThread.start();



    }

}
