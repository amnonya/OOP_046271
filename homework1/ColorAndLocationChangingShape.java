package homework1;

import java.awt.*;
import java.util.Random;

/**
 * A ColorAndLocationChaningShape is a Shape that can change its location and
 * color using its step() method. Thus, a typical LocationChaningShape consists
 * of the following set of properties: {location, color, shape, size, velocity}
 */
public abstract class ColorAndLocationChangingShape extends LocationChangingShape {
    private Random random;

    // Abstraction Function:
    // Represents a 2D geometric shape, that changes its location using this.step().
    // its bounding rectangle's top left corner is located at this.location,
    // and its color is this.color.
    // The size of the geometric shape is dependent on the type of the shape (e.g.
    // triangle, rectangle, oval) and is set via this.setSize(Dimension).
    // its velocity is randomized upon creation
    // but can be modified with this.setVelocity(int velocityX, int velocityY)
    // each time this shape changes direction it changes its color to a random
    // color.

    // Representation Invariant:
    // this class doesnt have any special fields therefore doesnt require CheckRep.

    /**
     * @effects Initializes this with a a given location and color. Each of the
     *          horizontal and vertical velocities of the new object is set to a
     *          random integral value i such that -5 <= i <= 5 and i != 0
     */
    public ColorAndLocationChangingShape(Point location, Color color) {
        super(location, color);
        random = new Random();
    }

    /**
     * @return a random color
     */
    private Color getRandomColor() {
        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();
        Color randomColor = new Color(r, g, b);
        return randomColor;
    }

    /**
     * @modifies this
     * @effects Changes the location of this as described in the specification of
     *          LocationChangingShape.step(Rectangle bound) && if the velocity of
     *          this needs to be changed (as described in
     *          LocationChangingShape.step(Rectangle bound)), changes the color of
     *          this to a new random color; else, does not change the color of this.
     */
    public void step(Rectangle bound) {
        int velocityXOrig = this.getVelocityX();
        int velocityYOrig = this.getVelocityY();
        super.step(bound);
        if (this.getVelocityY() != velocityYOrig || this.getVelocityX() != velocityXOrig) {
            this.setColor(getRandomColor());
        }
    }
}
