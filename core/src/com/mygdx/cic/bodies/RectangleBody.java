package com.mygdx.cic.bodies;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.*;

import java.awt.geom.RectangularShape;

public class RectangleBody  extends AbstractBody{
    private PolygonShape rectangle;
    private Body body;

    public RectangleBody(World world, Box2DDebugRenderer debugRenderer , OrthographicCamera camera) {
        super(world, debugRenderer, camera);

    }
    public void create(float x, float y) {

        BodyDef bodyDef = new BodyDef();

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);


         body = world.createBody(bodyDef);


        rectangle = new PolygonShape();
        rectangle.setRadius(4f);


        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = rectangle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f;


        Fixture fixture = body.createFixture(fixtureDef);

        rectangle.dispose();
    }
    public void update(){
        this.body.applyLinearImpulse(0.80f,0,body.getPosition().x,body.getPosition().y,true);
    }
}
