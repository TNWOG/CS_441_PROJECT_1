package boothAlgorithmPkg;

//import BoothAlgorithm.java;
import boothAlgorithmPkg.BoothAlgorithm;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;

/* Creating a User Input
 * Scanner scan = new Scanner(System.in);
 * system.out.println("Enter a Number: ");
 * int i = scan.nextInt();*/



public class Table_Group_Project extends JPanel {
	private boolean DEBUG = false;
	
	
	private static String[][] data = new String[9][5];
	
	public static void fillData(int i, String items,  String MCAND, String bin, int dec) 
	{
	
		data[i][0] = Integer.toString(i+1);
		data[i][1] = items;
		data[i][2] = MCAND;
		data[i][3] = bin;
		data[i][4] = Integer.toString(dec);
		
	}
	
	
	public Table_Group_Project(){
		
		
		//5 is the amount of columns and the other is rows
		super(new GridLayout(8,5));
		
		//User Input One
		System.out.println("Enter 1st Number: ");
		Scanner user_input = new Scanner (System.in);
		String Input = user_input.next();
		System.out.println("You Entered: " + Input);
		
		//User Input Two
		System.out.println("Enter 2nd Number: ");
		Scanner user_input2 = new Scanner (System.in);
		String Input2 = user_input2.next();
		System.out.println("You Entered: " + Input2);
		
		//getColumns();
		//or
		//getRows()
		String[] columnNames = {"Iteration",
								"Items",
								"MCAND",
								"Product In Binary",
								"Product In Decimal"};
	/*	String[][] data = new String[8][5];
		for (int i = 0; i < 8; i++)
		{
			data[i][0] = Integer.toString(i+1);
			data[i][1] = "";
			data[i][2] = "";
			data[i][3] = BoothAlgorithm.multiply(Integer.parseInt(Input),Integer.parseInt(Input2));
			data[i][4] = "";
		}*/
		
		//String[][] data = {
				//{System.out.println(/*"Iteration: "*/ + (i+1)), 
				//System.out.println(/*"           Post mcand operation: "*/ + product),
				//System.out.println(/*"           Post shift: "*/ + product),
				//System.out.println(/*"\n" + "Product in Binary: "*/ + product),
				//System.out.println(/*"Product in Decimal: "*/ + binToInt(product))},
				
				//{new Integer(i+1), new String(Items), new Integer(product),
				//new Integer(product),new Integer(product)}
				
				//{},
		BoothAlgorithm.multiply(Integer.parseInt(Input),Integer.parseInt(Input2));
	//	};
		
		final JTable table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(500,70));
		table.setFillsViewportHeight(true);
		
		if (DEBUG) {
            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    printDebugData(table);
                }
            });
        }
 
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
 
        //Add the scroll pane to this panel.
        add(scrollPane);
    }
 
    private void printDebugData(JTable table) {
        int numRows = table.getRowCount();
        int numCols = table.getColumnCount();
        javax.swing.table.TableModel model = table.getModel();
 
        System.out.println("Value of data: ");
        for (int i=0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j=0; j < numCols; j++) {
                System.out.print("  " + model.getValueAt(i, j));
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }
 
    private static void createAndShowGUI() {
    	
        //Create and set up the window.
        JFrame frame = new JFrame("Table_Group_Project");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
        Table_Group_Project newContentPane = new Table_Group_Project();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
    	
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
