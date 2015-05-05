**************************************
* COMP34120 AI & Games - Project 2
* Readme file for team Vanguard 
**************************************

I) Classes

Besides the provided source classes and compiled platform, we have created 4 classes of our own:

1) VanguardLeader.java

	The main bot class that extends the PlayerImpl class, implementing its methods. The endSimulation() method was changed to add a print of the total profit gained over the 30 days for debugging puposes and the proceedNewDay() method was changed to implement the functionality required for getting the price to be published in the new day. Additional methods added: getPrice(), getTotalProfit() and getRecords(). Most of these methods are self-explanatory and there are comments in the code for additional information. getPrice() contains the main logic to start the regression and based on that, return the new price to be published.

2) FollowerReaction.java

	This class models the follower player's reaction function and related logic. Relevant methods are: updateRecords(), doLinearRegression(), getFollowerReaction() - accessor method, findBestStrategy(). Again, the ones that are self-explanatory will not be explianed (additional comments in code), what matters here is doLinearRegression() which creates a LinearRegression object (details below) that will generate the values for 'a' and 'b' in the 'a + bx' linear reaction function.

3) LinearRegression.java

	This class models the mathematical linear regression used to approximate the values of 'a' and 'b' (commonly denoted as theta 0 and 1) from the function 'a + bx'. Relevant methods are: doRegression(), computeCost() - no longer used and gradientDescent().

4) Matrix.java

	Custom class created to model mathematical matrices. Has methods such as transpose(), multiply() - both scalar and matrix-type multiplication, add(), subtract() and any other matrix type operation needed to compute the formulae used in the regression.


II) Description of main method

As can be seen from the description of the classes above and the code, the flow of the logic is straightforward and can be broken up into a few steps:

	1) Get the history of the records of prices (strategies). Because we use the moving window approach, we usually get the records from the "last" 50 days (based on the current date).

	2) Launch the regression to estimate follower reaction function
		a)
		b)
		c)
		d)

	3) Having estimated the follower reaction, the application then finds the best strategy to publish (to maximise profit). This is done by finding the globam maximum of the function used to calculate the profit. The function in our case is (uL - 1) * (2 - uL + 0.3*uF), where uL is our strategy and uF is follower reaction. We have estimated the follower reaction, uF, as being a + bx, where a and b are the constants the regression returns. Replacing that in the above payoff function gives us an f(x) type function where x = uL. In order to find the maximum value of the function (maximum possible profit), we derive the function and set it equal to 0 -> f'(x) = 0. From this equation, we can then deduct x (uL) as the strategy that maximises our payoff.

	4) Last but not least, the new price is published to the platform and the current cycle (day) ends after the follower publishes his. This process repeats for every day until the game is over.