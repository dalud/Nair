package discordia.nair;

import com.badlogic.gdx.InputProcessor;

/**
 * Created by Dalud on 5.10.2016.
 */

public class MyInput implements InputProcessor {

    int width, height, state, finger, initY, dragSens;
    Hoglin player;

    public MyInput(int width, int height, Hoglin player){
        this.width = width;
        this.height = height;
        this.player = player;
        state = finger = 0;
        dragSens = height/7;
    }

    public void Poll(){
        player.move(state);
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
        initY = screenY;

        //UP
        if (screenY < height / 5) state = 1;

        //DIAG UP
        else if(screenY<height/5*2 && screenY>height/5){
            if(screenX<width/2) state = 8;
            else state = 2;
        }

        //LEFT or RIGHT
        else if (screenY < height / 5 * 3 && screenY > height / 5 * 2) {
            if (screenX < width / 2) state = 7;
            else state = 3;
        }

        //DIAG DOWN
        else if(screenY<height/5*4 && screenY>height/5*3){
            if(screenX<width/2) state = 6;
            else state = 4;
        }

        //DOWN
        else if (screenY > height / 5 * 4) state = 5;

        else state = 0;

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        finger--;

        if(finger == 0) state = 0;

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        if((screenY-initY) > dragSens) {
            if((screenY-initY) > dragSens*2) {
            //TURN EVEN DOWNER

                initY = screenY;

                switch (state){
                    case 1:
                        if(screenX < width/2) state = 7;
                        else state = 3;
                        break;
                    case 2:
                        state = 4;
                        break;
                    case 3:case 7:
                        state = 5;
                        break;
                    case 8:
                        state = 6;
                        break;
                    }
                }
                else {
                //TURN DOWN

                initY = screenY;

                switch (state){
                    case 1:
                        if(screenX < width/2) state = 8;
                        else state = 2;
                        break;
                    case 2:
                        state = 3;
                        break;
                    case 3:
                        state = 4;
                        break;
                    case 4:case 6:
                        state = 5;
                        break;
                    case 7:
                        state = 6;
                        break;
                    case 8:
                        state = 7;
                        break;
                }
            }
        }
        else if((initY-screenY) > dragSens) {
            if((initY-screenY) > dragSens*2) {
                //TURN EVEN UPPER

                initY = screenY;

                switch (state){
                    case 3:case 7:
                        state = 1;
                        break;
                    case 4:
                        state = 2;
                        break;
                    case 5:
                        if(screenX < width/2) state = 7;
                        else state = 3;
                        break;
                    case 6:
                        state = 8;
                        break;
                }
            }
            else {
                //TURN UP

                initY = screenY;

                switch (state){
                    case 2:case 8:
                        state = 1;
                        break;
                    case 3:
                        state = 2;
                        break;
                    case 4:
                        state = 3;
                        break;
                    case 5:
                        if(screenX < width/2) state = 6;
                        else state = 4;
                        break;
                    case 6:
                        state = 7;
                        break;
                    case 7:
                        state = 8;
                        break;
                }
            }
        }
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