import java.awt.*;

import java.util.ArrayList;

public class Collision {
    public ArrayList<Sprite> collSprites;

    public Collision() {
        this.collSprites = new ArrayList<>();
    }

    public void checkIfThereHasBeenACollisionWasThereOrNot_AlsoIsThereAnotherBooty_HEYsomePeepsAREjustINTObuttSTUFFyoo() {
        Rectangle rec1;
        Rectangle rec2;
        int impactValue1;
        int impactValue2;
        int x1, x2, y1, y2;

        Sprite sp1, sp2;


        for (int i = 0; i < collSprites.size(); i++) {
            sp1 = collSprites.get(i);
            x1 = sp1.getX();
            y1 = sp1.getY();

            rec1 = sp1.getRecBound();

            for (int j = i + 1; j < collSprites.size(); j++) {
                sp2 = collSprites.get(j);
                x2 = sp2.getX();
                y2 = sp2.getY();
                rec2 = sp2.getRecBound();
                //if objects intersect and they're off screen
                if (rec1.intersects(rec2) && rec1.x > -50 && rec2.x > -50) {


                    impactValue1 = collSprites.get(i).getImpactValue();
                    impactValue2 = collSprites.get(j).getImpactValue();


                    collSprites.get(i).setColImpactValue(impactValue2);
                    collSprites.get(j).setColImpactValue(impactValue1);


                    collSprites.get(j).isItInYet();
                    collSprites.get(i).isItInYet();
                }


            }

        }

    }

    public void addSprite(Sprite sprite1) {
        collSprites.add(sprite1);
    }

}
