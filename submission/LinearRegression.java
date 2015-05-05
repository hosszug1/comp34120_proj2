public class LinearRegression 
{
	public int numberOfDays, numberOfIterations;
	public double learningRate;
	public double[][] priceLeader;
	public double[] priceFollower;
	public double[] theta;

	// Constructor.
	public LinearRegression(int numberOfDays, int numberOfIterations, double learningRate, double[] priceLeader, double[] priceFollower) 
	{
		this.numberOfDays = numberOfDays;
		this.numberOfIterations = numberOfIterations;
		this.learningRate = learningRate;

		// Prepare theta.
		this.theta = new double[2];
		this.theta[0] = 0; this.theta[1] = 0;

		// Prepare price matrices.
		this.priceLeader = new double[numberOfDays][2];
		this.priceFollower = priceFollower;

		for (int i = 0; i < numberOfDays; i++)
		{
			this.priceLeader[i][0] = 1.0;
			this.priceLeader[i][1] = priceLeader[i];
		} // for

	} // LinearRegression

	public double[] doRegression()
	{

		// This is the initial cost.
		// At this point theta is (0,0) and the hypothesis will be 0.
		// The error will be maximum at this point.
		this.theta = gradientDescent(this.priceLeader, this.priceFollower, this.theta, this.learningRate, this.numberOfIterations);

		return this.theta;
	} // doRegression

	/* Function no longer used - gradientDescent now used */
	public double computeCost(double[][] x, double[] y, double[] theta)
	{
		int m = y.length;

		double result = 0;

		double[] hypothesis = new double[this.numberOfDays];
		double[] squaredErrors = new double[this.numberOfDays];

		// Estimate the follower's price using theta0 and theta1.
		hypothesis = Matrix.multiply(x, theta);
		squaredErrors = Matrix.subtract(hypothesis, y);
		// Matrix multiplication between x and theta.
		for (int i = 0; i < this.numberOfDays; i++)
		{
			// hypothesis[i] = x[i][0] * theta[0] + x[i][1] * theta[1];

			// Compute the squaredError between the hypothesis and the real price given by the follower.
			squaredErrors[i] = Math.pow(squaredErrors[i], 2);
		} // for

		// Sum all the squared errors.
		double sumSquaredErrors = 0.0;
		for (double error : squaredErrors)
		{
			sumSquaredErrors += error;
		} // for

		result = (1 / (2 * m)) * sumSquaredErrors;
		return result;
	} // computeCost


	public double[] gradientDescent(double[][] x, double[] y, double[] theta, double learningRate, int numberOfIterations)
	{
		double[] newThetas = new double[2];
		newThetas = theta;

		int m = y.length;
		double[] hypothesis = new double[this.numberOfDays];
		double[] diffHY = new double[this.numberOfDays];
		double[] xTransXdiffHY = new double[this.numberOfDays];
		double[][] xTranspose = Matrix.transpose(x);

		for (int i = 0; i < numberOfIterations; i++)
		{
			hypothesis = Matrix.multiply(x, theta);
			diffHY = Matrix.subtract(hypothesis, y);
			xTransXdiffHY = Matrix.multiply(xTranspose, diffHY);
			newThetas = Matrix.subtract(newThetas, Matrix.multiply(xTransXdiffHY, learningRate * (1.0 / m)));
		} // for

		return newThetas;
	} // gradientDescent

} // class LinearRegression