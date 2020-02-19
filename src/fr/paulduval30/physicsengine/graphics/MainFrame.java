package fr.paulduval30.physicsengine.graphics;

import fr.paulduval30.physicsengine.Main;
import fr.paulduval30.physicsengine.physics.Space;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame implements Runnable {
    private Space space;
    private boolean running;

    public MainFrame(Space space)
    {
        this.running = true;
        this.space = space;
        this.setVisible(true);
        this.setSize(800,800);
        this.setLayout(new FlowLayout());
        this.add(new RenderSpace(space));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        MainFrame m = new MainFrame(new Space(1f/60f));
        new Thread(m).start();
    }

    @Override
    public void run() {
        System.out.println("Starting simulation");
        float dt = 1000f / 60f;
        while (running)
        {
            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.space.update(1);
            this.repaint();

        }
        System.out.println("Simulation end ");
    }
}
