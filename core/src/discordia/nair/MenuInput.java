package discordia.nair;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Dalud on 19.1.2017.
 */

public class MenuInput implements InputProcessor {

    Texture crosshair;
    Level level;
    int x, y;

    public MenuInput(Level level){
        this.level = level;
        crosshair = new Texture(Gdx.files.internal("UI/crosshair.png"));
    }

    //CROSHAIRIN PROJISOINTI UI-ELEMENTTEIHIN TÄYTYY TEHDÄ
    public void draw(SpriteBatch batch){
        if(x!=0 || y!=0){
            //batch.draw(crosshair, x, -y+Gdx.graphics.getHeight());
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode){
            case Input.Keys.ESCAPE:
                NairMain.reset = true;
                break;
        }
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

        //UI-PROJISOINTIA
        x = screenX;
        y = screenY;

        NairMain.reset = true;
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        x = y = 0;
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        x = screenX;
        y = screenY;
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