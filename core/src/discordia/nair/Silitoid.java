package discordia.nair;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Dalud on 15.1.2017.
 */

public class Silitoid extends Creature {

    public Silitoid(){
        idle = walkBack = walkFront = walkLeft = walkRight = new Texture(Gdx.files.internal("Silitoid/silitoid_idle.png"));
        posX = 1300;
        posY = 600;
        idleAnimSpeed = .3f;
    }

    Vector2 direction = new Vector2(0, 0);

    public void Operate(){
        move(direction);
    }
}