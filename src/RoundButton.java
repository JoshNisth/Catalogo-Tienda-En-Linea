import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;

public class RoundButton extends JButton {

    public RoundButton(String text) {
        super(text);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setPreferredSize(new Dimension(120, 40));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (getModel().isPressed()) {
            g2.setColor(new Color(22, 45, 78));
        } else if (getModel().isRollover()) {
            g2.setColor(new Color(22, 45, 78));
        } else {
            g2.setColor(new Color(18, 55, 107));
        }

        int width = getWidth();
        int height = getHeight();
        g2.fillRoundRect(0, 0, width, height, 20, 20);

        g2.setColor(new Color(242, 242, 239));
        g2.setFont(getFont());
        String text = getText();
        int textWidth = g2.getFontMetrics().stringWidth(text);
        int textHeight = g2.getFontMetrics().getHeight();
        int x = (width - textWidth) / 2;
        int y = (height + textHeight) / 2 - 3;
        g2.drawString(text, x, y);

        g2.dispose();
    }
}