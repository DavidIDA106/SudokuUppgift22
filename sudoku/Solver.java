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
        if(row == 8 && col == 9){
            return true;
        }

        if(col == 9) {
            row++;
            col = 0;
        }

        if(board[row][col] != 0){
            legal(digit, row, col + 1);
        }

        for(digit = 1; digit <= 9; digit++){
            
            if(isOK(digit, row, col)){
                set(row, col, digit);
        
                if(legal(digit, row, col + 1)) return true;
    
            }
            
            board[row][col] = 0;
        }

        return false;
    }

    public boolean isOK(int num, int row, int col){
        for(int tempRow = 0; tempRow < 9; tempRow++) {
            if(board[tempRow][col] == num){
                return false;
            } 
        }

        for(int tempCol = 0; tempCol < 9; tempCol++){
            if(board[row][tempCol] == num){
                return false;
            } 
        }
        int strow = row-(row%3);
        int stcol = col-(col%3);
    
        for(int x=strow; x<strow+3; x++) {
            for(int y=stcol; y<stcol+3; y++) {
                if(board[x][y]==num) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void set(int row, int col, int digit) {
        if(row > 8 || row < 0 || col > 8 || col < 0) throw  new IllegalArgumentException();
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
        if(matrix.length > 9) throw new IllegalArgumentException();
		if(matrix[0].length > 9) throw new IllegalArgumentException();
		for(int i=0; i<9; i++)
			  for(int j=0; j<9; j++)
				  board[i][j]=matrix[i][j];
    }

    @Override
    public int[][] getMatrix() {
        return board;
    }
}
