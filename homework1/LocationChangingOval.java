package homework1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class LocationChangingOval extends LocationChangingShape {
    private Dimension dimension;

    // Abstraction Function:
    // Represents a 2D oval, changes its location using this.step().
    // where its bounding rectangle's top left
    // corner is located at this.location, and its color is this.color.
    // The size of the geometric shape is set via this.setSize(Dimension).
    // its velocity is randomized upon creation
    // but can be modified with this.setVelocity(int velocityX, int velocityY)
    
    // Representation Invariant:
    // this class doesnt have any special fields therefore doesnt require CheckRep.
    
    /**
     * @effects Initializes this with a a given location and color. Each of the
     *          horizontal and vertical velocities of the new object is set to a
     *          random integral value i such that -5 <= i <= 5 and i != 0
     */
    LocationChangingOval(Point location, Color color) {
        super(location, color);
    }

    /**
     * @modifies this
     * @effects Resizes this so that its bounding rectangle has the specified
     *          dimension. If this cannot be resized to the specified dimension =>
     *          this is not modified, throws ImpossibleSizeException (the exception
     *          suggests an alternative dimension that is supported by this).
     */
    @Override
    public void setSize(Dimension dimension) throws ImpossibleSizeException {
        this.dimension = dimension;

    }

    /**
     * @return the bounding rectangle of this.
     */
    @Override
    public Rectangle getBounds() {
        Rectangle r = new Rectangle(dimension);
        return r;
    }

    /**
     * @modifies g
     * @effects Draws this onto g.
     */
    @Override
    public void draw(Graphics g) {
        int x = this.getLocation().x;
        int y = this.getLocation().y;
        g.setColor(this.getColor());
        g.fillOval(x, y, dimension.width, dimension.height);
    }

    /**
     * @return A point object representing the center of the shape
     */
    protected Point getMiddle() {
        int middleX = this.getLocation().x + this.dimension.width / 2;
        int middleY = this.getLocation().y + this.dimension.height / 2;
        return new Point(middleX, middleY);
    }

}
