package com.mygdx.cic.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
//import com.mygdx.template.GameConstants;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.cic.CIC;


public  class Loading_Screen implements Screen {
    private TextureRegion trr;
    private CIC parent;
    private Stage stage;
    private Animation animation;
    private SpriteBatch batch;
    final Sound sound ;

    float time = 0;
    private float origin_x, origin_y;
    public Loading_Screen(CIC cic){
        sound = Gdx.audio.newSound(Gdx.files.internal("Sounds/LOADING.wav"));
        sound.play(1.0f);

        parent=cic;
    }



    @Override
    public void show() {

                stage = new Stage(new ScreenViewport());

                TextureRegion [] textrarray=new TextureRegion[21];
                for (int i=0;i<=20;i++){
                    textrarray[i]=new TextureRegion(new Texture(Gdx.files.internal("Loadingscreenimages/"+(String.valueOf(i)+".jpg"))));
                }

        animation=new Animation(0.1f,textrarray);
        TextureRegion firstTexture = textrarray[0];
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
        batch.end();
        stage.draw();
        if (time > 3) // If 5s happened
        {
            parent.changeScreen(CIC.AN_screen);
        }



    }

    @Override
    public void resize(int width, int height) {}

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