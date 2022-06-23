package com.mygdx.cic.bodies;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class GroundBody extends AbstractBody{

    public GroundBody(World world, Box2DDebugRenderer debugRenderer, OrthographicCamera camera) {
        super(world, debugRenderer, camera);
    }

    @Override
    public void create() {
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(new Vector2(0, -camera.viewportHeight/2));

        Body groundBody = world.createBody(groundBodyDef);

        PolygonShape groundBox = new PolygonShape();

        groundBox.setAsBox(camera.viewportWidth, 1);

        groundBody.createFixture(groundBox, 0.0f);

        groundBox.dispose();
    }
}
