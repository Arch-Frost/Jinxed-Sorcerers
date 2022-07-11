package com.mygdx.cic.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.cic.CIC;
public class Pause_Screen implements Screen{
    private CIC parent;
    private Stage stage;
    static boolean give_option=false;
    static boolean resume_game=false;
    static boolean restart_game=false;
    boolean open_controls_from_pause=false;
    private Skin mySkin;
    public Pause_Screen(CIC cic) {
        parent=cic;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

    }
    @Override
    public void show() {
        Texture texture = new Texture(Gdx.files.internal("backgroundImages/AboutScreenBackground.jpg"));
        Image image1 = new Image(texture);
        image1.setSize(1370,770);
        stage.addActor(image1);
        mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        Button Back_button = new TextButton("Exit", mySkin, "default");
        Back_button.setSize(180, 80);
        Back_button.setPosition(550, 150);
        Back_button.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                give_option=true;
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        Button Controls_button = new TextButton("Controls", mySkin,"default");
        Controls_button.setSize(160, 80);
        Controls_button.setPosition(520, 287);
        Controls_button.setSize(245, 80);
        Controls_button.addListener(new InputListener() {

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                open_controls_from_pause=true;
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                return true;
            }
        });
        Button Resume_button = new TextButton("Resume Game", mySkin, "default");
        Resume_button.setSize(200, 100);
        Resume_button.setPosition(430, 550);
        Resume_button.setSize(450,80);
        Resume_button.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                resume_game=true;
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        Button Restart_button = new TextButton("Restart Game", mySkin, "default");
        Restart_button.setSize(200, 100);
        Restart_button.setPosition(430, 417);
        Restart_button.setSize(450,80);
        Restart_button.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                restart_game=true;
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(Resume_button);
        stage.addActor(Restart_button);
        stage.addActor(Back_button);
        stage.addActor(Controls_button);
    }

    @Override
    public void render(float delta) {
        if(give_option){
            give_option=false;
            CIC.option_screen=new Option_Screen(parent);
            parent.changeScreen(CIC.O_screen);
        }
        if(resume_game){
            resume_game=false;
//            CIC.game_screen=new GameScreen(parent);
            parent.changeScreen(CIC.G_screen);
        }
        if(restart_game){
            restart_game=false;
            CIC.game_screen=new GameScreen(parent);
            parent.changeScreen(CIC.G_screen);
        }
        if (open_controls_from_pause){

            CIC.controls_screen=new Controls_Screen(parent);
            parent.changeScreen(CIC.C_screen);
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

