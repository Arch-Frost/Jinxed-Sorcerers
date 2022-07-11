package com.mygdx.cic.entities;

import com.badlogic.gdx.physics.box2d.*;

import static com.mygdx.cic.utils.Constants.PPM;

public class Player {
    public static Body Body;

    public Player(World world, float x, float y, float width, float height, boolean isStatic) {
        createBody(world, x, y, width, height, isStatic);
    }

    public static Body createBody(World world, float x, float y, float width, float height, boolean isStatic) {
        BodyDef bodydef = new BodyDef();

        if (isStatic)
            bodydef.type = BodyDef.BodyType.StaticBody;
        else
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
