package my.rpg.controller.scene.scenes.game;

import my.rpg.controller.inputReader.InputReader;
import my.rpg.controller.monstersAI.MonstersAI;
import my.rpg.controller.rewardSystem.RewardSystem;
import my.rpg.controller.scene.Scene;
import my.rpg.controller.scene.scenes.mainMenu.MainMenuScene;
import my.rpg.model.combatSystem.CombatManager;
import my.rpg.model.gameState.GameData;
import my.rpg.model.hero.Hero;
import my.rpg.model.monster.Monster;
import my.rpg.model.movementComponent.MovementComponent;
import my.rpg.model.movementComponent.MovementDirection;
import my.rpg.view.game.GameView;

import java.util.ArrayList;
import java.util.List;

public class GameScene extends Scene {

    private final GameData gameData;
    private static GameScene instance;
    private final List<Monster> deadMonsters = new ArrayList<>();

    public GameScene(Hero player){
        if (player == null){
            throw new RuntimeException("Can't start a game with player = null");
        }
        if (instance != null)
            System.out.println("WARNING: an instance of GameScene is already assigned");
        instance = this;

        inputReader = new InputReader.InputReaderBuilder()
                .bind("d", () -> player.getMovementComponent().move(MovementDirection.East))
                .bind("east", () -> player.getMovementComponent().move(MovementDirection.East))
                .bind("w", () -> player.getMovementComponent().move(MovementDirection.North))
                .bind("north", () -> player.getMovementComponent().move(MovementDirection.North))
                .bind("a", () -> player.getMovementComponent().move(MovementDirection.West))
                .bind("west", () -> player.getMovementComponent().move(MovementDirection.West))
                .bind("s", () -> player.getMovementComponent().move(MovementDirection.South))
                .bind("south", () -> player.getMovementComponent().move(MovementDirection.South))
                .build();
        gameData = new GameData(player);
        MonstersAI.initMonstersPositions(gameData.getMonsterList(), gameData.getGameMap().getSize());
        view = new GameView(gameData);
        view.render();
    }

    public static GameScene getInstance() {
        return instance;
    }

    @Override
    public void update() {
        inputReader.readInput();
        if (isPlayerAtBorder()){
            endRound();
            return;
        }
        CombatManager.checkForCombat(gameData.getPlayer(), gameData.getMonsterList());
        removeDeadMonsters();
        MonstersAI.moveMonsters(gameData.getMonsterList(), gameData.getGameMap().getSize());
        CombatManager.checkForCombat(gameData.getPlayer(), gameData.getMonsterList());
        removeDeadMonsters();

        view.render();
    }

    private boolean isPlayerAtBorder(){
        int mapSize = gameData.getGameMap().getSize();
        MovementComponent pMovComp = gameData.getPlayer().getMovementComponent();
        int pX = pMovComp.getX();
        int pY = pMovComp.getY();
        return (pX == 0 || pX == mapSize - 1 || pY == 0 || pY == mapSize - 1);
    }

    private void endRound(){
        instance = null;

        sceneManager.changeScene(new GameScene(gameData.getPlayer()));
    }

    public void looseGame(){
        instance = null;

        //Cambiar a una end screen
        sceneManager.changeScene(new MainMenuScene());
    }

    public void onMonsterDeath(Monster monster){
        deadMonsters.add(monster);
        System.out.println("YOU WON");
        RewardSystem.rewardPlayer(gameData.getPlayer(), monster.getLevel());
    }


    private void removeDeadMonsters(){
        for (Monster monster : deadMonsters){
            gameData.removeMonsterFromList(monster);
        }
    }
}
