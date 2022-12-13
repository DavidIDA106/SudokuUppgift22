package sudoku;

public class SudokuSolv implements SudokuSolver {
	private int[][] sudokuMatrix;
	
	public SudokuSolv() {
		sudokuMatrix = new int[9][9];
		
	}

	@Override
	public void set(int r, int c, int nbr) {
		if(r > 8 || r < 0 || c > 8 || c < 0) throw  new IllegalArgumentException();
		sudokuMatrix[r][c] = nbr;
	}

	public int getNumber(int r, int c) {
		if(r > 8 || r < 0 || c > 8 || c < 0) throw  new IllegalArgumentException();
		return sudokuMatrix[r][c];
	}

	@Override
	public void remove(int r, int c) {
		sudokuMatrix[r][c]  = 0;
	}

	@Override
	public boolean legal(int r, int c, int nbr) {
		if (r > 8 || r < 0 || c > 8 || c < 0 || nbr > 9 || nbr < 1 ) {
			throw  new IllegalArgumentException();
		}
		
		for (int i = 0; i < 9; i++) {
			if(sudokuMatrix[r][i] == nbr && i != c) {
				return false;
			}
			if(sudokuMatrix[i][c] == nbr && i != r) {
				return false;
			}
		}
		int x = r-r % 3;
		int y = c-c % 3;
		for(int i = x; i < x + 3; i++) {
			for (int j = y; j < y + 3; j++) {
				if(sudokuMatrix[i][j] == nbr && i != r && j != c) {
					return false;
				}
			}
		}	
		return true;
	}

	public boolean isAllValid() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if(getNumber(i,j) < 1) {
					return false;
				} else if (!legal(i,j,getNumber(i,j))) return false;
					
				
			}
		}
		return true;
	}

	@Override
	public boolean solve() {
		return solve(0,0);
	}
	private boolean solve(int r, int c) {
		if(c == 9) {
			r++;
			c = 0;
		} 
		if (r == 9) {
			return isAllValid();
		}
		if(getNumber(r,c) == 0) {
			for(int i = 1; i < 10; i++) {
				if(legal(r,c,i)) {
				
					set(r,c,i);
					if(solve(r,c +1)) {
						return true;
					}
				}
			}
			remove(r,c);
			return false;
		} else if(legal(r,c, getNumber(r,c))) {
			solve(r,c +1);
		} else return false;
		
		return isAllValid();
		
	}

	@Override
	public void clear() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				set(i,j,0);
			}
		}
		
	}

	@Override
	public int[][] getMatrix() {
		 for (int i = 0; i < 9; i++)
	            for (int j = 0; j < 9; j++) {
	                System.out.printf("%4d", getNumber(i,j));
	                if(j == 8) System.out.println("\t");
	            }
	        
	        System.out.println();
		return sudokuMatrix;
	}

	@Override
	public void setMatrix(int[][] nbrs) {
		if(nbrs.length > 9) throw new IllegalArgumentException();
		if(nbrs[0].length > 9) throw new IllegalArgumentException();
		for(int i=0; i<9; i++)
			  for(int j=0; j<9; j++)
				  sudokuMatrix[i][j]=nbrs[i][j];
	}

}

