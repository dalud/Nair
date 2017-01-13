package discordia.nair;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Dalud on 11.1.2017.
 */

public class Level {
    Texture YigL1, YigL2;
    SpriteBatch batch;
    Pixmap obstacles;
    Vector2 confirmed;
    int playerDimensionX, playerDimensionY, playerDimensionYPositive;
    Music bardic;

    public Level(SpriteBatch batch){

        YigL1 = new Texture(Gdx.files.internal("ForestOfYig/FoYL1.png"));
        YigL2 = new Texture(Gdx.files.internal("ForestOfYig/FoYL2.png"));
        this.batch = batch;
        obstacles = new Pixmap(Gdx.files.internal("ForestOfYig/collision.png"));
        playerDimensionX = 10;
        playerDimensionY = 16;
        playerDimensionYPositive = 1;
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
        }
    }

    public Vector2 collide(OrthographicCamera camera, Vector2 direction){

        int cx = Math.round(camera.position.x);
        int cy = Math.round(camera.position.y);
        int dx = Math.round(direction.x);
        int dy = Math.round(direction.y);
        int state = 0;

        //MÄÄRÄTÄÄN STATE/SUUNTA MISTÄ KOLLISIOTA TSEKATAAN
        if(dx > 0){
            if(dy >= 0) state = 1;
            else state = 2;
        }
        else if(dx < 0){
            if(dy <= 0) state = 3;
            else state = 4;
        }
        else if(dx == 0){
            if(dy < 0) state = 3;
            else if(dy > 0) state = 4;
        }

        //TÄSSÄ ITSE KOLLISIO STATEN/SUUNNAN MUKAAN
        switch(state) {
            case 1:
                if (obstacles.getPixel(cx + playerDimensionX, cy) < 1) confirmed.x = 0;
                else confirmed.x = dx;

                if (obstacles.getPixel(cx, cy + playerDimensionYPositive) < 1) confirmed.y = 0;
                else confirmed.y = dy;
                break;

            case 2:
                if (obstacles.getPixel(cx + playerDimensionX, cy) < 1) confirmed.x = 0;
                else confirmed.x = dx;

                if (obstacles.getPixel(cx, cy - playerDimensionY) < 1) confirmed.y = 0;
                else confirmed.y = dy;
                break;

            case 3:
                if (obstacles.getPixel(cx - playerDimensionX, cy) < 1) confirmed.x = 0;
                else confirmed.x = dx;

                if (obstacles.getPixel(cx, cy - playerDimensionY) < 1) confirmed.y = 0;
                else confirmed.y = dy;
                break;

            case 4:
                if (obstacles.getPixel(cx - playerDimensionX, cy) < 1) confirmed.x = 0;
                else confirmed.x = dx;

                if (obstacles.getPixel(cx, cy + playerDimensionYPositive) < 1) confirmed.y = 0;
                else confirmed.y = dy;
                break;

            default:
                confirmed = direction;
                break;
        }

        return confirmed;
    }
}