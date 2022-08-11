package network.client;

import network.shared.response.Response;
import network.shared.response.ResponseStatus;
import network.shared.util.Config;

import java.util.Scanner;

public class Client {
    private final Scanner scanner = new Scanner(System.in);

    private ServerController serverController;
    private final int port;

    public Client(int port) {
        this.port = port;
    }

    public void start() {
        serverController = new ServerController(port);
        serverController.connectToServer();
        loginCLI();
    }

    private void loginCLI() {
        System.out.println("Login Page:");

        while (true) {
            int command = scanner.nextInt();
            scanner.nextLine();

            Integer exitCode = Config.getConfig().getProperty(Integer.class, "exitCode");
            Integer loginCode = Config.getConfig().getProperty(Integer.class, "loginCode");

            if (command == exitCode) {
                System.exit(0);
            } else if (command == loginCode) {
                login();
            }
        }
    }

    private void login(){
        String name = scanner.nextLine();

        Response response = serverController.sendLoginRequest(name);

        if (response.getStatus() == ResponseStatus.OK) {
            waitForStart();
        } else {
            System.err.println(response.getErrorMessage());
        }
    }

    private void waitForStart() {

    }
}