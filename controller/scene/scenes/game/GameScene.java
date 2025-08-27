package my.rpg.controller.scene.scenes.game;

import my.rpg.controller.inputReader.InputReader;
import my.rpg.controller.monstersAI.MonstersAI;
import my.rpg.controller.scene.Scene;
import my.rpg.controller.scene.scenes.mainMenu.MainMenuScene;
import my.rpg.model.combatSystem.CombatManager;
import my.rpg.model.gameState.GameData;
import my.rpg.model.hero.Hero;
import my.rpg.model.movementComponent.MovementComponent;
import my.rpg.model.movementComponent.MovementDirection;

public class GameScene extends Scene {

    private GameData gameData;
    private static GameScene instance;

    public GameScene(Hero player){
        if (player == null){
            throw new RuntimeException("Can't start a game with player = null");
        }
        if (instance != null)
            System.out.println("WARNING: an instance of GameScene is already assigned");
        instance = this;

        inputReader = new InputReader.InputReaderBuilder()
                .bind("a", () -> player.getMovementComponent().move(MovementDirection.East))
                .bind("east", () -> player.getMovementComponent().move(MovementDirection.East))
                .bind("w", () -> player.getMovementComponent().move(MovementDirection.North))
                .bind("north", () -> player.getMovementComponent().move(MovementDirection.North))
                .bind("d", () -> player.getMovementComponent().move(MovementDirection.West))
                .bind("west", () -> player.getMovementComponent().move(MovementDirection.West))
                .bind("s", () -> player.getMovementComponent().move(MovementDirection.South))
                .bind("south", () -> player.getMovementComponent().move(MovementDirection.South))
                .build();
        gameData = new GameData(player);

    }

    public static GameScene getInstance() {
        return instance;
    }

    @Override
    public void update() {
        inputReader.readInput();
        if (isPlayerAtBorder()){
            endRound();
        }
        CombatManager.checkForCombat(gameData.getPlayer(), gameData.getMonsterList());
        MonstersAI.moveMonsters(gameData.getMonsterList(), gameData.getGameMap().getSize());
        CombatManager.checkForCombat(gameData.getPlayer(), gameData.getMonsterList());

        view.display();
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

    private void looseGame(){
        instance = null;

        //Cambiar a una end screen
        sceneManager.changeScene(new MainMenuScene());
    }
}
