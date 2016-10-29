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
		printGrid(n.state);
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
			System.out.println("found");
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
			System.out.println("found");
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
	
	public Node getResultNode() {
		if (!stack.isEmpty()) {
			return solution.get(0);
		} else {
			return null;
		}
	}

	public static void main(String[] args) {
		FileParser fp = new FileParser();
		Node startNode = new Node(fp.getCells());
		Solver s = new Solver();
		s.breadthFirst(startNode);
		s.printGrid(s.solution.get(0).state);
	}
}