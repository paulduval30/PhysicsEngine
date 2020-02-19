package fr.paulduval30.physicsengine.graphics;

import fr.paulduval30.physicsengine.physics.Circle;
import fr.paulduval30.physicsengine.physics.Entity;
import fr.paulduval30.physicsengine.physics.Space;

import javax.swing.*;
import java.awt.*;

public class RenderSpace extends JComponent {
    private Space space;

    public RenderSpace(Space space)
    {
        this.space = space;
        this.setPreferredSize(new Dimension(800,800));
        this.setSize(new Dimension(800,800));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0,0, this.getWidth(), this.getHeight());
        for(Entity entity : space.getEntities())
        {
            g.setColor(entity.getColor());
            if(entity instanceof Circle)
            {
                Circle c = (Circle)entity;
                g.fillOval((int)(c.getPosition().x - c.getRadius()), (int)(c.getPosition().y - c.getRadius()),
                        (int)c.getRadius() * 2, (int)c.getRadius() * 2);
            }
        }
    }
}
