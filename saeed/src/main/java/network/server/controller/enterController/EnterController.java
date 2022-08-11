package network.server.controller.enterController;

import logic.Game;
import logic.models.player.Player;
import logic.models.player.User;
import network.server.ClientHandler;
import network.shared.response.Response;

public class EnterController {
    private final Game game;

    public EnterController(Game game) {
        this.game = game;
    }
    public Response login(String name) {
       return null;
    }
}
