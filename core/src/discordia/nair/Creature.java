package discordia.nair;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * Created by Dalud on 15.1.2017.
 */

public abstract class Creature {
    private TextureRegion currentFrame;
    Texture idle, walkBack, walkFront, walkLeft, walkRight, animSheet;
    float stateTime, idleAnimSpeed, speedScalar, walk, run;
    int state, posX, posY;
    Pixmap obstacles;
    Vector2 confirmed;
    int[] dimensions;   /*  0 = x
                            1 = y
                            2 = yPositive */

    //DecimalFormatSymbols dfs = new DecimalFormatSymbols();
    //DecimalFormat df = new DecimalFormat("0.0");

    public Creature(Level level) {
        stateTime = 0;
        state = 0;
        animSheet = idle;
        dimensions = new int[3];
        obstacles = level.obstacles;
        confirmed = new Vector2(0,0);

        //LOKALISAATIOSTA JOHTUVA IDIOT CHECK
        //dfs.setDecimalSeparator('.');
        //df.setDecimalFormatSymbols(dfs);
    }

    public void setPosition(int x, int y){
        posX = x;
        posY = y;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(currentFrame, posX - 32, posY - 32);
    }

    public Vector2 collide(Vector2 direction){
        float dx = direction.x;
        float dy = direction.y;
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
                if (obstacles.getPixel(posX + dimensions[0], posY) < 1) confirmed.x = 0;
                else confirmed.x = dx;

                if (obstacles.getPixel(posX, posY + dimensions[2]) < 1) confirmed.y = 0;
                else confirmed.y = dy;
                break;

            case 2:
                if (obstacles.getPixel(posX + dimensions[0], posY) < 1) confirmed.x = 0;
                else confirmed.x = dx;

                if (obstacles.getPixel(posX, posY - dimensions[1]) < 1) confirmed.y = 0;
                else confirmed.y = dy;
                break;

            case 3:
                if (obstacles.getPixel(posX - dimensions[0], posY) < 1) confirmed.x = 0;
                else confirmed.x = dx;

                if (obstacles.getPixel(posX, posY - dimensions[1]) < 1) confirmed.y = 0;
                else confirmed.y = dy;
                break;

            case 4:
                if (obstacles.getPixel(posX - dimensions[0], posY) < 1) confirmed.x = 0;
                else confirmed.x = dx;

                if (obstacles.getPixel(posX, posY + dimensions[2]) < 1) confirmed.y = 0;
                else confirmed.y = dy;
                break;

            default:
                confirmed = direction;
                break;
        }
        return confirmed;
    }

    public void move(Vector2 direction) {
        float x = direction.x;
        float y = direction.y;

        //TÄSSÄ TSEKATAAN SUUNTA

        //SEKTORI UP
        if (y >= 1) {
            if (x > y) state = 2;
            else if (x < 0 && -x > y) state = 4;
            else state = 1;
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

        //JA MÄÄRÄTÄÄN ANIMSHEETTI SEN MUKAAN
        setAnimSheet(state);
        //JA LIIKUTAAN
        travel(state, direction);
    }

    public void setAnimSheet(int state) {
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
    }
    public void travel(int state, Vector2 direction) {
        //SKAALATAAN INPUT-VEKTORI LIIKE/ANIMAATIONOPEUDEKSI !!!TÄMÄ TÄYTYY KYLLÄ TEHÄ DELTATIMELLÄ EIKÄ FPS:N MUKAAN!!! (joskus...)
        if (state != 0) {
            switch ((int)direction.x) {
                case 1:
                case 2:
                case 3:
                case 4:
                    posX += 1;
                    speedScalar = walk;
                    break;
                case 5:
                case 6:
                case 7:
                    posX += 2;
                    speedScalar = run;
                    break;
                case -1:
                case -2:
                case -3:
                case -4:
                    posX += -1;
                    speedScalar = walk;
                    break;
                case -5:
                case -6:
                case -7:
                    posX += -2;
                    speedScalar = run;
                    break;
            }
            switch ((int)direction.y) {
                case 1:
                case 2:
                case 3:
                    posY += 1;
                    speedScalar = walk;
                    break;
                case 4:
                    posY += 2;
                    speedScalar = run;
                    break;
                case -1:
                case -2:
                case -3:
                    posY += -1;
                    speedScalar = walk;
                    break;
                case -4:
                    posY += -2;
                    speedScalar = run;
                    break;
            }
        }else speedScalar = 1;

        this.anim(animSheet, speedScalar);
    }

    public void anim(Texture animSheet, float speed) {
        int frame_cols = 4;
        int frame_rows = 1;
        float tick = Gdx.graphics.getDeltaTime();

        Animation anim;

        TextureRegion[] animFrames;

        TextureRegion[][] tmp = TextureRegion.split(animSheet, animSheet.getWidth() / frame_cols, animSheet.getHeight() / frame_rows);
        animFrames = new TextureRegion[frame_cols * frame_rows];
        int index = 0;
        for (int i = 0; i < frame_rows; i++) {
            for (int j = 0; j < frame_cols; j++) {
                animFrames[index++] = tmp[i][j];
            }
        }
        anim = new Animation(idleAnimSpeed * speed, animFrames);
        stateTime += tick;
        currentFrame = anim.getKeyFrame(stateTime, true);
    }
}