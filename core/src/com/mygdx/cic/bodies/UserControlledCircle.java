package com.mygdx.cic.bodies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class UserControlledCircle extends CircleBody {
    public UserControlledCircle(World world, Box2DDebugRenderer debugRenderer, OrthographicCamera camera) {
        super(world, debugRenderer, camera);
    }

    public void  update(){
    if(Gdx.input.isKeyPressed(Input.Keys.D)){
        this.body.applyLinearImpulse(4,0,body.getPosition().x,body.getPosition().y,true);}
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            this.body.applyLinearImpulse(-4,0,body.getPosition().x,body.getPosition().y,true);}
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            this.body.applyLinearImpulse(0,14,body.getPosition().x,body.getPosition().y,true);}
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            this.body.applyLinearImpulse(0,-4,body.getPosition().x,body.getPosition().y,true);}
    }
}
