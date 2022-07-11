package com.mygdx.cic.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.cic.CIC;
import com.mygdx.cic.entities.Bullet;
import com.mygdx.cic.entities.Player;
import com.mygdx.cic.utils.BodiesData;
import com.mygdx.cic.utils.CollisionListener;
import com.mygdx.cic.utils.TiledObjectUtil;

import java.util.ArrayList;
import java.util.Iterator;

import static com.mygdx.cic.utils.Constants.PPM;

public class DungeonMap implements Screen{
    private final float SCALE = 2;
    private final CIC parent;
    private float playerDistance;
    private float elapsedTime = 0f;

    private SpriteBatch batch;
    private Texture pauseImage;
    private Texture bulletTexture;
    private TextureAtlas p1atlas;
    private TextureAtlas p2atlas;
    private Animation<TextureRegion> player1animation;
    private Animation<TextureRegion> player2animation;


    private OrthographicCamera camera;
    private OrthographicCamera cameraUnprojected;
    private World world;
    private Body player1;
    private Body player2;
    private Body bullet;
    private Body bullet1;
    private Box2DDebugRenderer b2dr;
    private ArrayList<Body> bulletsToPlayerTwo;
    private ArrayList<Body> bulletsToPlayerOne;
    private ArrayList<Body> toberemoved;


    private OrthogonalTiledMapRenderer mapRenderer;
    private TiledMap map;
    private  CollisionListener listener;


    public boolean isPaused;

    public DungeonMap(CIC cic) {
        parent = cic;
    }

    @Override
    public void show() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        isPaused = false;

