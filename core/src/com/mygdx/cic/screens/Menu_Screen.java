package com.mygdx.cic.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.cic.CIC;

public class Menu_Screen  implements Screen {
    private CIC parent;
    private Stage stage;
    static boolean start_game=false;
    static boolean open_controls=false;
    private Skin mySkin;
    static int check=0;
    public Menu_Screen(CIC cic) {
        parent=cic;

            stage = new Stage(new ScreenViewport());

            Gdx.input.setInputProcessor(stage);


        }


    @Override
    public void show() {

        Texture texture = new Texture(Gdx.files.internal("d.jpg"));
        Image image1 = new Image(texture);
        stage.addActor(image1);
        mySkin = new Skin(Gdx.files.internal("skin/flat-earth-ui.json"));
        Button Play_button = new TextButton("Play", mySkin, "default");
        Play_button.setSize(160, 80);
        Play_button.setPosition(90, 530);

        Play_button.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                start_game = true;
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(Play_button);
        Button Controls_button = new TextButton("Controls", mySkin, "default");
        Controls_button.setSize(160, 80);
        Controls_button.setPosition(90, 400);

        Controls_button.addListener(new InputListener() {

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                 open_controls=true;
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                return true;
            }
        });

        Button Exit_button = new TextButton("Exit", mySkin, "default");
        Exit_button.setSize(160, 80);
        Exit_button.setPosition(90, 270);
        Exit_button.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                          CIC.menu_screen.dispose();
                          System.exit(0);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });


        Button About_button = new TextButton("About", mySkin, "default");
        About_button.setSize(160, 80);
        About_button.setPosition(90, 140);
        About_button.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        stage.addActor(Controls_button);
        stage.addActor(Exit_button);
        stage.addActor(About_button);



        // Create a table that fills the screen. Everything else will go inside this table.
//        Table table = new Table();
//        table.setFillParent(true);
//        table.setDebug(true);
//        stage.addActor(table);
//
//        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
//
//        TextButton newGame = new TextButton("New Game", skin);
//        TextButton preferences = new TextButton("Preferences", skin);
//        TextButton exit = new TextButton("Exit", skin);
//        newGame.addListener(new InputListener() {
//            @Override
//            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
//                start_game=true;
//            }
//
//            @Override
//            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                return true;
//            }
//        });
//        table.add(newGame).fillX().uniformX();
//        table.row().pad(10, 0, 10, 0);
//        table.add(preferences).fillX().uniformX();
//        table.row();
//        table.add(exit).fillX().uniformX();


//        private TextButton addbutton (String name){
///            return button;
//        }
    }

        @Override
        public void render ( float delta){
            if(start_game){
                parent.changeScreen(CIC.G_screen);
            }
            if (open_controls){
                open_controls=false;
                CIC.controls_screen=new Controls_Screen(parent);
                parent.changeScreen(CIC.C_screen);
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

