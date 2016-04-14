package com.myappstack.shoot.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.myappstack.shoot.MyGdxGame;
import com.myappstack.shoot.Utils;
import com.myappstack.shoot.actors.Bullet;
import com.myappstack.shoot.actors.Gun;
import com.myappstack.shoot.actors.InfoBar;
import com.myappstack.shoot.actors.Ufo;

import java.util.ArrayList;

/**
 * Created by seb on 19/03/16.
 */
public class GameStage extends Stage{

    MyGdxGame game;

    private final float TIME_STEP = 1 / 300f;
    private float accumulator = 0f;

    //load all textures needed
    Texture gunTexture;
    Texture ufoTexture;
    Texture bulletTexture;

    Gun gun;
    InfoBar bulletBar;
    InfoBar ufoBar;

    //other vars
    int centerx, centery;

    ArrayList<Bullet> bullets;
    ArrayList<Ufo> ufos;

    //util vars
    long timeMillSec;

    public GameStage(MyGdxGame game){
        super(new ScreenViewport());
        Gdx.input.setInputProcessor(this);
        this.game = game;
        //this.game.actionResolver.setOrientation(Utils.POTRAIT);
        loadTextures();
        loadActors();

        bullets = new ArrayList<Bullet>();
        ufos = new ArrayList<Ufo>();
        centerx = Gdx.graphics.getWidth()/2;
        centery = Gdx.graphics.getHeight()/2;

        addUfo();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        int bWid =(int) (Utils.gunHei * Gdx.graphics.getHeight());
        int newX =(int) (centerx + MathUtils.cos(MathUtils.degRad * gun.getAngle())* bWid/2);
        int newY =(int) (centery + MathUtils.sin(MathUtils.degRad * gun.getAngle())* bWid/2);
        Bullet b = new Bullet(this.bulletTexture,newX,newY,gun.getAngle());
        bullets.add(b);
        addActor(b);
        bulletBar.decrCurrVal();
        return true;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        accumulator += delta;
        while (accumulator >= delta) {
            accumulator -= TIME_STEP;
        }

        if(bulletBar.empty() || ufoBar.full()){
            System.out.println("Game Over");
            this.game.setScreen(new GameScreen(this.game, Utils.Screen.MAINMENU));
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
                ufos.remove(u);
                u.addAction(Actions.removeActor());
                ufoBar.decrCurrVal();
                bulletBar.incrBy(5);
            }
        }

    }

    public void addUfo(){
        timeMillSec = TimeUtils.millis();
        Ufo u = new Ufo(this.ufoTexture);
        ufos.add(u);
        addActor(u);
        ufoBar.incrCurrVal();
    }

    public void loadActors(){
        this.gun = new Gun(this.gunTexture);
        //this.ufo = new Ufo(this.ufoTexture);
        addActor(this.gun);
        //addActor(this.ufo);
        loadBulletInfoBar();
        loadUfoInfoBar();
    }

    public void loadUfoInfoBar(){
        Vector2 size = new Vector2(Utils.screenHeight/4, Utils.ufoBarHeight * Gdx.graphics.getHeight());
        //Vector2 pos = new Vector2(Utils.screenWidth - Utils.screenWidth/4 - 2*Utils.bulletBarHeight ,Utils.screenHeight - 2 * Utils.ufoBarHeight);
        Vector2 pos = new Vector2(size.y ,Gdx.graphics.getHeight() - size.y);

        this.ufoBar = new InfoBar(this.ufoTexture,pos,size,Utils.MAXUFO,0, false);
        addActor(this.ufoBar);
    }

    public void loadBulletInfoBar(){
        Vector2 size = new Vector2(Utils.screenHeight/4, Utils.bulletBarHeight * Gdx.graphics.getHeight());
        //Vector2 pos = new Vector2(Utils.screenWidth - Utils.screenWidth/4 -2*Utils.bulletBarHeight ,Utils.screenHeight - 3 * Utils.bulletBarHeight);
        Vector2 pos = new Vector2(2 * size.y, Gdx.graphics.getHeight() - size.y );
        this.bulletBar = new InfoBar(this.bulletTexture,pos,size,Utils.MAXBULLETS,Utils.MAXBULLETS,false);
        addActor(this.bulletBar);
    }

    public void loadTextures(){
        this.gunTexture = new Texture(Gdx.files.internal("gung.png"));
        this.ufoTexture = new Texture(Gdx.files.internal("ufog.png"));
        this.bulletTexture = new Texture(Gdx.files.internal("bulletg.png"));
    }
}
