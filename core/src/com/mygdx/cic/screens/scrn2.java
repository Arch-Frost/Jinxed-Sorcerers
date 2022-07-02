package com.mygdx.cic.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.particles.values.UnweightedMeshSpawnShapeValue;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class scrn2 extends ScreenAdapter {
     private Stage stage;
     private Viewport viewport;
     private AssetManager assetManager;
     private Skin skin;
    @Override
    public void show(){
        viewport=new ExtendViewport(1200,720);
        stage=new Stage(viewport);
        skin=new Skin(Gdx.files.internal("uiskin.json"));

    }
    private TextButton addbutton(String name){
        Skin myskn=new Skin(Gdx.files.internal("uiskin.json"));
        TextButton button =new TextButton(name,myskn);
        return button;
    }


    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(1f,1f,15f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }
    @Override
    public void resize(int width,int height) {
        viewport.update(width,height);
    }    }
