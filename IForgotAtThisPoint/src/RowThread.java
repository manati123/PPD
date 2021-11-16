public class RowThread extends Thread{
    private final Matrix firstMatrix;
    private final Matrix secondMatrix;
    private final Matrix productMatrix;
    private final Integer left;
    private final Integer right;
    private final Integer columns;

    public RowThread(Matrix firstMatrix, Matrix secondMatrix, Matrix productMatrix,
                     Integer left, Integer right, Integer columns) {
        this.firstMatrix = firstMatrix;
        this.secondMatrix = secondMatrix;
        this.productMatrix = productMatrix;
        this.left = left;
        this.right = right;
        this.columns = columns;
    }

    @Override
    public void run() {
        int row = this.left / this.columns;
        int column = this.left % this.columns;
        int k = this.right - this.left;

        for(int i = 0; i < k; ++i) {
            this.productMatrix.set(row, column,
                    Matrix.rowAndColumnProduct(this.firstMatrix, this.secondMatrix, row, column));

            ++column;
            if(column == this.columns) {
                column = 0;
                ++row;
            }
        }
    }
}
