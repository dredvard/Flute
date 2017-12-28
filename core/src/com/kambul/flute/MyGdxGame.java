package com.kambul.flute;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class MyGdxGame implements ApplicationListener, GestureListener {
	private OrthographicCamera camera, hubcamera;
	private SpriteBatch batch, hubbatch;
	private Texture texture, texture2;
	private Texture backtexture;
	private Sprite sprite, background, hub;
	private BitmapFont font;
	float hubInfo[] = new float[4];
	int num = 0;
	boolean dead=false;
	float fontX = 0;
	float fontY = 0;

	@Override
	public void create() {
		font = new BitmapFont();
		font.getData().setScale(2);
		font.setColor(Color.BLUE);
		camera = new OrthographicCamera(1280, 720);
		batch = new SpriteBatch();
		hubcamera = new OrthographicCamera(1280, 720);
		hubbatch = new SpriteBatch();

		texture = new Texture(Gdx.files.internal("jet.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		texture2 = new Texture(Gdx.files.internal("crosshair.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		backtexture = new Texture(Gdx.files.internal("city.jpg"));
		backtexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		background = new Sprite(backtexture);
		background.setOrigin(0, 0);
		background.setPosition(-background.getWidth() / 2, -background.getHeight() / 2);

		sprite = new Sprite(texture);
		sprite.setOrigin(0, 0);
		sprite.setPosition(-sprite.getWidth() / 2, -sprite.getHeight() / 2);

		hub = new Sprite(texture2);
		hubInfo[0]=200;
		hubInfo[1]=100;
		hubInfo[2]=hub.getWidth();
		hubInfo[3]=hub.getHeight();
		hub.setPosition(hubInfo[0], hubInfo[1]);

		Gdx.input.setInputProcessor(new GestureDetector(this));
	}

	@Override
	public void dispose() {
		batch.dispose();
		texture.dispose();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		background.draw(batch);
		sprite.draw(batch);
		if (!dead)
		sprite.setX(sprite.getX()-4);
		else{
			sprite.setY(sprite.getY()-4);
		}
		batch.end();


		hubbatch.begin();
		hub.draw(hubbatch);
		font.draw(hubbatch, Integer.toString(num), fontX+100, fontY+100);
		hubbatch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		// TODO Auto-generated method stub
		num += 1;
		dead=true;
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override

	public boolean fling(float velocityX, float velocityY, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override

	public boolean pan(float x, float y, float deltaX, float deltaY) {
		// TODO Auto-generated method stub
		camera.translate(deltaX, -deltaY);
		camera.update();
//		fontX += 2 * deltaX;
		sprite.translate(-2*deltaX, 2*deltaY);
		// fontY+=deltaY;
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override

	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;

	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void pinchStop() {
		// TODO Auto-generated method stub

	}
}
