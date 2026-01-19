package my.rpg.view.game;

import my.rpg.model.gameState.GameData;
import my.rpg.model.monster.Monster;
import my.rpg.view.TemplateView;
import my.rpg.view.heroStats.HeroStatsView;

public class GameView extends TemplateView {

    private final GameData gameData;
    private final String emptyMap;
    private final HeroStatsView heroStatsView;

    public GameView(GameData gameData){
        this.gameData = gameData;
        emptyMap = createEmptyMap();
        heroStatsView = new HeroStatsView(gameData.getPlayer());
    }

    @Override
    public void render() {
        printBold("THE GAME");

        String map = addEntitiesToEmptyMap();
        System.out.println(map);
        heroStatsView.render();
    }

    private String createEmptyMap(){
        int mapSize = gameData.getGameMap().getSize();
        StringBuilder map = new StringBuilder();

        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                if (i == 0 || i == mapSize - 1
                || j == 0 || j == mapSize - 1)
                    map.append("*");
                else
                    map.append(".");
            }
            map.append("\n");
        }

        return map.toString();
    }

    private String addEntitiesToEmptyMap(){
        StringBuilder map = new StringBuilder(emptyMap);

        int mapSize = gameData.getGameMap().getSize();

        for (Monster monster : gameData.getMonsterList()){
            int mX = monster.getMovementComponent().getX();
            int mY = monster.getMovementComponent().getY();

            map.replace(mX + mY * (mapSize + 1), mX + mY * (mapSize + 1) + 1, "O");
        }

        int pX = gameData.getPlayer().getMovementComponent().getX();
        int pY = gameData.getPlayer().getMovementComponent().getY();
        map.replace(pX + pY * (mapSize + 1), pX + pY * (mapSize + 1) + 1, "P");

        return map.toString();
    }
}
