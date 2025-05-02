package Integration;

import se.mau.DA343A.VT25.projekt.ServerGUI;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerStart {
    public static void main(String[] args) {
        int port; //För socket connection

        ServerGUI gui = new ServerGUI("Server");
        gui.createAndShowUI();

        ModelConsumption model = new ModelConsumption();
        new GuiUpdater(gui, model).start();

        //TODO Behöver lägga till kod för att acceptera socket connection
    }
}
