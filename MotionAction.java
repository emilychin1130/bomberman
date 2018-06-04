import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MotionAction extends AbstractAction implements ActionListener {
    
    private int deltaX;
    private int deltaY;
 
    public MotionAction(String name, int deltaX, int deltaY) {
        super(name);
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }
 
    public void actionPerformed(ActionEvent e) {
        move(deltaX, deltaY);
    }

    public MotionAction addAction(String name, int deltaX, int deltaY) {
        MotionAction action = new MotionAction(name, deltaX, deltaY);
 
        KeyStroke pressedKeyStroke = KeyStroke.getKeyStroke(name);
        InputMap inputMap = component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(pressedKeyStroke, name);
        component.getActionMap().put(name, action);
 
        return action;
    }
}
/*
int delta = 3;
MotionWithKeyBindings motion = new MotionWithKeyBindings(component);
motion.addAction("LEFT", -delta,  0);
motion.addAction("RIGHT", delta,  0);
motion.addAction("UP",    0, -delta);
motion.addAction("DOWN",  0,  delta);
*/