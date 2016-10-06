package discordia.nair;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by dalud on 22.9.2016.
 */

public class Hoglin {
    private TextureRegion currentFrame;
    private Texture idle, walkBack, walkLeft, walkFront, walkRight;
    float stateTime, moveSpeed, runAnimSpeed, idleAnimSpeed, frameT;
    OrthographicCamera camera;
    enum State {    IDLE,
                    WALK_BACK,
                    WALK_LEFT,
                    WALK_FRONT,
                    WALK_RIGHT
                                }
    private State state;

    public Hoglin(OrthographicCamera camera){
        this.camera = camera;
        idle = new Texture("Hoglin/Hoglin_idle.png");
        walkBack = new Texture("Hoglin/Hoglin_walkBack.png");
        walkLeft = new Texture("Hoglin/Hoglin_walkLeft.png");
        walkFront = new Texture("Hoglin/Hoglin_walkFront.png");
        walkRight = new Texture("Hoglin/Hoglin_walkRight.png");

        state = State.IDLE;
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
                state = State.WALK_LEFT;
                this.anim();
                camera.translate(-moveSpeed, 0);
            }
            else if(Gdx.input.getX()>365 && Gdx.input.getX()<914){
                if(Gdx.input.getY()<360) {
                    state = State.WALK_BACK;
                    this.anim();
                    camera.translate(0, +moveSpeed);
                }
                else {
                    state = State.WALK_FRONT;
                    this.anim();
                    camera.translate(0, -moveSpeed);
                }
            }
            else if(Gdx.input.getX()>914) {
                state = State.WALK_RIGHT;
                this.anim();
                camera.translate(+moveSpeed, 0);
            }

        }
        else {
            frameT = idleAnimSpeed;
            state = State.IDLE;
            this.anim();
        }
    }
    public void anim() {


        int frame_cols = 4;
        int frame_rows = 1;
        float tick = Gdx.graphics.getDeltaTime();

        Animation anim;
        Texture animSheet = new Texture("Hoglin/Hoglin_idle.png");
        TextureRegion[] animFrames;

        if(state == State.IDLE) animSheet = idle;
        if(state == State.WALK_BACK) animSheet = walkBack;
        if(state == State.WALK_LEFT) animSheet = walkLeft;
        if(state == State.WALK_FRONT) animSheet = walkFront;
        if(state == State.WALK_RIGHT) animSheet = walkRight;

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