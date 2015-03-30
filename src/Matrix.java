final public class Matrix 
{
	// Number of rows.
    private final int N;
    // Number of columns.
    private final int M;
    // The 2D array used to hold the data.
    private final double[][] data;

    // Create an N-by-M matrix of 0's.
    public Matrix(int n, int m) 
    {
        N = n;
        M = m;
        data = new double[N][M];
    } // Matrix

    // Create matrix based on 2D array.
    public Matrix(double[][] data) 
    {
        N = data.length;
        M = data[0].length;
        this.data = new double[N][M];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                    this.data[i][j] = data[i][j];
    } // Matrix

    // Copy constructor.
    private Matrix (Matrix originalMatrix) 
    { 
    	this(originalMatrix.data);
    } // Matrix

    // Create and return a random N-by-M matrix with values between 0 and 1.
    public static Matrix random(int N, int M) {
        Matrix newMatrix = new Matrix(N, M);
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                newMatrix.data[i][j] = Math.random();
        return newMatrix;
    } // random

    // Create and return the transpose of the invoking matrix.
    public Matrix transpose()
    {
        Matrix transposedM = new Matrix(M, N);
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                transposedM.data[j][i] = this.data[i][j];
        return transposedM;
    } // transpose

    // Sum of two matrices.
    public Matrix plus(Matrix other)
    {
        // Matrix A = this;
        if (other.N != this.N || other.M != this.M) throw new RuntimeException("Illegal matrix dimensions! Number of rows and columns have to match!");
        Matrix result = new Matrix(N, M);
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                result.data[i][j] = this.data[i][j] + other.data[i][j];
        return result;
    } // plus

    // Difference of two matrices.
    public Matrix minus(Matrix other) 
    {
        // Matrix A = this;
        if (other.N != this.N || other.M != this.M) throw new RuntimeException("Illegal matrix dimensions! Number of rows and columns have to match!");
        Matrix result = new Matrix(N, M);
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                result.data[i][j] = this.data[i][j] - other.data[i][j];
        return result;
    } // minus

    // Equals method.
    public boolean equals(Matrix other) 
    {
        // Matrix A = this;
        other = (Matrix) other;
        if (other.N != this.N || other.M != this.M) throw new RuntimeException("Illegal matrix dimensions! Number of rows and columns have to match!");
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if (this.data[i][j] != other.data[i][j]) return false;
        return true;
    } // equals

    // Compare method.
    public int compareTo(Matrix other)
    {
		// Matrix A = this;
        if (other.N != this.N || other.M != this.M) throw new RuntimeException("Illegal matrix dimensions! Number of rows and columns have to match!");
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if (this.data[i][j] > other.data[i][j]) 
                	return 1;
            	else if (this.data[i][j] < other.data[i][j]) 
            		return -1;
		return 0;
    } // compareTo

    // Matrix product.
    public Matrix times(Matrix other) 
    {
        // Matrix A = this;
        if (this.M != other.N) throw new RuntimeException("Illegal matrix dimensions!");
        Matrix result = new Matrix(this.N, other.M);
        for (int i = 0; i < result.N; i++)
            for (int j = 0; j < result.M; j++)
                for (int k = 0; k < this.N; k++)
                    result.data[i][j] += (this.data[i][k] * other.data[k][j]);
        return result;
    } // times

    // Scalar product.
    public Matrix times(double scalar)
    {
    	Matrix result = new Matrix(N, M);
    	for (int i = 0; i < N; i++)
    		for (int j = 0; j < M; j++)
    			result.data[i][i] = this.data[i][j] * scalar;
    	return result;
    } // times

    // Get matrix in printable form.
    @Override
    public String toString() 
    {
    	String printableMatrix = "";
        for (int i = 0; i < N; i++) 
        {
            for (int j = 0; j < M; j++) 
                // System.out.printf("%9.4f ", data[i][j]);
                printableMatrix += String.format("%9.4f ", data[i][j]);
            // System.out.println();
            printableMatrix += "\n";
        } // for

        return printableMatrix;
    } // toString

    // Method that prints out matrix, for test purposes.
    // (Uses existing toString implementation)
    public void show()
    {
    	String output = this.toString();
    	System.out.print(output);
    } // show

    // Main method to test matrix operations.
    public static void main(String[] args) 
    {
        double[][] d = { { 1, 2, 3 }, { 4, 5, 6 }, { 9, 1, 3} };
        Matrix D = new Matrix(d);
        D.show();        
        System.out.println();

        Matrix A = Matrix.random(5, 5);
        A.show(); 
        System.out.println();

        Matrix B = A.transpose();
        B.show(); 
        System.out.println();

        Matrix C = Matrix.random(5, 5);
        C.show(); 
        System.out.println();

        A.plus(B).show();
        System.out.println();

        B.times(A).show();
        System.out.println();

        // Shouldn't be equal since AB != BA in general.
        System.out.println(A.times(B).equals(B.times(A)));
        System.out.println();

        Matrix b = Matrix.random(5, 1);
        b.show();
        System.out.println();
    } // main

} // class Matrix