package com.myappstack.shoot.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by seb on 24/03/16.
 */
public class InfoBar extends Actor {
    Texture barTexture;
    Vector2 pos;
    Vector2 size;

    //the bar should be displayed according to current val and full val of the bar
    int fullVal, currentVal;
}
