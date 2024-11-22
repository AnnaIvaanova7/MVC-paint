package org.example.controller;

import org.example.controller.action.ActionDraw;
import org.example.controller.action.ActionMove;
import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.shape.factory.ShapeType;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class MenuController extends MenuState{
    private static MenuController instance;
    private JMenuBar menuBar;
    private ActionDraw actionDraw;

    private Model model;

    private MenuState state;
    private MenuController(){
        menuBar = createMenuBar();
    }
    public static MenuController getInstance(){
        if (instance == null){
            instance = new MenuController();
        }
        return instance;
    }
    public JMenuBar createMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        JMenu shapeMenu = createShapeMenu();
        JMenu colorMenu = createColorMenu();
        JMenu actionMenu = createActionMenu();
        JMenu fillMenu = createFillMenu();
        menuBar.add(shapeMenu);
        menuBar.add(colorMenu);
        menuBar.add(actionMenu);
        menuBar.add(fillMenu);

        return menuBar;
    }


    private JMenu createShapeMenu() {

        JMenu shapeMenu = new JMenu("Фигура");
        ButtonGroup group = new ButtonGroup();

        JRadioButtonMenuItem square = new JRadioButtonMenuItem("Прямоугольник");
        square.addActionListener(e -> {
            state.setShapeType(ShapeType.RECTANGLE);
        });
        shapeMenu.add(square);
        group.add(square);
        JRadioButtonMenuItem ellipse = new JRadioButtonMenuItem("Эллипс");
        ellipse.addActionListener(e -> {
            state.setShapeType(ShapeType.ELLIPSE);
        });
        shapeMenu.add(ellipse);
        group.add(ellipse);
        return shapeMenu;
    }

    private JMenu createColorMenu() {
        JMenu colorMenu = new JMenu("Цвет");
        ButtonGroup group = new ButtonGroup();

        JRadioButtonMenuItem blue = new JRadioButtonMenuItem("Синий");
        JRadioButtonMenuItem red = new JRadioButtonMenuItem("Красный");
        JRadioButtonMenuItem orange = new JRadioButtonMenuItem("Оранжевый");
        JRadioButtonMenuItem green = new JRadioButtonMenuItem("Зелёный");
        JRadioButtonMenuItem cyan = new JRadioButtonMenuItem("Бирюзовый");
        JRadioButtonMenuItem black = new JRadioButtonMenuItem("Чёрный");
        JRadioButtonMenuItem white = new JRadioButtonMenuItem("Белый");


        blue.addActionListener(e -> {
            state.setColor(Color.BLUE);
        });
        colorMenu.add(blue);
        group.add(blue);

        red.addActionListener(e -> {
            state.setColor(Color.RED);
        });
        colorMenu.add(red);
        group.add(red);

        orange.addActionListener(e -> {
            state.setColor(Color.ORANGE);
        });
        colorMenu.add(orange);
        group.add(orange);

        green.addActionListener(e -> {
            state.setColor(Color.GREEN);
        });
        colorMenu.add(green);
        group.add(green);

        cyan.addActionListener(e -> {
            state.setColor(Color.CYAN);
        });
        colorMenu.add(cyan);
        group.add(cyan);

        black.addActionListener(e -> {
            state.setColor(Color.BLACK);
        });
        colorMenu.add(black);
        group.add(black);

        white.addActionListener(e -> {
            state.setColor(Color.WHITE);
        });
        colorMenu.add(white);
        group.add(white);


        return colorMenu;
    }

    private JMenu createFillMenu(){
        JMenu fillMenu = new JMenu("Заливка");
        ButtonGroup group = new ButtonGroup();

        JRadioButtonMenuItem fill = new JRadioButtonMenuItem("Есть");
        JRadioButtonMenuItem noFill = new JRadioButtonMenuItem("Нет");

        fill.addActionListener(e -> {
            state.setFill(true);

        });
        fillMenu.add(fill);
        group.add(fill);

        noFill.addActionListener(e -> {
            state.setFill(false);

        });
        fillMenu.add(noFill);
        group.add(noFill);


        return fillMenu;
    }

    private JMenu createActionMenu(){
        JMenu actionMenu = new JMenu("Действие");
        ButtonGroup group = new ButtonGroup();

        JRadioButtonMenuItem move = new JRadioButtonMenuItem("Двигать");
        JRadioButtonMenuItem draw = new JRadioButtonMenuItem("Рисовать");

        move.addActionListener(e -> {
            state.setAction(new ActionMove(model));

        });
        actionMenu.add(move);
        group.add(move);

        draw.addActionListener(e -> {
            state.setAction(new ActionDraw(model));

        });
        actionMenu.add(draw);
        group.add(draw);

        return actionMenu;
    }

    public void setActionDraw(ActionDraw actionDraw) {
        this.actionDraw = actionDraw;
    }

    public void setState(MenuState state) {
        this.state = state;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
