/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.umk.raytracking.utility;

/**
 *
 * @author Szymon
 */
public class Color {

    public float r, g, b, s, p;

    public Color() {
        r = 0.0F;
        g = 0.0F;
        b = 0.0F;
        this.s = 0.25F;
    }

    public Color(float r, float g, float b, float s) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.s = s;
    }

    public Color(float r, float g, float b, float s, float p) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.s = s;
        this.p = p;
    }

    public Color(Color color) {
        this.r = color.r;
        this.g = color.g;
        this.b = color.b;
        this.s = color.s;
    }

    public void add(Color color) {
        this.r += color.r;
        this.g += color.g;
        this.b += color.b;
    }

    public Color colorAdd(Color color) {
        return new Color(r + color.r, g + color.g, b + color.b, s);
    }

    public void devide(int scalar) {
        r /= scalar;
        g /= scalar;
        b /= scalar;
    }

    public Color colorMultiply(Color color) {
        return new Color((float) (r * color.r), (float) (g * color.g), (float) (b * color.b), s);
    }
    
    public Color colorDevide(float scalar){
        return new Color(r/scalar, g/scalar, b/scalar, s);
    }

    public Color colorAverage(Color color) {
        return new Color(((float) (r * color.r)) / 2, ((float) (g * color.g)) / 2, ((float) (b * color.b)) / 2, s);
    }

    public Color colorScalar(double scalar) {
        return new Color((float) (scalar * r), (float) (scalar * g), (float) (scalar * b), s);
    }

    public double brigthness() {
        return (r + g + b) / 3;
    }

    public Color clip() {
        double alllight = r + g + b;
        double excesslight = alllight - 3;
        if (excesslight > 0) {
            r = (float) (r + excesslight * (r / alllight));
            g = (float) (g + excesslight * (g / alllight));
            b = (float) (b + excesslight * (b / alllight));
        }
        if (r > 1) {
            r = 1;
        }
        if (g > 1) {
            g = 1;
        }
        if (b > 1) {
            b = 1;
        }
        if (r < 0) {
            r = 0;
        }
        if (g < 0) {
            g = 0;
        }
        if (b < 0) {
            b = 0;
        }

        return new Color(r, g, b, s);
    }

    public int toInteger() {
        return (int) (r * 255) << 16 | (int) (g * 255) << 8 | (int) (b * 255);
    }

    public float getRed() {
        return r;
    }

    public void setRed(float red) {
        this.r = red;
    }

    public float getGreen() {
        return g;
    }

    public void setGreen(float green) {
        this.g = green;
    }

    public float getBlue() {
        return b;
    }

    public void setBlue(float blue) {
        this.b = blue;
    }

    public float getSpecial() {
        return s;
    }

    public void setSpecial(float special) {
        this.s = special;
    }

    public float getP() {
        return p;
    }

    public void setP(float p) {
        this.p = p;
    }

}
