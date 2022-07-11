package com.mygdx.cic.screens;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
//import com.mygdx.template.GameConstants;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.cic.CIC;

public  class Loading_Screen implements Screen{
    private CIC parent;
    private Skin mySkin;
    private Stage stage;
    float time = 0;
    public Loading_Screen(CIC cic){
        parent=cic;
    }

//        stage = new Stage(new ScreenViewport());
//        Gdx.input.setInputProcessor(stage);
//        int row_height = Gdx.graphics.getWidth() / 12;
//        int col_width = Gdx.graphics.getWidth() / 12;
//        Image image1 = new Image(texture);
        //    image1.setPosition(Gdx.graphics.getWidth()/3-image1.getWidth()/2,Gdx.graphics.getHeight()*2/3-image1.getHeight()/2);
        //stage.addActor(image1);
//        Skin mySkin = new Skin(Gdx.files.internal("uiskin.json"));
//        final TextButton b=new TextButton("Hello",mySkin);
//        Label.LabelStyle label1Style = new Label.LabelStyle();
//        Label label1 = new Label("Title (BitmapFont)",label1Style);
//        label1.setSize(Gdx.graphics.getWidth(),row_height);
//        label1.setPosition(0,Gdx.graphics.getHeight()-row_height*2);
//        label1.setAlignment(Align.center);
//        stage.addActor(label1);



    @Override
    public void show() {

                Texture texture = new Texture(Gdx.files.internal("d.jpg"));
                stage = new Stage(new ScreenViewport());
                Image image1 = new Image(texture);
               stage.addActor(image1);
        mySkin = new Skin(Gdx.files.internal("skin/flat-earth-ui.json"));
        Label Player1_control = new Label("Loading", mySkin);
        Player1_control.setSize(160, 80);
        Player1_control.setPosition(600,430);
        Player1_control.setAlignment(Align.center);
        stage.addActor(Player1_control);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();

        time += Gdx.graphics.getDeltaTime();stage.draw();
        if (time > 5) // If 5s happened
        {
            parent.changeScreen(CIC.M_screen);
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