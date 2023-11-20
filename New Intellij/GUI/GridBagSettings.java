package GUI;

import java.awt.*;

public class GridBagSettings extends GridBagConstraints
{
    public GridBagSettings(int _gridX, int _gridY)
    {
        super();
        gridx = _gridX;
        gridy = _gridY;
    }

    public GridBagSettings(int _gridX, int _gridY, Insets _insets)
    {
        super();
        gridx = _gridX;
        gridy = _gridY;
        insets = _insets;
    }

    public GridBagSettings(int _gridX, int _gridY, int _weightX, int _weightY, int _fill, Insets _insets )
    {
        super();
        gridx = _gridX;
        gridy = _gridY;
        weightx = _weightX;
        weighty = _weightY;
        fill = _fill;
        insets = _insets;
    }

    public GridBagSettings(int gdX, int gdY, int gdHt, int gdWd, int wtX, int wtY, int padX, int padY,
                           int fll, Insets insts)
    {
        super();
        if (gdX >= 0) gridx = gdX;
        if (gdY >= 0) gridy = gdY;
        if (gdHt >= 0) gridheight = gdHt;
        if (gdWd >= 0) gridwidth = gdWd;
        if (wtX >= 0) weightx = wtX;
        if (wtY >= 0) weighty = wtY;
        if (padX >= 0) ipadx = padX;
        if (padY >= 0) ipady = padY;
        if (fll >= 0) fill = fll;
        if (insts != null) insets = insts;
    }
}
