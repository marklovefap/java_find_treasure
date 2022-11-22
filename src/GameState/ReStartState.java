package GameState;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import Manager.Content;
import Manager.GameStateManager;
import Manager.Keys;

public class ReStartState extends GameState{
    private BufferedImage bg;
    private BufferedImage arrow;
    private int currentOption = 0;
    private String[] options = {
            "TRY AGAIN",
            "QUIT"
    };

    public ReStartState(GameStateManager gsm) {
        super(gsm);
    }

    public void init() {
        bg = Content.screen[0][0];
        arrow = Content.ARROW[0][0];
    }

    public void update() { handleInput(); }

    public void draw(Graphics2D g) {
        g.drawImage(bg, 46, 79, null);
        Content.drawString(g, options[0], 69, 102);
        Content.drawString(g, options[1], 69, 117);
        if (currentOption == 0) g.drawImage(arrow, 52, 97, null);
        else if (currentOption == 1) g.drawImage(arrow, 52, 112, null);
    }

    public void handleInput() {
        if (Keys.isPressed(Keys.DOWN) && currentOption < options.length - 1) currentOption++;
        if (Keys.isPressed(Keys.UP) && currentOption > 0) currentOption--;
        if (Keys.isPressed(Keys.ENTER)) selectOption();
        if (Keys.isPressed(Keys.SPACE)) selectOption();
    }

    private void selectOption() {
        if (currentOption == 0) gsm.setState(GameStateManager.PLAY);
        if (currentOption == 1) System.exit(0);
    }
}