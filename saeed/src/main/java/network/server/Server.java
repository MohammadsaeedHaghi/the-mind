package network.server;

import logic.Game;
import network.shared.request.Request;
import network.shared.response.Response;
import network.shared.response.ResponseStatus;
import network.shared.util.Config;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private final ArrayList<ClientHandler> clients;
    private static int clientCount = 0;
    private ServerSocket serverSocket;
    private final int port;
    private boolean running;

    private Game game;

    public Server(int port) {
        this.port = port;
        clients = new ArrayList<>();
        game = new Game();
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(port);
            running = true;

            listenForNewConnection();
        } catch (IOException e) {
            e.printStackTrace(); // Failed to run the server
        }
    }

    @SuppressWarnings("unused")
    public void stop() {
        try {
            serverSocket.close();
            running = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listenForNewConnection() {
        while (running) {
            try {
                clientCount++;
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientCount, this, socket);
                clients.add(clientHandler);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unused")
    public void clientDisconnected(ClientHandler clientHandler) {
        clients.remove(clientHandler);
    }

    public void handleRequest(int clientId, Request request) {
        System.out.println(request);
        Response response = null;
        switch (request.getRequestType()) {
            case LOGIN -> {
                response = login((String) request.getData("name"));
            }
            case PLAY_CARD -> {
                response = playCard((String) request.getData("cardNum"));
            }


        }
        findClientAndSendResponse(clientId, response);
    }

    private ClientHandler getClientHandler(int clientId) {
        for (ClientHandler clientHandler : clients) {
            if (clientHandler.getId() == clientId) {
                return clientHandler;
            }
        }
        return null;
    }

    private void findClientAndSendResponse(int clientId, Response response) {
        ClientHandler clientHandler = getClientHandler(clientId);
        if (clientHandler != null) {
            clientHandler.sendResponse(response);
        }
    }
//
    private Response login(String name) {
        Response response;
        if(game.isStarted()){
            response = new Response(ResponseStatus.ERROR);
            String message = Config.getConfig().getProperty(String.class, "game is started");
            response.setErrorMessage(message);
        } else{
            response = new Response(ResponseStatus.OK);
            game.addPlayer(name);
            response.addData("name", name);
        }
        return response;
    }

    private Response playCard(String cardNum){
        return null;
    }

}
