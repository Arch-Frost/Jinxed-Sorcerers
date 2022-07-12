package com.mygdx.cic.entities;

import com.badlogic.gdx.physics.box2d.*;

import static com.mygdx.cic.utils.Constants.PPM;

public class Bullet extends Entity{
    public Bullet(World world, Body pointOfOrigin, Boolean add) {
        createBullet(world, pointOfOrigin, add);
    }

    public static Body createBullet(World world, Body PointofOrigin,Boolean add) {
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
        BulletBody.createFixture(fixtureDef);

        shape.dispose();
        return BulletBody;
    }

}
