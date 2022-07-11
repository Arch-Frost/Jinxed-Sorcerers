package com.mygdx.cic.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import static com.mygdx.cic.utils.Constants.PPM;

public class Enemy extends Entity{
    private static Body Body;

    public static Body create(World world, float x, float y, float width, float height) {
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

}
