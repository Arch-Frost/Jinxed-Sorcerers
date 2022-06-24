package com.mygdx.cic.bodies;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class UsercontrolledCircle extends  CircleBody{
    public UsercontrolledCircle(World world, Box2DDebugRenderer debugRenderer, OrthographicCamera camera) {
        super(world, debugRenderer, camera);
    }
}
