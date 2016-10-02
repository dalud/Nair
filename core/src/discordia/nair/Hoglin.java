package discordia.nair;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

import static discordia.nair.NairMain.scale;

/**
 * Created by jaakk_000 on 22.9.2016.
 */

public class Hoglin {
    private TextureRegion currentFrame;
    int state;
    float stateTime, moveSpeed, runAnimSpeed, idleAnimSpeed, frameT;
    OrthographicCamera camera;

    public Hoglin(OrthographicCamera camera){
        this.camera = camera;

        stateTime = 0;
        moveSpeed = 1.1f;
        runAnimSpeed = .125f;
        idleAnimSpeed = .35f;
    }
    public void draw(SpriteBatch batch) {
        batch.draw(currentFrame, camera.position.x - 32, camera.position.y - 32);
    }
    public void move(){
        if(Gdx.input.isTouched()) {
            frameT = runAnimSpeed;

            if(Gdx.input.getX()<365) {
                this.anim(2);
                camera.translate(-moveSpeed, 0);
            }
            else if(Gdx.input.getX()>365 && Gdx.input.getX()<914){
                if(Gdx.input.getY()<360) {
                    this.anim(1);
                    camera.translate(0, +moveSpeed);
                }
                else {
                    this.anim(3);
                    camera.translate(0, -moveSpeed);
                }
            }
            else if(Gdx.input.getX()>914) {
                this.anim(4);
                camera.translate(+moveSpeed, 0);
            }

        }
        else {
            frameT = idleAnimSpeed;
            this.anim(0);
        }
    }
    public void anim(int state) {


        int frame_cols = 4;
        int frame_rows = 1;
        float tick = Gdx.graphics.getDeltaTime();

        Animation anim;
        Texture animSheet = new Texture("Hoglin/Hoglin_idle.png");
        TextureRegion[] animFrames;

        if(state==0) animSheet = new Texture("Hoglin/Hoglin_idle.png");
        if(state==1) animSheet = new Texture("Hoglin/Hoglin_walkBack.png");
        if(state==2) animSheet = new Texture("Hoglin/Hoglin_walkLeft.png");
        if(state==3) animSheet = new Texture("Hoglin/Hoglin_walkFront.png");
        if(state==4) animSheet = new Texture("Hoglin/Hoglin_walkRight.png");

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