package client.view.playground;

import java.awt.*;

/**
 * Playground Builder
 * Created by Chiara Varini on 05/07/17.
 */
public class PlaygroundBuilderImpl implements PlaygroundBuilder {

    private int columns = 0;
    private int rows = 0;
    private Color color = Color.black;
    private Image image = null;

    @Override
    public PlaygroundBuilder setColumns(int colums) {
        this.columns = colums;
        return this;
    }

    @Override
    public PlaygroundBuilder setRows(int rows) {
        this.rows = rows;
        return this;
    }

    @Override
    public PlaygroundBuilder setBackground(Color backgroundColor) {
        this.color = backgroundColor;
        return this;
    }

    @Override
    public PlaygroundBuilder setImageBackground(Image backgroundImage) {
        this.image = backgroundImage;
        return this;
    }

    @Override
    public PlaygroundPanel createPlayground(){
        PlaygroundSettings settings = new PlaygroundSettings(columns,rows);
        settings.setBackgroundColor(color);
        if(image!=null){
            settings.setBackgroundImage(image);
        }
        return new PlaygroundPanel(settings);
    }
}
