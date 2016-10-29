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

	public void generateSuccessors() {
		int[] nextEmptyCell = this.getNextEmptyCell();
		if (nextEmptyCell != null) {
			int x = nextEmptyCell[0];
			int y = nextEmptyCell[1];
			for (int j = 1; j <= 9; j++) {
				String[][] tempState = new String[9][9];
				for (int i = 0; i < state.length; i++)
					tempState[i] = state[i].clone();
				tempState[y][x] = "" + j;
				int[] change = { x, y, j };
				// System.out.println(change[0] + ", " + change[1] + ", value: "
				// + change[2]);
				this.successors.add(new Node(tempState, this, change));
			}
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
		String result = "";
		for (int y = 0; y < this.state.length; y++) {
			for (int x = 0; x < state[y].length; x++) {
				result = result + " " + state[y][x] + " ";
			}
			result = result + "\n";
		}
		return result;
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
	}
}