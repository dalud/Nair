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
	Silitoid sili;

	@Override
	public void create () {
		resoX = Gdx.graphics.getWidth();
		resoY = Gdx.graphics.getHeight();
		batch = new SpriteBatch();
		camera = new OrthographicCamera(resoX/scale, resoY/scale);
		camera.position.set(1400, 600, 0);
		player = new Hoglin();
		input = new MyInput(resoX/resoX*16, resoY/resoY*9, player);
		Gdx.input.setInputProcessor(input);
		level = new Level();
		sili = new Silitoid();
	}

	@Override
	public void render () {
		camera.update();
		input.poll(level);
		sili.Operate();
		batch.setProjectionMatrix(camera.combined);
		camera.position.set(player.posX, player.posY, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		level.draw(batch, 1);
		sili.draw(batch);
		player.draw(batch);
		level.draw(batch, 2);
		batch.end();
	}
}