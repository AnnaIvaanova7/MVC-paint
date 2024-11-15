package org.example.controller;

import org.example.controller.action.ActionDraw;
import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.shape.fill.Fill;
import org.example.model.shape.fill.FillBehavior;
import org.example.model.shape.fill.NoFill;
import org.example.view.MyFrame;
import org.example.view.MyPanel;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

// TODO: 24.10.2024 Сделать singleton класс
public class Controller extends MenuState{
    private Model model;
    private MyFrame frame;

    private MenuState menuState;

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

        menuState = new MenuState();
        ShapeCreator shapeCreator = ShapeCreator.getInstance();
        shapeCreator.configure(menuState);

        model = new Model();
        menuState.setActionDraw(new ActionDraw(model));

        panel = new MyPanel(this);

        model.addObserver(panel);

        frame = new MyFrame();
        frame.setPanel(panel);

        MenuController menuController = MenuController.getInstance();
        menuController.setActionDraw(actionDraw);
        menuController.setState(menuState);
        frame.setJMenuBar(menuController.createMenuBar());
    }
    public void getPointOne(Point2D p){
        ActionDraw actionDraw1 = menuState.getActionDraw();
        actionDraw1.mousePressed(p);
    }
    public void getPointTwo(Point2D p){
        ActionDraw actionDraw1 = menuState.getActionDraw();
        actionDraw1.mouseDragged(p);
    }

    public void draw(Graphics2D g2) {
        model.draw(g2);
    }
}

/*model = new Model();
        MyShape sampleShape = new MyShape(new Rectangle2D.Double());
        sampleShape.setFb(new NoFill());
        actionDraw = new ActionDraw(model, sampleShape);
        model.setMyShape(sampleShape);
        panel = new MyPanel(this);

        model.addObserver(panel);

        frame = new MyFrame();
        frame.setPanel(panel);

        MenuController menuController = MenuController.getInstance();
        menuController.setActionDraw(actionDraw);
        frame.setJMenuBar(menuController.createMenuBar());
*/