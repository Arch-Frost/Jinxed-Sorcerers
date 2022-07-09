package com.mygdx.cic.utils;

import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;

public class CollisionListener implements ContactListener {
    private ArrayList<Body> bodiestoberemoved ;

    public CollisionListener(){
        super();
        bodiestoberemoved = new ArrayList<>();
    }
    @Override
    public void beginContact(Contact contact) {
        if(contact.getFixtureA() == null && contact.getFixtureB() == null) return;
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();
        System.out.println("Collision between " + a.getUserData() + " and " + b.getUserData());
        if(a.getUserData() == BodiesData.PLAYER2 && b.getUserData() == BodiesData.BULLET){
            if(!bodiestoberemoved.contains(b)) bodiestoberemoved.add(b);

        }
        if(a.getUserData() == BodiesData.BULLET && b.getUserData() == BodiesData.PLAYER2){
            if(!bodiestoberemoved.contains(a)) bodiestoberemoved.add(a);

        }
        if(a.getUserData() == BodiesData.PLAYER1 && b.getUserData() == BodiesData.BULLET1){
            if(!bodiestoberemoved.contains(b)) bodiestoberemoved.add(b);

        }
        if(a.getUserData() == BodiesData.BULLET1 && b.getUserData() == BodiesData.PLAYER1){
            if(!bodiestoberemoved.contains(a)) bodiestoberemoved.add(a);

        }


    }
    public ArrayList<Body> getbodies (){
        return bodiestoberemoved;
    }

    @Override
    public void endContact(Contact contact) {
        if(contact.getFixtureA() == null && contact.getFixtureB() == null) return;

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
