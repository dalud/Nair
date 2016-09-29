package discordia.nair;

import com.badlogic.gdx.Gdx;
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
    float stateTime;

    public Hoglin(){
        state = 0;
        stateTime = 0;
    }
    public void draw(SpriteBatch batch) {
        batch.draw(currentFrame, NairMain.resoX/NairMain.scale/2-currentFrame.getRegionWidth()/2, NairMain.resoY/NairMain.scale/2-currentFrame.getRegionHeight()/2);
    }
    public void move(){
        if(Gdx.input.isTouched()) {
            if(Gdx.input.getX()<365) this.anim(2);
            else if(Gdx.input.getX()>365 && Gdx.input.getX()<914){
                if(Gdx.input.getY()<360) this.anim(1);
                else this.anim(3);
            }
            else if(Gdx.input.getX()>914) this.anim(4);

        }
        else this.anim(0);
    }
    public void anim(int state) {
        this.state = state;
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
        anim = new Animation(0.2f, animFrames);
        stateTime += tick;
        currentFrame = anim.getKeyFrame(stateTime, true);
    }
}