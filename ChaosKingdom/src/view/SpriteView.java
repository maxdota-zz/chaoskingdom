package view;

import controller.SpriteController;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.*;
import model.Sprite;

/**
 * Created with IntelliJ IDEA.
 * User: Ngoc
 * Date: 4/2/13
 * Time: 1:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class SpriteView extends JLabel implements Observer{
  private ImageIcon[] animation;
    public SpriteView(int id, SpriteController controller) {
        setName("" + id);
        addMouseListener(controller);
    }
    @Override
    public void update(Observable o, Object arg) {
         Sprite p = (Sprite) o;
        // initialize the label - assign image animations and location
        if (arg instanceof File) {
            File f = (File) arg;
            setVisible(true);
            BufferedImage img = null;
            try {
                img = ImageIO.read(f);
            } catch (IOException e) {
                e.printStackTrace();
            }

            int width = img.getWidth() / Sprite.COL;
            int height = img.getHeight() / Sprite.ROW;
            Graphics2D g;
            BufferedImage bi;

            animation = new ImageIcon[Sprite.COL * Sprite.ROW];
            for (int i = 0; i < Sprite.COL; i++) {
                for (int j = 0; j < Sprite.ROW; j++) {
                    // create an empty image
                    bi = new BufferedImage(width, height, img.getType());
                    // draw into the image
                    g = bi.createGraphics();
                    g.drawImage(img, 0, 0, width, height, i * width, height * j, width * i + width, height * j + height, null);
                    g.dispose();
                    animation[i * Sprite.ROW + j] = new ImageIcon(bi);
                }
            }
            setIcon(animation[Sprite.DOWN]);
            setSize(getPreferredSize());
            
//            setLocation(p.getPosition().x * Sprite.SIZE, p.getPosition().y * Sprite.SIZE);
      
        } else if (arg instanceof Point) {
            setLocation((Point) arg);
        } else if(arg instanceof Object[]){
            Object[]temp=(Object[])arg;
            int direction=(int)temp[0];
            int currentAnimation=(int)temp[1];
            switch(direction){
                case Sprite.LEFT:
                    setIcon(animation[Sprite.LEFT+currentAnimation]);
                    break;
                case Sprite.RIGHT:
                    setIcon(animation[Sprite.RIGHT+currentAnimation]);
                    break;
                case Sprite.UP:
                    setIcon(animation[Sprite.UP+currentAnimation]);
                    break;
                case Sprite.DOWN:
                    setIcon(animation[Sprite.DOWN+currentAnimation]);
                    break;
                case Sprite.UPLEFT:
                    setIcon(animation[Sprite.UPLEFT+currentAnimation]);
                    break;
                case Sprite.DOWNLEFT:
                    setIcon(animation[Sprite.DOWNLEFT+currentAnimation]);
                    break;
                case Sprite.UPRIGHT:
                    setIcon(animation[Sprite.UPRIGHT+currentAnimation]);
                    break;
                case Sprite.DOWNRIGHT:
                    setIcon(animation[Sprite.DOWNRIGHT+currentAnimation]);
                    break;
            }
        } else if (arg instanceof Boolean) {
            setVisible((Boolean) arg);
        }
    }

    /*
     * Below methods are used to tint the image
     * They are from the source stackoverflow.com
     * http://stackoverflow.com/questions/14225518/tinting-image-in-java-improvement
     */

    public ImageIcon toRed(ImageIcon img) {

//        BufferedImage master = ((ToolkitImage) img.getImage()).getBufferedImage();
        BufferedImage master = ((BufferedImage) img.getImage());
        BufferedImage mask = generateMask(master, Color.RED, 0.5f);
        return new ImageIcon(tint(master, mask));
    }

    public BufferedImage createCompatibleImage(int width, int height, int transparency) {
        BufferedImage image = getGraphicsConfiguration().createCompatibleImage(width, height, transparency);
        image.coerceData(true);
        return image;
    }

    public void applyQualityRenderingHints(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
    }

    public BufferedImage generateMask(BufferedImage imgSource, Color color, float alpha) {
        int imgWidth = imgSource.getWidth();
        int imgHeight = imgSource.getHeight();

        BufferedImage imgMask = createCompatibleImage(imgWidth, imgHeight, Transparency.TRANSLUCENT);
        Graphics2D g2 = imgMask.createGraphics();
        applyQualityRenderingHints(g2);

        g2.drawImage(imgSource, 0, 0, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_IN, alpha));
        g2.setColor(color);

        g2.fillRect(0, 0, imgSource.getWidth(), imgSource.getHeight());
        g2.dispose();

        return imgMask;
    }

    public BufferedImage tint(BufferedImage master, BufferedImage tint) {
        int imgWidth = master.getWidth();
        int imgHeight = master.getHeight();

        BufferedImage tinted = createCompatibleImage(imgWidth, imgHeight, Transparency.TRANSLUCENT);
        Graphics2D g2 = tinted.createGraphics();
        applyQualityRenderingHints(g2);
        g2.drawImage(master, 0, 0, null);
        g2.drawImage(tint, 0, 0, null);
        g2.dispose();

        return tinted;
    }
}
