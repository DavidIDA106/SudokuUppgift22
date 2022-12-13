package sudoku;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;



@SuppressWarnings("serial")
public class SudokuController extends JFrame {
	private Color background = new Color(255, 120, 0);
	private JPanel p2;
	private JTextField[][] field;
	private Border fieldBorder;
	private Font font;
	
	public static void main(String[] args) {
		 new SudokuController();
	}


	  public SudokuController() {
		  	
		  	fieldBorder = new LineBorder(Color.BLACK);
		  	font = new Font("Verdana", Font.CENTER_BASELINE, 20);
		  	JButton solve = new JButton("Solve");
		  	JButton clear = new JButton("Clear");
	       
		  	clear.addActionListener(e -> {
	    	   clearAll(getContentPane());
		  	});
		  	solve.addActionListener(e -> {
		  		solve();
		  	});
		  	p2 = null;
		  	field = new JTextField[9][9];
	       for (int k = 0; k < 9; k++) {
	    	   p2 = new JPanel(new GridLayout(9,9));
	        	p2.setBorder(fieldBorder); 
	        	for (int i = 0; i <9; i++) {
		        	for(int j =0; j <9; j++){
		        		field[i][j] = new JTextField(2);
		        		field[i][j].addKeyListener(new KeyAdapter() {
		                    public void keyTyped(KeyEvent evt) {
		                        if (!Character.isDigit(evt.getKeyChar())) {
		                            evt.consume();
		                        }
		                        
		                    }
		                });
		        		if (i < 3 && j < 3 || i < 9 && i > 5 && j  < 3 ||
		        				j < 9 && j > 5 && i  < 3 
		        				|| j < 9 && j > 5 && i  < 9 && i > 5 ||
		        				j < 6 && j > 2 &&  i < 6 && i > 2) 
		        			field[i][j].setBackground(background);
		        		
		        		field[i][j].setDocument(new JTextFieldLimit(1));
		        		field[i][j].setFont(font);
			            field[i][j].setHorizontalAlignment(JTextField.CENTER); 
			            field[i][j].setBorder(fieldBorder); 
			            p2.add(field[i][j]);
		        	}            
		        }
	       }
		  	        
	        JPanel Buttons = new JPanel(new GridLayout(9,1));
	        Buttons.add(solve,  BorderLayout.SOUTH);
	        Buttons.add(clear, BorderLayout.SOUTH);

	       add(p2);
	       add(Buttons);
	       setTitle("Sudoku");
	       setLayout(new GridLayout(1,4));
	       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       getContentPane();
	       pack();
	       setLocationByPlatform(true);
	       setVisible(true);
	  }
	  
	  private void clearAll(Container myContainer) {

		    Component myComps[] = myContainer.getComponents();

		    for (int i=0; i<myComps.length; i++) {
		      if(myComps[i] instanceof JPanel) {
		          JPanel myPanel = (JPanel) myComps[i];
		          clearAll(myPanel);
		      }
		      if(myComps[i] instanceof JTextField) {
		        JTextField myTextField = (JTextField) myComps[i];
		        myTextField.setText("");
		      }
		    }        
		}
	  
	  private void solve() {
		  int[][] temp = new int[9][9];
		  SudokuSolv solver = new SudokuSolv();
		  for (int i = 0; i < 9; i++) {
			  for (int j = 0; j < 9; j++) {
				  if(field[i][j].getText().isEmpty()) {
					  temp[i][j] = 0;
				  }else  temp[i][j] = Integer.parseInt(field[i][j].getText());
			  }
		  }
		  solver.setMatrix(temp);
		  solver.solve();
		  if (solver.solve()) {
			  for (int i = 0; i < 9; i++) {
				  for (int j = 0; j < 9; j++) {
					  field[i][j].setText(String.valueOf(solver.getNumber(i,j)));
				  }
			  }
		  } else  JOptionPane.showMessageDialog(getContentPane(), "Unsolvable sudoku"); 
	  }
}