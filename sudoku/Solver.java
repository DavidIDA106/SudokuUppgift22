package sudoku;
public class Solver implements SudokuSolver{
    private int[][] board;
    public Solver() {
        board = new int[9][9];
    }

    @Override
    public boolean solve() {
        return legal(1, 0, 0);
    }

    @Override
    public boolean legal(int digit, int row, int col) {
        boolean isLegal = false;
        boolean checkRow = true;
        boolean checkCol = true;
        if(row == 9 && col == 9){
            return isLegal;
        }

        if(board[row][col] == 0){
            for(int i = 1; i <= 9; i++){
                for(int tempRow = 0; tempRow < 9; tempRow++) {
                    if(board[tempRow][col] == digit){
                        checkRow = false;  
                        break;
                    } 
                }

                for(int tempCol = 0; tempCol < 9; tempCol++){
                    if(board[row][tempCol] == digit){
                        checkCol = false;
                        break;
                    } 
                }
                int strow = row-(row%3);
                int stcol = col-(col%3);
            
                for(int x=strow; x<strow+3; x++) {
                    for(int y=stcol; y<stcol+3; y++) {
                        if(board[x][y]==i) {
                            return false;
                        }
                    }
                }
            }

        }

        return false;
    }

    public boolean checkSquare(int startRow, int startCol){

        return false; 
    }
    

    @Override
    public void set(int row, int col, int digit) {
        board[row][col] = digit;
    }

    @Override
    public void remove(int row, int col) {
        board[row][col] = 0;
    }

    @Override
    public void clear() {
        for(int row = 0; row < 9; row++){
            for(int col = 0; col < 9; col++){
                board[row][col] = 0;
            }
        }
    }

    @Override
    public void setMatrix(int[][] matrix) {
        board = matrix;
    }

    @Override
    public int[][] getMatrix() {
        return board;
    }
}
