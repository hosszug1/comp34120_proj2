**************************************
* COMP34120 AI & Games - Project 2
* Readme file for team Vanguard 
**************************************

*** IMPORTANT NOTE *****************************************************************************************************************************************
* The submission page requested 3 files to be submitted: 1) a compiled version of our leader class, 2) this readme and 3) an archive of the source code. 
* Because our leader class uses 3 additional classes, their compiled versions have also been included. In order to run the leader, the usual command
* can be used (same as for SimpleLeader), however, these 3 additional classes need to be in the same folder or included in the classpath. The source.zip
* archive contains everything source-related, including the above classes. They can be recompiles using that if necessary.
************************************************************************************************************************************************************

I) Classes

Besides the provided source classes and compiled platform, we have created 4 classes of our own:

1) VanguardLeader.java

	The main bot class that extends the PlayerImpl class, implementing its methods. The endSimulation() method was changed to add a print of the total profit gained over the 30 days for debugging puposes and the proceedNewDay() method was changed to implement the functionality required for getting the price to be published in the new day. Additional methods added: getPrice(), getTotalProfit() and getRecords(). Most of these methods are self-explanatory and there are comments in the code for additional information. getPrice() contains the main logic to start the regression and based on that, return the new price to be published.

2) FollowerReaction.java

	This class models the follower player's reaction function and related logic. Relevant methods are: updateRecords(), doLinearRegression(), getFollowerReaction() - accessor method, findBestStrategy(). Again, the ones that are self-explanatory will not be explianed (additional comments in code), what matters here is doLinearRegression() which creates a LinearRegression object (details below) that will generate the values for 'a' and 'b' in the 'a + bx' linear reaction function.

3) LinearRegression.java

	This class models the mathematical linear regression used to approximate the values of 'a' and 'b' (commonly denoted as theta 0 and 1) from the function 'a + bx'. Relevant methods are: doRegression(), computeCost() - no longer used and gradientDescent().

	computeCost() was initially used to calculate the cost of the model and once it was tested, it was implemented in the gradientDescent() function.

	The model is computed and refined over a number of 1000 iterations, with a learning rate of 0.01. The learning rate represents how much the model changes from iteration to iteraion in order to best approximate a line to try to fit the entire dataset. Because the data is scattered over a broad area and not concentrated around certain points, using all the available history would generate a poor model with a high error (we measured the error as being the sum of all the difference between the model's prediction and the actual point for the entire set, divided by the number of available points). The final version is using the last 50 days to try and best approzimate all of them in a single linear model.

	The model is computed using the least squared error approach which is a minisation problem. Over the 1000 iterations it is trying to minimise the difference between the predicted point and the real one by adjusting the parameters of the hypothesis.

	hypothesis = theta0 + theta1 * X;

	theta0 and theta1 ====> the parameters that the program is trying to approximate;
	X ====> a real price provided by the leader.
	hypothesis ====> the prediction for the follower's price.

4) Matrix.java

	Custom class created to model mathematical matrices. Has methods such as transpose(), multiply() - both scalar and matrix-type multiplication, add(), subtract() and any other matrix type operation needed to compute the formulae used in the regression. The Matrix class was used mainly to keep the code readable and easy to follow and helped us to concentrate on implementing the linear regression once all the matrix operations were in place.


II) Description of flow of the application

As can be seen from the description of the classes above and the code, the flow of the logic is straightforward and can be broken up into a few steps:

	1) Get the history of the records of prices (strategies). Because we use the moving window approach, we usually get the records from the "last" 50 days (based on the current date).

	2) Launch the regression to estimate follower reaction function
		a) approximate and adjust the model on every iteration 
		b) the result is the best approximation while keeping the computation time down

	3) Having estimated the follower reaction, the application then finds the best strategy to publish (to maximise profit). This is done by finding the globam maximum of the function used to calculate the profit. The function in our case is (uL - 1) * (2 - uL + 0.3*uF), where uL is our strategy and uF is follower reaction. We have estimated the follower reaction, uF, as being a + bx, where a and b are the constants the regression returns. Replacing that in the above payoff function gives us an f(x) type function where x = uL. In order to find the maximum value of the function (maximum possible profit), we derive the function and set it equal to 0 -> f'(x) = 0. From this equation, we can then deduct x (uL) as the strategy that maximises our payoff.

	4) Last but not least, the new price is published to the platform and the current cycle (day) ends after the follower publishes his. This process repeats for every day until the game is over.