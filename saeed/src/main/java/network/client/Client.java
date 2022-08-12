package network.client;

import logic.models.player.User;
import network.shared.response.Response;
import network.shared.response.ResponseStatus;
import network.shared.util.Config;

import java.util.Scanner;

public class Client {
    private final Scanner scanner = new Scanner(System.in);

    private ServerController serverController;
    private final int port;

    private User user;
    private String auth;

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public Client(int port) {
        this.port = port;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void start() {
        serverController = new ServerController(port);
        serverController.connectToServer();
        loginCLI();
    }

    private void loginCLI() {
        System.out.println("Login Page: exit>>0 login>>1");

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
        System.out.println("Enter your Name!");
        String name = scanner.nextLine();

        Response response = serverController.sendLoginRequest(name);

        if (response.getStatus() == ResponseStatus.OK) {
            auth = response.getData("auth").toString();
            waitForStart();
        } else {
            System.err.println(response.getErrorMessage());
        }
    }

    private void waitForStart() {
        System.out.println("waiting... : startGame>>2");
        while (true){
            int command = scanner.nextInt();
            scanner.nextLine();

            Integer startGame = Config.getConfig().getProperty(Integer.class, "startCode");
            if(command==startGame){
                Response response = serverController.startGameRequest(auth);
                if(response.getStatus() == ResponseStatus.OK){
                    goToGame();
                }else {
                    System.err.println(response.getErrorMessage());
                }
            }
            try {
                Response response = serverController.isStartedRequest();
                if (response.getStatus() == ResponseStatus.OK) {
                    goToGame();
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void goToGame(){
        System.out.println("Game is started");
    }

}
