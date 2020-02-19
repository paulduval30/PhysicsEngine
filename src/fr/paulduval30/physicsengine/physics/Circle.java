package fr.paulduval30.physicsengine.physics;

import fr.paulduval30.physicsengine.physics.utils.Vec2d;

import java.awt.*;

public class Circle extends Entity {
    private float radius;

    public Circle(float radius, Vec2d position) {
        super(position, new Vec2d(position.x, position.y + radius));
        this.radius = radius;
    }

    public Circle(Vec2d min, float elasticity, float mass, Vec2d velocity, float radius, Color color) {
        super(min, min, elasticity, mass, velocity, color);
        this.radius = radius;
    }

    public float getRadius() {
        return this.radius;
    }

    public Vec2d getPosition() {
        return this.getMin();
    }
}
