package discordia.nair;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Dalud on 5.10.2016.
 */

public class MyInput implements InputProcessor {

    int width, height, state, finger;
    Hoglin player;
    Vector2 direction;

    public MyInput(int width, int height, Hoglin player){
        this.width = width;
        this.height = height;
        this.player = player;
        state = finger = 0;
        direction = new Vector2(0, 0);
    }

    public void Poll(){
        player.move(direction);
    }


    public Vector2 getDirection(int x, int y){

        direction.x = x*16/Gdx.graphics.getWidth() - width/2 +.5f;
        direction.y = -y*9/Gdx.graphics.getHeight() + height/2;

        return direction;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
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