package com.mygdx.cic.bodies;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.mygdx.cic.screens.GameScreen;

import java.util.Random;

import static com.mygdx.cic.utils.Constants.PPM;

public class Enemies extends GameScreen {

    public Body createEnemy(float x , float y) {
        Body pBody;
        BodyDef bodydef = new BodyDef();
        bodydef.type = BodyDef.BodyType.KinematicBody;
        bodydef.position.set(x,y);
        pBody = world.createBody(bodydef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(32/ 2 / PPM,32 / 2 / PPM);
        pBody.createFixture(shape, 1f);
        shape.dispose();
        return pBody;
    }
    public void addEnemies(){
        Random random = new Random();
        enemies.add(createEnemy(random.nextFloat(), random.nextFloat()));
    }
    public void ModifyEnemies(float delta){
        float x_position = super.player1.getPosition().x + super.player2.getPosition().x;
        float y_position = super.player2.getPosition().y + super.player2.getPosition().y;

        for(Body enemies : super.enemies){
            enemies.setLinearVelocity(x_position - enemies.getPosition().x,
                    y_position - enemies.getPosition().y);
        }
    }
}