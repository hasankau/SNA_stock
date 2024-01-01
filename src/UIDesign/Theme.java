/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UIDesign;

import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author HASANKA
 */
public class Theme {
    public static Color navColorDefault = new Color(51, 51, 51);
    public static Color navColorSelect = new Color(0, 153, 153);
    public static Color btColorMouseOver = new Color(0,102,102);
    public static Color btColorOrangeExit = new Color(255,153,0);
    public static Color btColorOrange = new Color(255,102,0);
    public static Color btColorGreen = new Color(255,153,0);
    public static Color btColorGreenExit = new Color(255,153,0);
    public static Color statusGreen = new Color(204,255,204);
    public static Color statusBlue = new Color(153,204,230);
    
    public static Color dangerRedDefault = new Color(204,51, 51);
    public static Color dangerRedHOver = new Color(180,28,28);
    
    
    final public static int BLUE = 0;
    final public static int GREEN = 1;
    final public static int ORANGE = 2;

    /**
     * @return the dangerRedDefault
     */
    public static Color getDangerRedDefault() {
        return dangerRedDefault;
    }

    /**
     * @param aDangerRedDefault the dangerRedDefault to set
     */
    public static void setDangerRedDefault(Color aDangerRedDefault) {
        dangerRedDefault = aDangerRedDefault;
    }

    /**
     * @return the dangerRedHOver
     */
    public static Color getDangerRedHOver() {
        return dangerRedHOver;
    }

    /**
     * @param aDangerRedHOver the dangerRedHOver to set
     */
    public static void setDangerRedHOver(Color aDangerRedHOver) {
        dangerRedHOver = aDangerRedHOver;
    }
    
    private Image newImg;
    
    //private String imagePath = "/img/user.png";

    public Theme() {
//        ImageIcon iconI = new javax.swing.ImageIcon(getClass().getResource(getImagePath()));
    //    Image img = iconI.getImage();
 //        newImg= img.getScaledInstance(79, 79, Image.SCALE_SMOOTH);
    }

    
    /**
     * @return the navColorDefault
     */
    public Color getNavColorDefault() {
        return navColorDefault;
    }

    /**
     * @param navColorDefault the navColorDefault to set
     */
    public void setNavColorDefault(Color navColorDefault) {
        this.navColorDefault = navColorDefault;
    }

    /**
     * @return the navColorSelect
     */
    public Color getNavColorSelect() {
        return navColorSelect;
    }

    /**
     * @param navColorSelect the navColorSelect to set
     */
    public void setNavColorSelect(Color navColorSelect) {
        this.navColorSelect = navColorSelect;
    }

    /**
     * @return the statusGreen
     */
    public Color getStatusGreen() {
        return statusGreen;
    }

    /**
     * @param statusGreen the statusGreen to set
     */
    public void setStatusGreen(Color statusGreen) {
        this.statusGreen = statusGreen;
    }

    /**
     * @return the statusBlue
     */
    public Color getStatusBlue() {
        return statusBlue;
    }

    /**
     * @param statusBlue the statusBlue to set
     */
    public void setStatusBlue(Color statusBlue) {
        this.statusBlue = statusBlue;
    }

    /**
     * @return the newImg
     */
    public Image getNewImg() {
        return newImg;
    }

    /**
     * @param newImg the newImg to set
     */
    public void setNewImg(Image newImg) {
        this.newImg = newImg;
    }

    /**
     * @return the btColorMouseOver
     */
    public Color getBtColorMouseOver() {
        return btColorMouseOver;
    }

    /**
     * @param btColorMouseOver the btColorMouseOver to set
     */
    public void setBtColorMouseOver(Color btColorMouseOver) {
        this.btColorMouseOver = btColorMouseOver;
    }

    /**
     * @return the btColorOrange
     */
    public Color getBtColorOrange() {
        return btColorOrange;
    }

    /**
     * @param btColorOrange the btColorOrange to set
     */
    public void setBtColorOrange(Color btColorOrange) {
        this.btColorOrange = btColorOrange;
    }

    /**
     * @return the btColorOrangeExit
     */
    public Color getBtColorOrangeExit() {
        return btColorOrangeExit;
    }

    /**
     * @param btColorOrangeExit the btColorOrangeExit to set
     */
    public void setBtColorOrangeExit(Color btColorOrangeExit) {
        this.btColorOrangeExit = btColorOrangeExit;
    }

    /**
     * @return the btColorGreen
     */
    public Color getBtColorGreen() {
        return btColorGreen;
    }

    /**
     * @param btColorGreen the btColorGreen to set
     */
    public void setBtColorGreen(Color btColorGreen) {
        this.btColorGreen = btColorGreen;
    }

    /**
     * @return the btColorGreenExit
     */
    public Color getBtColorGreenExit() {
        return btColorGreenExit;
    }

    /**
     * @param btColorGreenExit the btColorGreenExit to set
     */
    public void setBtColorGreenExit(Color btColorGreenExit) {
        this.btColorGreenExit = btColorGreenExit;
    }

    /**
     * @return the imagePath
     */
//    public String getImagePath() {
//        return imagePath;
//    }
//
//    /**
//     * @param imagePath the imagePath to set
//     */
//    public void setImagePath(String imagePath) {
//        this.imagePath = imagePath;
//    }
    
    
}
