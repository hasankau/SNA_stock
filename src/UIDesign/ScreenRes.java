/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIDesign;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author HASANKA
 */
public class ScreenRes {

    private static final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int height = size.height;
    private static final int width = size.width;

    /**
     * @return the size
     */
    
    public static Dimension getSize() {
        Dimension d = new Dimension(width, height-40);
        return d.getSize();
    }

    /**
     * @return the height
     */
    public static int getHeight() {
        return height;
    }

    /**
     * @return the width
     */
    public static int getWidth() {
        return width;
    }
}
