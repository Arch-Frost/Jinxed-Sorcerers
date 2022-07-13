package com.mygdx.cic.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.cic.CIC;

public class Animation_Screen implements Screen {
    private TextureRegion trr;
    private CIC parent;
    private Stage stage;
    private Animation animation;
    private SpriteBatch batch;
    private Sound sound;
    float time = 0;
    private float origin_x, origin_y;

    public Animation_Screen(CIC cic){
        sound = Gdx.audio.newSound(Gdx.files.internal("Sounds/LOADING.wav"));
        sound.play(1.0f);
        parent=cic;
    }
    @Override
    public void show() {

        stage = new Stage(new ScreenViewport());

        TextureRegion [] textarray=new TextureRegion[21];
        for (int i=0;i<=20;i++){
            textarray[i]=new TextureRegion(new Texture(Gdx.files.internal("MapsImages/"+(String.valueOf(i)+".png"))));
        }

        animation=new Animation(0.1f,textarray);
        TextureRegion firstTexture = textarray[0];
        origin_x = (Gdx.graphics.getWidth()  - firstTexture.getRegionWidth())  / 2;
        origin_y = (Gdx.graphics.getHeight() - firstTexture.getRegionHeight()) / 2;


        batch=new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        time += Gdx.graphics.getDeltaTime();
        trr= (TextureRegion) animation.getKeyFrame(time);
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        stage.act();

        batch.draw(trr,0,0,1370,770);
        batch.draw(trr,0,0,1370,770);
        batch.draw(trr,0,0,1370,770);
        batch.draw(trr,0,0,1370,770);

        batch.end();
        stage.draw();
        if (time > 5)
        {
            parent.changeScreen(CIC.LO_screen);
        }
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
