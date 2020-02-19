package fr.paulduval30.physicsengine.physics.utils;

public class Vec2d {
    public float x;
    public float y;

    public Vec2d(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getLengthSquared()
    {
        return this.x * this.x + this.y * this.y;
    }

    public float getLength()
    {
        return (float) Math.sqrt(x * x + y * y);
    }

    @Override
    public String toString() {
        return "[x : " + this.x + ", y : " + this.y + "]";
    }
}
