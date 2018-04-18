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

3. You can buy 2 "old fashioned" cocktails for the price of 1

4. Between 21:00 and 22:00 it's happy hour, all drinks are half price (2 for 1 old fashioneds still applies i.e. you can buy 2 old fashioned cocktails for 5.5)

5. Don't allow customers to get order 5 or more drinks in one go by throwing a ShouldntGetDrunkException