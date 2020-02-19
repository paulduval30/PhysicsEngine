package fr.paulduval30.physicsengine.physics;

import fr.paulduval30.physicsengine.physics.utils.MovementManager;
import fr.paulduval30.physicsengine.physics.utils.Vec2d;

import java.awt.*;
import java.util.ArrayList;

public class Space {

    private ArrayList<Entity> entities;
    private float dt;

    public Space(float dt)
    {
        this.entities = new ArrayList<>();
        this.entities.add(new Circle(new Vec2d(0,400), 0.1f, 50, new Vec2d(1.1f,0),50, Color.RED));
        this.entities.add(new Circle(new Vec2d(800,400),0.1f, 50, new Vec2d(-1.3f,0),50, Color.BLUE));
        this.dt = dt;
    }
    public ArrayList<Entity> getEntities() {
        return this.entities;
    }

    public void update(float dt)
    {
        for(Entity e : entities)
        {
            for(Entity f : entities)
            {
                if(f != e)
                {
                    if(e instanceof Circle && f instanceof Circle)
                    if(MovementManager.circleColideCircle((Circle)e,(Circle)f))
                    {
                        MovementManager.resolveCircleVsCircleCollision((Circle) e, (Circle) f);
                    }
                }
                f.update(dt);
            }
        }
    }
}
