package network.server;

import logic.Game;
import logic.models.player.Player;
import logic.models.player.User;
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
            case LOGIN : {
                response = login((String) request.getData("name"), clientId);
                break;
            }
            case PLAY_CARD : {
                response = playCard((String) request.getData("cardNum"));
                break;
            }
            case IS_STARTED : {
                response = checkStarted();
                break;
            }
            case START_GAME:{
                response = start((String) request.getData("auth"));
                break;
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

    private Response start(String auth){
        Response response =null;
        for (ClientHandler clientHandler:clients){
            if(clientHandler.getToken().equals(auth)){
                System.out.println("harrr");
                if(clientHandler.getUser().isHost()){
                    response = new Response(ResponseStatus.OK);
                }else{
                    response = new Response(ResponseStatus.ERROR);
                    String message = "You are not Host!";
                    response.setErrorMessage(message);
                }
            }
        }
        return response;
    }

    private Response login(String name, int clientId) {
        Response response;
        ClientHandler clientHandler = getClientHandler(clientId);
        if(game.isStarted()){
            response = new Response(ResponseStatus.ERROR);
            String message = "game is started";
            response.setErrorMessage(message);
        } else{
            response = new Response(ResponseStatus.OK);
            User user = new User(game, name);
            game.addPlayer(user);
            clientHandler.setUser(user);
            response.addData("auth", clientHandler.getToken());
            response.addData("name", name);
        }
        return response;
    }

    private Response playCard(String cardNum){
        return null;
    }

    private Response checkStarted(){
        Response response;
        if(game.isStarted())
            response = new Response(ResponseStatus.OK);
        else
            response = new Response(ResponseStatus.ERROR);
        return response;

    }

}
