package discordia.nair;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class NairMain extends ApplicationAdapter {
	SpriteBatch batch;
	private OrthographicCamera camera;
	static int scale = 3;
	static int resoX;
	static int resoY;
	Hoglin hoglin;
	Texture YigL1, YigL2;
	MyInput input;

	@Override
	public void create () {
		resoX = Gdx.graphics.getWidth();
		resoY = Gdx.graphics.getHeight();
		batch = new SpriteBatch();
		camera = new OrthographicCamera(resoX/scale, resoY/scale);
		camera.position.set(300, 400, 0);
		hoglin = new Hoglin(camera);
		YigL1 = new Texture(Gdx.files.internal("ForestOfYig/mapL1.png"));
		YigL2 = new Texture(Gdx.files.internal("ForestOfYig/mapL2.png"));
		input = new MyInput(resoX/resoX*16, resoY/resoY*9, hoglin);
		Gdx.input.setInputProcessor(input);
	}

	@Override
	public void render () {
		camera.update();
		input.Poll();
		batch.setProjectionMatrix(camera.combined);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(YigL1, 0, 0);
		hoglin.draw(batch);
		batch.draw(YigL2, 0, 0);
		batch.end();
	}
}