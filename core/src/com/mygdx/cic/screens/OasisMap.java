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
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.cic.entities.*;
import com.mygdx.cic.savedata.Save;
import com.mygdx.cic.utils.*;

import java.util.ArrayList;
import java.util.Iterator;

import static com.mygdx.cic.utils.Constants.PPM;

public class OasisMap implements Screen{
    private final float SCALE = 2;
    private float playerDistance;
    private float tempDistance;
    private float elapsedTime = 0f;
    private int score;

    private SpriteBatch batch;
    private Texture pauseImage;
    private Texture bulletTexture;
    private TextureAtlas demonAtlas;
    private TextureAtlas p1Atlas;
    private TextureAtlas p2Atlas;
    private Animation<TextureRegion> player1Animation;
    private Animation<TextureRegion> player2Animation;
    private Animation<TextureRegion> demonAnimation;


    private OrthographicCamera camera;
    private OrthographicCamera cameraUnprojected;
    private World world;
    private Box2DDebugRenderer b2dr;

    private Body player1;
    private Body player2;
    private Body bullet;
    private Body bullet1;
    private Body demon;
    private ArrayList<Body> bulletsToPlayerTwo;
    private ArrayList<Body> bulletsToPlayerOne;
    private ArrayList<Body> allEnemies;
    private ArrayList<Body> toberemoved;


    private OrthogonalTiledMapRenderer mapRenderer;
    private TiledMap map;
    private CollisionListener listener;


    public boolean isPaused;
    private Body enemy;

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



        p1Atlas = new TextureAtlas(Gdx.files.internal("EntityTextures/wizard_m.atlas"));
        player1Animation = new Animation(1f/4f, p1Atlas.getRegions());

        p2Atlas = new TextureAtlas(Gdx.files.internal("EntityTextures/wizard_f.atlas"));
        player2Animation = new Animation(1f/4f, p2Atlas.getRegions());

        demonAtlas = new TextureAtlas("EntityTextures/demon_run.atlas");
        demonAnimation = new Animation(1f/4f, demonAtlas.getRegions());

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
        allEnemies = new ArrayList<>();

        // Player 1: x = 4, y = 3, Player 2: x = 7, y = 3 --------- Map 2
        // Player 1: x = 4, y = 9, Player 2: x = 7, y = 9 --------- Dark Map
        // Player 1: x = 4, y = 5, Player 2: x = 7, y = 5 --------- Green Map

        player1 = Player.create(world, 22.5f, 45.8f, 16f, 16f, false);
        player1.setUserData(BodiesData.PLAYER1);
        player2 = Player.create(world, 27.5f, 45.8f, 16f, 16f, false);
        player2.setUserData(BodiesData.PLAYER2);
//        demon = Enemy.createBody(world, 25.5f, 45.8f, 4f, 4f);
//        demon.setUserData(BodiesData.ENEMY);

        map = new TmxMapLoader().load("OasisMap/oasis.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);

        TiledObjectUtil.parseTiledObjectLayer(world, map.getLayers().get("collision-layer").getObjects());

        camera.zoom = 0.8f;

        Save.load();
        score = (int) Save.gd.getTentativeScore();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.08f,0.42f,0.597f, 1f); // Green Map
//        Gdx.gl.glClearColor(1f,0.328f,0.257f, 1f); // Dark Map
//        Gdx.gl.glClearColor(0.4f,0f,0.8f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        elapsedTime += delta;


        mapRenderer.render();

