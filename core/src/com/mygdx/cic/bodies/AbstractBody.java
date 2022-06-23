package com.mygdx.cic.bodies;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.*;

public abstract class AbstractBody {
    protected final World world;
    protected final Box2DDebugRenderer debugRenderer;
    protected final OrthographicCamera camera;

    public AbstractBody(World world, Box2DDebugRenderer debugRenderer, OrthographicCamera camera) {
        Box2D.init();

        this.world = world;
        this.debugRenderer = debugRenderer;
        this.camera = camera;
    }

    public void create() {

    }

    public void render() {
        debugRenderer.render(world, camera.combined);
        world.step(1/60f, 6, 2);
    };

    public void dispose() {
        world.dispose();
    }
}
