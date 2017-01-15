package discordia.nair;

import com.badlogic.gdx.Gdx;
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
    float stateTime, moveSpeed, idleAnimSpeed, frameT;
    int state, posX, posY;

    DecimalFormatSymbols dfs = new DecimalFormatSymbols();
    DecimalFormat df = new DecimalFormat("0.0");

    public Creature(){
        stateTime = 0;
        moveSpeed = 3f;
        idleAnimSpeed = .4f;
        state = 0;

        //LOKALISAATIOSTA JOHTUVA IDIOT CHECK
        dfs.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(dfs);

        animSheet = idle;
    }
    public void draw(SpriteBatch batch) {
        batch.draw(currentFrame, posX-32, posY-32);
    }
    public void move(Vector2 direction){

        float x = direction.x;
        float y = direction.y;

        //TÄSSÄ TSEKATAAN SUUNTA JA MÄÄRÄTÄÄN ANIMSHEETTI SEN MUKAAN
        if(x>0 && y>=0){
            if(x > y) state = 2;
            else state = 1;
        }
        else if(x>=0 && y<0){
            y = -y;
            if(x > y) state = 2;
            else state = 3;
        }
        else if(x<0 && y<=0){
            if(x < y) state = 4;
            else state = 3;
        }
        else if(x<=0 && y>0){
            x = -x;
            if(x > y) state = 4;
            else state = 1;
        }
        else state = 0;

        switch(state){
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

        this.anim(direction, animSheet);
        posX += Math.round(direction.x/16*moveSpeed);
        posY += Math.round(direction.y/9*moveSpeed);
    }

    public void anim(Vector2 direction, Texture animSheet) {

        int frame_cols = 4;
        int frame_rows = 1;
        float tick = Gdx.graphics.getDeltaTime();

        Animation anim;

        TextureRegion[] animFrames;

        //TÄSSÄ MÄÄRÄTÄÄN ANIMAATION NOPEUS LIIKEVEKTORIN MUKAAN
        frameT = Float.parseFloat(df.format(idleAnimSpeed - (direction.len()/9*idleAnimSpeed)));
        if(frameT < .1) frameT = .1f;

        TextureRegion[][] tmp = TextureRegion.split(animSheet, animSheet.getWidth()/frame_cols, animSheet.getHeight()/frame_rows);
        animFrames = new TextureRegion[frame_cols * frame_rows];
        int index = 0;
        for (int i = 0; i < frame_rows; i++) {
            for (int j = 0; j < frame_cols; j++) {
                animFrames[index++] = tmp[i][j];
            }
        }
        anim = new Animation(frameT, animFrames);
        stateTime += tick;
        currentFrame = anim.getKeyFrame(stateTime, true);
    }
}