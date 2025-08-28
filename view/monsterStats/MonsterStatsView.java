package my.rpg.view.monsterStats;

import my.rpg.model.monster.Monster;
import my.rpg.view.TemplateView;

public class MonsterStatsView extends TemplateView {

    private final Monster monster;

    public MonsterStatsView(Monster monster){
        this.monster = monster;
    }

    @Override
    public void render() {
        System.out.println("Opponent: " + monster.getName() + ", lvl " + monster.getLevel());
        System.out.print("Atk " + monster.getAttack());
        System.out.print(" | Def " + monster.getDefense());
        System.out.print(" | Hp " + monster.getHp());
    }
}
