package com.myappstack.shoot;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by seb on 19/03/16.
 */
public class Utils {

    public static int MAXUFO = 20;
    public static int MAXBULLETS = 50;
    public static int DIFFSIDE = 5;

    public static int screenWidth = Gdx.graphics.getWidth();
    public static int screenHeight = Gdx.graphics.getHeight();

    public static float ufoHei = 0.06f;
    public static float bulletHei =0.02f;
    public static float bulletHeiLand =0.03f;
    public static float gunHei =0.1f;

    public static float ufoBarHeight = 0.03f ;
    public static float bulletBarHeight = 0.03f;

    public static float twoSideBarHeight = 0.03f;
    public static float twoSideBarWidth = 0.3f;

    public static float landInfoBarWidth = 0.2f;
    public static float landInfoBarHeig = 0.1f;


    public static String LANDSCAPE = "LANDSCAPE";
    public static String POTRAIT = "POTRAIT";

    public static enum Screen{
        MAINMENU,
        SINGLEHAND,
        TWOHAND,
        TWOPLAYER
    };

    public static int getProportionalHeight(int width, Vector2 dims){
        return (int) ((dims.y*width)/dims.x);
    }

    public static int getProportionalWidth(int height, Vector2 dims){
        return (int) ((dims.x*height)/dims.y);
    }
}
