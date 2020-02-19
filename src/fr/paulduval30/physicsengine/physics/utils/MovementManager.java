package fr.paulduval30.physicsengine.physics.utils;

import fr.paulduval30.physicsengine.physics.Circle;
import fr.paulduval30.physicsengine.physics.Entity;
import fr.paulduval30.physicsengine.physics.Manifold;

public class MovementManager {

    public static boolean entityCollideEntity(Entity a, Entity b)
    {
        return !(a.getMax().x < b.getMin().x || a.getMax().x > b.getMax().x) ||
                !(a.getMax().y < b.getMin().y || a.getMin().y > b.getMin().y);
    }

    public static float distance(Vec2d a, Vec2d b)
    {
        return (float) Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }

    public static boolean circleColideCircle(Circle a, Circle b)
    {
        float r = a.getRadius() + b.getRadius();
        return r > distance(a.getPosition(), b.getPosition());
    }

    public static Vec2d getMoovementVector(Vec2d endpoint, Vec2d startpoint)
    {
        return MovementManager.vec2dMinusVec2d(endpoint, startpoint);
    }

    public static Vec2d vec2dMinusVec2d(Vec2d a, Vec2d b)
    {
        return new Vec2d(a.x - b.x, a.y -b.y);
    }

    public static Vec2d vec2dMultiplyVec2d(Vec2d a, Vec2d b)
    {
        return new Vec2d(a.x * b.x, a.y * b.y);
    }

    public static Vec2d vec2dDivideVec2D(Vec2d a, Vec2d b)
    {
        return new Vec2d(a.x / b.x, a.y / b.y);
    }

    public static float getRestitutionCoef(Entity a, Entity b)
    {
        return Math.min(a.getElasticity(), b.getElasticity());
    }

    public static float dotProduct(Vec2d a, Vec2d b)
    {
        return a.x * b.x + a.y * b.y;
    }

    public static Vec2d perpendicularClockwise(Vec2d a)
    {
        return new Vec2d(a.y, -a.x);
    }

    public static Vec2d perpendicularCounterClockwise(Vec2d a)
    {
        return new Vec2d(-a.y, a.x);
    }

    public static void resolveCircleVsCircleCollision(Circle a, Circle b)
    {
        Vec2d rv = vec2dMinusVec2d(a.getVelocity(), b.getVelocity());
        Manifold  m = new Manifold(a, b);
        Vec2d normal = m.getNormal();
        float velAlongNormal = dotProduct(rv, normal);

        if(velAlongNormal > 0)
            return;
        float e = getRestitutionCoef(a, b);

        float j = -(1 + e) * velAlongNormal;
        j = j / (1 / a.getMass() + 1 / b.getMass());

        Vec2d impulse = vec2dMultiplyByFloat(j , normal);

        a.setVelocity(vec2dMinusVec2d(a.getVelocity(), vec2dMultiplyByFloat( a.getInvMass(), impulse)));
        b.setVelocity(vec2dPlusVec2d(b.getVelocity(), vec2dMultiplyByFloat( 1 / b.getMass(), impulse)));
    }

    public static void PositionalCorrection(Entity a, Entity b, Manifold m)
    {
        float percent = 0.8f;
        float slop = 0.1f;
        Vec2d correction =MovementManager.vec2dMultiplyByFloat(Math.max(m.penetration - slop, 0.0f) / (a.getInvMass() + b.getInvMass()) * percent, m.normal);
        a.setMin(MovementManager.vec2dMinusVec2d(a.getMin(), MovementManager.vec2dMultiplyByFloat(a.getInvMass(), correction)));
        a.setMax(MovementManager.vec2dMinusVec2d(a.getMax(), MovementManager.vec2dMultiplyByFloat(a.getInvMass(), correction)));
        b.setMin(MovementManager.vec2dPlusVec2d(b.getMin(), MovementManager.vec2dMultiplyByFloat(b.getInvMass(), correction)));
        b.setMax(MovementManager.vec2dPlusVec2d(b.getMax(), MovementManager.vec2dMultiplyByFloat(b.getInvMass(), correction)));
    }

    public static Vec2d vecMinusFloat(float f, Vec2d v)
    {
        return new Vec2d(v.x - f, v.y - f);
    }

    public static Vec2d vec2dPlusVec2d(Vec2d a, Vec2d b) {
        return new Vec2d(a.x + b.x, a.y + b.y);
    }

    public static Vec2d vec2dMultiplyByFloat(float f, Vec2d vec) {
        return new Vec2d(vec.x * f, vec.y * f);
    }

}
