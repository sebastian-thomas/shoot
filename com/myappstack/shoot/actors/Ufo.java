package com.myappstack.shoot.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.myappstack.shoot.Utils;

/**
 * Created by seb on 19/03/16.
 */
public class Ufo extends Image {

    private Vector2 pos;
    private Vector2 size;
    private Rectangle bounds;

    public Ufo(Texture img){
        super(img);
        this.size = calcSize(new Vector2(img.getWidth(), img.getHeight()));
        this.pos = calcPos();

        setSize(this.size.x, this.size.y);
        setPosition(this.pos.x, this.pos.y);

        bounds = new Rectangle();
        bounds.set(getX(), getY(), getWidth(), getHeight());
    }

    private Vector2 calcSize(Vector2 imgSize){
        int nHeight = (int)(Gdx.graphics.getHeight() * Utils.ufoHei);
        int nWidth = Utils.getProportionalWidth(nHeight, imgSize);
        return new Vector2(nWidth, nHeight);
    }

    public Rectangle getBounds(){
        return bounds;
    }

    private Vector2 calcPos(){
        //int xPos = (int) (3*Gdx.graphics.getWidth()/4 - this.size.x/2);
        //int yPos = (int) (Gdx.graphics.getHeight()/2 - this.size.y);
        int xPos = MathUtils.random(1,(int)(Gdx.graphics.getWidth() - this.size.x));
        int yPos = MathUtils.random(1, (int)(Gdx.graphics.getHeight() - this.size.y));

        return new Vector2(xPos, yPos);
    }

}
