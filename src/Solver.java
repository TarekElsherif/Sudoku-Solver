import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;

public class Solver {
	public Node pre;
	public Node post;
	public ArrayList<Node> successors = new ArrayList<Node>();
	public ArrayList<Node> stack = new ArrayList<Node>();
	public ArrayList<Node> traverse = new ArrayList<Node>();
	public ArrayList<Node> solution = new ArrayList<Node>();

	public Solver() {
	}

	public void printGrid(String grid[][]) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				System.out.print("  " + grid[i][j]);
			}
			System.out.println('\n');

		}
	}

	public boolean checkConstraints(String[][] grid) {
		String currentValue;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				currentValue = grid[i][j];
				if (currentValue.equals("*")) {
					return false;
				} else {
					for (int k = 0; k < grid.length; k++) {
						if (k != i) {
							if (grid[k][j].equals(currentValue) || grid[k][j].equals("*")) {
								// System.out.println("copies in the column");
								return false;
							}
						}
					}

					for (int l = 0; l < grid.length; l++) {
						if (l != j) {
							if (grid[i][l].equals(currentValue) || grid[i][l].equals("*")) {
								// System.out.println("copies in the row");
								return false;
							}
						}
					}
				}

				int lowerLimitX = 0;
				int upperLimitX = 0;
				int lowerLimitY = 0;
				int upperLimitY = 0;

				if (j < 3) {
					lowerLimitX = 0;
					upperLimitX = 3;
					if (i < 3) {
						lowerLimitY = 0;
						upperLimitY = 3;
					}
					if (i >= 3 && i < 6) {
						lowerLimitY = 3;
						upperLimitY = 6;
					}
					if (i >= 6) {
						lowerLimitY = 6;
						upperLimitY = 9;
					}
				}

				if (j >= 3 && j < 6) {
					lowerLimitX = 0;
					upperLimitX = 3;
					if (i < 3) {
						lowerLimitY = 0;
						upperLimitY = 3;
					}
					if (i >= 3 && i < 6) {
						lowerLimitY = 3;
						upperLimitY = 6;
					}
					if (i >= 6) {
						lowerLimitY = 6;
						upperLimitY = 9;
					}
				}

				if (j >= 6) {
					lowerLimitX = 0;
					upperLimitX = 3;
					if (i < 3) {
						lowerLimitY = 0;
						upperLimitY = 3;
					}
					if (i >= 3 && i < 6) {
						lowerLimitY = 3;
						upperLimitY = 6;
					}
					if (i >= 6) {
						lowerLimitY = 6;
						upperLimitY = 9;
					}
				}

				int copies = 0;
				while (lowerLimitX < upperLimitX) {
					while (lowerLimitY < upperLimitY) {
						if (grid[lowerLimitY][lowerLimitX].equals(currentValue)) {
							copies++;
						}

						if (copies > 1) {
							// System.out.println("many copies");
							return false;
						}

						lowerLimitY++;
					}
					lowerLimitX++;
				}
			}
		}

		return true;
	}

	public void getSolutionBranch(Node n) {
		solution.add(n);
		if (n.parent != null) {
			getSolutionBranch(n.parent);
		} else {
			return;
		}

	}

	public void depthFirst(Node n) {
		// printGrid(n.state);
		// System.out.println(stack.size() + " total");
		if (!checkConstraints(n.state)) {
			if (stack.contains(n)) {
				stack.remove(n);
				// System.out.println(stack.size() + " after remove");
			}

			n.generateSuccessors();
			if (n.successors.isEmpty() != true) {
				successors.clear();
				successors = n.successors;
				for (int i = successors.size() - 1; i >= 0; i--) {
					stack.add(successors.get(i));
					// System.out.println(stack.size() + " after add");
				}
				traverse.add(n);

			}

		} else {
			// System.out.println("found");
			solution.clear();
			getSolutionBranch(n);
			return;
		}

		if (stack.size() > 0) {
			depthFirst(stack.get(stack.size() - 1));
		} else {
			return;
		}

	}

	public void breadthFirst(Node n) {

		if (!checkConstraints(n.state)) {
			if (n.parent == null) {
				stack.add(n);
			}
			n.generateSuccessors();
			if (n.successors.isEmpty() != true) {
				stack.addAll(n.successors);
			}

			traverse.add(n);
		} else {
			// System.out.println("found");
			solution.clear();
			getSolutionBranch(n);
			return;
		}

		if (stack.indexOf(n) + 1 < stack.size()) {
			breadthFirst(stack.get(stack.indexOf(n) + 1));
		} else {
			return;
		}

	}

	public Node forwardChecking(Node n) {
		if (n.getAllEmptyCells().isEmpty()) {
			return n;
		} else {
			int[] axis = n.getNextEmptyCell();
			ArrayList<String> domain = domainCheckConstraints(n.state, axis[1], axis[0]);
			int[] domainInt = new int[domain.size()];
			for (int i = 0; i < domain.size(); i++) {
				domainInt[i] = Integer.parseInt(domain.get(i));
			}
			n.generateSuccessorsMC(axis[0], axis[1], domainInt);
			return forwardChecking(n.getSuccessors().get(0));
		}

		// solution.clear();
		// getSolutionBranch(n);
		// System.out.println(n.getSuccessors().get(0).toString());

	}

	public ArrayList<int[]> getEmptyCells(String[][] grid) {
		ArrayList<int[]> result = new ArrayList<int[]>();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				if (grid[i][j].equals("*")) {
					// System.out.println(new String[] {i+"",j+""});
					result.add(new int[] { i, j });
				}
			}
		}
		return result;
	}

	public void generateArcs(String[][] grid, ArrayList<int[]> emptyCells) {

	}

	public void arcConsistency(String[][] grid) {

	}

	public ArrayList<Integer[]> orderCellsMC(ArrayList<Integer[]> arr) {
		return null;
	}
	
	public static ArrayList<String> domainCheckConstraints(String[][] grid, int x, int y) {
		ArrayList<String> existingValues = new ArrayList<String>();

		for (int j = 0; j < grid.length; j++) {
			if (!grid[x][j].equals("*") && !existingValues.contains(grid[x][j])) {
				existingValues.add(grid[x][j]);
			}
		}
		for (int i = 0; i < grid.length; i++) {
			if (!grid[i][y].equals("*") && !existingValues.contains(grid[i][y])) {
				existingValues.add(grid[i][y]);
			}
		}

		int lowerLimitX = 0;
		int upperLimitX = 0;
		int lowerLimitY = 0;
		int upperLimitY = 0;

		if (x < 3) {
			lowerLimitX = 0;
			upperLimitX = 3;
			if (y < 3) {
				lowerLimitY = 0;
				upperLimitY = 3;
			}
			if (y >= 3 && y < 6) {
				lowerLimitY = 3;
				upperLimitY = 6;
			}
			if (y >= 6) {
				lowerLimitY = 6;
				upperLimitY = 9;
			}
		}

		if (x < 6 && x >= 3) {
			lowerLimitX = 3;
			upperLimitX = 6;
			if (y < 3) {
				lowerLimitY = 0;
				upperLimitY = 3;
			}
			if (y >= 3 && y < 6) {
				lowerLimitY = 3;
				upperLimitY = 6;
			}
			if (y >= 6) {
				lowerLimitY = 6;
				upperLimitY = 9;
			}
		}

		if (x >= 6) {
			lowerLimitX = 6;
			upperLimitX = 9;
			if (y < 3) {
				lowerLimitY = 0;
				upperLimitY = 3;
			}
			if (y >= 3 && y < 6) {
				lowerLimitY = 3;
				upperLimitY = 6;
			}
			if (y >= 6) {
				lowerLimitY = 6;
				upperLimitY = 9;
			}
		}

		while (lowerLimitX < upperLimitX) {
			while (lowerLimitY < upperLimitY) {
				if (!grid[lowerLimitX][lowerLimitY].equals("*")
						&& !existingValues.contains(grid[lowerLimitX][lowerLimitY])) {
					existingValues.add(grid[lowerLimitX][lowerLimitY]);
				}
				lowerLimitY++;
			}
			lowerLimitX++;
		}
		
		// add the non-existing values in an ArrayList
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 1; i <= 9; i++) {
			if (!existingValues.contains("" + i)) {
				result.add("" + i);
			}
		}
