package my.rpg.controller.scene;

public class SceneManager {

    private Scene currentScene;
    private boolean isPlaying = true;

    public SceneManager(Scene startingScene){
        currentScene = startingScene;
        currentScene.setSceneManager(this);
    }

    public void start(){
        while(isPlaying){
            currentScene.update();
        }
    }

    public void changeScene(Scene newScene){
        currentScene = newScene;
        currentScene.setSceneManager(this);
    }

    public void stop(){
        isPlaying = false;
    }
}
