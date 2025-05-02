package Integration;

import se.mau.DA343A.VT25.projekt.ServerGUI;

import java.net.Socket;

public class ConnectionHandler implements Runnable {
    private final Socket socket;
    private final ModelConsumption model;
    private final ServerGUI gui;

    public ConnectionHandler(Socket socket, ModelConsumption model, ServerGUI gui) {
        this.socket = socket;
        this.model = model;
        this.gui = gui;
    }

    @Override
    public void run() {

    }
}

