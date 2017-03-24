package tree.codjava;

class FancyVisitor extends TreeVis {
	private int _resultTreeNonLeaf = 0;
	private int _resultGreenLeaves = 0;
	
    public int getResult() {
    	return Math.abs(_resultTreeNonLeaf - _resultGreenLeaves);        
    }

    public void visitNode(TreeNode node) {
    	if (node.getDepth() % 2 == 0)
    		_resultTreeNonLeaf += node.getValue();
    }

    public void visitLeaf(TreeLeaf leaf) {
    	if (leaf.getColor() == Color.GREEN)
    		_resultGreenLeaves += leaf.getValue();
    }
}