        batch.begin();
        batch.draw(player1Animation.getKeyFrame(elapsedTime,true), (player1.getPosition().x * PPM) - (player1Animation.getKeyFrame(elapsedTime).getRegionWidth() / 2), player1.getPosition().y * PPM - (player1Animation.getKeyFrame(elapsedTime).getRegionHeight() / 2));
        batch.draw(player2Animation.getKeyFrame(elapsedTime,true), (player2.getPosition().x * PPM) - (player2Animation.getKeyFrame(elapsedTime).getRegionWidth() / 2), player2.getPosition().y * PPM - (player2Animation.getKeyFrame(elapsedTime).getRegionHeight() / 2));
//        batch.draw(demonAnimation.getKeyFrame(elapsedTime,true), (demon.getPosition().x * PPM) - (demonAnimation.getKeyFrame(elapsedTime).getRegionWidth() / 2), demon.getPosition().y * PPM - (demonAnimation.getKeyFrame(elapsedTime).getRegionHeight() / 2));
        for (Body thisBullet : bulletsToPlayerOne) {
            batch.draw(bulletTexture, thisBullet.getPosition().x * PPM - bulletTexture.getWidth()/2, thisBullet.getPosition().y * PPM - bulletTexture.getHeight()/2);
        }
        for (Body thisBullet : bulletsToPlayerTwo) {
            batch.draw(bulletTexture, thisBullet.getPosition().x * PPM - bulletTexture.getWidth()/2, thisBullet.getPosition().y * PPM - bulletTexture.getHeight()/2);
        }
        for (Body enemy : allEnemies) {
            batch.draw(demonAnimation.getKeyFrame(elapsedTime,true), (enemy.getPosition().x * PPM) - (demonAnimation.getKeyFrame(elapsedTime).getRegionWidth() / 2), enemy.getPosition().y * PPM - (demonAnimation.getKeyFrame(elapsedTime).getRegionHeight() / 2));
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
            pause();
        } else {
            update(delta);
        }
    }


    public void update(float delta) {
        world.step(1/60f, 6,2);

//        System.out.println("Player 1: x: " + player1.getPosition().x +" y: " + player1.getPosition().y);
//        System.out.println("Player 2: x: " + player2.getPosition().x +" y: " + player2.getPosition().y);
//        System.out.println("Camera Zoom: " + camera.zoom);

        toberemoved = listener.getBodies();
        Iterator<Body> i = toberemoved.iterator();
        if(!world.isLocked()){
            while(i.hasNext()){
                Body b = i.next();
                if(bulletsToPlayerTwo.contains(b)) bulletsToPlayerTwo.remove(b);
                if(bulletsToPlayerOne.contains(b)) bulletsToPlayerOne.remove(b);
                if(allEnemies.contains(b)) {
                    allEnemies.remove(b);
                    score += 1;
                }

                world.destroyBody(b);
                i.remove();
            }
        }
//        tempDistance = playerDistance;
        playerDistance = Vector2.dst2(player1.getPosition().x, player1.getPosition().y, player2.getPosition().x, player2.getPosition().y);
//        System.out.println("Player Distance: " + playerDistance);

        inputUpdate(delta);
        cameraUpdate(delta);
        for(Body b : bulletsToPlayerTwo){
            Bullet.update(delta,b,player2, 5);}
        for(Body B : bulletsToPlayerOne){
            Bullet.update(delta,B,player1, 5);
        }
        for(Body enemy : allEnemies){
            Enemy.update(delta, enemy, player1, 1, true);
        }
//        Enemy.updateEnemy(delta, demon, player1);

        batch.setProjectionMatrix(camera.combined);

        mapRenderer.setView(camera);
        Save.gd.setTenativeScore(score);
    }

    public void cameraUpdate(float delta) {
        Vector3 position = camera.position;
        position.x = (player1.getPosition().x + player2.getPosition().x)/2 * PPM;
        position.y = (player1.getPosition().y + player2.getPosition().y)/2 * PPM   ;
        camera.position.set(position);


//        while (tempDistance < playerDistance) {
//            camera.zoom += 0.2;
//        }
//        while (tempDistance > playerDistance) {
//            camera.zoom -= 0.2;
//        }

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
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            enemy = Enemy.create(world, player2.getPosition().x, player2.getPosition().y, 16, 16);
            enemy.setUserData(BodiesData.ENEMY);
            allEnemies.add(enemy);
        }
    }

    @Override
    public void dispose() {
        world.dispose();
        b2dr.dispose();
        batch.dispose();
        p1Atlas.dispose();
        p2Atlas.dispose();
        demonAtlas.dispose();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width / SCALE, height / SCALE);

    }

    @Override
    public void pause() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
            isPaused = false;
        Save.save();
    }

    @Override
    public void resume() {
        isPaused = false;

    }

    @Override
    public void hide() {

    }
}
