package com.myappstack.shoot.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.myappstack.shoot.Utils;

import javax.rmi.CORBA.Util;

/**
 * Created by seb on 19/03/16.
 */
public class Gun extends Image{

    private Vector2 pos;
    private Vector2 size;

    private int angle;
    private int INCANGLE = 5;
    private int rotation;

    public Gun(Texture img){
        super(img);
        this.size = calcSizeLand(new Vector2(img.getWidth(), img.getHeight()));
        this.pos = calcPos();
        this.angle = 0;
        this.rotation = 1;

        setSize(this.size.x, this.size.y);
        setPosition(this.pos.x, this.pos.y);
        setOrigin(getWidth() / 2, getWidth() / 2);
    }

    public Gun(Texture img, int pos, int rotation){
        super(img);
        this.size = calcSizeLand(new Vector2(img.getWidth(), img.getHeight()));
        this.pos = calcPosLand(pos);

        this.angle = 0;
        this.rotation = rotation;

        setSize(this.size.x, this.size.y);
        setPosition(this.pos.x, this.pos.y);
        if(this.rotation == 0){
            setOrigin(getWidth() - getHeight() / 2, getHeight() / 2);
        }
        else{
            setOrigin(getHeight() / 2, getHeight() / 2);
        }

    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if(rotation == 0){
            this.angle += this.INCANGLE;
            if(this.angle >= 360){
                this.angle = 0;
            }
            rotateBy(this.INCANGLE);
        }
        else{
            this.angle -= this.INCANGLE;
            if(this.angle <= -360){
                this.angle = 0;
            }
           rotateBy(-1 * this.INCANGLE);
        }

    }

    public int getAngle(){
        if(this.rotation == 1){
            return angle;
        }
        else{
            return 180 + angle;
        }
    }

    private Vector2 calcSizeLand(Vector2 imgSize){
        //nt nWidth = (int) (Gdx.graphics.getWidth() * Utils.gunWid);
        //int nHeight = Utils.getProportionalHeight(nWidth, imgSize);
        int nHeight = (int) (Gdx.graphics.getHeight() * Utils.gunHei);
        int nWidth = Utils.getProportionalWidth(nHeight,imgSize);
        return new Vector2(nWidth, nHeight);
    }

    private Vector2 calcPosLand(int pos){
        int xPos =0, yPos = 0;
        if(pos == 1){
            //first
            xPos = (int) (Gdx.graphics.getWidth()/4 - this.size.x/2);
            yPos = (int) (Gdx.graphics.getHeight()/2 - this.size.y/2);
        }
        else {
            xPos = (int) (3*Gdx.graphics.getWidth()/4 - this.size.x/2);
            yPos = (int) (Gdx.graphics.getHeight()/2 - this.size.y/2);
        }

        return new Vector2(xPos, yPos);
    }

//    private Vector2 calcSize(Vector2 imgSize){
//        int nWidth = (int)(Gdx.graphics.getWidth() * Utils.gunWid);
//        int nHeight = Utils.getProportionalHeight(nWidth, imgSize);
//        return new Vector2(nWidth, nHeight);
//    }

    private Vector2 calcPos(){
        int xPos = (int) (Gdx.graphics.getWidth()/2 - this.size.x/2);
        int yPos = (int) (Gdx.graphics.getHeight()/2 - this.size.y/2);
        return new Vector2(xPos, yPos);
    }


}
