package com.mygdx.cic.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.cic.CIC;

public class Menu_Screen  implements Screen {
    Sound sound ;
    private CIC parent;
    private Stage stage;
    static boolean start_game=false;
    static boolean open_controls=false;
    static boolean open_About=false;
    private Skin mySkin;
    public Menu_Screen(CIC cic) {
        parent=cic;

            stage = new Stage(new ScreenViewport());

            Gdx.input.setInputProcessor(stage);


        }


    @Override
    public void show() {
        //   final Sound sound = Gdx.audio.newSound(Gdx.files.internal("Sounds/g.wav"));
        Texture texture = new Texture(Gdx.files.internal("BackgroundImages/MenuScreenBackground.jpg"));
        Image image1 = new Image(texture);
        image1.setSize(1370,770);
        stage.addActor(image1);


        Texture PlayTexture = new Texture(Gdx.files.internal("Buttons/playButton.jpg"));
        TextureRegion myPlayTextureRegion = new TextureRegion(PlayTexture);
        TextureRegionDrawable myPlayTexRegionDrawable = new TextureRegionDrawable(myPlayTextureRegion);
        ImageButton PlayButton = new ImageButton(myPlayTexRegionDrawable); //Set the button up
        PlayButton.setPosition(788, 440);
        PlayButton.setSize(265, 70);
        PlayButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                start_game = true;
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        Texture ControlTexture = new Texture(Gdx.files.internal("Buttons/ControlsButton.jpg"));
        TextureRegion myControlTextureRegion = new TextureRegion(ControlTexture);
        TextureRegionDrawable myControlTexRegionDrawable = new TextureRegionDrawable(myControlTextureRegion);
        ImageButton ControlButton = new ImageButton(myControlTexRegionDrawable); //Set the button up

        ControlButton.setPosition(732, 345);
        ControlButton.setSize(370, 45);
        ControlButton.addListener(new InputListener() {

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                open_controls=true;
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                return true;
            }
        });



        Texture AboutTexture = new Texture(Gdx.files.internal("Buttons/AboutButton.jpg"));
        TextureRegion myAboutTextureRegion = new TextureRegion(AboutTexture);
        TextureRegionDrawable myAboutTexRegionDrawable = new TextureRegionDrawable(myAboutTextureRegion);
        ImageButton AboutButton = new ImageButton(myAboutTexRegionDrawable); //Set the button up

        AboutButton.setPosition(763, 265);
        AboutButton.setSize(310, 35);

        AboutButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                open_About=true;
              //  sound.play(1.0f);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });










        Texture ExitTexture = new Texture(Gdx.files.internal("Buttons/exitButton.jpg"));
        TextureRegion myExitTextureRegion = new TextureRegion(ExitTexture);
        TextureRegionDrawable myExitTexRegionDrawable = new TextureRegionDrawable(myExitTextureRegion);
        ImageButton ExitButton = new ImageButton(myExitTexRegionDrawable); //Set the button up

        ExitButton.setPosition(770, 180);
        ExitButton.setSize(300, 35);
        ExitButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.exit(0);}



            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        stage.addActor(PlayButton);
        stage.addActor(ControlButton);
        stage.addActor(AboutButton);
        stage.addActor(ExitButton);



    }

        @Override
        public void render ( float delta){
            if(start_game){
                start_game=false;
                CIC.game_screen=new GameScreen(parent);
                parent.changeScreen(CIC.G_screen);
            }
            if (open_controls){
                open_controls=false;
                CIC.controls_screen=new Controls_Screen(parent);
                parent.changeScreen(CIC.C_screen);
            }
            if (open_About){
                open_About=false;
                CIC.about_screen=new About_Screen(parent);
                parent.changeScreen(CIC.A_screen);
            }

            Gdx.gl.glClearColor(1, 1, 1, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            stage.act();
            stage.draw();
        }


    @Override
        public void resize ( int width, int height){

        }

        @Override
        public void pause () {

        }

        @Override
        public void resume () {

        }

        @Override
        public void hide () {

        }

        @Override
        public void dispose () {
               stage.dispose();
        }

    }

