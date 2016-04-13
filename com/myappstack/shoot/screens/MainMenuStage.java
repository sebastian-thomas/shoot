package com.myappstack.shoot.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.myappstack.shoot.MyGdxGame;
import com.myappstack.shoot.Utils;

/**
 * Created by seb on 13/04/16.
 */
public class MainMenuStage extends Stage {

    MyGdxGame game;

    Texture singleHandTxt;
    Texture twoHandsTxt;
    Texture twoPlayerTxt;

    public MainMenuStage(MyGdxGame game){
        loadTextures();
        Gdx.input.setInputProcessor(this);
        this.game = game;
        addMenuItems();
    }

    public void addMenuItems(){
        Image singleHand = new Image(singleHandTxt);
        Image twoHand = new Image(twoHandsTxt);
        Image twoPlayer = new Image(twoPlayerTxt);

        int sz = Gdx.graphics.getHeight()/4;


        singleHand.setSize(sz, sz);
        twoHand.setSize(sz, sz);
        twoPlayer.setSize(sz, sz);

        int posy = Gdx.graphics.getHeight()/3;
        singleHand.setPosition(sz, posy);
        twoHand.setPosition(Gdx.graphics.getWidth() / 2 - sz / 2, posy);
        twoPlayer.setPosition(Gdx.graphics.getWidth() - 2 * sz, posy);

        singleHand.setBounds(sz, posy, sz, sz);
        twoHand.setBounds(Gdx.graphics.getWidth() / 2 - sz / 2, posy, sz,sz);
        twoPlayer.setBounds(Gdx.graphics.getWidth() - 2 * sz,posy,sz,sz);
        singleHand.setDebug(true);

        addActor(singleHand);
        addActor(twoHand);
        addActor(twoPlayer);

        singleHand.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Helllooo");
                game.setScreen(new GameScreen(game,Utils.Screen.SINGLEHAND));
            }
        });

        twoHand.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GameScreen(game, Utils.Screen.TWOHAND));
            }
        });

        twoPlayer.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                //game.setScreen(new GameScreen(game, Utils.Screen.TWOPLAYER));
            }
        });
    }

    public void loadTextures(){
        this.singleHandTxt = new Texture(Gdx.files.internal("gung.png"));
        this.twoHandsTxt = new Texture(Gdx.files.internal("ufog.png"));
        this.twoPlayerTxt = new Texture(Gdx.files.internal("bulletg.png"));
    }

}
