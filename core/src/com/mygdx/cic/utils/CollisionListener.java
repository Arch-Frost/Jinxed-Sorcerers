package com.mygdx.cic.utils;

import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;

public class CollisionListener implements ContactListener {
    private ArrayList<Body> bodiesToBeRemoved;

    public CollisionListener(){
        super();
        bodiesToBeRemoved = new ArrayList<>();
    }
    @Override
    public void beginContact(Contact contact) {
        if(contact.getFixtureA() == null && contact.getFixtureB() == null) return;
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();
        if(a.getUserData() == BodiesData.PLAYER2 && b.getUserData() == BodiesData.BULLET){
            if(!bodiesToBeRemoved.contains(b)) bodiesToBeRemoved.add(b);

        }
        if(a.getUserData() == BodiesData.BULLET && b.getUserData() == BodiesData.PLAYER2){
            if(!bodiesToBeRemoved.contains(a)) bodiesToBeRemoved.add(a);

        }
        if(a.getUserData() == BodiesData.PLAYER1 && b.getUserData() == BodiesData.BULLET1){
            if(!bodiesToBeRemoved.contains(b)) bodiesToBeRemoved.add(b);

        }
        if(a.getUserData() == BodiesData.BULLET1 && b.getUserData() == BodiesData.PLAYER1){
            if(!bodiesToBeRemoved.contains(a)) bodiesToBeRemoved.add(a);

        }


    }
    public ArrayList<Body> getbodies (){
        return bodiesToBeRemoved;
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
