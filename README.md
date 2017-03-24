# Solve-Complex-Equations-Set
Solve the complex equations sets in android

__How to input the coefficients?__

_For normal form:_<br>
a+bi, a+bj, a, bi, bj, a+j<br>
with a, b are in double,<br>
'i' or 'j' are the letter to define the complex number<br>
_Example:_<br>
3+2i, 3-5.3j, 3i<br>

_For Euler form:_<br>
r a phi<br>
with r is the magnitude and phi is the argument of the complex number<br>
phi in degree.<br>
'a' or 'A' is the letter that separates r and phi<br>
_Example:_<br>
3a30, -2a45<br>

You can input both form of complex numbers in the same equations set. Result returned in normal complex number form.<br>


__ComplexNumber(double re, double im) class__


I provide you some operation of Complex number by using these methods:<br>
```java
ComplexNumber z = new ComplexNumber(re, im); 
//Interactions between two complex numbers
z.add(z1);
z.subtract(z1);
z.multiply(z1);
z.divide(re);
z.divide(z1);

//Operations of a complex number
z.conjugate();
z.opposite();
```
See more at [ComplexNumber.class!](https://github.com/levulinh/Solve-Complex-Equations-Set/blob/master/app/src/main/java/com/mandevices/complexEquationsSet/model/ComplexNumber.java)<br>

To clone a ComplexNumber object, use `cloneFrom(ComplexNumber z)` method.<br>
For example:
```java
//clone value of z1 to z
ComplexNumber z = new ComplexNumber();
z.cloneFrom(z1);
```

__Matrix22, Matrix33, Matrix44 classes__

These objects take a two-dimension array of ComplexNumber as the argument.<br>
Method `Matrix22.det()` return a ComplexNumber that is the det of the Matrix. similar to Matrix33 and Matrix44.

##That's it, have fun##

