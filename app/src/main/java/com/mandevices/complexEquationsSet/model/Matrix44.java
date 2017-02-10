package com.mandevices.complexEquationsSet.model;

/**
 * Created by levul on 2/10/2017.
 */

public class Matrix44 {
    private ComplexNumber[][] matrix;

    public Matrix44() {

    }

    public Matrix44(ComplexNumber[][] matrix) {

        this.matrix = matrix;
    }

    public ComplexNumber[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(ComplexNumber[][] matrix) {
        this.matrix = matrix;
    }

    public Matrix33 toMatrix33(int u, int v) {

        ComplexNumber[][] matrix3 = new ComplexNumber[3][3];
        int di, dj; di =-1; dj = -1;
        for (int i = 0; i < 4; i++) {
            if (i != u) {
                di++;
                dj = -1;
                for (int j = 0; j < 4; j++) {
                    if (j != v){
                        dj++;
                        matrix3[di][dj] = new ComplexNumber();
                        matrix3[di][dj].copyFrom(this.matrix[i][j]);
                    }
                }
            }
        }

        return new Matrix33(matrix3);
    }

    public ComplexNumber det(){
        ComplexNumber cmplx = new ComplexNumber(0,0);
        int j = -1;
        for(int i=0; i<4; i++){
            j*= -1;
            cmplx = cmplx.add(this.toMatrix33(0,i).det().multiply(new ComplexNumber(j,0)));
        }
        return cmplx;
    }
}
