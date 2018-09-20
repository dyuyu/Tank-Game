import java.awt.*;
import java.util.Observable;

public class Bullet extends Sprite
{
    public Rectangle rectum;
    Explosion boomshakalaka = new Explosion();
    private int exploStart;
    private int exploEnd;
    private int exploTime = 10;
    private boolean showDragonBallls = false;

    public Bullet()
    {
        super.setImg(TankGame.bulletIm);
        this.rectum = new Rectangle(super.x, super.y, super.img.getWidth(), super.img.getHeight());
        super.x = -100;
        super.y = -100;
        super.setImpactValue(1);
        TankGame.aColl.addSprite(this);
        loadExplosion();
        PanelAndObserver.addToObserver(boomshakalaka);

    }

    @Override
    public void update(Observable o, Object arg)
    {
        super.x += super.vx;
        super.y += super.vy;

        this.rectum.setLocation(super.x, super.y);

        System.out.println(super.collisionTruth);

        kablooweeUpdateToShowPoopies();


    }

    private void kablooweeUpdateToShowPoopies() {
        if(this.showDragonBallls){
            this.exploStart++;
        }
        if(this.exploStart == this.exploEnd){
            this.showDragonBallls = false;
            boomshakalaka.x = -100;
            boomshakalaka.y = -100;
        }
    }

    public Rectangle getRecBound()
    {
        return this.rectum;
    }

    @Override
    public void isItInYet()
    {
        this.collisionTruth = true;
        this.showDragonBallls = true;
        showThoseDB();
        this.exploStart = TankGame.loopCount;
        this.exploEnd = this.exploStart + this.exploTime;
        this.x = -100;
        this.y = -100;
        this.vx = 0;
        this.vy = 0;
    }

    private void loadExplosion()
    {
        boomshakalaka.setX(-100);
        boomshakalaka.setY(-100);
        boomshakalaka.setImg(TankGame.explosionIm);
        TankGame.nonColl.add(boomshakalaka);
    }

    private void showThoseDB()
    {
        boomshakalaka.x = this.x;
        boomshakalaka.y = this.y;
    }

}
