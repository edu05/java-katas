# provide a calculator for how much cocktails cost at a cocktail bar

The goal of this kata is to reinforce TDD and get an introduction to mocking.

1. Your local cocktail bar wants you to write a system that will calculate the price of a customer's order. Every cocktail
has a price and a description. The cost of an order is the sum of the prices of the cocktails in it.

2. The bar only offers certain cocktails, and wouldn't like for customers to order any cocktail outside their menu. The bar offers:

   | cocktail name | description                               | price |
   +---------------+-------------------------------------------+-------|
   | mojito        | traditional Cuban highball                | 10    |
   | godfather     | scotch single malt and amaretto           | 9     |
   | bloody mary   | vodka meets tomato juice and other spices | 12    |
   | old fashioned | whisky muddled with sugar and bitters     | 11    |
   +---------------+-------------------------------------------+-------+

   This is an acceptable use case for using static methods as factory methods.