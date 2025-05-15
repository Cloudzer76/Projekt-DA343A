package Client;

import se.mau.DA343A.VT25.projekt.Buffer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/*8. Use a worker thread to read the values from this bu\er, and sends via a TCP connection to the
server application (use java.net.Socket for the client side).
9. When the client first connects to the server, the security token (see below), the name of the
electrical appliance and initial consumption value need to be sent over this TCP connection.
 10. The TCP connection remains open until the client is closed */

public class Client implements Runnable { //Innehåller logik för trådar
    private Appliances appliance;
    private Buffer buffer;

    public Client (Appliances appliance, Buffer buffer) {
        this.appliance = appliance;
        this.buffer = buffer;
    }

    @Override

    public void run() {          //Hämta från Buffer och skicka

        try {                    //Anslut till Server
            Socket socket = new Socket("localhost", 1025);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.write("Grupp 31");
            writer.write(appliance.getApplianceName());
            writer.write(appliance.getApplianceWatt());


            writer.flush();              //Ser till så att allt har skickats

            while (true) {       //Skicka nya ändrade watt värden från BUffer till server, stäng anslutningen när klienten stängs.



               writer.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
