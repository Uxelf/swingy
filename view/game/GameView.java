package my.rpg.view.game;

import my.rpg.model.gameState.GameData;
import my.rpg.model.map.GameMap;
import my.rpg.view.TemplateView;

public class GameView extends TemplateView {

    private GameData gameData;
    private String emptyMap;

    public GameView(GameData gameData){
        this.gameData = gameData;
        emptyMap = createEmptyMap();
    }

    @Override
    public void render() {
        printBold("THE GAME");


    }

    private String createEmptyMap(){
        int mapSize = gameData.getGameMap().getSize();
        StringBuilder map = new StringBuilder();

        for (int i = 0; i < mapSize + 2; i++) {
            for (int j = 0; j < mapSize + 2; j++) {
                if (i == 0 || j == 0 || i == mapSize + 1 || j == mapSize + 1)
                    map.append("*");
                else
                    map.append(" ");
            }
        }

        return map.toString();
    }
}
