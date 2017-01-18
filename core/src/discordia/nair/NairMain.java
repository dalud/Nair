package discordia.nair;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class NairMain extends ApplicationAdapter {
	SpriteBatch batch;
	OrthographicCamera camera;
	static int scale = 3;
	static int resoX;
	static int resoY;
	Creature player;
	MyInput input;
	Level level;

	@Override
	public void create () {
		resoX = Gdx.graphics.getWidth();
		resoY = Gdx.graphics.getHeight();
		batch = new SpriteBatch();
		camera = new OrthographicCamera(resoX/scale, resoY/scale);
		player = new Hoglin();
		input = new MyInput(resoX, resoY, player);
		Gdx.input.setInputProcessor(input);
		level = new Level();
	}

	@Override
	public void render () {
		camera.update();
		input.poll(level);
		batch.setProjectionMatrix(camera.combined);
		camera.position.set(player.posX, player.posY, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		level.draw(batch, 1);
		player.draw(batch);
		level.draw(batch, 2);
		batch.end();
	}
}