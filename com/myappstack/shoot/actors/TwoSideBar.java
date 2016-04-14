package com.myappstack.shoot.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.myappstack.shoot.Utils;

/**
 * Created by seb on 14/04/16.
 */
public class TwoSideBar extends Actor {

    int totalTxt = 20;

    Texture leftSideTxt, rightSideTxt;
    Sprite leftSprite, rightSprite;
    Vector2 pos;
    Vector2 size;

    int leftCount, rightCount;
    int indvSprWid;

    public TwoSideBar(Texture leftSideTxt, Texture rightSideTxt, Vector2 pos, Vector2 size){
        this.leftSideTxt = leftSideTxt;
        this.rightSideTxt = rightSideTxt;
        this.pos = pos;
        this.size = size;

        this.leftCount = 0;
        this.rightCount = 0;
        this.indvSprWid =(int) size.x/totalTxt;

        leftSprite = new Sprite(leftSideTxt);
        rightSprite = new Sprite(rightSideTxt);

        leftSprite.setSize(size.x/totalTxt, size.y);
        rightSprite.setSize(size.x/totalTxt, size.y);
    }

    public boolean sideFull(){
        if(leftCount > rightCount){
            if(leftCount - rightCount > Utils.DIFFSIDE){
                return true;
            }
        }
        else{
            if(rightCount - leftCount > Utils.DIFFSIDE){
                return true;
            }
        }
        return false;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        int leftTxtNum = 10, rightTextNum = 10;
        if(leftCount == rightCount || (leftCount == 0 && rightCount ==0)){
            leftTxtNum = rightTextNum = 10; //equal
        }
        else if(leftCount > rightCount ){
            int extraLeftCount = leftCount - rightCount;
            int extraTxt = totalTxt * (extraLeftCount / Utils.DIFFSIDE);
            leftTxtNum = extraTxt > totalTxt ? totalTxt : extraTxt;
            rightTextNum = totalTxt - leftTxtNum;
        }
        else {
            int extraRightCount = rightCount - leftCount;
            int extraTxt = totalTxt * (extraRightCount / Utils.DIFFSIDE);
            rightTextNum = extraTxt > totalTxt ? totalTxt : extraTxt;
            leftTxtNum = totalTxt - rightTextNum;
        }

        int nextXPos =(int) pos.x;
        for(int i=0; i < leftTxtNum; ++i){
            leftSprite.setPosition(nextXPos,pos.y);
            leftSprite.draw(batch);
            nextXPos += leftSprite.getWidth();
        }
        for(int i=0; i < rightTextNum; ++i){
            rightSprite.setPosition(nextXPos, pos.y);
            rightSprite.draw(batch);
            nextXPos += rightSprite.getWidth();
        }

    }
}
