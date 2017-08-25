package client.view.utils;

import javax.swing.*;
import java.awt.*;

/**
 * Class for creation of standard game's view components
 * Created by Chiara Varini on 16/07/17.
 */
public class JComponentsUtils {

    public final static Color LOGIN_COLOR = Color.BLACK;
    public final static Color BACKGROUND_COLOR = Color.WHITE;
    public final static int FONT_SIZE = 20;

    /**
     * Creates a background color panel declared in the same class
     * @return colored panel
     */
    public static JPanel createBackgroundColorPanel(){ return createColoredPanel(BACKGROUND_COLOR); }

    /**
     * Creates a login color panel declared in the same class
     * @return colored panel
     */
    public static JPanel createLoginColorPanel(){ return createColoredPanel(LOGIN_COLOR); }

    /**
     * Creates a transparent panel
     * @return transparent panel
     */
    public static JPanel createTransparentPanel(){return createColoredPanel(new Color(0,0,0,0)); }

    /**
     * Creates a black button with the blank name
     * @param name
     * @return
     */
    public static JButton createBlackButton(final String name){
        JButton button = createButton(name);
        button.setBackground(LOGIN_COLOR);
        button.setForeground(BACKGROUND_COLOR);
        return button;
    }

    /**
     * Creates a button in the style of the game with the specified name
     * @param name
     * @return
     */
    public static  JButton createButton(final String name){
        JButton button = new JButton(name);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFont(new Font(button.getFont().getName(), Font.BOLD, FONT_SIZE));
        return button;
    }

    /**
     * Creates a JLable in the style of the game with the specified nameTitle
     * @param nameTitle
     * @return
     */
    public static JLabel createSectionTitle(final String nameTitle){
        JLabel title = new JLabel(nameTitle);
        title.setForeground(Color.WHITE);
        title.setFont(new Font(title.getFont().getName(),Font.PLAIN, FONT_SIZE));
        return title;
    }

    private static JPanel createColoredPanel(final Color color){
        JPanel panel = new JPanel();
        panel.setBackground(color);
        return panel;
    }
}
