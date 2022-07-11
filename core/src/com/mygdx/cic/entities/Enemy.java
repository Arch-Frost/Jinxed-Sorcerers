package com.mygdx.cic.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import static com.mygdx.cic.utils.Constants.PPM;

public class Enemy {
    private static Body Body;

    public static Body createBody(World world, float x, float y, float width, float height) {
        BodyDef bodydef = new BodyDef();

        bodydef.type = BodyDef.BodyType.DynamicBody;


        bodydef.position.set(x, y);
        bodydef.fixedRotation = true;


        Body = world.createBody(bodydef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2 / PPM,height / 2 / PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;

        Body.createFixture(fixtureDef);
        shape.dispose();
        return Body;
    }

    public static void updateEnemy(float delta, Body enemy, Body Endpoint){
        float x_position = Endpoint.getPosition().x - enemy.getPosition().x;
        float y_position  = Endpoint.getPosition().y - enemy.getPosition().y;
        float magnitude = (float) Math.pow(x_position*x_position + y_position*y_position,0.5);
        float i_cap = x_position/magnitude;
        float j_cap = y_position/magnitude;
        enemy.setLinearVelocity(2*i_cap,2*j_cap);
        enemy.applyForceToCenter(new Vector2((float) Math.random(), (float) Math.random()), false);
    }
}
