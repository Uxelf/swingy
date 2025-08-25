package my.rpg.model.utils;

public class Utils {
    public static int randomInRange(int min, int max){
        return (int) ((Math.random() * (max - min)) + min);
    }
}
