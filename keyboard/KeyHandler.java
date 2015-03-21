package keyboard;

/**
 * 
 * @author keng
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import environment.Environment;

public class KeyHandler extends KeyTrigger implements KeyListener{
    
    public KeyTrigger up = new KeyTrigger();
    public KeyTrigger down = new KeyTrigger();
    public KeyTrigger left = new KeyTrigger();
    public KeyTrigger right = new KeyTrigger();
    
    public void keyPressed(KeyEvent keyEvent) {
        triggerKey(keyEvent.getKeyCode(), true);
    }
    
    public void keyReleased(KeyEvent keyEvent) {
        triggerKey(keyEvent.getKeyCode(), false);
    }
    
    public void triggerKey(int keyCode, boolean isTriggered) {
        switch(keyCode) {
            case KeyEvent.VK_UP: up.trigger(isTriggered);
            case KeyEvent.VK_DOWN: up.trigger(isTriggered);
            case KeyEvent.VK_LEFT: up.trigger(isTriggered);
            case KeyEvent.VK_RIGHT: up.trigger(isTriggered);
        }
    }

    public void keyTyped (KeyEvent e) {
    }
}
