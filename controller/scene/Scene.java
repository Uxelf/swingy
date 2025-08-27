package my.rpg.controller.scene;

import my.rpg.view.GameView;

public abstract class Scene {
    protected GameView view;
    protected SceneManager sceneManager = null;

    public void setSceneManager(SceneManager newSceneManager){
        sceneManager = newSceneManager;
    }
    public abstract void update();
}
