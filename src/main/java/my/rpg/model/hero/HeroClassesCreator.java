package my.rpg.model.hero;

public class HeroClassesCreator {
    public static Hero CreateHero(HeroClass heroClass, String name){
        switch (heroClass){
            //Stats logic -> attack + defense + hp = 25 (at lvl 1)
            case Rogue -> {
                return (Hero) new Hero.HeroBuilder().setHeroClass(heroClass)
                        .setAttack(12)
                        .setDefense(2)
                        .setMaxHp(11)
                        .setLevel(1)
                        .setName(name)
                        .build();
            }

            case Tank -> {
                return (Hero) new Hero.HeroBuilder().setHeroClass(heroClass)
                        .setAttack(4)
                        .setDefense(6)
                        .setMaxHp(15)
                        .setLevel(1)
                        .setName(name)
                        .build();
            }

            case Warrior -> {
                return (Hero) new Hero.HeroBuilder().setHeroClass(heroClass)
                        .setAttack(8)
                        .setDefense(4)
                        .setMaxHp(13)
                        .setLevel(1)
                        .setName(name)
                        .build();
            }

            default -> {return null;}

        }
    }
}
