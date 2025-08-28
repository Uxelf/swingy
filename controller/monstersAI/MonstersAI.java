package my.rpg.controller.monstersAI;

import my.rpg.model.monster.Monster;
import my.rpg.model.movementComponent.MovementComponent;
import my.rpg.model.movementComponent.MovementDirection;

import java.util.ArrayList;
import java.util.List;

public class MonstersAI {
    private static final int maxMovementIterations = 8;

    public static void initMonstersPositions(List<Monster> monstersList, int mapSize){
        List<Integer> occupiedSpaces = new ArrayList<>();

        boolean inserted;
        int mapCenter = (mapSize - 1) / 2;
        for (Monster monster : monstersList){
            inserted = false;
            while (!inserted){
                int randomX = (int)(Math.random() * mapSize);
                int randomY = (int)(Math.random() * mapSize);

                if (randomX == 0 || randomX == mapSize - 1
                || randomY == 0 || randomY == mapSize -1
                || (randomX == mapCenter && randomY == mapCenter)){
                    continue;
                }

                int coordsInLine = randomX + randomY * mapSize;
                if (!occupiedSpaces.contains(coordsInLine)){
                    occupiedSpaces.add(coordsInLine);
                    inserted = true;
                    monster.getMovementComponent().setPosition(randomX, randomY);
                }
            }
        }
    }

    public static void moveMonsters(List<Monster> monstersList, int mapSize){
        for (Monster monster : monstersList){
            MovementComponent mMovComp = monster.getMovementComponent();
            MovementDirection mDir = null;
            int movX = 0;
            int movY = 0;

            for (int i = 0; i < maxMovementIterations; i++) {
                int randomDir = (int)(Math.random() * 4);

                switch (randomDir){
                    case 0 -> {movX = 0; movY = -1; mDir = MovementDirection.North;}
                    case 1 -> {movX = 1; movY = 0; mDir = MovementDirection.East;}
                    case 2 -> {movX = 0; movY = 1; mDir =  MovementDirection.South;}
                    case 3 -> {movX = -1; movY = 0; mDir = MovementDirection.West;}
                }

                if (isSpaceFree(mMovComp.getX() + movX, mMovComp.getY() + movY, mapSize, monstersList)){
                    monster.getMovementComponent().move(mDir);
                    break;
                }
            }
        }
    }

    private static boolean isSpaceFree(int x, int y, int mapSize, List<Monster> monstersList){
        if (x == 0 || x == mapSize -1 || y == 0 || y == mapSize - 1)
            return false;
        for (Monster monster : monstersList){
            MovementComponent mComp = monster.getMovementComponent();
            if (mComp.getX() == x && mComp.getY() == y)
                return false;
        }
        return true;
    }
}