//		
//		return existingValues;
		return result;
	}

	public Node solveMostConstraint(Node n) {
		ArrayList<Integer[]> emptyCells = n.getAllEmptyCells();
		if (emptyCells.isEmpty()) {
			return n;
		}
		Integer[] firstEmpty = n.getAllEmptyCells().get(0);
		int min = domainCheckConstraints(n.state, firstEmpty[1], firstEmpty[0]).size();
		int minIndex = 0;
		for (int i = 1; i < emptyCells.size(); i++) {
			Integer[] curEmpty = emptyCells.get(i);
			int curSize = domainCheckConstraints(n.state, curEmpty[1], curEmpty[0]).size();
			System.out.println(curEmpty[0] + " " + curEmpty[1]);
			System.out.println("Size: " + curSize);
			if (curSize < min) {
				min = curSize;
				minIndex = i;
			}
		}
		Integer[] chosen = emptyCells.get(minIndex);
		// coverting
		ArrayList<String> domain = domainCheckConstraints(n.state, chosen[1], chosen[0]);
		int[] domainInt = new int[domain.size()];
		for (int i = 0; i < domain.size(); i++) {
			domainInt[i] = Integer.parseInt(domain.get(i));
		}
		n.generateSuccessorsMC(chosen[0], chosen[1], domainInt);
		stack.add(n);
		return solveMostConstraint(n.successors.get(0));
	}
	
	public Node arcConsistency(Node n) {
		ArrayList<Integer[]> emptyCells = n.getAllEmptyCells();
		if(emptyCells.isEmpty()) {
			//printGrid(n.state);
			return null;
		} else {
			ArrayList<Integer[]> domains = new ArrayList<Integer[]>();
			for(int i = 0; i < emptyCells.size(); i++){
				Integer[] cell = emptyCells.get(i);
				ArrayList<String> domainString = domainCheckConstraints(n.state, cell[1], cell[0]);
				Integer[] domainInt = new Integer[domainString.size()];
				//System.out.println(domain[1]);
				for(int j = 0; j<domainString.size(); j++){
					domainInt[j] = Integer.parseInt(domainString.get(j));					
				}
				domains.add(domainInt);
				
			}
			Node curNode = n;
			while(!emptyCells.isEmpty()){
				for(int l = 0; l <domains.get(0).length; l++){
					if(!wipeout(domains.get(0)[l], 0, domains)){
						n.state[emptyCells.get(0)[1]][emptyCells.get(0)[0]] = domains.get(0)[l].toString();
						int[] change = { emptyCells.get(0)[0], emptyCells.get(0)[1], domains.get(0)[l] };
						Node newNode = new Node(n.state, curNode, change);
						emptyCells.remove(0);
						domains.remove(0);
						curNode = newNode;
						break;
					}
				}
				solution.add(curNode);
			}
			printGrid(n.state);
			return curNode;
		}
	}
	
	
	public boolean wipeout (int value,int index, ArrayList<Integer[]> domains) {
		for(int i = 0; i < domains.size(); i++){
			if(i != index) {
				Integer[] domain = domains.get(i);
				if(domain.length == 1 && domain[0] == value){
					return true;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		FileParser fp = new FileParser();
		Node startNode = new Node(fp.getCells());
		Solver s = new Solver();
		s.getEmptyCells(startNode.state);
	}
}