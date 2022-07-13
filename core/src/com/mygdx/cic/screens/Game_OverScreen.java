package com.mygdx.cic.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.cic.CIC;
import com.mygdx.cic.savedata.Save;

import java.util.concurrent.TimeUnit;

public class Game_OverScreen implements Screen {
    private CIC parent;
    private Stage stage;
    static boolean return_main_menu=false;
    static boolean open_maps_menu=false;
    private Skin mySkin;
    public Game_OverScreen(CIC cic) {
        parent=cic;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

    }
    @Override
    public void show() {
        Texture texture = new Texture(Gdx.files.internal("backgroundImages/GameOverScreenBackground.jpg"));
        Image image1 = new Image(texture);
        image1.setSize(1370,770);
        stage.addActor(image1);
        mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        Texture ExitTexture = new Texture(Gdx.files.internal("Buttons/exitButton.jpg"));
        TextureRegion myExitTextureRegion = new TextureRegion(ExitTexture);
        TextureRegionDrawable myExitTexRegionDrawable = new TextureRegionDrawable(myExitTextureRegion);
        ImageButton ExitButton = new ImageButton(myExitTexRegionDrawable);
        ExitButton.setPosition(805, 445);
        ExitButton.setSize(265, 60);
        ExitButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                return_main_menu=true;
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(ExitButton);



        Texture PlayAgainTexture = new Texture(Gdx.files.internal("Buttons/playAgainButton.jpg"));
        TextureRegion myPlayAgainTextureRegion = new TextureRegion(PlayAgainTexture);
        TextureRegionDrawable myPlayAgainTexRegionDrawable = new TextureRegionDrawable(myPlayAgainTextureRegion);
        ImageButton PlayAgainButton = new ImageButton(myPlayAgainTexRegionDrawable);
        PlayAgainButton.setPosition(293, 402);
        PlayAgainButton.setSize(230, 50);
        PlayAgainButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                open_maps_menu=true;
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(PlayAgainButton);
        Label Game_over_l = new Label("Game Over", mySkin,"default");
        Game_over_l.setSize(200, 100);
        Game_over_l.setPosition(565,300);
        Game_over_l.setColor(Color.BLACK);
        Game_over_l.setAlignment(Align.center);
        Game_over_l.setFontScale(2.5f);
        stage.addActor(Game_over_l);
        Label Score = new Label("Latest Score", mySkin,"default");
        Score.setSize(200, 100);
        Score.setPosition(360,230);
        Score.setColor(Color.BLACK);
        Score.setAlignment(Align.center);
        Score.setFontScale(1.5f);
        stage.addActor(Score);
        Label Score1 = new Label(Long.toString(Save.gd.getTentativeScore()), mySkin,"default");
        Save.save();
        Score1.setSize(200, 100);
        Score1.setPosition(710,230);
        Score1.setColor(Color.BLACK);
        Score1.setAlignment(Align.center);
        Score1.setFontScale(1.5f);
        stage.addActor(Score1);
        Label EnemiesKilled = new Label("Enemies Killed", mySkin,"default");
        EnemiesKilled.setSize(200, 100);
        EnemiesKilled.setPosition(360,170);
        EnemiesKilled.setColor(Color.BLACK);
        EnemiesKilled.setAlignment(Align.center);
        EnemiesKilled.setFontScale(1.5f);
        stage.addActor(EnemiesKilled);
        Label EnemiesKilled1 = new Label(Integer.toString(Save.enemiesKilled), mySkin,"default");
        EnemiesKilled1.setSize(200, 100);
        EnemiesKilled1.setPosition(710,170);
        EnemiesKilled1.setColor(Color.BLACK);
        EnemiesKilled1.setAlignment(Align.center);
        EnemiesKilled1.setFontScale(1.5f);
        stage.addActor(EnemiesKilled1);
        Label Time_Played = new Label("Time Survived", mySkin,"default");
        Time_Played.setSize(200, 100);
        Time_Played.setPosition(360,110);
        Time_Played.setColor(Color.BLACK);
        Time_Played.setAlignment(Align.center);
        Time_Played.setFontScale(1.5f);
        stage.addActor(Time_Played);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(Save.timeSurvived);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(Save.timeSurvived) - (minutes * 60);
        Label Time_Played1 = new Label((minutes + " Min " + seconds + " Seconds"), mySkin,"default");
        Time_Played1.setSize(200, 100);
        Time_Played1.setPosition(710,110);
        Time_Played1.setColor(Color.BLACK);
        Time_Played1.setAlignment(Align.center);
        Time_Played1.setFontScale(1.5f);
        stage.addActor(Time_Played1);

        Save.enemiesKilled = 0;
        Save.timeSurvived = 0;

        Label colon1 = new Label(":", mySkin,"default");
        colon1.setSize(200, 100);
        colon1.setPosition(550,230);
        colon1.setColor(Color.BLACK);
        colon1.setAlignment(Align.center);
        colon1.setFontScale(1.5f);
        stage.addActor(colon1);
        Label colon2 = new Label(":", mySkin,"default");
        colon2.setSize(200, 100);
        colon2.setPosition(550,170);
        colon2.setColor(Color.BLACK);
        colon2.setAlignment(Align.center);
        colon2.setFontScale(1.5f);
        stage.addActor(colon2);
        Label colon3 = new Label(":", mySkin,"default");
        colon3.setSize(200, 100);
        colon3.setPosition(550,110);
        colon3.setColor(Color.BLACK);
        colon3.setAlignment(Align.center);
        colon3.setFontScale(1.5f);
        stage.addActor(colon3);





    }

    @Override
    public void render(float delta) {
        if(return_main_menu){
            return_main_menu=false;
            CIC.menu_screen=new Menu_Screen(parent);
            parent.changeScreen(CIC.M_screen);
        }
        if(open_maps_menu){
            open_maps_menu=false;
            CIC.maps_screen=new Maps_Screen(parent);
            parent.changeScreen(CIC.G_screen);
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
