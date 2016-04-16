package com.myappstack.shoot.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.TimeUtils;
import com.myappstack.shoot.MyGdxGame;
import com.myappstack.shoot.Utils;
import com.myappstack.shoot.actors.Bullet;
import com.myappstack.shoot.actors.Gun;
import com.myappstack.shoot.actors.InfoBar;
import com.myappstack.shoot.actors.TwoSideBar;
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

    Gun redGun, blueGun;
    TwoSideBar twoSideBar;
    InfoBar bLeftBar, bRightBar;
    ArrayList<Bullet> bullets;
    ArrayList<Ufo> ufos;



    long timeMillSec;

    public LandTwoGunStage(MyGdxGame game){
        this.game = game;
        Gdx.input.setInputProcessor(this);

        loadTextures();
        loadActors();

        bullets = new ArrayList<Bullet>();
        ufos = new ArrayList<Ufo>();

        addUfo();
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        accumulator += delta;
        while (accumulator >= delta) {
            accumulator -= TIME_STEP;
        }

        if(bRightBar.empty() || bLeftBar.empty() || twoSideBar.sideFull()){
            System.out.println("Game Over");
            this.game.setScreen(new GameScreen(this.game, Utils.Screen.MAINMENU));
        }

        if(timeMillSec != 0L && TimeUtils.timeSinceMillis(timeMillSec) > 2000){
            addUfo();
        }

        ArrayList<Ufo> ufosToRemove = new ArrayList<Ufo>();
        int lCount =0, rCount = 0;
        for(Actor a : this.getActors()){
            if(a instanceof Bullet){
                for(Ufo u : ufos){
                    if(u.getBounds().overlaps(((Bullet) a).getBounds())){
                        ufosToRemove.add(u);
                        if(((Bullet) a).getType() == Bullet.Type.FROMLEFT){
                            lCount++;
                            bLeftBar.incrBy(3);
                        }
                        else{
                            rCount++;
                            bRightBar.incrBy(3);
                        }
                    }
                }
            }
        }

        for(Ufo u : ufosToRemove){
            if(ufos.contains(u)){
                ufos.remove(u);
                u.addAction(Actions.removeActor());
            }
        }

        twoSideBar.addLeftCount(lCount);
        twoSideBar.addRightCount(rCount);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //return super.touchDown(screenX, screenY, pointer, button);
        int bw = (int) (Utils.gunHei * Gdx.graphics.getWidth());
        int centerx, centery;
        Bullet b;
        centery = Gdx.graphics.getHeight()/2;
        if(screenX < Gdx.graphics.getWidth()/2){
            centerx = Gdx.graphics.getWidth()/4;
            int newX =(int) (centerx + MathUtils.cos(MathUtils.degRad * redGun.getAngle())* bw/2);
            int newY =(int) (centery + MathUtils.sin(MathUtils.degRad * redGun.getAngle())* bw/2);
            b = new Bullet(this.bRedTexture,Bullet.Type.FROMLEFT,newX,newY,redGun.getAngle());
            bullets.add(b);
            bLeftBar.decrCurrVal();
            addActor(b);
        }
        else{
            centerx = 3*Gdx.graphics.getWidth()/4;
            int newX =(int) (centerx + MathUtils.cos(MathUtils.degRad * blueGun.getAngle())* bw/2);
            int newY =(int) (centery + MathUtils.sin(MathUtils.degRad * blueGun.getAngle())* bw/2);
            b = new Bullet(this.bBlueTexture,Bullet.Type.FROMRIGHT,newX,newY,blueGun.getAngle());
            bullets.add(b);
            bRightBar.decrCurrVal();
            addActor(b);
        }



        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return super.touchUp(screenX, screenY, pointer, button);
    }

    public void addUfo(){
        timeMillSec = TimeUtils.millis();
        Ufo u = new Ufo(this.ufoGreenTexture);
        ufos.add(u);
        addActor(u);
    }

    public void loadActors(){
        this.blueGun = new Gun(this.gunBlueTexture, 0,0);
        this.redGun = new Gun(this.gunRedTexture, 1,1);

        this.redGun.setDebug(true);
        this.blueGun.setDebug(true);

        addActor(redGun);
        addActor(blueGun);

        //two side bar
        Vector2 tsbSize = new Vector2(Gdx.graphics.getWidth() * Utils.twoSideBarWidth, Gdx.graphics.getHeight() * Utils.twoSideBarHeight);
        Vector2 tsbPos = new Vector2(Gdx.graphics.getWidth()/2 - tsbSize.x/2 ,Gdx.graphics.getHeight() - tsbSize.y - 5);
        twoSideBar = new TwoSideBar(bRedTexture, bBlueTexture,tsbPos,tsbSize);
        addActor(twoSideBar);

        //bullet info bars
        int wid = (int) (Gdx.graphics.getWidth() * Utils.landInfoBarWidth);
        int hei = (int) (Gdx.graphics.getHeight() * Utils.landInfoBarHeig);
        int xLPos = (int) tsbPos.x/2 - wid/2;
        bLeftBar = new InfoBar(this.bRedTexture,this.bRedTexture, new Vector2(xLPos, tsbPos.y),
                new Vector2(wid,hei),Utils.MAXBULLETS, Utils.MAXBULLETS, true);
        bRightBar = new InfoBar(this.bBlueTexture,this.bBlueTexture, new Vector2(Gdx.graphics.getWidth() - xLPos - wid, tsbPos.y),
                new Vector2(wid,hei),Utils.MAXBULLETS, Utils.MAXBULLETS, true);

        addActor(bLeftBar);
        addActor(bRightBar);

    }


    public void loadTextures(){
        this.gunRedTexture = new Texture(Gdx.files.internal("land/gunRed.png"));
        this.gunBlueTexture = new Texture(Gdx.files.internal("land/gunBlue.png"));
        this.ufoGreenTexture = new Texture(Gdx.files.internal("land/uGreen.png"));
        this.bBlueTexture = new Texture(Gdx.files.internal("land/bBlue.png"));
        this.bRedTexture = new Texture(Gdx.files.internal("land/bRed.png"));

        //this.bulletInfoElemTxt = new Texture(Gdx.files.internal("BulletBarElem.png"));
        //this.ufoInfoElemTxt = new Texture(Gdx.files.internal("UfoBarElem.png"));
        //this.infoBackTxt = new Texture(Gdx.files.internal("InfoBarBack.png"));
    }
}
