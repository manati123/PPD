import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Matrix {
    List<List<Integer>> matrix;
    private final Integer rows;
    private final Integer columns;

   public Matrix(Integer rows, Integer columns) {
        this.rows = rows;
        this.columns = columns;
        this.matrix = new ArrayList<>(this.rows);

        this.createRandomMatrix();
    }

    public void createRandomMatrix() {
        Random random = new Random();

        for(int i = 0; i < this.rows; ++i) {
            this.matrix.add(new ArrayList<>(this.columns));
            for(int j = 0; j < this.columns; ++j) {
                int value = random.nextInt(10);
                this.matrix.get(i).add(value);
            }
        }
    }

    // This function returns the element from a given row and column
    public int get(int row, int column) {
        return this.matrix.get(row).get(column);
    }

    // This function sets a given value in a matrix
    public void set(int row, int col, int value){
        this.matrix.get(row).set(col, value);
    }

    // This function represents the first step of multiplication algorithm:
    // it computes a single element of the resulting matrix.
    public static int rowAndColumnProduct(Matrix firstMatrix, Matrix secondMatrix,
                                                 int row, int column) {

        int sum = 0;
        // or secondMatrix.rows
        for(int i = 0; i < firstMatrix.columns; ++i) {
            int product = firstMatrix.get(row, i) * secondMatrix.get(i, column);
            sum += product;
        }

        return sum;
    }

    // This function computes sequentially the product of two matrices
    public static Matrix computeSequentiallyProduct(Matrix firstMatrix, Matrix secondMatrix) {
        Matrix productMatrix = new Matrix(firstMatrix.rows, secondMatrix.columns);

        for(int i = 0; i < firstMatrix.rows; ++i) {
            for(int j = 0; j < secondMatrix.columns; ++j) {
                int value = rowAndColumnProduct(firstMatrix, secondMatrix, i, j);
                productMatrix.set(i, j, value);
            }
        }

        return productMatrix;
    }

    public String printMatrix() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < this.rows; i++){
            for(int j = 0; j < this.columns; j++) {
                stringBuilder.append(get(i, j)).append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
