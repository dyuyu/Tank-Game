import java.awt.*;
import java.util.Observable;

public class PowerUp extends Sprite {


    public PowerUp(int x, int y) {
        super.setImg(TankGame.puIm);
        super.x = x;
        super.y = y;
        this.rectum = new Rectangle(super.x, super.y, super.img.getWidth(), super.img.getHeight());
        super.setImpactValue(2);
        TankGame.aColl.addSprite(this);
    }

    public static void powerUpMaker(int x, int y) {
        PowerUp pu1 = new PowerUp(x, y);
        PanelAndObserver.addToObserver(pu1);
    }

    @Override
    public void update(Observable o, Object arg) {

        if (collisionTruth && super.colImpactValue == 10) {
            setX(-100);
            setY(-100);
            this.rectum.setLocation(-100, -100);
        }


        super.repaint();
        collisionTruth = false;
    }

}
