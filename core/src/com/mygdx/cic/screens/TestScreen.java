package com.mygdx.cic.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.cic.bodies.CircleBody;
import com.mygdx.cic.bodies.GroundBody;

public class TestScreen implements Screen {
    private World world;
    private OrthographicCamera camera;
    private Box2DDebugRenderer debugRenderer;
    private CircleBody circle;
    private GroundBody ground;

    @Override
    public void show() {
        world = new World(new Vector2(0, -10), true);
        camera = new OrthographicCamera(128f, 102.4f);
        debugRenderer = new Box2DDebugRenderer();

        circle = new CircleBody(world, debugRenderer, camera);
        ground = new GroundBody(world, debugRenderer, camera);

        ground.create();
        circle.create(5, 18);
        for (int i = 0; i < 10; i++) {
            circle.create(-(float) Math.random() * 50, (float) Math.random() * 50);
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        circle.render();
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
        circle.dispose();
    }
}
