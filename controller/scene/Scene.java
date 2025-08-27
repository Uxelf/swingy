package my.rpg.controller.scene;

import my.rpg.controller.inputReader.InputReader;
import my.rpg.view.TemplateView;

public abstract class Scene {
    protected TemplateView view;
    protected SceneManager sceneManager = null;
    protected InputReader inputReader;

    public void setSceneManager(SceneManager newSceneManager){
        sceneManager = newSceneManager;
    }
    public abstract void update();
}
