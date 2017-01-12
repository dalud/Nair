package discordia.nair;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class NairMain extends ApplicationAdapter {
	SpriteBatch batch;
	private OrthographicCamera camera;
	static int scale = 3;
	static int resoX;
	static int resoY;
	Hoglin hoglin;
	MyInput input;
	Level level;

	@Override
	public void create () {
		resoX = Gdx.graphics.getWidth();
		resoY = Gdx.graphics.getHeight();
		batch = new SpriteBatch();
		camera = new OrthographicCamera(resoX/scale, resoY/scale);
		camera.position.set(1400, 600, 0);
		hoglin = new Hoglin(camera);
		input = new MyInput(resoX/resoX*16, resoY/resoY*9, hoglin);
		Gdx.input.setInputProcessor(input);
		level = new Level(batch);
	}

	@Override
	public void render () {
		camera.update();
		input.poll(level, camera);
		batch.setProjectionMatrix(camera.combined);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		level.draw(1);
		hoglin.draw(batch);
		level.draw(2);
		batch.end();
	}
}