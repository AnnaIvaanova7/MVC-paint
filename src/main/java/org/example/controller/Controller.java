package org.example.controller;

import org.example.controller.action.ActionDraw;
import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.fill.Fill;
import org.example.model.fill.NoFill;
import org.example.view.MyFrame;
import org.example.view.MyPanel;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

// TODO: 24.10.2024 Сделать singleton класс
public class Controller {
    private Model model;
    private MyFrame frame;

    public static Controller instance;
    private MyPanel panel;
    private Point2D firstPoint;
    private Point2D secondPoint;

    public static synchronized Controller getInstance(){
        if (instance == null){
            instance = new Controller();
        }
        return instance;
    }
    public Controller() {
        model = new Model();
        MyShape shape = new MyShape(new Rectangle2D.Double());
        Fill fill = new Fill();
        shape.setFb(fill);

        ActionDraw actionDraw = new ActionDraw(model, shape);
        MyPanel panel = new MyPanel(this, actionDraw);
        // TODO: 25.10.2024 Поменять наблюдатель на более современную реализацию
        model.addObserver(panel);

        frame = new MyFrame();
        frame.setPanel(panel);
    }
    public void getPointOne(Point2D p){
        firstPoint = p;
    }
    public void getPointTwo(Point2D p){
        secondPoint = p;
        model.changeShape(firstPoint, secondPoint);
    }

    public void draw(Graphics2D g2) {
        model.draw(g2);
    }
}
