package com.myappstack.shoot;


import com.badlogic.gdx.Game;
import com.myappstack.shoot.screens.GameScreen;


public class MyGdxGame extends Game {

    public ActionResolver actionResolver;

    public MyGdxGame(ActionResolver actionResolver){
        this.actionResolver = actionResolver;
    }

    @Override
    public void create(){
        setScreen(new GameScreen(this, Utils.Screen.MAINMENU));
    }

}
