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
        dimensions = new int[]{20, 8, 1};
    }
    //KOSKA ANIMSHEETIT LIIAN SAMANLAISIA
    @Override
    public void move(Vector2 direction){

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

        switch (state) {
            case 0:
                animSheet = idle;
                break;
            case 1:
                animSheet = walkBack;
                break;
            case 2:
                animSheet = walkRight;
                break;
            case 3:
                animSheet = walkFront;
                break;
            case 4:
                animSheet = walkLeft;
                break;
        }

        //SKAALATAAN INPUT-VEKTORI LIIKKEEKSI: X = 1 - 3, Y = 1 - 2 !!!TÄMÄ TÄYTYY KYLLÄ TEHÄ DELTATIMELLÄ EIKÄ FPS:N MUKAAN!!! (joskus...)
        if (state != 0) {
            switch ((int) direction.x) {
                case 1:
                case 2:
                case 3:
                    posX += 1;
                    break;
                case 4:
                case 5:
                case 6:
                case 7:
                    posX += 2;
                    break;
                case -1:
                case -2:
                case -3:
                    posX += -1;
                    break;
                case -4:
                case -5:
                case -6:
                case -7:
                    posX += -2;
                    break;
            }
            switch ((int) direction.y) {
                case 1:
                case 2:
                case 3:
                    posY += 1;
                    break;
                case 4:
                    posY += 2;
                    break;
                case -1:
                case -2:
                case -3:
                    posY += -1;
                    break;
                case -4:
                    posY += -2;
                    break;
            }
        }

        //SÄÄDETÄÄN ANIMAATIONOPEUS LIIKEVEKTORIN MUKAAN (kuinkahan raskaita nuo sqrtit on. pitääkö käyttää likiarvoja?)
        float speed;
        //WALK
        if (direction.len() >= 1 && direction.len() < Math.sqrt(11)) speed = .5f;
            //JOG
        else if (direction.len() >= Math.sqrt(11)) speed = .3f;

        else speed = 1;

        this.anim(animSheet, speed);
    }
}