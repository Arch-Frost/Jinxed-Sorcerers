package com.mygdx.cic.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.cic.CIC;

public class About_Screen implements Screen {
    private CIC parent;
    private Stage stage;
    static boolean return_main_menu=false;
    private Skin mySkin;
    public About_Screen(CIC cic) {
        parent=cic;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

    }
    @Override
    public void show() {
        Texture texture = new Texture(Gdx.files.internal("backgroundimage.jpg"));
        Image image1 = new Image(texture);
        image1.setSize(1370,770);
        stage.addActor(image1);
        mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        Label discription1 = new Label("This game is created by Abubakar Ahmed and Adeel, (A)3.", mySkin,"default");
        Label discription2 = new Label("We created this game using Libgdx Box 2d and Scene 2d.", mySkin,"default");
        Label discription3=new Label("Tiled map editor is used to create maps for this game.",mySkin,"default");
        Label discription4 = new Label("This game is a 2 Player game where these both have to survive " +
                "\nusing assests they have.", mySkin,"default");
        Label discription5 = new Label("You can follow us on instagram: ", mySkin,"default");
        Label insta1 = new Label("Abubakar Javed : abubakar_.jani", mySkin,"default");
        Label insta2 = new Label("Ahmed Zafar : notahmad152", mySkin,"default");
        Label insta3 = new Label("Adeel Ahmed : Abhi nhi banaya, humain follow ker lo\n jab banaya tou bata dein ge.", mySkin,"default");
        discription1.setSize(160, 80);
        discription1.setPosition(500,550);
        discription1.setAlignment(Align.center);
        discription1.setFontScale(1.5f);
        stage.addActor(discription1);
        discription2.setSize(160, 80);
        discription2.setPosition(490,500);
        discription2.setAlignment(Align.center);
        discription2.setFontScale(1.5f);
        stage.addActor(discription2);
        discription3.setSize(160, 80);
        discription3.setPosition(468,450);
        discription3.setAlignment(Align.center);
        discription3.setFontScale(1.5f);
        stage.addActor(discription3);
        discription4.setSize(160, 80);
        discription4.setPosition(538,390);
        discription4.setAlignment(Align.center);
        discription4.setFontScale(1.5f);
        stage.addActor(discription4);
        discription5.setSize(160, 80);
        discription5.setPosition(333,330);
        discription5.setAlignment(Align.center);
        discription5.setFontScale(1.5f);
        stage.addActor(discription5);
        insta1.setSize(160, 80);
        insta1.setPosition(405,280);
        insta1.setAlignment(Align.center);
        insta1.setFontScale(1.5f);
        stage.addActor(insta1);
        insta2.setSize(160, 80);
        insta2.setPosition(370,240);
        insta2.setAlignment(Align.center);
        insta2.setFontScale(1.5f);
        stage.addActor(insta2);
        insta3.setSize(160, 80);
        insta3.setPosition(530,185);
        insta3.setAlignment(Align.center);
        insta3.setFontScale(1.5f);
        stage.addActor(insta3);
        Button Back_button = new TextButton("Back", mySkin, "default");
        Back_button.setSize(160, 80);
        Back_button.setPosition(90, 30);
        Back_button.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                return_main_menu=true;
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(Back_button);
    }

    @Override
    public void render(float delta) {
        if(return_main_menu){
            return_main_menu=false;
            CIC.menu_screen=new Menu_Screen(parent);
            parent.changeScreen(CIC.M_screen);
        }
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
