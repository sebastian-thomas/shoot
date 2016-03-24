package com.myappstack.shoot.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.myappstack.shoot.Utils;

/**
 * Created by seb on 19/03/16.
 */
public class Gun extends Image{

    private Vector2 pos;
    private Vector2 size;

    private int angle;
    private int INCANGLE = 5;

    public Gun(Texture img){
        super(img);
        this.size = calcSize(new Vector2(img.getWidth(), img.getHeight()));
        this.pos = calcPos();
        this.angle = 0;

        setSize(this.size.x, this.size.y);
        setPosition(this.pos.x, this.pos.y);
        setOrigin(getHeight()/2, getHeight()/2);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        this.angle += this.INCANGLE;
        this.angle = (this.angle % 360);
        rotateBy(this.INCANGLE);
    }

    public int getAngle(){
        return angle;
    }

    private Vector2 calcSize(Vector2 imgSize){
        int nWidth = Utils.gunWid;
        int nHeight = Utils.getProportionalHeight(nWidth, imgSize);
        return new Vector2(nWidth, nHeight);
    }

    private Vector2 calcPos(){
        int xPos = (int) (Gdx.graphics.getWidth()/2 - this.size.x/2);
        int yPos = (int) (Gdx.graphics.getHeight()/2 - this.size.y/2);
        return new Vector2(xPos, yPos);
    }


}
