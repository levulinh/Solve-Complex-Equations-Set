package com.mandevices.complexEquationsSet.model;

/**
 * Created by levul on 2/8/2017.
 */

public class Matrix22 {
    private ComplexNumber[][] matrix;

    public Matrix22(ComplexNumber[][] matrix) {
        this.matrix = matrix;
    }

    public Matrix22() {
    }

    public ComplexNumber[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(ComplexNumber[][] matrix) {
        this.matrix = matrix;
    }

    public ComplexNumber det(){
        return (this.matrix[0][0].multiply(this.matrix[1][1])).subtract(this.matrix[0][1].multiply(this.matrix[1][0]));
    }
}
