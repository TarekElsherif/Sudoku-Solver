import java.util.ArrayList;

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
			for (int j = 0; j < grid[i].length; j++) {
				currentValue = grid[i][j];
				for (int z = j + 1; z < grid.length; z++) {
					if (grid[i][z].equals(currentValue) || grid[i][z].equals("*") || currentValue.equals("*")) {
						return false;
					}
				}

				int w = 0, v = 0;
				if (j < 3) {
					v = 2;
					if (i < 3) {
						w = 2;
					}
					if (i < 6 && i > 3) {
						w = 5;
					}
					if (i > 6) {
						w = 8;
					}
				} else if (j < 6 && j > 3) {
					v = 5;
					if (i < 3) {
						w = 2;
					}
					if (i < 6 && i > 3) {
						w = 5;
					}
					if (i > 6) {
						w = 8;
					}
				} else if (j > 6) {
					v = 8;
					if (i < 3) {
						w = 2;
					}
					if (i < 6 && i > 3) {
						w = 5;
					}
					if (i > 6) {
						w = 8;
					}
				}

				int copies = 0;
				while (w >= 0) {
					while (v >= 0) {
						if (grid[w][v].equals(currentValue)) {
							copies++;
						}
						if (copies > 1) {
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

	public void getSolutionBranch(Node n) {
		solution.add(n);
		if (n.parent != null) {
			getSolutionBranch(n.parent);
		} else {
			return;
		}

	}

	public void depthFirst(Node n) {
		printGrid(n.state);
		System.out.println(stack.size() + " total");
		if (!checkConstraints(n.state)) {
			if (stack.contains(n)) {
				stack.remove(n);
				System.out.println(stack.size() + " after remove");
			}
			
			n.generateSuccessors();
			if (n.successors.isEmpty() != true) {
				successors.clear();
				successors = n.successors;
				for (int i = successors.size() - 1; i >= 0; i--) {
					stack.add(successors.get(i));
					System.out.println(stack.size() + " after add");
				}

				traverse.add(n);

			}

		} else {
			System.out.println("found");
			getSolutionBranch(n);
			return;
		}
		
		if(stack.size() > 0){
			depthFirst(stack.get(stack.size() - 1));
		} else {
			return;
		}


	}

	public void breadthFirst(Node n) {
		/*
		 * Here we first check this isn't the right one if not, get its children
		 */
		if (n.parent == null) {
			stack.add(n);
		}
		n.generateSuccessors();
		if (n.successors.isEmpty() != true) {
			stack.addAll(n.successors);

		}

		breadthFirst(stack.get(stack.indexOf(n) + 1));
	}

	public static void main(String[] args) {
		FileParser fp = new FileParser();
		Node startNode = new Node(fp.getCells());
		Solver s = new Solver();
		s.depthFirst(startNode);
		s.printGrid(s.solution.get(0).state);
	}
}
