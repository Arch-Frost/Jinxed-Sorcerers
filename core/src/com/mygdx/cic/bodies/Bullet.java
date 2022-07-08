package com.mygdx.cic.bodies;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import static com.mygdx.cic.utils.Constants.PPM;

public class Bullet {
    public final String id;

    public Bullet(World world, String id, Body player1, Body player2) {
        this.id = id;
        createBullet(world, player1, player2);
    }

    public Body createBullet(World world, Body player1, Body player2) {
        Body BulletBody;
        BodyDef bodydef = new BodyDef();

        bodydef.type = BodyDef.BodyType.DynamicBody;
        bodydef.bullet = true;
        bodydef.position.set(player1.getPosition().x + 16/PPM, player1.getPosition().y);
        bodydef.fixedRotation = false;

        BulletBody = world.createBody(bodydef);
        BulletBody.setLinearVelocity(new Vector2(player2.getPosition().x - player1.getPosition().x, player2.getPosition().y - player1.getPosition().y));
        BulletBody.applyForceToCenter(new Vector2(player2.getPosition().x - player1.getPosition().x, player2.getPosition().y - player1.getPosition().y), false);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(4 / 2 / PPM,4 / 2 / PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        BulletBody.createFixture(fixtureDef).setUserData(this);

        shape.dispose();
        return BulletBody;
    }

    public void collided() {
        System.out.println(id + " has collided.");
    }
}
