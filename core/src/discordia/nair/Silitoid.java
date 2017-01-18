package discordia.nair;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Dalud on 15.1.2017.
 */

public class Silitoid extends Creature {

    public Silitoid() {
        idle = new Texture(Gdx.files.internal("Silitoid/silitoid_idle.png"));
        walkFront = walkRight = new Texture(Gdx.files.internal("Silitoid/Silitoid_walkRight.png"));
        walkBack = new Texture(Gdx.files.internal("Silitoid/Silitoid_walkBack.png"));
        walkLeft = new Texture(Gdx.files.internal("Silitoid/Silitoid_walkLeft.png"));
        posX = 700;
        posY = 400;
        idleAnimSpeed = .2f;
        walk = .5f;
        run = .3f;
        dimensions = new int[]{20, 8, 1};
    }

    //KOSKA ANIMSHEETIT LIIAN SAMANLAISIA
    @Override
    public void move(Vector2 direction) {

        float x = direction.x;
        float y = direction.y;

        //TÄSSÄ TSEKATAAN SUUNTA JA MÄÄRÄTÄÄN ANIMSHEETTI SEN MUKAAN

        //SEKTORI UP
        if (y >= 1) {
            state = 1;
        }
        //SEKTORI RIGHT
        else if (x >= 1) {
            if (y > x) state = 1;
            else if (y < 0 && -y > x) state = 3;
            else state = 2;
        }
        //SEKTORI DOWN
        else if (y <= -1) {
            if (x > -y) state = 2;
            else if (x < y) state = 4;
            else state = 3;
        }
        //SEKTORI LEFT
        else if (x <= -1) {
            if (y < x) state = 3;
            else if (y > -x) state = 1;
            else state = 4;
        } else state = 0;

        setAnimSheet(state);
        travel(state, direction);
    }
}