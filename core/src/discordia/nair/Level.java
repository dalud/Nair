package discordia.nair;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Dalud on 11.1.2017.
 */

public class Level {
    Texture YigL1, YigL2, deathScreen;
    Pixmap obstacles;
    Music bardic;
    Sound deathScream;

    public Level(){
        YigL1 = new Texture(Gdx.files.internal("ForestOfYig/FoYL1.png"));
        YigL2 = new Texture(Gdx.files.internal("ForestOfYig/FoYL2.png"));
        obstacles = new Pixmap(Gdx.files.internal("ForestOfYig/collision.png"));

        deathScreen = new Texture(obstacles);

        deathScream = Gdx.audio.newSound(Gdx.files.internal("sfx/inhumaneScream.mp3"));
        bardic = Gdx.audio.newMusic(Gdx.files.internal("music/Bardic.mp3"));
        bardic.setLooping(true);
        bardic.play();
    }

    public void draw(SpriteBatch batch, int layer){
        switch(layer){
            case 1:
                batch.draw(YigL1, 0, 0);
                break;
            case 2:
                batch.draw(YigL2, 0, 0);
                break;
        }
    }

    public void death(){
        bardic.stop();
        deathScream.play();
        YigL2 = deathScreen;

    }
}