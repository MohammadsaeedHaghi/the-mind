package network.client;

import network.shared.util.Config;

public class ClientMain {
    public static void main(String[] args) {
        Integer port = Config.getConfig().getProperty(Integer.class, "serverPort");
        Client client = new Client(port);
        client.start();
    }
}
