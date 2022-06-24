package com.mygdx.cic.bodies;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.*;

public class CircleBody extends AbstractBody{
    private CircleShape circle;
    protected Body body;

    public CircleBody(World world, Box2DDebugRenderer debugRenderer , OrthographicCamera camera) {
        super(world, debugRenderer, camera);

    }

    public void create(float x, float y) {

        BodyDef bodyDef = new BodyDef();

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);


         body = world.createBody(bodyDef);


        circle = new CircleShape();
        circle.setRadius(4f);


        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f;


        Fixture fixture = body.createFixture(fixtureDef);

        circle.dispose();
    }


    @Override
    public void dispose () {
        super.dispose();
    }


}
