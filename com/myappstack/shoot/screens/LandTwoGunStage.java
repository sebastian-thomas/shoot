package com.myappstack.shoot.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.myappstack.shoot.MyGdxGame;
import com.myappstack.shoot.Utils;
import com.myappstack.shoot.actors.Bullet;
import com.myappstack.shoot.actors.Gun;
import com.myappstack.shoot.actors.Ufo;

import java.util.ArrayList;

/**
 * Created by seb on 05/04/16.
 */
public class LandTwoGunStage extends Stage {
    MyGdxGame game;

    private final float TIME_STEP = 1 / 300f;
    private float accumulator = 0f;

    //load all textures needed
    Texture gunRedTexture, gunBlueTexture;
    Texture ufoGreenTexture;
    Texture bBlueTexture, bRedTexture;

    ArrayList<Bullet> bullets;
    ArrayList<Ufo> ufos;

    Gun redGun, blueGun;

    public LandTwoGunStage(MyGdxGame game){
        this.game = game;
        //this.game.actionResolver.setOrientation(Utils.LANDSCAPE);
        Gdx.input.setInputProcessor(this);

        loadTextures();
        loadActors();

        bullets = new ArrayList<Bullet>();
        ufos = new ArrayList<Ufo>();
    }

    public void loadActors(){
        this.redGun = new Gun(this.gunRedTexture, 0, 1);
        this.blueGun = new Gun(this.gunBlueTexture, 1 , 0);

        addActor(redGun);
        addActor(blueGun);
    }

    public void loadTextures(){
        this.gunRedTexture = new Texture(Gdx.files.internal("land/gunRed.png"));
        this.gunBlueTexture = new Texture(Gdx.files.internal("land/gunBlue.png"));
        this.ufoGreenTexture = new Texture(Gdx.files.internal("land/uGreen.png"));
        this.bBlueTexture = new Texture(Gdx.files.internal("land/bBlue.png"));
        this.bRedTexture = new Texture(Gdx.files.internal("land/bRed.png"));
    }
}
