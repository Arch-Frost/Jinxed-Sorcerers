package com.mygdx.cic;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.cic.bodies.*;



public class CIC extends ApplicationAdapter {
	private World world;
	private OrthographicCamera camera;
	private Box2DDebugRenderer debugRenderer;
	private CircleBody circle;
	private GroundBody ground;
	private UserControlledCircle player;


	@Override
	public void create () {
		world = new World(new Vector2(0, -10), true);
		camera = new OrthographicCamera(128f, 102.4f);
		debugRenderer = new Box2DDebugRenderer();
		world.setContactListener(new ContactListener() {
			int i = 0;
			@Override
			public void beginContact(Contact contact) {

				// Check to see if the collision is between the second sprite and the bottom of the screen
				// If so apply a random amount of upward force to both objects... just because
				if((contact.getFixtureA().getBody() == player.getBody()) || (contact.getFixtureB().getBody() == player.getBody()))
				{contact.getFixtureA().getBody().applyLinearImpulse(
						10,10,contact.getFixtureA().getBody().getPosition().x,
						contact.getFixtureA().getBody().getPosition().y,true

				);
					i++;

				Gdx.app.log("msg", String.valueOf(i));
				}


			}

			@Override
			public void endContact(Contact contact) {

			}

			@Override
			public void preSolve(Contact contact, Manifold oldManifold) {

			}

			@Override
			public void postSolve(Contact contact, ContactImpulse impulse) {

			}
		});
		circle = new CircleBody(world, debugRenderer, camera);
		ground = new GroundBody(world, debugRenderer, camera);
		player = new UserControlledCircle(world,debugRenderer,camera);

		ground.create();
		circle.create(5, 18);
		for (int i = 0; i < 10; i++) {
			circle.create(-(float) Math.random() * 50, (float) Math.random() * 50);
		}
		player.create(5,6);


	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		circle.render();
		player.update();

	}

	@Override
	public void dispose () {
		circle.dispose();
	}
}
