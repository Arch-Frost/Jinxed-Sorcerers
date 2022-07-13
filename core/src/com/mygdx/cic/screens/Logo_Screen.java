package com.mygdx.cic.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.cic.CIC;

public class Logo_Screen implements Screen {
    private CIC parent;
    private Stage stage;
    float time = 0;
    public Logo_Screen(CIC cic) {
        parent=cic;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Texture texture = new Texture(Gdx.files.internal("Logo/LoadingLogo.jpg"));
        Image image1 = new Image(texture);
        image1.setSize(1370,770);
        stage.addActor(image1);
    }

    @Override
    public void render(float delta) {
        time += Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();


        stage.draw();
    if (time > 5)
    {
        parent.changeScreen(CIC.M_screen);
    }}

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
