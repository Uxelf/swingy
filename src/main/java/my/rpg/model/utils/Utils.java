package my.rpg.model.utils;

import java.util.concurrent.TimeUnit;

public class Utils {
    public static int randomInRange(int min, int max){
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static void waitSeconds(int seconds){
        try{
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException _) {
        }
    }

    public static void waitMilliseconds(int milliseconds){
        try{
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException _) {
        }
    }
}
