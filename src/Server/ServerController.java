package Server;

import Integration.ModelConsumption;
import se.mau.DA343A.VT25.projekt.ServerGUI;
import se.mau.DA343A.VT25.projekt.LiveXYSeries;

import javax.swing.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServerController {
    private static final int MAX_ITEMS = 20;
    private final ServerGUI serverGUI;
    private final Server server;
    private final ModelConsumption model;
    private final ConcurrentHashMap<String, LiveXYSeries<Double>> liveSeriesMap = new ConcurrentHashMap<>();
    private final Timer timer;

    public ServerController() {
        this.serverGUI = new ServerGUI("Server");
        this.model = new ModelConsumption();
        this.server = new Server(4321, model, serverGUI);

        serverGUI.setOnExitingCallback(this::stopServer);
        SwingUtilities.invokeLater(serverGUI::createAndShowUI);
        startServer();

        // GUI-uppdatering varje sekund

        timer = new Timer(1000, e -> {
            double now = System.currentTimeMillis() / 1000.0;
            double total = 0;

            for (Map.Entry<String, Double> entry : model.getCurrentConsumption().entrySet()) {
                String name = entry.getKey();
                double watts = entry.getValue();

                total += watts;
                liveSeriesMap.computeIfAbsent(name, n -> {
                    LiveXYSeries<Double> series = new LiveXYSeries<>(n, MAX_ITEMS);
                    SwingUtilities.invokeLater(() -> serverGUI.addSeries(series));
                    return series;
                }).addValue(now, watts);
            }

            serverGUI.setTotalConsumption(total);
        });
        timer.start();
    }

    private void startServer() {
        Thread serverThread = new Thread(server);
        serverThread.start();
    }

    private void stopServer() {
        server.stop();
    }

    public static void main(String[] args) {
        new ServerController();
    }
}


