package discordia.nair;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class NairMain extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	private OrthographicCamera camera;
	static int scale = 3;
	static int resoX = 1280;
	static int resoY = 720;
	Hoglin hoglin;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, resoX/scale, resoY/scale);
		hoglin = new Hoglin();
	}

	@Override
	public void render () {
		camera.update();
		Gdx.gl.glClearColor(143/255f, 151/255f, 74/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		hoglin.move();
		hoglin.draw(batch);
		batch.end();
	}
}
