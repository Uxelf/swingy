package my.rpg.model.hpComponent;

import java.util.ArrayList;
import java.util.List;

public class HpComponent {
    private int maxHp;
    private int currentHp;
    private final List<DeathListener> deathListeners = new ArrayList<>();

    public HpComponent(int maxHp){
        this.maxHp = maxHp;
        currentHp = maxHp;
    }

    public int getMaxHp(){
        return maxHp;
    }
    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
        if (currentHp > maxHp)
            currentHp = maxHp;
    }
    public void addMaxHp(int hp){
        this.maxHp += maxHp;
        if (currentHp > maxHp)
            currentHp = maxHp;
    }

    public int getCurrentHp() {
        return currentHp;
    }
    public void takeDamage(int damage){
        currentHp -= damage;
        if (currentHp <= 0)
            die();
    }

    private void die(){
        for (DeathListener listener: deathListeners){
            listener.onDeath();
        }
    }

    public void addDeathListener(DeathListener deathListener){
        deathListeners.add(deathListener);
    }
}
