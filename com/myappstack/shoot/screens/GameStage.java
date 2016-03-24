package com.myappstack.shoot.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.TimeUtils;
import com.myappstack.shoot.Utils;
import com.myappstack.shoot.actors.Bullet;
import com.myappstack.shoot.actors.Gun;
import com.myappstack.shoot.actors.Ufo;

import java.util.ArrayList;

/**
 * Created by seb on 19/03/16.
 */
public class GameStage extends Stage{

    private final float TIME_STEP = 1 / 300f;
    private float accumulator = 0f;

    //load all textures needed
    Texture gunTexture;
    Texture ufoTexture;
    Texture bulletTexture;

    //all actors
    Gun gun;
    //Ufo ufo;

    //other vars
    int centerx, centery;

    ArrayList<Bullet> bullets;
    ArrayList<Ufo> ufos;

    //util vars
    long timeMillSec;

    public GameStage(){
        Gdx.input.setInputProcessor(this);

        loadTextures();
        loadActors();

        bullets = new ArrayList<Bullet>();
        ufos = new ArrayList<Ufo>();
        centerx = Gdx.graphics.getWidth()/2;
        centery = Gdx.graphics.getHeight()/2;

        addUfo();
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        int newX =(int) (centerx + MathUtils.cos(MathUtils.degRad * gun.getAngle())* Utils.gunWid/2);
        int newY =(int) (centery + MathUtils.sin(MathUtils.degRad * gun.getAngle())* Utils.gunWid/2);
        Bullet b = new Bullet(this.bulletTexture,newX,newY,gun.getAngle());
        bullets.add(b);
        addActor(b);
        return true;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        accumulator += delta;
        while (accumulator >= delta) {
            accumulator -= TIME_STEP;
        }

        if(timeMillSec != 0L && TimeUtils.timeSinceMillis(timeMillSec) > 2000){
            addUfo();
        }

        ArrayList<Ufo> ufosToRemove = new ArrayList<Ufo>();
        for(Actor a : this.getActors()){
            if(a instanceof Bullet){
                for(Ufo u : ufos){
                    if(u.getBounds().overlaps(((Bullet) a).getBounds())){
                        ufosToRemove.add(u);
                    }
                }
            }
        }

        for(Ufo u : ufosToRemove){
            if(ufos.contains(u)){
                System.out.println("Removin ufo");
                ufos.remove(u);
                u.addAction(Actions.removeActor());
            }
        }

    }

    public void addUfo(){
        timeMillSec = TimeUtils.millis();
        Ufo u = new Ufo(this.ufoTexture);
        ufos.add(u);
        addActor(u);
    }

    public void loadActors(){
        this.gun = new Gun(this.gunTexture);
        //this.ufo = new Ufo(this.ufoTexture);
        addActor(this.gun);
        //addActor(this.ufo);
    }

    public void loadTextures(){
        this.gunTexture = new Texture(Gdx.files.internal("gung.png"));
        this.ufoTexture = new Texture(Gdx.files.internal("ufog.png"));
        this.bulletTexture = new Texture(Gdx.files.internal("bulletg.png"));
    }
}
