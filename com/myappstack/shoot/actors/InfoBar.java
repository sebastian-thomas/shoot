package com.myappstack.shoot.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.myappstack.shoot.Utils;

/**
 * Created by seb on 24/03/16.
 */
public class InfoBar extends Actor {
    Texture barTexture;
    Sprite barSprite;
    Vector2 pos;
    Vector2 size;
    Vector2 textureSize;

    //the bar should be displayed according to current val and full val of the bar
    int fullVal, currentVal;
    int numTextFullVal = 10;//# of textures to be drawn to represent full val

    public InfoBar(Texture barTexture, Vector2 pos, Vector2 size, int fullVal, int currentVal){
        this.barTexture = barTexture;
        this.barSprite = new Sprite(barTexture);
        this.pos = pos;
        this.size = size;
        this.fullVal = fullVal;
        this.currentVal = currentVal;

        setSpriteVals();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //super.draw(batch, parentAlpha);
        int numSpritesToBeDrawn = (int) ((this.currentVal*this.numTextFullVal/this.fullVal));
        int nextXpos = (int)this.pos.x;
        for(int i=0; i < numSpritesToBeDrawn; ++i){
            barSprite.setPosition(nextXpos, this.pos.y);
            barSprite.draw(batch);
            nextXpos = nextXpos + (int)this.textureSize.x;
        }
    }

    public void setSpriteVals(){
        int w = (int)(size.x / numTextFullVal);
        int h = Utils.getProportionalHeight(w, new Vector2(barTexture.getWidth(), barTexture.getHeight()));
        if(h > size.y){
            h = (int) (size.y - 2);
            w = Utils.getProportionalWidth(h,new Vector2(barTexture.getWidth(), barTexture.getHeight()));
        }

        this.textureSize = new Vector2(w,h);
        this.numTextFullVal =(int) (this.size.x/w);
        barSprite.setSize(w,h);
    }

    public void incrCurrVal(){
        if(this.currentVal < this.fullVal){
            this.currentVal++;
        }
    }

    public void decrCurrVal(){
        if(this.currentVal > 0){
            this.currentVal--;
        }
    }

    public boolean empty(){
        return this.currentVal == 0;
    }

    public boolean full(){
        return this.currentVal == this.fullVal;
    }

    public void setCurrentVal(int currentVal){
        this.currentVal = currentVal;
    }
}
