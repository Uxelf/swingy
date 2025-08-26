package my.rpg.model.game;

import my.rpg.model.hero.Hero;
import my.rpg.model.map.Map;
import my.rpg.model.monster.Monster;
import my.rpg.model.monster.MonstersGenerator;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private static Game instance;

    private List<Monster> monstersList;
    private Map map;
    private final Hero player;

    private Game(Hero player){
        if (player == null){
            throw new RuntimeException("Can't start a game with player = null");
        }
        this.player = player;
        monstersList = new ArrayList<>();
    }

    public static Game getInstance(){
        if (instance == null){
            throw new IllegalStateException("The game is not initialized");
        }
        return instance;
    }

    public Hero getPlayer() {
        return player;
    }

    public Map getMap() {
        return map;
    }

    public static Game init(Hero player){
        if (instance == null){
            instance = new Game(player);
        } else {
            throw new IllegalStateException("The game is already initialized");
        }
        return instance;
    }

    public static void newRound(){
        if (instance == null) {
            throw new IllegalStateException("Initialize a game before starting a new round");
        }

        int level = instance.player.getLevel();
        instance.map = Map.init(level);
        instance.monstersList = MonstersGenerator.generateMonsters(level);

        int middle = (instance.map.getSize() - 1) / 2;
        instance.player.getMovementComponent().setPosition(middle, middle);

    }

    public static void loop(){
        if (instance == null) {
            throw new IllegalStateException("The Game is not initialized, can't loop");
        }


    }

    public static void endRound(){
        if (instance == null){
            throw new IllegalStateException("Initialize a game before starting a new round");
        }
    }

    public static void exitGame(){
        //salvar progreso
    }

}
