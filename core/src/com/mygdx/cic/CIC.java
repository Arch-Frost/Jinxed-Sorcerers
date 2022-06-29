package com.mygdx.cic;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import static com.mygdx.cic.utils.Constants.PPM;

public class CIC extends ApplicationAdapter {
	private OrthographicCamera camera;
	private World world;
	private Box2DDebugRenderer box2DDebugRenderer;
	private Body mybody;
	private Body myplatform;
	private TiledMap mymap;
	private OrthogonalTiledMapRenderer tmr;
	@Override
	public void create(){
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera();
		camera.setToOrtho(false,w/2,h/2);
		world = new World(new Vector2(0f,0),false);
		box2DDebugRenderer = new Box2DDebugRenderer();
		mybody = createBox(0,0,32,32);
		myplatform = createBox(0,-64,64,32);
		mymap = new TmxMapLoader().load("mymap.tmx");
		tmr = new OrthogonalTiledMapRenderer(mymap);
	}

	private Body createBox(int x , int y , int width , int height) {
		Body tempbody;
		BodyDef mydef = new BodyDef();
		mydef.type = BodyDef.BodyType.DynamicBody;
		mydef.position.set(x/PPM,y/PPM);
		mydef.fixedRotation = false;
		tempbody = world.createBody(mydef);
		PolygonShape myshape = new PolygonShape();
		myshape.setAsBox(width/2/PPM,height/2/PPM);
		tempbody.createFixture(myshape,1);
		myshape.dispose();
		return tempbody;
	}

	@Override
	public void render(){
		update(Gdx.graphics.getDeltaTime());
		Gdx.gl.glClearColor(0f,0f,0f,1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		tmr.render();

		box2DDebugRenderer.render(world,camera.combined.scl(PPM));
	}

	@Override
	public void resize(int width , int height){
		camera.setToOrtho(false,width/2,height/2);

	}

	@Override
	public void dispose(){

	}

	public void update(float delta){
		world.step(1/60f,6,2);
		playerupdate(delta);
		cameraupdate(delta);
		tmr.setView(camera);
		
	}

	private void playerupdate(float delta) {
		int horizontalforce = 0;
		int verticalforce = 0;
		if (Gdx.input.isKeyPressed(Input.Keys.D)){ horizontalforce += 1;}
		if (Gdx.input.isKeyPressed(Input.Keys.A)){ horizontalforce -= 1;}
		if (Gdx.input.isKeyPressed(Input.Keys.W)){ verticalforce += 1;}
		if (Gdx.input.isKeyPressed(Input.Keys.S)){ verticalforce -= 1;}
		mybody.setLinearVelocity(horizontalforce*5,verticalforce*5);
	}

	private void cameraupdate(float delta) {
		Vector3 position= camera.position;
		position.x = mybody.getPosition().x * PPM;
		position.y = mybody.getPosition().y * PPM;
		camera.position.set(position);
		camera.update();
	}

}
