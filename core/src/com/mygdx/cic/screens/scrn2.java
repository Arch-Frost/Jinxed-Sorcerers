package com.mygdx.cic.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.particles.values.UnweightedMeshSpawnShapeValue;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.text.CollationElementIterator;

public class scrn2 extends ScreenAdapter {

    private Stage stage;

    private Viewport viewport;
    private AssetManager assetManager;
    private Skin skin;
    private Label outputLabel;

    @Override
    public void show(){
        viewport = new ScreenViewport();
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        int row_height = Gdx.graphics.getWidth() / 12;
        int col_width = Gdx.graphics.getWidth() / 12;

        Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        Button button2 = new TextButton("Text Button",mySkin,"small");
        button2.setSize(col_width*4,row_height);
        button2.setPosition(col_width*7,Gdx.graphics.getHeight()-row_height*3);
        button2.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                outputLabel.setText("Press a Button");
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                outputLabel.setText("Pressed Text Button");
                return true;
            }
        });
        stage.addActor(button2);

    }
    private TextButton addbutton(String name){
        Skin myskn=new Skin(Gdx.files.internal("uiskin.json"));
        TextButton button =new TextButton(name,myskn);
        return button;
    }


    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }
    @Override
    public void resize(int width,int height) {
        viewport.update(width,height);
    }    }
