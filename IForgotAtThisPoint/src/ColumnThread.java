public class ColumnThread extends Thread{
    private final Matrix firstMatrix;
    private final Matrix secondMatrix;
    private final Matrix productMatrix;
    private final Integer left;
    private final Integer right;
    private final Integer rows;

    public ColumnThread(Matrix firstMatrix, Matrix secondMatrix, Matrix productMatrix,
                        Integer left, Integer right, Integer rows) {
        this.firstMatrix = firstMatrix;
        this.secondMatrix = secondMatrix;
        this.productMatrix = productMatrix;
        this.left = left;
        this.right = right;
        this.rows = rows;
    }

    @Override
    public void run() {
        int row = this.left % this.rows;
        int column = this.left / this.rows;
        // k - number of computed cells in resulting matrix
        int k = this.right - this.left;

        for(int i = 0; i < k; ++i) {
            this.productMatrix.set(row, column,
                    Matrix.rowAndColumnProduct(this.firstMatrix, this.secondMatrix, row, column));

            ++row;
            if(row == this.rows) {
                row = 0;
                ++column;
            }
        }
    }
}
