package com.mygdx.cic.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

public class ContactListenerReference implements Screen {
    enum BodyData
    {
        BIRD, ROCKET;
    }

    class MyContactListener implements ContactListener
    {

        @Override
        public void beginContact(Contact contact)
        {
            Body a = contact.getFixtureA().getBody();
            Body b = contact.getFixtureB().getBody();

            if (a.getUserData() == BodyData.BIRD
                    && b.getUserData() == BodyData.ROCKET)
            {
                // play sound
                toBeDeletedBodies.add(b);
            }
            if (b.getUserData() == BodyData.BIRD
                    && a.getUserData() == BodyData.ROCKET)
            {
                // play sound
                toBeDeletedBodies.add(a);
            }
        }

        @Override
        public void endContact(Contact contact)
        {
            // TODO Auto-generated method stub

        }

        @Override
        public void preSolve(Contact contact, Manifold oldManifold)
        {
            // TODO Auto-generated method stub

        }

        @Override
        public void postSolve(Contact contact, ContactImpulse impulse)
        {
            // TODO Auto-generated method stub

        }

    }

    Array<Body>         toBeDeletedBodies   = new Array<>();
    Array<Body>         allBodies           = new Array<>();

    World               world;

    Box2DDebugRenderer  renderer;

    OrthographicCamera  camera;

    @Override
    public void show()
    {
        world = new World(new Vector2(0, -1), true);

        BodyDef bodyDefBird = new BodyDef();
        bodyDefBird.position.set(0, 0.1f);
        bodyDefBird.type = BodyDef.BodyType.DynamicBody;

        BodyDef bodyDefRocket1 = new BodyDef();
        bodyDefRocket1.position.set(0, 0.5f);
        bodyDefRocket1.type = BodyDef.BodyType.DynamicBody;

        BodyDef bodyDefRocket2 = new BodyDef();
        bodyDefRocket2.position.set(0.1f, 0.8f);
        bodyDefRocket2.type = BodyDef.BodyType.DynamicBody;

        FixtureDef circleFixture = new FixtureDef();
        circleFixture.shape = new CircleShape();
        ((CircleShape) circleFixture.shape).setRadius(0.02f);

        FixtureDef rocketFixture = new FixtureDef();
        rocketFixture.shape = new PolygonShape();
        ((PolygonShape) rocketFixture.shape).set(new float[]
                { 0, 0, 0.01f, 0, 0.01f, 0.05f, 0, 0.05f, 0, 0 });

        BodyDef bodyDefFloor = new BodyDef();
        bodyDefFloor.position.set(-100, 0);
        bodyDefFloor.type = BodyDef.BodyType.StaticBody;

        FixtureDef lineFixture = new FixtureDef();
        lineFixture.shape = new EdgeShape();
        ((EdgeShape) lineFixture.shape).set(Vector2.Zero, new Vector2(200, 0));

        Body floor = world.createBody(bodyDefFloor);
        floor.createFixture(lineFixture);

        Body bird = world.createBody(bodyDefBird);
        bird.createFixture(circleFixture);
        bird.setUserData(BodyData.BIRD);

        Body rocket1 = world.createBody(bodyDefRocket1);
        rocket1.createFixture(rocketFixture);
        rocket1.setUserData(BodyData.ROCKET);

        Body rocket2 = world.createBody(bodyDefRocket2);
        rocket2.createFixture(rocketFixture);
        rocket2.setUserData(BodyData.ROCKET);

        renderer = new Box2DDebugRenderer();

        camera = new OrthographicCamera();

        world.setContactListener(new MyContactListener());

        rocketFixture.shape.dispose();
        circleFixture.shape.dispose();
        lineFixture.shape.dispose();
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.step(1 / 60f, 8, 3);

        renderer.render(world, camera.combined);

        for (Body body : toBeDeletedBodies)
        {
            world.destroyBody(body);
        }

        toBeDeletedBodies.clear();
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
    public void dispose()
    {
        renderer.dispose();
        world.dispose();
    }
}
