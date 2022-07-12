package com.mygdx.cic.utils;

import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.cic.savedata.Save;

import java.util.ArrayList;

import static com.mygdx.cic.utils.BodiesData.*;
import static com.mygdx.cic.utils.BodiesData.BULLET1;

public class CollisionListener implements ContactListener {
    private ArrayList<Body> bodiesToBeRemoved;

    public CollisionListener(){
        super();
        bodiesToBeRemoved = new ArrayList<>();
    }
    @Override
    public void beginContact(Contact contact) {
        try {


            if (contact.getFixtureA() == null && contact.getFixtureB() == null) return;
            Body a = contact.getFixtureA().getBody();
            Body b = contact.getFixtureB().getBody();
            if (a.getUserData() == PLAYER2 && b.getUserData() == BULLET) {
                if (!bodiesToBeRemoved.contains(b)) bodiesToBeRemoved.add(b);

            }
            if (a.getUserData() == BULLET && b.getUserData() == PLAYER2) {
                if (!bodiesToBeRemoved.contains(a)) bodiesToBeRemoved.add(a);

            }
            if (a.getUserData() == PLAYER1 && b.getUserData() == BULLET1) {
                if (!bodiesToBeRemoved.contains(b)) bodiesToBeRemoved.add(b);

            }
            if (a.getUserData() == BULLET1 && b.getUserData() == PLAYER1) {
                if (!bodiesToBeRemoved.contains(a)) bodiesToBeRemoved.add(a);

            }
            if (a.getUserData() == BULLET || a.getUserData() == BULLET1) {
                if (b.getUserData() == ENEMY) {
                    if (!bodiesToBeRemoved.contains(b)) bodiesToBeRemoved.add(b);
                }
            }
            if (b.getUserData() == BULLET || b.getUserData() == BULLET1) {
                if (a.getUserData() == ENEMY) {
                    if (!bodiesToBeRemoved.contains(a)) bodiesToBeRemoved.add(a);
                }
            }
            if (a.getUserData() == ENEMY && b.getUserData() == PLAYER1) {
                System.out.println("Game Over. Final Score: " + Save.gd.getTentativeScore());
                System.exit(0);
            }
            if (b.getUserData() == ENEMY && a.getUserData() == PLAYER1) {
                System.out.println("Game Over. Final Score: " + Save.gd.getTentativeScore());
                System.exit(0);
            }
        }
        catch (NullPointerException e){
            System.out.println("Object is declared Null!");

        }

    }
    public ArrayList<Body> getBodies(){
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
