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
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import com.mygdx.cic.CIC;
public class Controls_Screen implements Screen{
    private CIC parent;
    private Stage stage;
    static boolean return_main_menu=false;
    private Skin mySkin;
    public Controls_Screen(CIC cic) {
        parent=cic;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void show() {
        Texture texture = new Texture(Gdx.files.internal("BackgroundImages/ControlsScreenBackground.jpg"));
        Image image1 = new Image(texture);
        image1.setSize(1370,770);
        stage.addActor(image1);
        mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        Label Player1_control = new Label("Player 2 Controls", mySkin,"default");
        Player1_control.setSize(160, 80);
        Player1_control.setPosition(280,395);
        Player1_control.setAlignment(Align.center);
        Player1_control.setFontScale(1.5f);
        Label l1 = new Label("Forward", mySkin);
        l1.setSize(160, 80);
        l1.setPosition(195,320);
        l1.setAlignment(Align.center);
        Label l11 = new Label("UP Arrow Key", mySkin);
        l11.setSize(160, 80);
        l11.setPosition(365,320);
        l11.setAlignment(Align.center);
        Label l2 = new Label("Backward", mySkin);
        l2.setSize(160, 80);
        l2.setPosition(195,270);
        l2.setAlignment(Align.center);
        Label l22 = new Label("DOWN Arrow Key", mySkin);
        l22.setSize(160, 80);
        l22.setPosition(365,270);
        l22.setAlignment(Align.center);
        Label l3 = new Label("Rightward", mySkin);
        l3.setSize(160, 80);
        l3.setPosition(195,220);
        l3.setAlignment(Align.center);
        Label l33 = new Label("RIGHT Arrow Key", mySkin);
        l33.setSize(160, 80);
        l33.setPosition(365,220);
        l33.setAlignment(Align.center);
        Label l4 = new Label("Leftward", mySkin);
        l4.setSize(160, 80);
        l4.setPosition(195,170);
        l4.setAlignment(Align.center);
        Label l44 = new Label("LEFT Arrow Key", mySkin);
        l44.setSize(160, 80);
        l44.setPosition(365,170);
        l44.setAlignment(Align.center);
        Label l5 = new Label("Zoom Out", mySkin);
        l5.setSize(160, 80);
        l5.setPosition(195,120);
        l5.setAlignment(Align.center);
        Label l55 = new Label("P", mySkin);
        l55.setSize(160, 80);
        l55.setPosition(365,120);
        l55.setAlignment(Align.center);

        Label Player2_control = new Label("Player 1 Controls", mySkin);
        Player2_control.setSize(160, 80);
        Player2_control.setPosition(855,440);
        Player2_control.setFontScale(1.5f);
        Player2_control.setAlignment(Align.center);
        Label l6 = new Label("Forward", mySkin);
        l6.setSize(160, 80);
        l6.setPosition(785,360);
        l6.setAlignment(Align.center);
        Label l66 = new Label("W", mySkin);
        l66.setSize(160, 80);
        l66.setPosition(955,360);
        l66.setAlignment(Align.center);
        Label l7 = new Label("Backward", mySkin);
        l7.setSize(160, 80);
        l7.setPosition(785,310);
        l7.setAlignment(Align.center);
        Label l77 = new Label("S", mySkin);
        l77.setSize(160, 80);
        l77.setPosition(955,310);
        l77.setAlignment(Align.center);
        Label l8 = new Label("Rightward", mySkin);
        l8.setSize(160, 80);
        l8.setPosition(785,260);
        l8.setAlignment(Align.center);
        Label l88 = new Label("D", mySkin);
        l88.setSize(160, 80);
        l88.setPosition(955,260);
        l88.setAlignment(Align.center);
        Label l9 = new Label("Leftward", mySkin);
        l9.setSize(160, 80);
        l9.setPosition(785,210);
        l9.setAlignment(Align.center);
        Label l99 = new Label("A", mySkin);
        l99.setSize(160, 80);
        l99.setPosition(955,210);
        l99.setAlignment(Align.center);
        Label l10 = new Label("Zoom In", mySkin);
        l10.setSize(160, 80);
        l10.setPosition(785,160);
        l10.setAlignment(Align.center);
        Label l1010 = new Label("O", mySkin);
        l1010.setSize(160, 80);
        l1010.setPosition(955,160);
        l1010.setAlignment(Align.center);

        Texture BackTexture = new Texture(Gdx.files.internal("Buttons/backButton.jpg"));
        TextureRegion myBackTextureRegion = new TextureRegion(BackTexture);
        TextureRegionDrawable myBackTexRegionDrawable = new TextureRegionDrawable(myBackTextureRegion);
        ImageButton BackButton = new ImageButton(myBackTexRegionDrawable);

        BackButton.setPosition(830, 45);
        BackButton.setSize(223, 60);
        BackButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    return_main_menu=true;

            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        Player1_control.setColor(Color.BLACK);
        Player2_control.setColor(Color.BLACK);
        l1.setColor(Color.BLACK);
        l11.setColor(Color.BLACK);
        l2.setColor(Color.BLACK);
        l22.setColor(Color.BLACK);
        l3.setColor(Color.BLACK);
        l33.setColor(Color.BLACK);
        l4.setColor(Color.BLACK);
        l44.setColor(Color.BLACK);
        l5.setColor(Color.BLACK);
        l55.setColor(Color.BLACK);
        l6.setColor(Color.BLACK);
        l66.setColor(Color.BLACK);
        l7.setColor(Color.BLACK);
        l77.setColor(Color.BLACK);
        l8.setColor(Color.BLACK);
        l88.setColor(Color.BLACK);
        l9.setColor(Color.BLACK);
        l99.setColor(Color.BLACK);
        l10.setColor(Color.BLACK);
        l1010.setColor(Color.BLACK);
        stage.addActor(BackButton);
        stage.addActor(Player1_control);
        stage.addActor(Player2_control);
        stage.addActor(l1);
        stage.addActor(l11);
        stage.addActor(l2);
        stage.addActor(l22);
        stage.addActor(l3);
        stage.addActor(l33);
        stage.addActor(l4);
        stage.addActor(l44);
        stage.addActor(l5);
        stage.addActor(l55);
        stage.addActor(l6);
        stage.addActor(l66);
        stage.addActor(l7);
        stage.addActor(l77);
        stage.addActor(l8);
        stage.addActor(l88);
        stage.addActor(l9);
        stage.addActor(l99);
        stage.addActor(l10);
        stage.addActor(l1010);
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
