package my.rpg.controller.game;

import my.rpg.controller.monstersAI.MonstersAI;
import my.rpg.model.combatSystem.CombatManager;
import my.rpg.model.gameState.GameData;
import my.rpg.model.hero.Hero;
import my.rpg.controller.inputReader.InputType;
import my.rpg.controller.inputReader.PlayerInputReader;
import my.rpg.model.movementComponent.MovementComponent;

public class Game {

    private static Game instance = null;

    private GameData gameData;
    private PlayerInputReader inputReader;
    private boolean playing;

    public Game(Hero player, InputType inputType){
        if (player == null){
            throw new RuntimeException("Can't start a game with player = null");
        }

        if (instance != null){
            System.out.println("Warning, an instance of Game is already running, it will be overwritten");
        }
        instance = this;

        this.gameData = new GameData(player);
        inputReader = new PlayerInputReader(inputType);

        //mapear inputs a jugador
    }

    public static Game getInstance() {
        return instance;
    }

    public void start(){
        gameData.initNewRound();
        playing = true;

        try{
            while (playing){
                loop();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void loop(){
        inputReader.readInput();
        if (isPlayerAtBorder()){
            endRound();
        }
        CombatManager.checkForCombat(gameData.getPlayer(), gameData.getMonsterList());
        MonstersAI.moveMonsters(gameData.getMonsterList(), gameData.getGameMap().getSize());
        CombatManager.checkForCombat(gameData.getPlayer(), gameData.getMonsterList());
    }

    private boolean isPlayerAtBorder(){
        int mapSize = gameData.getGameMap().getSize();
        MovementComponent pMovComp = gameData.getPlayer().getMovementComponent();
        int pX = pMovComp.getX();
        int pY = pMovComp.getY();
        return (pX == 0 || pX == mapSize - 1 || pY == 0 || pY == mapSize - 1);
    }

    public void endRound(){
        playing = false;
    }

    public void looseGame(){
        playing = false;
    }

    public void rewardPlayer(int level){
        System.out.println("Rewarding player with things, level " + level);
    }

    public static void exitGame(){
        //salvar progreso
    }

}
