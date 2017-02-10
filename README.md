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
z.add(z1);
z.subtract(z1);
z.multiply(z1);
z.divide(re);
z.divide(z1);
```
