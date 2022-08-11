package network.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import network.shared.request.Request;
import network.shared.request.RequestType;
import network.shared.response.Response;
import network.shared.util.Jackson;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ServerController {
    private PrintStream printStream;
    private Scanner scanner;

    private final int port;

    private final ObjectMapper objectMapper;

    public ServerController(int port) {
        this.port = port;
        objectMapper = Jackson.getNetworkObjectMapper();
    }

    public void connectToServer() {
        try {
            Socket socket = new Socket(InetAddress.getLocalHost(), port);
            printStream = new PrintStream(socket.getOutputStream());
            scanner = new Scanner(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendRequest(Request request) {
        try {
            String requestString = objectMapper.writeValueAsString(request);
            printStream.println(requestString);
            printStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Response sendLoginRequest(String name) {
        Request request = new Request(RequestType.LOGIN);
        request.addData("name", name);
        sendRequest(request);

        Response response = null;
        try {
            response = objectMapper.readValue(scanner.nextLine(), Response.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public Response playCardRequest(int num) {
        Request request = new Request(RequestType.PLAY_CARD);
        request.addData("cardNum", num);
        sendRequest(request);

        Response response = null;
        try {
            response = objectMapper.readValue(scanner.nextLine(), Response.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public Response sendMessageRequest(String text) {
        Request request = new Request(RequestType.SEND_MASSAGE);
        request.addData("text", text);
        sendRequest(request);

        Response response = null;
        try {
            response = objectMapper.readValue(scanner.nextLine(), Response.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public Response useNinjaRequest() {
        Request request = new Request(RequestType.USE_NINJA);
        request.addData("pk", null);
        sendRequest(request);

        Response response = null;
        try {
            response = objectMapper.readValue(scanner.nextLine(), Response.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

}