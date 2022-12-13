package testqueue;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sudoku.Solver;
public class SolverTests {
    private Solver board;
    private int[][] matrix;
    
    @BeforeEach
    public void setUp() throws Exception{
        board = new Solver();
        matrix = new int[][]
        {{0, 0, 8, 0, 0, 9, 0, 6, 2},
        {0, 0, 0, 0, 0, 0, 0, 0, 5},
        {1, 0, 2, 5, 0, 0, 0, 0, 0},
        {0, 0, 0, 2, 1, 0, 0, 9, 0},
        {0, 5, 0, 0, 0, 0, 6, 0, 0},
        {6, 0, 0, 0, 0, 0, 0, 2, 8},
        {4, 1, 0, 6, 0, 8, 0, 0, 0},
        {8, 6, 0, 0, 3, 0, 1, 0, 0},
        {0, 0, 0, 0, 0, 0, 4, 0, 0}};
        
    }

    @AfterEach
    public void tearDown() throws Exception{
        board.clear();
    }

    @Test
    void testSolve(){
        board.setMatrix(matrix);
        assertTrue(board.solve(), "Did not solve");
    }

}
