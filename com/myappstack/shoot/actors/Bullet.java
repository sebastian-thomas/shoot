package com.myappstack.shoot.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.myappstack.shoot.Utils;

/**
 * Created by seb on 19/03/16.
 */
public class Bullet extends Image {
    private Vector2 pos;
    private Vector2 size;
    private Vector2 velocity;
    private Rectangle bounds;

    private int SPEED = 5;

    public Bullet(Texture img, int x, int y, int angle){
        super(img);
        this.size = calcSize(new Vector2(img.getWidth(), img.getHeight()));
        this.pos = new Vector2(x,y);

        setSize(this.size.x, this.size.y);
        setPosition(this.pos.x, this.pos.y);
        setRotation(angle);
        setVelocity(angle);

        bounds = new Rectangle();
        bounds.set(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        this.pos.add(this.velocity);
        setPosition(this.pos.x, this.pos.y);
        bounds.set(getX(), getY(), getWidth(), getHeight());

        if(outOfScreen()){
            this.addAction(Actions.removeActor());
        }
    }

    public boolean outOfScreen(){
        if(this.pos.x < 0 || this.pos.x > Gdx.graphics.getWidth() ||
                this.pos.y < 0 || this.pos.y > Gdx.graphics.getHeight()){
            return true;
        }
        return false;
    }

    public Rectangle getBounds(){
        return bounds;
    }

    private void setVelocity(int angle){
        float rad =(float) (angle * Math.PI)/180;
        this.velocity = new Vector2(this.SPEED * MathUtils.cos(rad), this.SPEED * MathUtils.sin(rad));
    }

    private Vector2 calcSize(Vector2 imgSize){
        int nHeight =(int) (Gdx.graphics.getHeight() * Utils.bulletHei);
        int nWidth = Utils.getProportionalWidth(nHeight, imgSize);
        return new Vector2(nWidth, nHeight);
    }

    private Vector2 calcPos(){
        int xPos = (int) (3*Gdx.graphics.getWidth()/4 - this.size.x/2);
        int yPos = (int) (Gdx.graphics.getHeight()/2 - this.size.y);
        return new Vector2(xPos, yPos);
    }
}
