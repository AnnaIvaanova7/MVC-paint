package org.example.controller;

import org.example.controller.action.ActionDraw;
import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.shape.fill.Fill;
import org.example.model.shape.fill.FillBehavior;
import org.example.view.MyFrame;
import org.example.view.MyPanel;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

// TODO: 24.10.2024 Сделать singleton класс
public class Controller {
    private Model model;
    private MyFrame frame;

    private MyShape sampleShape;

    public static Controller instance;
    private MyPanel panel;
    private Point2D firstPoint;
    private Point2D secondPoint;

    private ActionDraw actionDraw;

    public static synchronized Controller getInstance(){
        if (instance == null){
            instance = new Controller();
        }
        return instance;
    }
    public Controller() {
        model = new Model();
        MyShape sampleShape = new MyShape(new Rectangle2D.Double());
        FillBehavior fill = new Fill();
        fill.setColor(Color.BLUE);
        sampleShape.setFb(fill);

        actionDraw = new ActionDraw(model, sampleShape);
        model.setMyShape(sampleShape);
        panel = new MyPanel(this, actionDraw);
        // TODO: 25.10.2024 Поменять наблюдатель на более современную реализацию
        model.addObserver(panel);

        frame = new MyFrame();
        frame.setPanel(panel);
    }
    public void getPointOne(Point2D p){
        actionDraw.createShape(p);
    }
    public void getPointTwo(Point2D p){
        actionDraw.stretchShape(p);
    }

    public void draw(Graphics2D g2) {
        model.draw(g2);
    }
}
