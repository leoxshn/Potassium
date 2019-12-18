package posidon.potassium.backend;

import posidon.potassium.Window;
import posidon.potassium.backend.events.PlayerMovementEvent;
import posidon.potassium.packets.PlayerJoinPacket;
import posidon.potassium.ui.color;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Player extends Thread {

    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Socket socket;
    public int id;
    public String name;
    private boolean running = true;
    public PlayerInfo info;
    public float x, y, z, moveSpeed = 0.5f, jumpHeight = 0.5f;
    private List<PlayerMovementEvent> pendingMovementEvents = new ArrayList<>();

    public void addPendingMovementEvent(PlayerMovementEvent movementEvent) { pendingMovementEvents.add(movementEvent); }
    public void runPendingMovementEvents() {
        List<PlayerMovementEvent> tmpPendingMovementEvents = pendingMovementEvents;
        pendingMovementEvents = new ArrayList<>();
        for (PlayerMovementEvent movEvent : tmpPendingMovementEvents) movEvent.run(this);
        if (tmpPendingMovementEvents.size() > 10) Window.println(name + " is sending too many packets!", color.RED);
        tmpPendingMovementEvents.clear();
    }

    public Player(Socket s) {
        super(s.getInetAddress().getHostAddress());
        this.socket = s;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            Object tmp = null;
            do try { tmp = in.readObject(); } catch (ClassNotFoundException e) { Window.println(s.getInetAddress().getHostAddress() + " sent an unknown class"); } while (!(tmp instanceof PlayerJoinPacket));
            PlayerJoinPacket packet = (PlayerJoinPacket) tmp;
            this.id = packet.id;
            this.name = packet.name;
            packet.player = this;
            EventListener.onEvent(packet, packet.id);
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void send(Object obj) {
        try {
            out.writeObject(obj);
            out.flush();
        } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public void run() { while (running) {
        Object obj = getSent();
        if (obj == null) disconnect();
        else EventListener.onEvent(obj, id);
    }}

    private Object getSent() {
        try { return in.readObject(); }
        catch (ClassNotFoundException e) { Window.println(name + " sent an unknown packet", color.RED); }
        //catch (IOException e) { Window.println(name + "'s connection was lost"); }
        catch (Exception ignore) {}
        return null;
    }

    public void disconnect() { running = false;
        try {
            out.close();
            in.close();
            socket.close();
        } catch (Exception ignore) {}
        Window.println(PlayerHandler.getName(id) + " left", color.GREEN);
        PlayerHandler.remove(id);
    }
}
