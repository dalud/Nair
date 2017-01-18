package discordia.nair;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Dalud on 18.1.2017.
 */

public class AI {
    Creature sili;
    Vector2 direction;
    Level level;
    boolean gameOver;

    public AI(Level level){
        this.level = level;
        sili = new Silitoid(level);
        sili.setPosition(700, 400);
        direction = new Vector2(0,0);
        gameOver = false;

    }
    public void operate(Creature player){
        if(!gameOver){
            direction.x = player.posX-sili.posX;
            if(direction.x > 7) direction.x = 4;
            else if(direction.x < -7) direction.x = -4;
            direction.y = player.posY-sili.posY;
            if(direction.y > 4) direction.y = 2;
            else if(direction.y < -4) direction.y = -2;

            if(direction.len() < 1) {
                gameOver = true;
                level.death();
            }

            sili.move(sili.collide(direction));

        }



    }
    public void draw(SpriteBatch batch){
        sili.draw(batch);
    }
}