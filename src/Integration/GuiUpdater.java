package Integration;

import se.mau.DA343A.VT25.projekt.LiveXYSeries;
import se.mau.DA343A.VT25.projekt.ServerGUI;

import javax.swing.*;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class GuiUpdater {

    private ServerGUI gui;
    private ModelConsumption model; //Placeholder
    private Map<String, LiveXYSeries<Double>> applianceSeries = new HashMap<>();
    private Timer timer;

    public GuiUpdater(ServerGUI gui, ModelConsumption model) {
        this.gui = gui;
        this.model = model;
        timer = new Timer(1000, e -> updateGui());
    }

    public void start() {
        timer.start();
    }

    public void updateGui() {
        double now = Instant.now().getEpochSecond();
        Map<String, Double> current = model.getCurrentConsumption();

        double total = 0;
        for(Map.Entry<String, Double> entry : current.entrySet()) {
            String name = entry.getKey();
            double watt = entry.getValue();

            applianceSeries.computeIfAbsent(name, n -> {
                LiveXYSeries<Double> seconds = new LiveXYSeries<>(n, 20);
                gui.addSeries(seconds);
                return seconds;
            });

            applianceSeries.get(name).addValue(now, watt);
            total += watt; //TODO Flytta uträkning av totalConsumption till ModelConsumption istället.
        }

        gui.setTotalConsumption(total);
    }
}

