import java.util.ArrayList;

public class Node {

	public String[][] state;
	public ArrayList<Node> successors = new ArrayList<Node>();
	public int[] domain = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	public Node parent;
	public static int level = 0;

	public Node() {
		state = new String[9][9];
		parent = null;
		generateSuccessors();
	}

	public Node(String[][] s) {
		state = s;
		parent = null;
		generateSuccessors();
	}

	public Node(String[][] s, Node p) {
		state = s;
		parent = p;
		generateSuccessors();
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
			//System.out.println("Generating Successors!");
			int x = nextEmptyCell[0];
			int y = nextEmptyCell[1];
			for (int i = 1; i <= 9; i++) {
				String[][] tempState = this.state;
				tempState[y][x] = "" + i;
				System.out.println(x + ", " + y + ", value: " + i);
				this.successors.add(new Node(tempState, this));
			}
		}
	}

	public static void main(String[] args) {
		FileParser f = new FileParser();
		Node n = new Node(f.getCells());
		System.out.println(level);
	}
}
