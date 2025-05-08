package Server;

import Integration.ConnectionHandler;
import Integration.ModelConsumption;
import se.mau.DA343A.VT25.projekt.ServerGUI;

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
            //lägg till koppla klienter --> koppla till connectionhandler
        }
    }

    public void stop() {
        running = false;
        try {

        }
    }



}


