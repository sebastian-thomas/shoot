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
    Texture barTexture,backTexture;
    Sprite barSprite,backSprite;
    Vector2 pos;
    Vector2 size;
    Vector2 textureSize;
    boolean orientationLand;

    //the bar should be displayed according to current val and full val of the bar
    int fullVal, currentVal;
    int numTextFullVal = 10;//# of textures to be drawn to represent full val

    public InfoBar(Texture barTexture, Texture backTexture, Vector2 pos, Vector2 size, int fullVal, int currentVal, boolean orientationLand){
        this.barTexture = barTexture;
        this.backTexture = backTexture;
        this.barSprite = new Sprite(barTexture);
        this.backSprite = new Sprite(backTexture);
        this.pos = pos;
        this.size = size;
        this.fullVal = fullVal;
        this.currentVal = currentVal;
        this.orientationLand = orientationLand;

        setSpriteVals();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //super.draw(batch, parentAlpha);
        backSprite.draw(batch);
        int numSpritesToBeDrawn = (int) ((this.currentVal*this.numTextFullVal/this.fullVal));

        if(this.orientationLand){
            int nextXpos = (int)this.pos.x+2;
            for(int i=0; i < numSpritesToBeDrawn; ++i){
                barSprite.setPosition(nextXpos -2, this.pos.y);
                barSprite.draw(batch);
                nextXpos = nextXpos + (int)this.textureSize.x+2;
            }
        }
        else{
            int nextYpos = (int) this.pos.y+2;
            for(int i=0; i < numSpritesToBeDrawn; ++i){
                barSprite.setPosition(this.pos.x + 2, nextYpos);
                barSprite.draw(batch);
                nextYpos = nextYpos + (int)this.textureSize.y+2;
            }
        }

    }

    public void setSpriteVals(){
        int w = (int)(size.x / numTextFullVal);
        int h = Utils.getProportionalHeight(w, new Vector2(barTexture.getWidth(), barTexture.getHeight()));
        if(h > size.y){
            h = (int) (size.y - 2);
            w = Utils.getProportionalWidth(h,new Vector2(barTexture.getWidth(), barTexture.getHeight()));
            System.out.print("Prob?");
        }

        h = (int)size.y;
        w = (int) ((size.x - numTextFullVal*4)/numTextFullVal);


        if(this.orientationLand){
            this.textureSize = new Vector2(w,h);
            barSprite.setSize(w,h);
        }
        else{
            this.textureSize = new Vector2(h,w);
            barSprite.setSize(h,w);
        }

//        if(this.orientationLand){
//           // barSprite.rotate(90);
//            this.numTextFullVal =(int) (this.size.x/w);
//        }
//        else{
//            this.numTextFullVal =(int) (this.size.x/h);
//        }


        backSprite.setPosition(this.pos.x, this.pos.y-2);
        if(this.orientationLand){
            backSprite.setSize(size.x+4,size.y+2);
        }
        else{
            backSprite.setSize(size.y+4,size.x+2);
        }
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

    public void incrBy(int by){
        this.currentVal = this.currentVal + by;
        if(this.currentVal > this.fullVal){
            this.currentVal = this.fullVal;
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
