import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class mainer{
	public static String[][] startGrid = {
			{"3", "*", "*", "2", "4", "*", "*", "6", "*"},
			{"*", "4", "*", "*", "*", "*", "*", "5", "3"},
			{"1", "8", "9", "6", "3", "5", "4", "*", "*"},
			{"*", "*", "*", "*", "8", "*", "2", "*", "*"},
			{"*", "*", "7", "4", "9", "6", "8", "*", "1"},
			{"8", "9", "3", "1", "5", "*", "6", "*", "4"},
			{"*", "*", "1", "9", "2", "*", "5", "*", "*"},
			{"2", "*", "*", "3", "*", "*", "7", "4", "*"},
			{"9", "6", "*", "5", "*", "*", "3", "*", "2"}
		};
	
	public static String[][] correctGrid = {
			{"5", "3", "4", "6", "7", "8", "9", "1", "2"},
            {"6", "7", "2", "1", "9", "5", "3", "4", "8"},
            {"1", "9", "8", "3", "4", "2", "5", "6", "7"},
            {"8", "5", "9", "7", "6", "1", "4", "2", "3"},
            {"4", "2", "6", "8", "5", "3", "7", "9", "1"},
            {"7", "1", "3", "9", "2", "4", "8", "5", "6"},
            {"9", "6", "1", "5", "3", "7", "2", "8", "4"},
            {"2", "8", "7", "4", "1", "9", "6", "3", "5"},
            {"3", "4", "5", "2", "8", "6", "1", "7", "9"}
		};

	mainer()
	{
//		startGrid[][] = ;
	}
	
	public static void printGrid(String grid[][]){
		for(int i=0; i<grid.length; i++)
		{
			for(int j=0; j<grid[i].length; j++)
			{
				System.out.print("  " + grid[i][j]);
			}
			System.out.println('\n');
			
		}
	}
	
	public static boolean checkConstraints(String[][] grid){
		String currentValue;
		for(int i=0; i<grid.length; i++)
		{
			for(int j=0; j<grid[i].length; j++)
			{
				currentValue = grid[i][j];
				for(int z=j + 1; z<grid.length; z++)
				{
					if(grid[i][z].equals(currentValue) || grid[i][z].equals("*"))
					{
						return false;
					}
				}
				
				int w = 0, v = 0;
				if(j<3)
				{
					v = 2;
					if(i<3)
					{
						w = 2;
					}
					if(i<6 && i>3)
					{
						w = 5;
					}
					if(i>6)
					{
						w = 8;
					}
				}
				else if(j<6 && j>3)
				{
					v = 5;
					if(i<3)
					{
						w = 2;
					}
					if(i<6 && i>3)
					{
						w = 5;
					}
					if(i>6)
					{
						w = 8;
					}
				}
				else if(j>6)
				{
					v = 8;
					if(i<3)
					{
						w = 2;
					}
					if(i<6 && i>3)
					{
						w = 5;
					}
					if(i>6)
					{
						w = 8;
					}
				}
				
				int copies = 0;
				while(w>=0)
				{
					while(v>=0)
					{
						if(grid[w][v].equals(currentValue))
						{
							copies ++;
						}
						if(copies > 1)
						{
							return false;
						}
						v--;
					}
					w--;
				}
			}
		}
		return true;
	}
	
	public static void writeSolutionToFile(Node n) {
		String str = "Solution:\n";
		FileParser fp = new FileParser();
		str = str + n.showCells();
		Stack steps = new Stack();
		Node pn = n;
		while (pn.parent != null) {
			steps.push(pn);
			pn = pn.parent;
		}
		str = str + "\nPlacements:";
		while (!steps.isEmpty()) {
			Node x = (Node) steps.pop();
			str = str + "\n" + x.getChange();
		}
		fp.writeFile(str);
	}
	
	public static void main(String [] args)
	{
		FileParser fp = new FileParser();
		Node startNode = new Node(fp.getCells());
		Solver s = new Solver();
		s.depthFirst(startNode);
		System.out.println();
		System.out.println(s.stack.get(s.stack.size() - 1).toString());
		writeSolutionToFile(s.solution.get(0));
	}

}
