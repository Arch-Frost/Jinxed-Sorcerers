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

public class Option_Screen  implements Screen{
    private CIC parent;
    private Stage stage;
    static boolean return_main_menu=false;
    static boolean return_pause_menu=false;
    private Skin mySkin;
    public Option_Screen(CIC cic) {
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
        Label Choice_Given = new Label("Do you want to Exit Game and Return to Main menu?", mySkin,"default");
        Choice_Given.setSize(160, 80);
        Choice_Given.setPosition(580,480);
        Choice_Given.setAlignment(Align.center);
        Choice_Given.setFontScale(2f);
        stage.addActor(Choice_Given);
        Button Yes_button = new TextButton("Yes", mySkin, "default");
        Yes_button.setSize(120, 60);
        Yes_button.setPosition(950, 380);
        Yes_button.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                return_main_menu=true;
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        Button No_button = new TextButton("No", mySkin, "default");
        No_button.setSize(120, 60);
        No_button.setPosition(750, 380);
        No_button.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                return_pause_menu=true;
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(Yes_button);
        stage.addActor(No_button);
    }

    @Override
    public void render(float delta) {
        if(return_main_menu){
            return_main_menu=false;
            CIC.game_screen.dispose();
            CIC.menu_screen=new Menu_Screen(parent);
            parent.changeScreen(CIC.M_screen);
        }
        if(return_pause_menu){
            return_pause_menu=false;
            CIC.pause_screen=new Pause_Screen(parent);
            parent.changeScreen(CIC.P_screen);
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
