package fr.paulduval30.physicsengine.physics;

import fr.paulduval30.physicsengine.physics.utils.MovementManager;
import fr.paulduval30.physicsengine.physics.utils.Vec2d;

import java.awt.*;

public class Entity {
    private Color color;
    private Vec2d min;
    private Vec2d max;
    private Vec2d velocity;
    private float elasticity;
    private float mass;
    private float invMass;

    public Entity(Vec2d min, Vec2d max) {
        this.max = max;
        this.min = min;
        this.velocity = new Vec2d(0,0);
        this.elasticity = 0;
        this.mass = 0;
        this.invMass = 0;
    }

    public Entity(Vec2d min, Vec2d max, float elasticity) {
        this.min = min;
        this.max = max;
        this.elasticity = elasticity;
        this.velocity = new Vec2d(0,0);
        this.mass = 0;
        this.invMass = 0;
    }

    public Entity(Vec2d min, Vec2d max, float elasticity, float mass) {
        this.min = min;
        this.max = max;
        this.elasticity = elasticity;
        this.velocity = new Vec2d(0,0);
        this.mass = mass;
        this.invMass = mass == 0 ? 0 : 1 / mass;
    }

    public Entity(Vec2d min, Vec2d max, float elasticity, float mass, Vec2d velocity) {
        this.min = min;
        this.max = max;
        this.elasticity = elasticity;
        this.velocity = velocity;
        this.mass = mass;
        this.invMass = mass == 0 ? 0 : 1 / mass;

    }

    public Entity(Vec2d min, Vec2d max, float elasticity, float mass, Vec2d velocity, Color color) {
        this.min = min;
        this.max = max;
        this.elasticity = elasticity;
        this.velocity = velocity;
        this.mass = mass;
        this.invMass = mass == 0 ? 0 : 1 / mass;
        this.color = color;
    }

    public void translate()
    {
        this.min = MovementManager.vec2dPlusVec2d(min, velocity);
        this.max = MovementManager.vec2dPlusVec2d(max, velocity);
    }

    public Vec2d getMax() {
        return max;
    }

    public Vec2d getMin() {
        return min;
    }

    public void setMin(Vec2d min) {
        this.min = min;
    }

    public void setMax(Vec2d max) {
        this.max = max;
    }

    public Vec2d getVelocity() {
        return velocity;
    }

    public void setVelocity(Vec2d velocity) {
        this.velocity = velocity;
    }

    public float getElasticity() {
        return elasticity;
    }

    public void setElasticity(float elasticity) {
        this.elasticity = elasticity;
    }

    public float getMass() {
        return this.mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
        this.invMass = mass == 0 ? 0 : 1 / mass;
    }

    public void update(float dt)
    {
        this.min = MovementManager.vec2dPlusVec2d(this.min, MovementManager.vec2dMultiplyByFloat(dt, this.velocity));
        this.max = MovementManager.vec2dPlusVec2d(this.max, MovementManager.vec2dMultiplyByFloat(dt, this.velocity));
    }

    public float getInvMass() {
        return this.invMass;
    }

    public Color getColor() {
        return this.color;
    }
}
