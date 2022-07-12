package com.mygdx.cic.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public abstract class Entity {
    public static void update(float delta, Body entity, Body Endpoint, float scalarMultiple){
        float x_position = Endpoint.getPosition().x - entity.getPosition().x;
        float y_position  = Endpoint.getPosition().y - entity.getPosition().y;
        float magnitude = (float) Math.pow(x_position*x_position + y_position*y_position,0.5);
        float i_cap = x_position/magnitude;
        float j_cap = y_position/magnitude;
        entity.setLinearVelocity(scalarMultiple * i_cap,scalarMultiple * j_cap);
    }

    public static void update(float delta, Body entity, Body endpoint) {
        update(delta, entity, endpoint, 1);
    }
    public static void update(float delta, Body entity, Body endpoint, float scalarMultiple, boolean random) {
        update(delta, entity, endpoint, scalarMultiple);
        entity.applyForceToCenter(new Vector2((float) Math.random(), (float) Math.random()), false);
    }


}
