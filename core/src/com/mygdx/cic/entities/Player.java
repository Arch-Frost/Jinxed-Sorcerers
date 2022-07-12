package com.mygdx.cic.entities;

import com.badlogic.gdx.physics.box2d.*;

import static com.mygdx.cic.utils.Constants.PPM;

public class Player extends Entity{
    public static Body Body;

    public Player(World world, float x, float y, float width, float height, boolean isStatic, short c_bits ,
                  short m_bits) {
        create(world, x, y, width, height, isStatic, c_bits,m_bits);
    }

    public static Body create(World world, float x, float y, float width, float height, boolean isStatic, short c_bits,short m_bits) {
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
        fixtureDef.filter.categoryBits = c_bits;
        fixtureDef.filter.maskBits = m_bits;
        fixtureDef.filter.groupIndex = (short) 0;

        Body.createFixture(fixtureDef);
        shape.dispose();
        return Body;
    }


}
