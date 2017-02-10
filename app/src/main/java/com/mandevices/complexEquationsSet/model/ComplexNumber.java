package com.mandevices.complexEquationsSet.model;

/**
 * Created by levul on 2/8/2017.
 */

public class ComplexNumber {
    private double realPart;
    private double imaginaryPart;

    public ComplexNumber(double realPart, double imaginaryPart) {
        this.realPart = realPart;
        this.imaginaryPart = imaginaryPart;
    }

    public ComplexNumber() {
    }

    public static ComplexNumber parseCplx(String s) {
        s = s.replaceAll(" ", "");
        s = s.replaceAll("\\+i","+1i");
        s = s.replaceAll("\\+j","+1i");
        s = s.replaceAll("\\-i","-1i");
        s = s.replaceAll("\\-j","-1i");
        String[] parts;
        float a;
        float b;

        if(s.contains("a") || s.contains("A")){
            s = s.replaceAll("A","a");
            parts = s.split("a");

            double r, phi;
            r = Float.parseFloat(parts[0]);
            phi = Float.parseFloat(parts[1]);
            phi = phi*3.1415926/180;

            return new ComplexNumber(r*Math.cos(phi),r*Math.sin(phi));
        }

        if (!s.contains("i") && !s.contains("j")) {
            a = Float.parseFloat(s);
            return new ComplexNumber(a, 0);
        } else {
            s = s.replaceAll("j", "");
            s = s.replaceAll("i", "");
            if (s.charAt(0) == '-') {
                s = s.replaceFirst("\\-", "");
                s = s.replaceAll("\\-", "+-");
                s = "-" + s;
            }
            s = s.replaceAll("\\-","+-");
            parts = s.split("\\+");
            if (parts.length == 2) {
                a = Float.parseFloat(parts[0]);
                b = Float.parseFloat(parts[1]);

                return new ComplexNumber(a, b);
            } else {
                a = Float.parseFloat(s);
                return new ComplexNumber(0, a);
            }
        }
    }

    public double getRealPart() {
        return realPart;
    }

    public void setRealPart(double realPart) {
        this.realPart = realPart;
    }

    public double getImaginaryPart() {
        return imaginaryPart;
    }

    public void setImaginaryPart(double imaginaryPart) {
        this.imaginaryPart = imaginaryPart;
    }

    public ComplexNumber add(ComplexNumber z) {
        double a1 = this.realPart;
        double b1 = this.imaginaryPart;
        double a2 = z.getRealPart();
        double b2 = z.getImaginaryPart();

        return new ComplexNumber(a1 + a2, b1 + b2);
    }

    public ComplexNumber opposite() {
        return new ComplexNumber(-this.realPart, -this.imaginaryPart);
    }

    public ComplexNumber subtract(ComplexNumber z) {
        ComplexNumber z1 = this.add(z.opposite());
        return z1;
    }

    public ComplexNumber multiply(ComplexNumber z) {
        double a1 = this.realPart;
        double b1 = this.imaginaryPart;
        double a2 = z.getRealPart();
        double b2 = z.getImaginaryPart();

        return new ComplexNumber(a1 * a2 - b1 * b2, a1 * b2 + a2 * b1);
    }

    public boolean isZero() {
        return this.realPart == 0 && this.imaginaryPart == 0;
    }

    public ComplexNumber inverse() {
        return new ComplexNumber(this.realPart, -this.imaginaryPart);
    }

    public ComplexNumber divide(double f) {
        return new ComplexNumber(1.0f*this.realPart / f, 1.0f*this.imaginaryPart / f);
    }

    public ComplexNumber divide(ComplexNumber z) {
        ComplexNumber z1 = new ComplexNumber(this.realPart, this.imaginaryPart);
        return z1.multiply(z.inverse().divide(z.getRealPart() * z.getRealPart() + z.getImaginaryPart() * z.getImaginaryPart()));
    }

    public String toString() {
        if (this.imaginaryPart < 0) return (float)this.realPart + "" + (float)this.imaginaryPart + "i";
        if (this.imaginaryPart == 0) return (float)this.realPart+"";
        return (float)this.realPart + "+" + (float)this.imaginaryPart + "i";
    }

    public void copyFrom(ComplexNumber z){
        this.setRealPart(z.getRealPart());
        this.setImaginaryPart(z.getImaginaryPart());
    }
}
