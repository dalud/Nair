package discordia.nair;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Dalud on 11.1.2017.
 */

public class Level {
    Texture YigL1, YigL2, ui, crosshair;
    Pixmap obstacles, deathScreen;
    Music bardic;
    Sound deathScream;
    int width, height;
    SpriteBatch batch;
    OrthographicCamera camera;

    Rectangle touch, resetButton;

    public Level(int x, int y, SpriteBatch batch, OrthographicCamera camera){
        YigL1 = new Texture(Gdx.files.internal("ForestOfYig/FoYL1.png"));
        YigL2 = new Texture(Gdx.files.internal("ForestOfYig/FoYL2.png"));
        crosshair = new Texture(Gdx.files.internal("UI/crosshair.png"));
        obstacles = new Pixmap(Gdx.files.internal("ForestOfYig/collision.png"));
        deathScream = Gdx.audio.newSound(Gdx.files.internal("sfx/inhumaneScream.mp3"));
        width = x;
        height = y;
        this.batch = batch;
        this.camera = camera;
        bardic = Gdx.audio.newMusic(Gdx.files.internal("music/Bardic.mp3"));
        bardic.setLooping(true);
        bardic.play();
    }

    public void draw(int layer){
        switch(layer){
            case 1:
                batch.draw(YigL1, 0, 0);
                break;
            case 2:
                batch.draw(YigL2, 0, 0);
                break;
            case 3:
                if(ui != null) batch.draw(ui, 0, 0);
                //FOR DEBUGGING'S SAKE
                //if(touch != null) batch.draw(crosshair, touch.getX()-touch.getWidth()/2, height-touch.getY()-touch.getHeight()/2);
                break;


        }
    }

    //TÄMÄ PITÄIS TAPAHTUA JOSSAIN IHAN MUUALLA. HOW ABOUT A UI-CLASS?
    public void death(Creature player){

        Gdx.input.setInputProcessor(NairMain.menu);
        player.travel(0, new Vector2(0,0));
        bardic.stop();
        deathScream.play();
        camera.setToOrtho(false);
        camera.position.set(width/2, height/2, 0);
        camera.update();

        deathScreen = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        deathScreen.setColor(0, 0, 0, 1);
        deathScreen.fillRectangle(0, 0, width, height);
        Pixmap dead = new Pixmap(Gdx.files.internal("UI/dead.png"));
        deathScreen.drawPixmap(dead, width/2-dead.getWidth()/2, height/2-dead.getHeight());
        Pixmap again = new Pixmap(Gdx.files.internal("UI/again.png"));
        resetButton = new Rectangle(width/2-again.getWidth()/2, height-height/3, again.getWidth(), again.getHeight());
        deathScreen.drawPixmap(again, (int)resetButton.getX(), (int)resetButton.getY());
        ui = new Texture(deathScreen);


        /*float slider = 0f; KAUNIS AJATUS, MUTTA JOUTUNEE KÄYTTÄÄMÄÄN SHAPERENDERIÄ TAI ANIMAATIOTA
        while(slider < 1) {
            deathScreen.setColor(slider, 0, 0, 1);
            deathScreen.fillRectangle(posX - viewWidth / 2, (deathScreen.getHeight() - posY) - viewHeight / 2, viewWidth, viewHeight);

            slider += .1f;
        }*/
    }

    public void getTouch(int x, int y){
        touch = new Rectangle(x, y, crosshair.getWidth(), crosshair.getHeight());
        if(touch.overlaps(resetButton)) NairMain.reset = true;
    }
}