        batch = new SpriteBatch();
        pauseImage = new Texture("Images/PauseScreen.png");
        bulletTexture = new Texture("Images/bullet.png");

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w / SCALE, h / SCALE);
        camera.zoom = 5f;



        p1atlas = new TextureAtlas(Gdx.files.internal("EntityTextures/wizard_m.atlas"));
        player1animation = new Animation(1f/4f, p1atlas.getRegions());

        p2atlas = new TextureAtlas(Gdx.files.internal("EntityTextures/wizard_f.atlas"));
        player2animation = new Animation(1f/4f, p2atlas.getRegions());

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w / SCALE, h / SCALE);
        cameraUnprojected = new OrthographicCamera();
        cameraUnprojected.setToOrtho(false, w / SCALE, h / SCALE);

        world = new World(new Vector2(0f,0f), false);
        b2dr = new Box2DDebugRenderer();
        listener = new CollisionListener();
        world.setContactListener(listener);

        toberemoved = new ArrayList<>();
        bulletsToPlayerTwo = new ArrayList<>();
        bulletsToPlayerOne = new ArrayList<>();

        // Player 1: x = 4, y = 3, Player 2: x = 7, y = 3 --------- Map 2
        // Player 1: x = 4, y = 9, Player 2: x = 7, y = 9 --------- Dark Map
        // Player 1: x = 4, y = 5, Player 2: x = 7, y = 5 --------- Green Map

        player1 = Player.createBody(world, 9f, 5f, 16f, 16f, false);
        player1.setUserData(BodiesData.PLAYER1);
        player2 = Player.createBody(world, 41f, 5f, 16f, 16f, false);
        player2.setUserData(BodiesData.PLAYER2);

        map = new TmxMapLoader().load("DungeonMap/dungeon.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);

        TiledObjectUtil.parseTiledObjectLayer(world, map.getLayers().get("collision-layer").getObjects());

    }

    @Override
    public void render(float delta) {

//        Gdx.gl.glClearColor(0.08f,0.42f,0.597f, 1f); // Green Map
        Gdx.gl.glClearColor(1f,0.328f,0.257f, 1f); // Dark Map
//        Gdx.gl.glClearColor(0.4f,0f,0.8f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        elapsedTime += delta;

        mapRenderer.render();

        batch.begin();
        batch.draw(player1animation.getKeyFrame(elapsedTime,true), (player1.getPosition().x * PPM) - (player1animation.getKeyFrame(elapsedTime).getRegionWidth() / 2), player1.getPosition().y * PPM - (player1animation.getKeyFrame(elapsedTime).getRegionHeight() / 2));
        batch.draw(player2animation.getKeyFrame(elapsedTime,true), (player2.getPosition().x * PPM) - (player2animation.getKeyFrame(elapsedTime).getRegionWidth() / 2), player2.getPosition().y * PPM - (player2animation.getKeyFrame(elapsedTime).getRegionHeight() / 2));
        for (Body thisBullet : bulletsToPlayerOne) {
            batch.draw(bulletTexture, thisBullet.getPosition().x * PPM - bulletTexture.getWidth()/2, thisBullet.getPosition().y * PPM - bulletTexture.getHeight()/2);
        }
        for (Body thisBullet : bulletsToPlayerTwo) {
            batch.draw(bulletTexture, thisBullet.getPosition().x * PPM - bulletTexture.getWidth()/2, thisBullet.getPosition().y * PPM - bulletTexture.getHeight()/2);
        }
        if (isPaused) {
            batch.setProjectionMatrix(cameraUnprojected.combined);
            batch.draw(pauseImage, 0f, 0f, cameraUnprojected.viewportWidth, cameraUnprojected.viewportHeight);
            batch.setProjectionMatrix(camera.combined);
        }
        batch.end();


//        b2dr.render(world, camera.combined.scl(PPM));
        if (isPaused) {
            delta = 0;
            if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
                isPaused = false;
        } else {
            update(delta);
        }
    }


    public void update(float delta) {
        world.step(1/60f, 6,2);

        System.out.println("Player 1: x: " + player1.getPosition().x +" y: " + player1.getPosition().y);
        System.out.println("Player 2: x: " + player2.getPosition().x +" y: " + player2.getPosition().y);
        System.out.println("Camera Zoom: " + camera.zoom);

        toberemoved = listener.getbodies();
        Iterator<Body> i = toberemoved.iterator();
        if(!world.isLocked()){
            while(i.hasNext()){
                Body b = i.next();
                if(bulletsToPlayerTwo.contains(b)) bulletsToPlayerTwo.remove(b);
                if(bulletsToPlayerOne.contains(b)) bulletsToPlayerOne.remove(b);

                world.destroyBody(b);
                i.remove();
            }
        }

        inputUpdate(delta);
        cameraUpdate(delta);
        for(Body b : bulletsToPlayerTwo){
            Bullet.updateBullet(delta,b,player2);}
        for(Body B : bulletsToPlayerOne){
            Bullet.updateBullet(delta,B,player1);
        }
        batch.setProjectionMatrix(camera.combined);

        playerDistance = Vector2.dst2(player1.getPosition().x, player1.getPosition().y, player2.getPosition().x, player2.getPosition().y);

        mapRenderer.setView(camera);
    }

    public void cameraUpdate(float delta) {
        Vector3 position = camera.position;
        position.x = (player1.getPosition().x + player2.getPosition().x)/2 * PPM;
        position.y = (player1.getPosition().y + player2.getPosition().y)/2 * PPM;
        camera.position.set(position);

        do {camera.zoom = 2.5f;} while (false);

        camera.update();
    }

    public void inputUpdate(float delta) {
        int p1HorizontalForce = 0;
        int p1VerticalForce = 0;
        int p2HorizontalForce = 0;
        int p2VerticalForce = 0;
        

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            p1HorizontalForce += 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            p1HorizontalForce -= 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            p1VerticalForce += 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            p1VerticalForce -= 1;
        }
        player1.setLinearVelocity(p1HorizontalForce * 5, p1VerticalForce * 5);

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            p2HorizontalForce += 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            p2HorizontalForce -= 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            p2VerticalForce += 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            p2VerticalForce -= 1;
        }
        player2.setLinearVelocity(p2HorizontalForce * 5, p2VerticalForce * 5);

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
            isPaused = true;

        if (Gdx.input.isKeyPressed(Input.Keys.P))
            camera.zoom += 0.02;

        if (Gdx.input.isKeyPressed(Input.Keys.O))
            camera.zoom -= 0.02;

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {

            bullet = Bullet.createBullet(world, player1,true);
            bullet.setUserData(BodiesData.BULLET);
            bulletsToPlayerTwo.add(bullet);

            bullet1 = Bullet.createBullet(world, player2,false);
            bullet1.setUserData(BodiesData.BULLET1);
            bulletsToPlayerOne.add(bullet1);
        }
    }

    @Override
    public void dispose() {
        world.dispose();
        b2dr.dispose();
        batch.dispose();
        p1atlas.dispose();
        p2atlas.dispose();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width / SCALE, height / SCALE);

    }

    @Override
    public void pause() {
        isPaused = true;
    }

    @Override
    public void resume() {
        isPaused = false;

    }

    @Override
    public void hide() {

    }
}
