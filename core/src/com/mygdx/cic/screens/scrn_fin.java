package com.mygdx.cic.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;


public class scrn_fin  implements Screen {
    private Stage stage;
    private Image logo;
    private Image loadingFrame;
    private Image loadingBarHidden;
    private Image screenBg;
    private Image loadingBg;

    private float startX, endX;
    private float percent;

    private Actor loadingBar;
//    private CIC parent;

//
//    private Viewport viewport;
//    private Skin mySkin;
//    private Label outputLabel;
//    public static boolean new_screen=true;

//        stage = new Stage(new ScreenViewport());
//        parent=cic;
//        viewport = new ScreenViewport();
//        stage = new Stage(viewport);
//        int row_height = Gdx.graphics.getWidth() / 12;
//        int col_width = Gdx.graphics.getWidth() / 12;





//        outputLabel = new Label("Press a Button", mySkin, "black");
//        outputLabel.setSize(Gdx.graphics.getWidth(), row_height);
//        outputLabel.setPosition(0, row_height);
//        outputLabel.setAlignment(Align.center);
//        stage.addActor(outputLabel);






    @Override
    public void show() {
        // Tell the manager to load assets for the loading screen

        // Wait until they are finished loading


        // Initialize the stage where we will place everything
        stage = new Stage();

        // Get our textureatlas from the manager



        // Or if you only need a static bar, you can do
        // loadingBar = new Image(atlas.findRegion("loading-bar1"));

        // Add all the actors to the stage
        stage.addActor(screenBg);
        stage.addActor(loadingBar);
        stage.addActor(loadingBg);
        stage.addActor(loadingBarHidden);
        stage.addActor(loadingFrame);
        stage.addActor(logo);

//        Gdx.input.setInputProcessor(stage);
    }

//    private TextButton addbutton (String name){
//        Skin myskn = new Skin(Gdx.files.internal("uiskin.json"));
//        TextButton button = new TextButton(name, myskn);
//        return button;
//    }


    @Override
    public void render ( float delta){


    }


    @Override
    public void resize ( int width, int height){
//        viewport.update(width, height);
        // Set our screen to always be XXX x 480 in size
        width = 480 * width / height;
        height = 480;


        // Make the background fill the screen
        screenBg.setSize(width, height);

        // Place the logo in the middle of the screen and 100 px up
        logo.setX((width - logo.getWidth()) / 2);
        logo.setY((height - logo.getHeight()) / 2 + 100);

        // Place the loading frame in the middle of the screen
        loadingFrame.setX((stage.getWidth() - loadingFrame.getWidth()) / 2);
        loadingFrame.setY((stage.getHeight() - loadingFrame.getHeight()) / 2);

        // Place the loading bar at the same spot as the frame, adjusted a few px
        loadingBar.setX(loadingFrame.getX() + 15);
        loadingBar.setY(loadingFrame.getY() + 5);

        // Place the image that will hide the bar on top of the bar, adjusted a few px
        loadingBarHidden.setX(loadingBar.getX() + 35);
        loadingBarHidden.setY(loadingBar.getY() - 3);
        // The start position and how far to move the hidden loading bar
        startX = loadingBarHidden.getX();
        endX = 440;

        // The rest of the hidden bar
        loadingBg.setSize(450, 50);
        loadingBg.setX(loadingBarHidden.getX() + 30);
        loadingBg.setY(loadingBarHidden.getY() + 3);
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


    }

}

