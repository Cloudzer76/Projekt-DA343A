package Integration;

import se.mau.DA343A.VT25.projekt.ServerGUI;
import se.mau.DA343A.VT25.projekt.net.SecurityTokens;

import javax.swing.*;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConnectionHandler implements Runnable {
    private final Socket socket;
    private final ModelConsumption model;
    private final ServerGUI gui;

    private static final SecurityTokens TOKEN = new SecurityTokens("Grupp31");

    public ConnectionHandler(Socket socket, ModelConsumption model, ServerGUI gui) {
        this.socket = socket;
        this.model = model;
        this.gui = gui;
    }

    private void log(String msg) {
        SwingUtilities.invokeLater(() -> gui.addLogMessage(msg));
    }
    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String token = in.readLine();
            if (!TOKEN.verifyToken(token)) {
                log("Invalid token, disconnecting");
                return;
            }

            String name = in.readLine();
            if (name == null || name.isBlank()) {
                log("Appliance name is missing, disconnecting");
                return;
            }

            String line;
            while ((line = in.readLine()) != null) {
                try {
                    int watts = Integer.parseInt(line.trim());
                    model.put(name, watts);
                    log(name + " " + watts + " W");
                }
                catch (NumberFormatException ignored) {
                    log("Invalid watt value: " + line + " from " + name);
                }
            }

        } catch (IOException e) {
            log("Client error: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException ignored) {}
            log("Client disconnected");
        }
    }
}

