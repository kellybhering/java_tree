package tree.codjava;

class FancyVisitor extends TreeVis {
	private int _resultTreeNonLeaf = 0;
	private int _resultGreenLeaves = 0;
	
    public int getResult() {
      	int result = _resultTreeNonLeaf - _resultGreenLeaves;
        return result < 0 ? result * -1 : result;
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
