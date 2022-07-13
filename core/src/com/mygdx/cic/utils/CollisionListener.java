package com.mygdx.cic.utils;

import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.cic.CIC;
import com.mygdx.cic.savedata.Save;
import com.mygdx.cic.screens.DungeonMap;
import com.mygdx.cic.screens.Game_OverScreen;
import com.mygdx.cic.screens.Menu_Screen;
import com.mygdx.cic.screens.OasisMap;

import java.util.ArrayList;

import static com.mygdx.cic.utils.BodiesData.*;
import static com.mygdx.cic.utils.BodiesData.BULLET1;

public class CollisionListener implements ContactListener {
    private ArrayList<Body> bodiesToBeRemoved;
    private CIC parent;

    public CollisionListener(CIC cic){
        super();
        this.parent = cic;
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
                OasisMap.sound.stop();
                DungeonMap.sound.stop();
                CIC.game_overScreen=new Game_OverScreen(parent);
                parent.changeScreen(CIC.GO_screen);
            }
            if (b.getUserData() == ENEMY && a.getUserData() == PLAYER1) {
                OasisMap.sound.stop();
                DungeonMap.sound.stop();
                CIC.game_overScreen=new Game_OverScreen(parent);
                parent.changeScreen(CIC.GO_screen);
            }
            if (a.getUserData() == ENEMY && b.getUserData() == PLAYER2) {
                OasisMap.sound.stop();
                DungeonMap.sound.stop();
                CIC.game_overScreen=new Game_OverScreen(parent);
                parent.changeScreen(CIC.GO_screen);
            }
            if (b.getUserData() == ENEMY && a.getUserData() == PLAYER2) {
                OasisMap.sound.stop();
                DungeonMap.sound.stop();
                CIC.game_overScreen=new Game_OverScreen(parent);
                parent.changeScreen(CIC.GO_screen);
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
