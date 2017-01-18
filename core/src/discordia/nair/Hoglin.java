package discordia.nair;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Dalud on 15.1.2017.
 */

public class Hoglin extends Creature {

    public Hoglin(){
        posX = 1450;
        posY = 475;
        idleAnimSpeed = .4f;
        walk = .5f;
        run = .3f;
        dimensions = new int[] {16, 20, 1};

        idle = new Texture("Hoglin/Hoglin_idle.png");
        walkBack = new Texture("Hoglin/Hoglin_walkBack.png");
        walkFront = new Texture("Hoglin/Hoglin_walkFront.png");
        walkLeft = new Texture("Hoglin/Hoglin_walkLeft.png");
        walkRight = new Texture("Hoglin/Hoglin_walkRight.png");

    }
}