package discordia.nair;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Dalud on 22.9.2016.
 */

public class Hoglin {
    private TextureRegion currentFrame;
    Texture idle, walkBack, walkFront, walkLeft, walkRight;
    float stateTime, moveSpeed, runAnimSpeed, idleAnimSpeed, frameT, diagSpeed;
    OrthographicCamera camera;
    int state = 0;

    public Hoglin(OrthographicCamera camera){
        this.camera = camera;
        stateTime = 0;
        moveSpeed = 1.1f;
        runAnimSpeed = .125f;
        idleAnimSpeed = .35f;
        diagSpeed = .73f;
        idle = new Texture("Hoglin/Hoglin_idle.png");
        walkBack = new Texture("Hoglin/Hoglin_walkBack.png");
        walkFront = new Texture("Hoglin/Hoglin_walkFront.png");
        walkLeft = new Texture("Hoglin/Hoglin_walkLeft.png");
        walkRight = new Texture("Hoglin/Hoglin_walkRight.png");
    }
    public void draw(SpriteBatch batch) {
        batch.draw(currentFrame, camera.position.x - 32, camera.position.y - 32);
    }
    public void move(int state){

        this.state = state;

        if(state != 0) frameT = runAnimSpeed;
        else frameT = idleAnimSpeed;

        switch(state){
                case 1:
                    camera.translate(0, +moveSpeed);
                    break;
                case 2:
                    camera.translate(+moveSpeed*diagSpeed, +moveSpeed*diagSpeed);
                    break;
                case 3:
                    camera.translate(+moveSpeed, 0);
                    break;
                case 4:
                    camera.translate(+moveSpeed*diagSpeed, -moveSpeed*diagSpeed);
                    break;
                case 5:
                    camera.translate(0, -moveSpeed);
                    break;
                case 6:
                    camera.translate(-moveSpeed*diagSpeed, -moveSpeed*diagSpeed);
                    break;
                case 7:
                    camera.translate(-moveSpeed, 0);
                    break;
                case 8:
                    camera.translate(-moveSpeed*diagSpeed, +moveSpeed*diagSpeed);
            }

        this.anim(state);
    }

    public void anim(int state) {

        int frame_cols = 4;
        int frame_rows = 1;
        float tick = Gdx.graphics.getDeltaTime();

        Animation anim;
        Texture animSheet = idle;
        TextureRegion[] animFrames;

        switch(state){
            case 0:
                animSheet = idle;
                break;
            case 1:
                animSheet = walkBack;
                break;
            case 3:case 2:case 4:
                animSheet = walkRight;
                break;
            case 5:
                animSheet = walkFront;
                break;
            case 7:case 8: case 6:
                animSheet = walkLeft;
                break;
        }

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