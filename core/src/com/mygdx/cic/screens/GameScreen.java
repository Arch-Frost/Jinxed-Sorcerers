package com.mygdx.cic.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;

import static com.mygdx.cic.utils.Constants.PPM;

public class GameScreen implements Screen {
    private final float SCALE = 2;
    private SpriteBatch batch;
    private Texture p1tex;
    private Texture p2tex;

    private OrthographicCamera camera;
    private World world;
    private Body player1;
    private Body player2;
    private Body platform;
    private Box2DDebugRenderer b2dr;


    @Override
    public void show() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w / SCALE, h / SCALE);

        world = new World(new Vector2(0f,-9.8f), false);
        b2dr = new Box2DDebugRenderer();

        player1 = createBox(-2f, 3f, 32f, 32f, false);
        player2 = createBox(2f, 3f, 32f, 32f, false);
        platform = createBox(0,0, w, 32, true);

        batch = new SpriteBatch();
        p1tex = new Texture("Images/player1.png");
        p2tex = new Texture("Images/player2.png");


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.6f,0f,0.8f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(p1tex, player1.getPosition().x * PPM - (p1tex.getWidth()/2) , player1.getPosition().y * PPM - (p1tex.getHeight()/2));
        batch.draw(p2tex, player2.getPosition().x * PPM - (p2tex.getWidth()/2) , player2.getPosition().y * PPM - (p2tex.getHeight()/2));
        batch.end();


        b2dr.render(world, camera.combined.scl(PPM));
        update(delta);
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width / SCALE, height / SCALE);

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
        world.dispose();
        b2dr.dispose();
        batch.dispose();
    }

    public void update(float delta) {
        world.step(1/60f, 6,2);

        inputUpdate(delta);
        cameraUpdate(delta);
        batch.setProjectionMatrix(camera.combined);
    }

    public void cameraUpdate(float delta) {
        Vector3 position = camera.position;
        position.x = (player1.getPosition().x + player2.getPosition().x)/2 * PPM;
        position.y = (player1.getPosition().y + player2.getPosition().y)/2 * PPM   ;
        camera.position.set(position);

        camera.update();
    }

    public void inputUpdate(float delta) {
        int p1horizontalForce = 0;
        int p2horizontalForce = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.X)){ player2.setActive(false);}
        if (Gdx.input.isKeyPressed(Input.Keys.Z)){ player2.setActive(true);}

        if (Gdx.input.isKeyPressed(Input.Keys.D)){ p1horizontalForce += 1;}
        if (Gdx.input.isKeyPressed(Input.Keys.A)){ p1horizontalForce -= 1;}
        if (Gdx.input.isKeyJustPressed(Input.Keys.W)){ player1.applyLinearImpulse(0,5, player1.getPosition().x, player1.getPosition().y ,false);}
        player1.setLinearVelocity(p1horizontalForce*5, player1.getLinearVelocity().y);

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){ p2horizontalForce += 1;}
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){ p2horizontalForce -= 1;}
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)){ player2.applyLinearImpulse(0,5, player2.getPosition().x, player2.getPosition().y ,false);}
        player2.setLinearVelocity(p2horizontalForce*5, player2.getLinearVelocity().y);


        if (Gdx.input.isKeyPressed(Input.Keys.P))
            camera.zoom += 0.02;

        if (Gdx.input.isKeyPressed(Input.Keys.O))
            camera.zoom -= 0.02;
    }

    public Body createBox(float x, float y, float width, float height, boolean isStatic) {
        Body pBody;
        BodyDef bodydef = new BodyDef();

        if (isStatic)
            bodydef.type = BodyDef.BodyType.StaticBody;
        else
            bodydef.type = BodyDef.BodyType.DynamicBody;


        bodydef.position.set(x, y);
        bodydef.fixedRotation = false;


        pBody = world.createBody(bodydef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2 / PPM,height / 2 / PPM);

        pBody.createFixture(shape, 1f);
        shape.dispose();
        return pBody;
    }
}
