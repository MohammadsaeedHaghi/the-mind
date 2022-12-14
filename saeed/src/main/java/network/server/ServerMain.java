package network.server;

import network.shared.util.Config;

public class ServerMain {
    public static void main(String[] args) {
        Integer port = Config.getConfig().getProperty(Integer.class, "serverPort");
        Server server = new Server(port);
        server.start();
    }
}
