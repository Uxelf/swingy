package my.rpg.model.map;

public class GameMap {

    private static GameMap instance;

    private final int size;

    private GameMap(int level){
        size = calculateMapSize(level);
    }

    public static GameMap init(int level){
        instance = new GameMap(level);
        return instance;
    }

    public static GameMap getInstance() {
        return instance;
    }

    public int getSize(){
        return size;
    }

    private int calculateMapSize(int level){
        return (int)(level - 1) * 5 + 10 - (level % 2);
    }
}
