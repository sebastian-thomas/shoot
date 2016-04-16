package com.myappstack.shoot.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.myappstack.shoot.MyGdxGame;
import com.myappstack.shoot.Utils;

/**
 * Created by seb on 19/03/16.
 */
public class GameScreen implements Screen {

    private Stage stage;

    public GameScreen(MyGdxGame game, Utils.Screen stage){
        if(stage == Utils.Screen.MAINMENU){
            this.stage = new MainMenuStage(game);
        }
        else if(stage == Utils.Screen.SINGLEHAND){
            this.stage = new GameStage(game);
        }
        else if(stage == Utils.Screen.TWOHAND){
            this.stage = new LandTwoGunStage(game);
        }
        else{
            //hii
            this.stage = new GameStage(game);
        }
        //this.stage = new LandTwoGunStage(game);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(23/256f, 35/256f, 69/256f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
        stage.act();
    }

    @Override
    public void resize(int width, int height) {
        //stage.getViewport().update(width, height, false);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
