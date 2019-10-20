/*
   Author: Mike O'Malley
   Description: Ch03_Q19__FactorialApproximationStirlingsFormula__uses_PI
   My solution for: Q26 - Chapter 9, p325.

   Structured Fortran 77 for Engineers and Scientists,
   D. M. Etter.
   Ch03, Q19, p136.

Question:

Factorials can be estimated using Stirling's Approximation:

   N! = Sqrt (2 * PI * N) * (N / e)^N
where
 PI = 3.14159265358979;
  e = 2.718281828459045;

Write a program to calculate the factorials for all integers
between 1 and 20.  This program should calculate:
* the exact Factorial values using iteration or recursion,
* the approximate Factorial values using Stirling's Approximation,

and display the results in a table.

For each factorial, calculate and display the error%:

   Error% = (Exact Value - Approximate Value) / Exact Value * 100.0

What happens to the error% as N increases ?

*

Benchmark the calculation of factorials.

Calculate the above factorials 1000 times and time each method.
WHich is fastest ?  By how much ?
*/

/*
Sample Output:

 N             Factorial               Stirling Factorial            Error%
----  --------------------------  -----------------------------  -------------------
   1                           1                          0.922     7.7862991104211%
   2                           2                          1.919     4.0497824255509%
   3                           6                          5.836     2.7298401442356%
   4                          24                         23.506     2.0576036129446%
   5                         120                        118.019     1.6506933686750%
   6                         720                        710.078     1.3780299108077%
   7                       5,040                      4,980.396     1.1826223886417%
   8                      40,320                     39,902.395     1.0357255638475%
   9                     362,880                    359,536.873     0.9212762230081%
  10                   3,628,800                  3,598,695.619     0.8295960443939%
  11                  39,916,800                 39,615,625.051     0.7545067475912%
  12                 479,001,600                475,687,486.473     0.6918794273806%
  13               6,227,020,800              6,187,239,475.193     0.6388500389670%
  14              87,178,291,200             86,661,001,740.599     0.5933695789179%
  15           1,307,674,368,000          1,300,430,722,199.468     0.5539334545198%
  16          20,922,789,888,000         20,814,114,415,223.133     0.5194119587235%
  17         355,687,428,096,000        353,948,328,666,100.940     0.4889403708218%
  18       6,402,373,705,728,000      6,372,804,626,194,310.000     0.4618455731073%
  19     121,645,100,408,832,000    121,112,786,592,294,128.000     0.4375957722496%
  20   2,432,902,008,176,640,000  2,422,786,846,761,133,600.000     0.4157652622880%

Stirling's method is brilliant and gives increasingly accurate answers
as N increases.  (Error -> 0 as N -> Infinity).

There are lots of other brilliant Facorial methods, so give even better answers
than Stirling's method.

REF:
https://en.wikipedia.org/wiki/Stirling%27s_approximation
http://www.luschny.de/math/factorial/approx/SimpleCases.html

*/

import java.math.BigDecimal;


public class Ch03_Q19__FactorialApproximationStirlingsFormula__uses_PI
{
   // Constants:
   public static long Factorial (int number)
   {
      long result = 0;

      if ((number >= 0) && (number <= 1))
         result = 1;
      else if (number > 1)
      {
         result = 1;
         for (int k = 1; k <= number; k++)
            result = result * k;
      }
      return result;
   }

   public static double StirlingFactorial (int number)
   {
      double PIactual = 3.14159265358979;
      double eActual  = 2.718281828459045;

      // Stirling's Approximation: N! = Sqrt (2 * PI * N) * (N / e)^N
      double stirlingFactorial = Math.sqrt (2.0 * PIactual * number) *
                                 Math.pow  (1.0 * number / eActual, 1.0 * number);

      return stirlingFactorial;
   }

   public static void main (String[] args)
   {
      System.out.println (" N             Factorial               Stirling Factorial            Error%       ");
      System.out.println ("----  --------------------------  -----------------------------  -------------------");

      long factorial = 0;
      double stirlingFactorial = 0.0;
      double errorPct;

      for (int n = 1; n <= 20; n++)
      {
         factorial         = Factorial (n);
         stirlingFactorial = StirlingFactorial (n);

         errorPct = 1.0 * (factorial - stirlingFactorial) / factorial * 100.0;

         System.out.println (String.format ("%,4d",     n)                 + "  " +
                             String.format ("%,26d",    factorial)         + "  " +
                             String.format ("%,29.3f",  stirlingFactorial) + "  " +
                             String.format ("%,18.13f", errorPct)          + "%");
      }

      System.out.println ("\nlong: max value= " + String.format ("%,d",    Long.MAX_VALUE) +
                         " = " + ("" + Long.MAX_VALUE).length() + " digits");
   }
} // public class Ch03_Q19__FactorialApproximationStirlingsFormula__uses_PI
