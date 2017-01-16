package discordia.nair;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Dalud on 5.10.2016.
 */

public class MyInput implements InputProcessor {

    int width, height, state, finger;
    Creature player;
    Vector2 direction;
    float fixedSpeedX, fixedSpeedY;

    public MyInput(int width, int height, Creature player){
        this.width = width;
        this.height = height;
        this.player = player;
        state = finger = 0;
        direction = new Vector2(0, 0);
        fixedSpeedX = 5;
        fixedSpeedY = 3;
    }

    public void poll(Level level){
        player.move(level.collide(direction, player.posX, player.posY, player.dimensions));
    }


    public Vector2 getDirection(int x, int y){

        //LUODAAN VEKTORI, JONKA X-MAX = 8 ja Y-MAX = 4 (16:9)
        direction.x = (x - width/2)*8 / (width/2);
        direction.y = -(y - height/2)*5 / (height/2);

        return direction;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode){
            case Input.Keys.UP:
                direction.y = fixedSpeedY;
                break;
            case Input.Keys.RIGHT:
                direction.x = fixedSpeedX;
                break;
            case Input.Keys.DOWN:
                direction.y = -fixedSpeedY;
                break;
            case Input.Keys.LEFT:
                direction.x = -fixedSpeedX;
                break;
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode){
            case Input.Keys.UP:
                direction.y = 0;
                break;
            case Input.Keys.RIGHT:
                direction.x = 0;
                break;
            case Input.Keys.DOWN:
                direction.y = 0;
                break;
            case Input.Keys.LEFT:
                direction.x = 0;
                break;
        }

        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        finger++;

        getDirection(screenX, screenY);

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        finger--;

        if(finger == 0) {
            direction.x = 0;
            direction.y = 0;
        }

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        getDirection(screenX, screenY);

        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}