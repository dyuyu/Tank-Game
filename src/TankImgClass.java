import java.util.Observable;

public class TankImgClass extends Sprite {


    public TankImgClass() {

        super.setImg(TankGame.tankIm1);
        super.setImpactValue(1);
        TankGame.nonColl.add(this);
    }

    @Override
    public void update(Observable o, Object arg) {

    }


}

















