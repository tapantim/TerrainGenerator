package fi.timotapanainen.tergen;

import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * Created with IntelliJ IDEA.
 * User: tapantim
 * Date: 21.5.2013
 * Time: 20:56
 * To change this template use File | Settings | File Templates.
 */
public class ImageBuilder {

    private double[][] values;
    private double waterCoveragePercent;

    private boolean grayScale = true;


    public Image build() {
        int width = values.length;
        int height = values[0].length;
        WritableImage wi = new WritableImage(width, height);
        PixelWriter pw = wi.getPixelWriter();
        double dividerValue = MathUtil.percentile(values, waterCoveragePercent);

        if (!grayScale) {
            MathUtil.addToAll(values, -dividerValue);
            MathUtil.scaleToRange(values, 0.0, 1.0, 0.0);
        }

        for (int x = 0; x < width; x = ++x) {
            for (int y = 0; y < height; y = ++y) {
                double z = values[x][y];
                if (grayScale)
                    pw.setColor(x, y, new Color(z, z, z, 1.0));
                else
                    pw.setColor(x, y, getColor(z));
            }

        }

        return wi;
    }

    public Color getColor(double height) {
        if (height < -0.1) {
            return Color.rgb(117, 175, 240);
        } else if (height >= -0.1 && height < 0) {
            return Color.rgb(180, 210, 240);
        } else if (height >= 0 && height < 0.1) {
            return Color.rgb(117, 156, 95);
        } else if (height >= 0.1 && height < 0.2) {
            return Color.rgb(154, 183, 116);
        } else if (height >= 0.2 && height < 0.3) {
            return Color.rgb(198, 210, 141);
        } else if (height >= 0.3 && height < 0.4) {
            return Color.rgb(242, 238, 165);
        } else if (height >= 0.4 && height < 0.5) {
            return Color.rgb(242, 224, 154);
        } else if (height >= 0.5 && height < 0.6) {
            return Color.rgb(235, 196, 132);
        } else if (height >= 0.6 && height < 0.7) {
            return Color.rgb(211, 149, 113);
        } else if (height >= 0.7 && height < 0.8) {
            return Color.rgb(164, 111, 84);
        } else if (height >= 0.8 && height < 0.9) {
            return Color.rgb(134, 77, 48);
        } else if (height >= 0.9 && height <= 1.0) {
            return Color.rgb(159, 159, 159);
        } else {
            assert false : "invalid height " + height;
            return Color.WHITE;
        }

    }

    public double[][] getValues() {
        return values;
    }

    public void setValues(double[][] values) {
        this.values = values;
    }

    public boolean getGrayScale() {
        return grayScale;
    }

    public boolean isGrayScale() {
        return grayScale;
    }

    public void setGrayScale(boolean grayScale) {
        this.grayScale = grayScale;
    }

    public double getWaterCoveragePercent() {
        return waterCoveragePercent;
    }

    public void setWaterCoveragePercent(double waterCoveragePercent) {
        this.waterCoveragePercent = waterCoveragePercent;
    }

}
