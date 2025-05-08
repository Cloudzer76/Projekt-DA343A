package Server;

import Integration.ConnectionHandler;
import Integration.ModelConsumption;
import se.mau.DA343A.VT25.projekt.ServerGUI;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {

    private final int port;
    private final ModelConsumption model;
    private final ServerGUI gui;
    private volatile boolean running = true;
    private ServerSocket serverSocket;
    private final ExecutorService executor = Executors.newCachedThreadPool();

    public Server(int port, ModelConsumption model, ServerGUI gui) {
        this.port = port;
        this.model = model;
        this.gui = gui;
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(port);
            log("Server has started on port: " + port);
            while (running) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    log("Client is connected: " + clientSocket.getRemoteSocketAddress());
                    executor.submit(new ConnectionHandler(clientSocket, model, gui));
                } catch (IOException e) {
                    if (running) {
                        log("Problem with connecting client - " + e.getMessage());
                    }

                }

            }
        } catch (IOException e) {
            log("Problem with starting the server - " + e.getMessage());
        } finally {
            stop();

        }
    }

    public void stop() {
        running = false;
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
                log("Server has stopped");
            }

        } catch (IOException e) {
            log("Problem stopping server - " + e.getMessage());

        }

        executor.shutdownNow();

    }
    private void log(String message) {
        SwingUtilities.invokeLater(() -> gui.addLogMessage(message));
    }

}


