public class ThreadK extends Thread{

    private final Matrix firstMatrix;
    private final Matrix secondMatrix;
    private final Matrix productMatrix;
    // k - the index of the cell where the computation begin
    private final Integer k;
    // stepSize - represents the number of cells I need to jump over
    private final Integer stepSize;
    private final Integer rows;
    private final Integer columns;

    ThreadK(Matrix firstMatrix, Matrix secondMatrix, Matrix productMatrix,
              int k, int stepSize, int rows, int columns) {
        this.firstMatrix = firstMatrix;
        this.secondMatrix = secondMatrix;
        this.productMatrix = productMatrix;
        this.k = k;
        this.stepSize = stepSize;
        this.rows = rows;
        this.columns = columns;
    }

    @Override
    public void run() {
        int row = 0;
        int column = k;

        while (true) {
            // overshoot - the number of lines I jumped over
            int overshoot = column / this.columns;
            row += overshoot;
            column -= overshoot * this.columns;

            if (row >= this.rows) {
                break;
            }

            this.productMatrix.set(row, column,
                    Matrix.rowAndColumnProduct(this.firstMatrix, this.secondMatrix, row, column));
            column += stepSize;
        }
    }

}
