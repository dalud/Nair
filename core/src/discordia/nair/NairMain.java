package discordia.nair;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;

public class NairMain extends ApplicationAdapter {
	SpriteBatch batch;
	ModelBatch models;

	private OrthographicCamera camera;
	private PerspectiveCamera cam;
	static int scale = 5;
	static int resoX = 1280;
	static int resoY = 720;

	Hoglin hoglin;

	ModelLoader loader;
	public Model grund;
	public ModelInstance grundIns;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		models = new ModelBatch();
		//camera = new OrthographicCamera();
		cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.position.set(0, 10, 100);
		cam.lookAt(0,0,0);
		cam.near = 1f;
		cam.far = 3000f;
		cam.update();
		//camera.setToOrtho(false, resoX/scale, resoY/scale);
		hoglin = new Hoglin();
		loader = new ObjLoader();
		//grund = loader.loadModel(Gdx.files.internal("grund.obj"));
		//grundIns = new ModelInstance(grund);
	}

	@Override
	public void render () {
		//camera.update();
		cam.update();
		Gdx.gl.glClearColor(143/255f, 151/255f, 74/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		//models.begin(cam);
		//models.render(grundIns);
		hoglin.move();
		hoglin.draw(batch);
		batch.end();
	}
}
