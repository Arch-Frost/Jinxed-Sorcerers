package com.mygdx.cic.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
//import com.mygdx.template.GameConstants;
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
    private Skin mySkin;
    private Stage stage;
    Animation animation;
    private SpriteBatch batch;
    private BitmapFontCache titleCache;
    TextureRegion [] textrarray;
    float time = 0;
    private float origin_x, origin_y;
    public Loading_Screen(CIC cic){
        textrarray=new TextureRegion[21];
        for (int i=0;i<=20;i++){
            textrarray[i]=new TextureRegion(new Texture(Gdx.files.internal("Loadingscreenimages/"+(String.valueOf(i)+".jpg"))));
        }
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

                stage = new Stage(new ScreenViewport());

                TextureRegion [] textrarray=new TextureRegion[21];
                for (int i=0;i<=20;i++){
                    textrarray[i]=new TextureRegion(new Texture(Gdx.files.internal("Loadingscreenimages/"+(String.valueOf(i)+".jpg"))));
                }
//               TextureRegion t1= new TextureRegion(new Texture(Gdx.files.internal("a4.png")));
//               TextureRegion t2= new TextureRegion(new Texture(Gdx.files.internal("a3.png")));
//               TextureRegion t3= new TextureRegion(new Texture(Gdx.files.internal("a2.png")));
//               TextureRegion t4= new TextureRegion(new Texture(Gdx.files.internal("a1.png"))
        animation=new Animation(0.1f,textrarray);
        TextureRegion firstTexture = textrarray[0];
        origin_x = (Gdx.graphics.getWidth()  - firstTexture.getRegionWidth())  / 2;
        origin_y = (Gdx.graphics.getHeight() - firstTexture.getRegionHeight()) / 2;

        //               abc[0] = new Image(t1);
//               abc[1] =new Image(t2);
//               abc[2] =new Image(t3);
//               abc[3] =new Image(t4);
//               int j=5;
//               while(j<10){
//                   for (int i=0;i<3;i++){
//                       stage.addActor(abc[i]);
//                   }
//                   j++;
//               }
           batch=new SpriteBatch();

//                Texture texture1 = new Texture(Gdx.files.internal("123.jpg"));
//                Image image1 = new Image(texture1);
//                stage.addActor(image1);
//                mySkin = new Skin(Gdx.files.internal("12345.json"));
//                Label Player1_control = new Label("Loading", mySkin);
//                Player1_control.setSize(160, 80);
//                Player1_control.setPosition(600,430);
//                Player1_control.setAlignment(Align.center);
//                stage.addActor(Player1_control);
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