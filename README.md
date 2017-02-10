# Solve-Complex-Equations-Set
Solve the complex equations sets in android

<b>How to input the coefficients?</b>

For normal form:
a+bi, a+bj, a, bi, bj, a+j
with a, b are in double,
'i' or 'j' are the letter to define the complex number
Example:
3+2i, 3-5.3j, 3i

For Euler form:
r a phi
with r is the magnitude and phi is the argument of the complex number
phi in degree.
'a' or 'A' is the letter that separates r and phi
Example:
3a30, -2a45

You can input both form of complex numbers in the same equations set. Result returned in normal complex number form.


<b>ComplexNumber(double re, double im) class</b>


I provide you some operation of Complex number by using these methods:
<code>
ComplexNumber z = new ComplexNumber(re, im);
z.add(z1);
z.subtract(z1);
z.multiply(z1);
z.divide(re);
z.divide(z1);
</code>
