package com.mygdx.cic.bodies;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.*;

public abstract class AbstractBody {
    // Created common objects to be used by every box2d body
    protected final World world;
    protected final Box2DDebugRenderer debugRenderer;
    protected final OrthographicCamera camera;

    public AbstractBody(World world, Box2DDebugRenderer debugRenderer, OrthographicCamera camera) {
        Box2D.init();

        this.world = world;
        this.debugRenderer = debugRenderer;
        this.camera = camera;
    }

    // Empty method to be overriden or overloaded by sub-classes
    public void create() {}

    // Render method to be called in render() of main class. Only call render method of any ONE object in main class.
    public void render() {

    }

    // Call this method in dispose() method of main class by any ONE object.
    public void dispose() {
        world.dispose();
    }
}
