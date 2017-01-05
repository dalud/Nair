package discordia.nair;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Dalud on 22.9.2016.
 */

public class Hoglin {
    private TextureRegion currentFrame;
    Texture idle, walkBack, walkFront, walkLeft, walkRight, animSheet;
    float stateTime, moveSpeed, idleAnimSpeed, frameT;
    OrthographicCamera camera;
    int state = 0;

    public Hoglin(OrthographicCamera camera){
        this.camera = camera;
        stateTime = 0;
        moveSpeed = 2f;
        idleAnimSpeed = .5f;

        idle = new Texture("Hoglin/Hoglin_idle.png");
        walkBack = new Texture("Hoglin/Hoglin_walkBack.png");
        walkFront = new Texture("Hoglin/Hoglin_walkFront.png");
        walkLeft = new Texture("Hoglin/Hoglin_walkLeft.png");
        walkRight = new Texture("Hoglin/Hoglin_walkRight.png");
        animSheet = idle;
    }
    public void draw(SpriteBatch batch) {
        batch.draw(currentFrame, camera.position.x - 32, camera.position.y - 32);
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
        camera.translate(direction.x/16*moveSpeed, direction.y/9*moveSpeed);
    }

    public void anim(Vector2 direction, Texture animSheet) {

        int frame_cols = 4;
        int frame_rows = 1;
        float tick = Gdx.graphics.getDeltaTime();

        Animation anim;

        TextureRegion[] animFrames;

        //TÄSSÄ MÄÄRÄTÄÄN ANIMMATION NOPEUS LIIKEVEKTORIN MUKAAN
        frameT = idleAnimSpeed - direction.len()/9*idleAnimSpeed;

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