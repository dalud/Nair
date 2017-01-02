package discordia.nair;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

/**
 * Created by Dalud on 5.10.2016.
 */

public class MyInput implements InputProcessor {

    int width, height;
    Hoglin player;

    public MyInput(int width, int height, Hoglin player){
        this.width = width;
        this.height = height;
        this.player = player;
    }

    public void Poll(){
        if(Gdx.input.isTouched()) {

            //UP
            if (Gdx.input.getY() < height / 5) player.move(1);

            //DIAG UP
            else if(Gdx.input.getY()<height/5*2 && Gdx.input.getY()>height/5){
                if(Gdx.input.getX()<width/2) player.move(8);
                else player.move(2);
            }

            //LEFT or RIGHT
            else if (Gdx.input.getY() < height / 5 * 3 && Gdx.input.getY() > height / 5 * 2) {
                if (Gdx.input.getX() < width / 2) player.move(7);
                else player.move(3);
            }

            //DIAG DOWN
            else if(Gdx.input.getY()<height/5*4 && Gdx.input.getY()>height/5*3){
                if(Gdx.input.getX()<width/2) player.move(6);
                else player.move(4);
            }

            //DOWN
            else if (Gdx.input.getY() > height / 5 * 4) player.move(5);

        }else player.move(0);
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
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
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