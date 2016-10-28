import java.util.ArrayList;

public class Solver {
	public Node pre;
	public Node post;
	public ArrayList<Node> successors = new ArrayList<Node>();
	public ArrayList<Node> stack = new ArrayList<Node>();
	
	public Solver () {
	}
	
	
	public void depthFirst (Node n) {
		/* Here we first check this isn't the right one 
		 * if not we added to the stack */
		if(stack.contains(n))
		{
			stack.remove(n);
		}
		
		if(n.successors.isEmpty() != true)
		{
			successors.clear();
			successors = n.successors;			
			for(int i = successors.size()-1; i >= 0; i--){
				stack.add(successors.get(i));
			}
		}
		
		depthFirst(stack.get(stack.size() - 1));
	}
	
	public void breadthFirst (Node n) {
		/* Here we first check this isn't the right one
		 * if not, get its children */
		if(n.parent == null)
		{
			stack.add(n);
		}
		if(n.successors.isEmpty() != true)
		{
			stack.addAll(n.successors);
			
		}
		
		breadthFirst(stack.get(stack.indexOf(n) + 1));
	}
}
