package Client;

import se.mau.DA343A.VT25.projekt.Buffer;
import se.mau.DA343A.VT25.projekt.net.SecurityTokens;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/*8. Use a worker thread to read the values from this bu\er, and sends via a TCP connection to the
server application (use java.net.Socket for the client side).
9. When the client first connects to the server, the security token (see below), the name of the
electrical appliance and initial consumption value need to be sent over this TCP connection.
 10. The TCP connection remains open until the client is closed */

public class Client implements Runnable {
    private Appliances appliance;
    private Buffer buffer;
    private volatile boolean running = true;
    private Socket socket;
    private final SecurityTokens tokens = new SecurityTokens("Grupp31");

    public Client (Appliances appliance, Buffer buffer) {
        this.appliance = appliance;
        this.buffer = buffer;
    }

    @Override

    public void run() {

        try {
            socket = new Socket("localhost", 1025);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.write(tokens.generateToken());
            writer.newLine();
            writer.write(appliance.getApplianceName());
            writer.newLine();
            writer.write(Integer.toString(appliance.getApplianceWatt()));
            writer.newLine();

            writer.flush();

            while (running) {

              int newWatt = (int) buffer.get();

              writer.write (String.valueOf(newWatt));
              writer.newLine();

              writer.flush();
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void stop () throws IOException {

        running = false;
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }


    }

}
