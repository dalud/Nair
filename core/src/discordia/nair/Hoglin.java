package discordia.nair;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static discordia.nair.NairMain.scale;

/**
 * Created by jaakk_000 on 22.9.2016.
 */

public class Hoglin {
    private Texture idle;
    private TextureRegion currentFrame;
    int state;
    float stateTime;

    public void Hoglin(){
        idle = new Texture(Gdx.files.internal("Hoglin/Hoglin_front.png"));
        state = 0;
        stateTime = 0;
        currentFrame = new TextureRegion(idle);
    }
    public void draw(SpriteBatch batch) {
        batch.draw(currentFrame, 1280/4/2-29, 720/4/2-29/*(NairMain.resoX/ scale)/2-idle.getWidth()/2 , (NairMain.resoY/scale)/2-idle.getHeight()/2*/);
    }
    public void anim(int state) {
        this.state = state;
        int frame_cols = 4;
        int frame_rows = 1;
        float tick = Gdx.graphics.getDeltaTime();

        Animation anim;
        Texture animSheet = idle;
        TextureRegion[] animFrames;

        if(state==0) animSheet = idle;
        if(state==1) animSheet = new Texture(Gdx.files.internal("Hoglin/Hoglin_walkBack.png"));
        if(state==2) animSheet = new Texture(Gdx.files.internal("Hoglin/Hoglin_walkLeft.png"));
        if(state==3) animSheet = new Texture(Gdx.files.internal("Hoglin/Hoglin_walkFront.png"));
        if(state==4) animSheet = new Texture(Gdx.files.internal("Hoglin/Hoglin_walkRight.png"));

        TextureRegion[][] tmp = TextureRegion.split(animSheet, animSheet.getWidth()/frame_cols, animSheet.getHeight()/frame_rows);
        animFrames = new TextureRegion[frame_cols * frame_rows];
        int index = 0;
        for (int i = 0; i < frame_rows; i++) {
            for (int j = 0; j < frame_cols; j++) {
                animFrames[index++] = tmp[i][j];
            }
        }
        anim = new Animation(0.075f, animFrames);
        stateTime += tick;
        currentFrame = anim.getKeyFrame(stateTime, true);
    }
}