package com.mygdx.cic.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.cic.utils.CollisionListener;
import com.mygdx.cic.utils.BodiesData;
import com.mygdx.cic.utils.TiledObjectUtil;

import static com.mygdx.cic.utils.Constants.PPM;

public class GameScreen implements Screen {
    private final float SCALE = 2;
    private float playerDistance;

    private SpriteBatch batch;
    private Texture p1tex;
    private Texture p2tex;

    private OrthographicCamera camera;
    private World world;
    private Body player1;
    private Body player2;
    private Box2DDebugRenderer b2dr;

    private OrthogonalTiledMapRenderer mapRenderer;
    private TiledMap map;


//    private RayHandler rayHandler;
//    private PointLight myLight;


    @Override
    public void show() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        batch = new SpriteBatch();
        p1tex = new Texture("Images/player1.png");
        p2tex = new Texture("Images/player2.png");

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w / SCALE, h / SCALE);

        world = new World(new Vector2(0f,0f), false);
        b2dr = new Box2DDebugRenderer();
        world.setContactListener(new CollisionListener());

        player1 = createBox(4f, 3f, 16f, 32f, false);
        player1.setUserData(BodiesData.PLAYER1);
        player2 = createBox(7f, 3f, 16f, 32f, false);
        player2.setUserData(BodiesData.PLAYER2);


        map = new TmxMapLoader().load("Map2/map 2.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);

//        rayHandler = new RayHandler(world);
//        rayHandler.setAmbientLight(0.5f);
//        myLight = new PointLight(rayHandler, 120, Color.WHITE ,5, 0,0);
//        myLight.attachToBody(player1, 1, 1);

        TiledObjectUtil.parseTiledObjectLayer(world, map.getLayers().get("ahmad").getObjects());

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.6f,0f,0.8f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mapRenderer.render();

        batch.begin();
        batch.draw(p1tex, player1.getPosition().x * PPM - (p1tex.getWidth()/2) , player1.getPosition().y * PPM - (p1tex.getHeight()/2));
        batch.draw(p2tex, player2.getPosition().x * PPM - (p2tex.getWidth()/2) , player2.getPosition().y * PPM - (p2tex.getHeight()/2));
        batch.end();


        b2dr.render(world, camera.combined.scl(PPM));
//        rayHandler.render();
        update(delta);
    }


    public void update(float delta) {
        world.step(1/60f, 6,2);

//        rayHandler.update();

        inputUpdate(delta);
        cameraUpdate(delta);
        batch.setProjectionMatrix(camera.combined);
//        rayHandler.setCombinedMatrix(camera.combined.cpy().scl(PPM));

//        playerDistance = (float) Math.sqrt(Math.pow((player2.getPosition().y - player1.getPosition().y), 2)
//                + Math.pow((player2.getPosition().x - player1.getPosition().x), 2)) * PPM + p1tex.getWidth();
        playerDistance = Vector2.dst2(player1.getPosition().x, player1.getPosition().y, player2.getPosition().x, player2.getPosition().y);

        mapRenderer.setView(camera);
    }

    public void cameraUpdate(float delta) {
        Vector3 position = camera.position;
        position.x = (player1.getPosition().x + player2.getPosition().x)/2 * PPM;
        position.y = (player1.getPosition().y + player2.getPosition().y)/2 * PPM   ;
        camera.position.set(position);

        camera.update();
    }

    public void inputUpdate(float delta) {
        int p1HorizontalForce = 0;
        int p1VerticalForce = 0;
        int p2HorizontalForce = 0;
        int p2VerticalForce = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.X)){ player2.setActive(false);}
        if (Gdx.input.isKeyPressed(Input.Keys.Z)){ player2.setActive(true);}

        if (Gdx.input.isKeyPressed(Input.Keys.D)){ p1HorizontalForce += 1;}
        if (Gdx.input.isKeyPressed(Input.Keys.A)){ p1HorizontalForce -= 1;}
        if (Gdx.input.isKeyPressed(Input.Keys.W)){ p1VerticalForce += 1; }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){ p1VerticalForce -= 1; }
        player1.setLinearVelocity(p1HorizontalForce * 5, p1VerticalForce * 5);

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){ p2HorizontalForce += 1;}
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){ p2HorizontalForce -= 1;}
        if (Gdx.input.isKeyPressed(Input.Keys.UP)){ p2VerticalForce += 1; }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){ p2VerticalForce -= 1; }
            player2.setLinearVelocity(p2HorizontalForce * 5, p2VerticalForce * 5);


        if (Gdx.input.isKeyPressed(Input.Keys.P))
            camera.zoom += 0.02;

        if (Gdx.input.isKeyPressed(Input.Keys.O))
            camera.zoom -= 0.02;

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            createBullet(world, player1, player2);
        }
    }

    public Body createBox(float x, float y, float width, float height, boolean isStatic) {
        Body pBody;
        BodyDef bodydef = new BodyDef();

        if (isStatic)
            bodydef.type = BodyDef.BodyType.StaticBody;
        else
            bodydef.type = BodyDef.BodyType.DynamicBody;


        bodydef.position.set(x, y);
        bodydef.fixedRotation = true;


        pBody = world.createBody(bodydef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2 / PPM,height / 2 / PPM);

        pBody.createFixture(shape, 1f);
        shape.dispose();
        return pBody;
    }

    @Override
    public void dispose() {
        world.dispose();
        b2dr.dispose();
        batch.dispose();
//        rayHandler.dispose();
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

    public Body createBullet(World world, Body player1, Body player2) {
        Body BulletBody;
        BodyDef bodydef = new BodyDef();

        bodydef.type = BodyDef.BodyType.DynamicBody;
        bodydef.bullet = true;
        bodydef.position.set(player1.getPosition().x + 16/PPM, player1.getPosition().y);
        bodydef.fixedRotation = false;

        BulletBody = world.createBody(bodydef);
        BulletBody.setLinearVelocity(new Vector2(player2.getPosition().x - player1.getPosition().x, player2.getPosition().y - player1.getPosition().y));
        BulletBody.applyForceToCenter(new Vector2(player2.getPosition().x - player1.getPosition().x, player2.getPosition().y - player1.getPosition().y), false);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(4 / 2 / PPM,4 / 2 / PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        BulletBody.createFixture(fixtureDef);
        BulletBody.setUserData(BodiesData.BULLET);

        shape.dispose();
        return BulletBody;
    }
}
