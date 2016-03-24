package com.myappstack.shoot;


import com.badlogic.gdx.Game;
import com.myappstack.shoot.screens.GameScreen;


public class MyGdxGame extends Game {

    @Override
    public void create(){
        setScreen(new GameScreen());
    }

}
