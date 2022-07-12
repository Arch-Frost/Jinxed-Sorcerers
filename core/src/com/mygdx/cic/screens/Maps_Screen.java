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

public class Maps_Screen implements Screen {

    private CIC parent;
    private Stage stage;
    static boolean return_main_menu=false;
    static boolean start_Oasis_map=false;
    static boolean start_Dungeon_map=false;

    private Skin mySkin;
    public Maps_Screen(CIC cic) {
        parent=cic;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void show() {
        Texture texture = new Texture(Gdx.files.internal("backgroundImages/AboutScreenBackground.jpg"));
        Image image1 = new Image(texture);
        image1.setSize(1370, 770);
        stage.addActor(image1);
        mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));


        Texture BackTexture = new Texture(Gdx.files.internal("Buttons/backButton.jpg"));
        TextureRegion myBackTextureRegion = new TextureRegion(BackTexture);
        TextureRegionDrawable myBackTexRegionDrawable = new TextureRegionDrawable(myBackTextureRegion);
        ImageButton BackButton = new ImageButton(myBackTexRegionDrawable);

        BackButton.setPosition(810, 445);
        BackButton.setSize(265, 60);
        BackButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                return_main_menu = true;
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(BackButton);


        Texture PlayOasisTexture = new Texture(Gdx.files.internal("MapsSc/oasisMap.png"));
        TextureRegion myPlayOasisTextureRegion = new TextureRegion(PlayOasisTexture);
        TextureRegionDrawable myPlayOasisTexRegionDrawable = new TextureRegionDrawable(myPlayOasisTextureRegion);
        ImageButton PlayOasisButton = new ImageButton(myPlayOasisTexRegionDrawable); //Set the button up
        PlayOasisButton.setPosition(250, 150);
        PlayOasisButton.setSize(400, 220);
        PlayOasisButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                       start_Oasis_map=true;
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        Label oasisdiscription = new Label("Oasis Map ", mySkin,"default");
        oasisdiscription.setSize(200, 100);
        oasisdiscription.setPosition(350,70);
        oasisdiscription.setColor(Color.BLACK);
        oasisdiscription.setAlignment(Align.center);
        oasisdiscription.setFontScale(1.5f);


        Texture PlayDungeonTexture = new Texture(Gdx.files.internal("MapsSc/dungeonMap.png"));
        TextureRegion myPlayDungeonTextureRegion = new TextureRegion(PlayDungeonTexture);
        TextureRegionDrawable myPlayDungeonTexRegionDrawable = new TextureRegionDrawable(myPlayDungeonTextureRegion);
        ImageButton PlayDungeonButton = new ImageButton(myPlayDungeonTexRegionDrawable); //Set the button up
        PlayDungeonButton.setPosition(630, 150);
        PlayDungeonButton.setSize(500, 220);
        PlayDungeonButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                start_Dungeon_map=true;
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        Label Dungeondiscription = new Label("Dungeon Map ", mySkin,"default");
        Dungeondiscription.setSize(200, 100);
        Dungeondiscription.setPosition(790,70);
        Dungeondiscription.setColor(Color.BLACK);
        Dungeondiscription.setAlignment(Align.center);
        Dungeondiscription.setFontScale(1.5f);






        stage.addActor(oasisdiscription);
        stage.addActor(Dungeondiscription);
        stage.addActor(PlayDungeonButton);




        stage.addActor(PlayOasisButton);


    }





    @Override
    public void render(float delta) {
        if(return_main_menu){
            return_main_menu=false;
            CIC.menu_screen=new Menu_Screen(parent);
            parent.changeScreen(CIC.M_screen);
        }
        if(start_Oasis_map){
            start_Oasis_map=false;
            CIC.oasisMap=new OasisMap(parent);
            parent.changeScreen(CIC.O_screen);
        }
        if(start_Dungeon_map){
            start_Dungeon_map=false;
            CIC.dungeonMap=new DungeonMap(parent);
            parent.changeScreen(CIC.D_screen);
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
