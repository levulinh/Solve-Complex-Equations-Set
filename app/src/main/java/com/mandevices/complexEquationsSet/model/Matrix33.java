package com.mandevices.complexEquationsSet.model;

/**
 * Created by levul on 2/9/2017.
 */

public class Matrix33 {
    private ComplexNumber[][] matrix;

    public ComplexNumber[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(ComplexNumber[][] matrix) {
        this.matrix = matrix;
    }

    public Matrix33() {

    }

    public Matrix33(ComplexNumber[][] matrix) {

        this.matrix = matrix;
    }

    public ComplexNumber det(){
        ComplexNumber[][] m = this.matrix;

        return  m[0][0].multiply(m[1][1]).multiply(m[2][2]).add(
                m[0][1].multiply(m[1][2]).multiply(m[2][0])).add(
                m[0][2].multiply(m[1][0]).multiply(m[2][1])).subtract(
                m[0][2].multiply(m[1][1]).multiply(m[2][0])).subtract(
                m[0][1].multiply(m[1][0]).multiply(m[2][2])).subtract(
                m[0][0].multiply(m[1][2]).multiply(m[2][1]));
    }
}
