package com.mygdx.cic.entities;

import com.badlogic.gdx.physics.box2d.*;

import static com.mygdx.cic.utils.Constants.PPM;

public class Bullet extends Entity{
    public Bullet(World world, Body pointOfOrigin, Boolean add,short c_bits , short m_bits) {
        createBullet(world, pointOfOrigin, add
                , c_bits , m_bits);
    }

    public static Body createBullet(World world, Body PointofOrigin,Boolean add, short c_bits , short m_bits) {
        Body BulletBody;
        BodyDef bodydef = new BodyDef();

        bodydef.type = BodyDef.BodyType.KinematicBody;
        bodydef.bullet = true;
        float toBeAdded = (add? 16.0f : -16.0f);
        bodydef.position.set(PointofOrigin.getPosition().x + toBeAdded/PPM , PointofOrigin.getPosition().y);
        bodydef.fixedRotation = false;


        BulletBody = world.createBody(bodydef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(2f / 2f / PPM,2f / 2f / PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.isSensor = true;
        fixtureDef.filter.categoryBits = c_bits;
        fixtureDef.filter.maskBits = m_bits;
        fixtureDef.filter.groupIndex = (short) 0;
        BulletBody.createFixture(fixtureDef);

        shape.dispose();
        return BulletBody;
    }
}
