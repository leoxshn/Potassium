package posidon.potassium;

import posidon.potassium.backend.Player;
import posidon.potassium.backend.PlayerHandler;
import posidon.potassium.ui.color;
import posidon.potassium.universe.PlanetWorld;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class Main implements Runnable {

    public static String key = "leoleo";
    private static final int port = 2512;
    public static boolean running = true;

    @Override
    public void run() {
        Window.create();
        Window.println("Setting up networking stuff...", color.WHITE);
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            new Thread(new PlanetWorld(), "world").start();
            while (running) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    new Player(clientSocket).start();
                } catch (Exception e) { e.printStackTrace(); }
            }
            Window.println("Killing server...");
            kill();
            try { serverSocket.close(); }
            catch (IOException e) { e.printStackTrace(); }
        } catch (IOException e) { e.printStackTrace(); }
    }

    private void kill() {
        for (Player player : PlayerHandler.values()) player.disconnect();
    }

    public static void main(String[] args) { new Thread(new Main()).start(); }

    public static String getExtIP() {
        String out;
        try {
            URL ipUrl = new URL("http://checkip.amazonaws.com");
            BufferedReader in = null;
            try {
                in = new BufferedReader(new InputStreamReader(ipUrl.openStream()));
                out = in.readLine();
            }
            catch (Exception e) { out = "error: " + e.getMessage(); }
            if (in != null) in.close();
        } catch (Exception e) { out = "error: " + e.getMessage(); }
        return out;
    }
}
