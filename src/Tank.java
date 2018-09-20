import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;

public class Tank extends Sprite {
    //	private boolean forwardTruth = false;
//	private boolean backwardTruth = false;
    //	private int impactValue = 10;
    private int spawnX;
    private int spawnY;

    public int getSpawnX() {
        return spawnX;
    }

    public void setSpawnX(int spawnX) {
        this.spawnX = spawnX;
    }

    public int getSpawnY() {
        return spawnY;
    }

    public void setSpawnY(int spawnY) {
        this.spawnY = spawnY;
    }

    //	public boolean collisionTruth = false;
    public Rectangle rectum;
    //    Bullet blt1 = new Bullet();
    public ArrayList<Bullet> bullArray;
    private int livesCount = 3;
    HealthBar hb1 = new HealthBar();
    LiveOvals ov1 = new LiveOvals();
    LiveOvals ov2 = new LiveOvals();
    LiveOvals ov3 = new LiveOvals();

    private int index = 0;
    private boolean UpPressed;
    private boolean DownPressed;
    private boolean RightPressed;
    private boolean LeftPressed;
    private boolean TriggerPressed;

    public TankImgClass ti1;

    public Tank(int x, int y, int vx, int vy, int angle, String filePath) {
        super(x, y, vx, vy, angle, filePath);
        super.img = TankGame.inIm;
        super.r = 4;
        super.setImpactValue(10);
        this.rectum = new Rectangle(super.x, super.y, TankGame.tankIm1.getWidth(), TankGame.tankIm1.getHeight());
        ti1 = new TankImgClass();
        ti1.x = x;
        ti1.y = y;
        TankGame.aColl.addSprite(this);
        loadBulletArrayList();


    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void toggleUpPressed() {
        this.UpPressed = true;
    }

    public void toggleDownPressed() {

        this.DownPressed = true;
    }

    public void toggleRightPressed() {

        this.RightPressed = true;
    }

    public void toggleLeftPressed() {

        this.LeftPressed = true;
    }

    public void toggleTriggerPressed() {

        this.TriggerPressed = true;
    }

    public void unToggleUpPressed() {

        this.UpPressed = false;
    }

    public void unToggleDownPressed() {

        this.DownPressed = false;
    }

    public void unToggleRightPressed() {

        this.RightPressed = false;
    }

    public void unToggleLeftPressed() {

        this.LeftPressed = false;
    }

    public void unToggleTriggerPressed() {

        this.TriggerPressed = false;
    }

    public void setHealthBarLocation(int x, int y) {
        hb1.setHealthX(x);
        hb1.setHealthY(y);
        ov1.setOvalX(x);
        ov1.setOvalY(y + 35);
        ov2.setOvalX(x + 35);
        ov2.setOvalY(y + 35);
        ov3.setOvalX(x + 70);
        ov3.setOvalY(y + 35);
    }

    @Override
    public void update(Observable o, Object o1) { //check the keys values


        if (this.UpPressed) {
            this.moveForwards();
        }
        if (this.DownPressed) {
            this.moveBackwards();
        }
        if (this.LeftPressed) {
            this.rotateLeft();
        }
        if (this.RightPressed) {
            this.rotateRight();
        }
        if (this.TriggerPressed && (TankGame.loopCount % TankGame.fireRate == 0)) {
            this.shootBullshit();
        }

        this.rectum.setLocation(super.x, super.y);
        this.ti1.x = super.x;
        this.ti1.y = super.y;
        this.ti1.angle = super.angle;

        this.repaint();


        if (!collisionTruth) {
            this.pre_x = x;
            this.pre_y = y;
            this.pre_vx = vx;
            this.pre_vy = vy;
            this.pre_angle = angle;
        } else {
            collisionTruth = false;
        }

        this.rectum.setLocation(x, y);

    }

    private void shootBullshit() {
        this.bullArray.get(index).x = this.x + 15 + (int) Math.round(50 * Math.cos(Math.toRadians(this.angle)));
        this.bullArray.get(index).y = this.y + 10 + (int) Math.round(50 * Math.sin(Math.toRadians(this.angle)));

        this.bullArray.get(index).angle = this.angle;

        this.bullArray.get(index).vx = (int) Math.round(6 * Math.cos(Math.toRadians(this.angle)));
        this.bullArray.get(index).vy = (int) Math.round(6 * Math.sin(Math.toRadians(this.angle)));

        index = (index + 1) % 30;
    }

    // do not do shit on the angles.... do not worry about overflown either
    private void rotateLeft() {

        if (collisionTruth) {
            this.angle = pre_angle;

        } else {
            this.angle -= 1;
        }

    }

    private void rotateRight() {
        if (collisionTruth) {
            this.angle = pre_angle;

        } else {
            this.angle += 1;
        }
    }

    private void moveBackwards() {

        vx = (int) Math.round(r * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(r * Math.sin(Math.toRadians(angle)));

        if (collisionTruth) {
            x = pre_x + super.colImpactValue * vx;
            y = pre_y + super.colImpactValue * vy;

        } else {
            x -= vx;
            y -= vy;
        }
        checkBorder();
    }

    private void moveForwards() {

        vx = (int) Math.round(r * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(r * Math.sin(Math.toRadians(angle)));

        if (collisionTruth) {
            x = pre_x - super.colImpactValue * vx;
            y = pre_y - super.colImpactValue * vy;

        } else {
            x += vx;
            y += vy;
        }
        checkBorder();
    }

    private void loadBulletArrayList() {
        this.bullArray = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            Bullet bullet = new Bullet();

            bullet.setImg(TankGame.bulletIm);
            bullet.setX(-100);
            bullet.setY(-100);
            bullet.rectum.setLocation(-100, -100);

            this.bullArray.add(bullet);
            PanelAndObserver.addToObserver(this.bullArray.get(i));
        }


    }

    public Rectangle getRecBound() {
        return this.rectum;
    }

    @Override
    public void isItInYet() {
        this.collisionTruth = true;

        if (this.UpPressed) {
            this.moveForwards();
        }
        if (this.DownPressed) {
            this.moveBackwards();
        }

        if (colImpactValue == 1) {
            hb1.setHealthFill(hb1.getHealthFill() - 10);
        }

        if (colImpactValue == 2) {
            hb1.setHealthFill(100);
        }

        if (hb1.getHealthFill() == 0) {
            livesCount--;
            if (livesCount == 2) {
                ov3.setOvalFill(0);
                hb1.setHealthFill(100);
                setX(spawnX);
                setY(spawnY);
            }
            if (livesCount == 1) {
                ov2.setOvalFill(0);
                hb1.setHealthFill(100);
                setX(spawnX);
                setY(spawnY);
            }
            if (livesCount == 0) {
                ov1.setOvalFill(0);
                this.ti1.setImg(TankGame.inIm);
                PanelAndObserver.observer1.deleteObserver(this);
            }
        }
    }


    public void setSpawn(int x, int y) {
        setSpawnX(x);
        setSpawnY(y);
    }


    public int getTankCenterX() {

        return x + TankGame.tankIm1.getWidth(null) / 60 / 2;
    }

    public int getTankCenterY() {
        return y + TankGame.tankIm1.getHeight(null) / 2;
    }

    //BORDERS OF THE GAME
    private void checkBorder() {
        if (x < 640) {
            x = 640;
        }
        if (x >= 1805) {
            x = 1805;
        }
        if (y < 800) {
            y = 800;
        }
        if (y >= 1655) {
            y = 1655;
        }
    }


}
