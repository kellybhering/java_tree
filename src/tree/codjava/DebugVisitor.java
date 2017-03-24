package tree.codjava;

class DebugVisitor extends TreeVis {
	private long  _result = 1;
	private final int M = 1000000007;
	
    public int getResult() {	
      	
        return (int) _result;
    }   
    

    public void visitNode(TreeNode node) {
      	if (node.getColor() == Color.RED)
      		_result = (_result * node.getValue())  % M;
      	
      	System.out.println("Node: " + node.getValue());
    }

    public void visitLeaf(TreeLeaf leaf) {
    	if (leaf.getColor() == Color.RED)
      		_result = (_result * leaf.getValue())  % M;
    	
    	System.out.println("Tree: " + leaf.getValue());
    }
}