package fr.paulduval30.physicsengine.physics;

import fr.paulduval30.physicsengine.physics.utils.Vec2d;

public class Rectangle extends Entity {
    public Rectangle(Vec2d min, Vec2d max) {
        super(min, max);
    }

    public Rectangle(Vec2d min, Vec2d max, float elasticity) {
        super(min, max, elasticity);
    }

    public Rectangle(Vec2d min, Vec2d max, float elasticity, float mass) {
        super(min, max, elasticity, mass);
    }
}
