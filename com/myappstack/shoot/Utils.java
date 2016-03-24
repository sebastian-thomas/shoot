package com.myappstack.shoot;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by seb on 19/03/16.
 */
public class Utils {

    public static int MAXUFO = 20;

    public static int screenWidth = Gdx.graphics.getWidth();
    public static int screenHeight = Gdx.graphics.getHeight();

    public static int ufoWid =(int) (0.06 * screenWidth);
    public static int bulletWid =(int) (0.03 * screenWidth);
    public static int gunWid =(int) (0.1 * screenWidth);

    public static int ufoBarHeight = (int) (0.03 * screenWidth);
    public static int bulletBarHeight = (int) (0.03 * screenWidth);

    public static int getProportionalHeight(int width, Vector2 dims){
        return (int) ((dims.y*width)/dims.x);
    }

    public static int getProportionalWidth(int height, Vector2 dims){
        return (int) ((dims.x*height)/dims.y);
    }
}
