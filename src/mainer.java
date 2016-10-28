

import java.util.Arrays;

public class mainer{
	public static char[][] startGrid = {
			{'3', '*', '*', '2', '4', '*', '*', '6', '*'},
			{'*', '4', '*', '*', '*', '*', '*', '5', '3'},
			{'1', '8', '9', '6', '3', '5', '4', '*', '*'},
			{'*', '*', '*', '*', '8', '*', '2', '*', '*'},
			{'*', '*', '7', '4', '9', '6', '8', '*', '1'},
			{'8', '9', '3', '1', '5', '*', '6', '*', '4'},
			{'*', '*', '1', '9', '2', '*', '5', '*', '*'},
			{'2', '*', '*', '3', '*', '*', '7', '4', '*'},
			{'9', '6', '*', '5', '*', '*', '3', '*', '2'}
		};
	
	public static char[][] correctGrid = {
			{'5', '3', '4', '6', '7', '8', '9', '1', '2'},
            {'6', '7', '2', '1', '9', '5', '3', '4', '8'},
            {'1', '9', '8', '3', '4', '2', '5', '6', '7'},
            {'8', '5', '9', '7', '6', '1', '4', '2', '3'},
            {'4', '2', '6', '8', '5', '3', '7', '9', '1'},
            {'7', '1', '3', '9', '2', '4', '8', '5', '6'},
            {'9', '6', '1', '5', '3', '7', '2', '8', '4'},
            {'2', '8', '7', '4', '1', '9', '6', '3', '5'},
            {'3', '4', '5', '2', '8', '6', '1', '7', '9'}
		};

	mainer()
	{
//		startGrid[][] = ;
	}
	
	public static void dfs(char grid[][]){
		int value = 0;
		char valuec;
		for(int i=0; i<grid.length; i++)
		{
			for(int j=0; j<grid[i].length; j++)
			{
				System.out.print("  " + grid[i][j]);
			}
			System.out.println('\n');
		}
	}
	
	public static boolean checkConstraints(char[][] grid){
		char currentValue;
		for(int i=0; i<grid.length; i++)
		{
			for(int j=0; j<grid[i].length; j++)
			{
				currentValue = grid[i][j];
				for(int z=j + 1; z<grid.length; z++)
				{
					if(grid[i][z] == currentValue || grid[i][z] == '*')
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
						if(grid[w][v] == currentValue)
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
	
	public static void main(String [] args)
	{
		char[][] grid = {{'3', '1', '5', '2', '4', '7', '8', '6', '9'},{'1', '4', '2', '6', '7', '8', '9', '5', '3'}};
		dfs(correctGrid);
		boolean test = checkConstraints(correctGrid);
		System.out.println(test);
	}

}
