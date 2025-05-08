package Integration;

import se.mau.DA343A.VT25.projekt.ServerGUI;
import se.mau.DA343A.VT25.projekt.net.SecurityTokens;

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

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String token = in.readLine();
            if (!TOKEN.verifyToken(token)) {
                gui.addLogMessage("Invalid token, disconnecting");
                return;
            }

            String name = in.readLine();
            if (name == null || name.isBlank()) {
                gui.addLogMessage("Appliance name is missing, disconnecting");
                return;
            }

            String line;
            while ((line = in.readLine()) != null) {
                try {
                    int watts = Integer.parseInt(line.trim());
                    model.put(name, watts);
                }
                catch (NumberFormatException ignored) {
                    gui.addLogMessage("Invalid watt value: " + line + " from " + name);
                }
            }

        } catch (IOException e) {
            gui.addLogMessage("Client error: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException ignored) {}
            gui.addLogMessage("Client disconnected");
        }
    }
}

