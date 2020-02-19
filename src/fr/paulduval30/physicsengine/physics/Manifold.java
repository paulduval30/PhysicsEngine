package fr.paulduval30.physicsengine.physics;

import fr.paulduval30.physicsengine.physics.utils.MovementManager;
import fr.paulduval30.physicsengine.physics.utils.Vec2d;
import org.w3c.dom.css.Rect;

public class Manifold {
    private Entity a;
    private Entity b;
    public Vec2d normal;
    public float penetration;


    public Manifold(Circle a, Circle b)
    {
        this.a = a;
        this.b = b;

        Vec2d n = MovementManager.vec2dMinusVec2d(b.getPosition(), a.getPosition());

        float r = a.getRadius() + b.getRadius();

        if(MovementManager.circleColideCircle(a, b))
        {
            float d = n.getLength();
            if(d != 0)
            {
                float distanceX = a.getPosition().x - b.getPosition().x;
                float distanceY = a.getPosition().y - b.getPosition().y;
                float unitX = distanceX / d;
                float unitY = distanceY / d;
                this.penetration = r - d;
                this.normal = new Vec2d(unitX, unitY);
                System.out.println(normal);
            }
            else
            {
                this.penetration = a.getRadius();
                this.normal = new Vec2d(1, 0);
            }
        }
        else
        {
            this.normal = new Vec2d(0,0);
        }
    }

    public Vec2d getNormal() {
        return this.normal;
    }
}
