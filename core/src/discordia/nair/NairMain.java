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
	static MenuInput menu;
	Level level;
	AI ai;
	static boolean gameOver, reset;  //REALLY?

	@Override
	public void create() {
		resoX = Gdx.graphics.getWidth();
		resoY = Gdx.graphics.getHeight();
		batch = new SpriteBatch();
		camera = new OrthographicCamera(resoX/scale, resoY/scale);
		level = new Level(resoX, resoY, batch, camera);
		player = new Hoglin(level);
		input = new MyInput(resoX, resoY, player);
		menu = new MenuInput(level);
		Gdx.input.setInputProcessor(input);
		gameOver = reset = false;
		ai = new AI(level);
	}

	@Override
	public void render () {
		camera.update();
		ai.operate(player);

		batch.setProjectionMatrix(camera.combined);

		//REALLY?
		if(!gameOver){
			input.poll();
			camera.position.set(player.posX, player.posY, 0);
		}

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		level.draw(1);
		player.draw(batch);
		ai.draw(batch);
		level.draw(2);
		level.draw(3);
		batch.end();

		//REALLY?
		if(reset){
			level = new Level(resoX, resoY, batch, camera);
			camera.setToOrtho(false, resoX/scale, resoY/scale);
			player = new Hoglin(level);
			input = new MyInput(resoX, resoY, player);
			input.finger = 0;
			Gdx.input.setInputProcessor(input);
			gameOver = reset = false;
			ai = new AI(level);
		}
	}
}