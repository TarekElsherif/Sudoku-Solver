import java.util.ArrayList;

public class Node {

	public String[][] state;
	public ArrayList<Node> successors = new ArrayList<Node>();
	public int[] domain = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	public Node parent;
	public int[] assignment; // [x, y, value]

	public Node() {
		state = new String[9][9];
		parent = null;
	}

	public Node(String[][] s) {
		state = s;
		parent = null;
	}

	public Node(String[][] s, Node p, int[] a) {
		state = s;
		parent = p;
		assignment = a;
	}

	public int[] getNextEmptyCell() {
		// returns the x and y values of the next empty cells in the node (e.g.
		// [3, 4] in which x = 3 and y = 4)
		int[] result = new int[2];
		for (int y = 0; y < state.length; y++) {
			for (int x = 0; x < state[y].length; x++) {
				// System.out.println(x + " " + y);
				if ((state[y][x].equals("*")) || (state[y][x].isEmpty())
						|| (state[y][x].equals("0"))) {
					result[0] = x;
					result[1] = y;
					return result;
				}
			}
		}
		return null;
	}
	
	public ArrayList<Integer[]> getAllEmptyCells() {
		ArrayList<Integer[]> arr = new ArrayList<Integer[]>();
		for (int y = 0; y < state.length; y++) {
			for (int x = 0; x < state[y].length; x++) {
				if (state[y][x].equals("*")) {
					Integer[] xy = {x, y};
					arr.add(xy);
				}
			}
		}
		return arr;
	}

	public void generateSuccessors() {
		// generates successors (possible moves) in the next empty cell
		// according
		// to the domain
		int[] nextEmptyCell = this.getNextEmptyCell();
		if (nextEmptyCell != null) {
			int x = nextEmptyCell[0];
			int y = nextEmptyCell[1];
			for (int j = 0; j < this.domain.length; j++) {
				String[][] tempState = new String[9][9];
				for (int i = 0; i < state.length; i++)
					tempState[i] = state[i].clone();
				tempState[y][x] = "" + domain[j];
				int[] change = { x, y, domain[j] };
				// System.out.println(change[0] + ", " + change[1] + ", value: "
				// + change[2]);
				this.successors.add(new Node(tempState, this, change));
			}
		}
	}

	public void generateSuccessorsMC(int x, int y, int[] value) {
		// generates successors (possible moves) in the given cell according to
		// the given domain
		for (int j = 0; j < value.length; j++) {
			String[][] tempState = new String[9][9];
			for (int i = 0; i < state.length; i++)
				tempState[i] = state[i].clone();
			tempState[y][x] = "" + value[j];
			int[] change = { x, y, j };
			System.out.println(change[0] + ", " + change[1] + ", value: "
					+ change[2]);
			this.successors.add(new Node(tempState, this, change));
		}

	}

	public String toString() {
		if (assignment != null) {
			return "" + showCells() + "\n=====" + "\nx: " + this.assignment[0]
					+ ", y: " + this.assignment[1] + ", value: "
					+ this.assignment[2] + "\n=====";
		} else {
			return "" + showCells() + "\n=====";
		}
	}

	public String showCells() {
		// returns the Sudoku grid as a String
		String result = "";
		for (int y = 0; y < this.state.length; y++) {
			for (int x = 0; x < state[y].length; x++) {
				result = result + " " + state[y][x] + " ";
			}
			result = result + "\n";
		}
		return result;
	}

	public void changeDomain(int[] newDomain) {
		// changes the domain of the node
		this.domain = new int[newDomain.length];
		for (int i = 0; i < newDomain.length; i++) {
			domain[i] = newDomain[i];
		}
	}

	public String getChange() {
		return "x: " + assignment[0] + ", y: " + assignment[1] + ", value: "
				+ assignment[2];
	}

	public ArrayList<Node> getSuccessors() {
		return successors;
	}

	public static void main(String[] args) {
		FileParser f = new FileParser();
		Node n = new Node(f.getCells());
		int[] v = { 2, 3, 4 };
		n.generateSuccessorsMC(1, 0, v);
		System.out.println(n.successors.get(0).toString());
	}
}