package com.mygdx.cic;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.cic.bodies.UsercontrolledCircle;


public class CIC extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		i = 0;
		world = new World(new Vector2(0, -10), true);
		camera = new OrthographicCamera(128f, 102.4f);
		debugRenderer = new Box2DDebugRenderer();

		circle = new CircleBody(world, debugRenderer, camera);
		ground = new GroundBody(world, debugRenderer, camera);
//		rectangleBody = new RectangleBody(world,debugRenderer,camera);
		player = new UsercontrolledCircle(world,debugRenderer,camera);
		ground.create();
		circle.create(5, 18);
		for (int i = 0; i < 10; i++) {
			circle.create(-(float) Math.random() * 50, (float) Math.random() * 50);
		}
		player.create(-10,-10);


	}

	@Override
	public void render () {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		rectangleBody.update();
		player.update();
		while (i <10){
			circle.create(0,0);
			i++;
		}
		circle.render();


	}
	
	@Override
	public void dispose () {

	}
}
