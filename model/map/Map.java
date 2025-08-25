package my.rpg.model.map;

public class Map {

    private static Map instance;

    private final int size;

    private Map(int level){
        size = calculateMapSize(level);
    }

    public static Map init(int level){
        instance = new Map(level);
        return instance;
    }

    public static Map getInstance() {
        return instance;
    }

    public int getSize(){
        return size;
    }

    private int calculateMapSize(int level){
        return (int)Math.pow(level - 1, 5) + 10 - (level % 2);
    }
}
