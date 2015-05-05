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

		//prepare theta
		this.theta = new double[2];
		this.theta[0] = 0; this.theta[1] = 0;

		//prepare price matrices
		this.priceLeader = new double[numberOfDays][2];
		// this.priceFollower = new double[numberOfDays];

		// this.priceLeader = priceLeader;
		this.priceFollower = priceFollower;

		for (int i = 0; i < numberOfDays; i++)
		{
			this.priceLeader[i][0] = 1.0;
			this.priceLeader[i][1] = priceLeader[i];
		} // for

	} // LinearRegression

	public double[] doRegression()
	{

		// double[] j = new double[2];

		//this is the initial cost
		//at this point theta is (0,0) and the hypothesis will be 0
		//the error will be maximum at this point
		// j = computeCost(this.priceLeader, this.priceFollower, this.theta);
		this.theta = gradientDescent(this.priceLeader, this.priceFollower, this.theta, this.learningRate, this.numberOfIterations);

		// System.out.println("theta0 : " + this.theta[0] + " theta1 : " + this.theta[1]);

		return this.theta;
	} // doRegression

	public double computeCost(double[][] x, double[] y, double[] theta)
	{
		int m = y.length;

		double result = 0;

		double[] hypothesis = new double[this.numberOfDays];
		double[] squaredErrors = new double[this.numberOfDays];

		//estimate the follower's price using theta0 and theta1
		hypothesis = Matrix.multiply(x, theta);
		squaredErrors = Matrix.subtract(hypothesis, y);
		//matrix multiplication between x and theta
		for (int i = 0; i < this.numberOfDays; i++)
		{
			// hypothesis[i] = x[i][0] * theta[0] + x[i][1] * theta[1];

			//compute the squaredError between the hypothesis and the real price given by the follower
			squaredErrors[i] = Math.pow(squaredErrors[i], 2);
		} // for

		//sum all the squared errors
		double sumSquaredErrors = 0.0;
		for (double error : squaredErrors)
		{
			sumSquaredErrors += error;
		} // for

		result = (1 / (2 * m)) * sumSquaredErrors;
		return result;
	} // computeCost


	/* MIEZUL */
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