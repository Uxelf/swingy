package my.rpg.model.gameState;

import my.rpg.model.hero.Hero;
import my.rpg.model.map.GameMap;
import my.rpg.model.monster.Monster;
import my.rpg.model.monster.MonstersGenerator;

import java.util.ArrayList;
import java.util.List;

public class GameData {
    private List<Monster> monsterList;
    private Hero player;
    private GameMap gameMap;

    public GameData(Hero player){
        if (player == null){
            throw new IllegalStateException("Can't start a game with player = null");
        }
        this.player = player;
        int level = player.getLevel();
        gameMap = GameMap.init(level);
        monsterList = MonstersGenerator.generateMonsters(level);

        int middle = (gameMap.getSize() - 1 ) / 2;
        player.getMovementComponent().setPosition(middle, middle);
    }

    public Hero getPlayer() {
        return player;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public List<Monster> getMonsterList() {
        return monsterList;
    }

    public void removeMonsterFromList(Monster monster){
        monsterList.remove(monster);
    }
}
