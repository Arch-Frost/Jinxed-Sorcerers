package com.mygdx.cic.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import static com.mygdx.cic.utils.Constants.PPM;

public class Enemy extends Entity{
    private static Body Body;

    public static Body create(World world, Vector2 position, float width, float height, short c_bits , short m_bits) {
        BodyDef bodydef = new BodyDef();

        bodydef.type = BodyDef.BodyType.DynamicBody;


        bodydef.position.set(position);
        bodydef.fixedRotation = true;


        Body = world.createBody(bodydef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2 / PPM,height / 2 / PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = c_bits;
        fixtureDef.filter.groupIndex = (short) 0;
        fixtureDef.filter.maskBits = m_bits;
        fixtureDef.density = 1.0f;

        Body.createFixture(fixtureDef);
        shape.dispose();
        return Body;
    }
}
