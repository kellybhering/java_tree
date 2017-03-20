package tree.codjava;

class ProductOfRedNodesVisitor extends TreeVis {
	private int _result = 1;
	
    public int getResult() {
    	
      	if(_result < 0)
      		_result = _result * -1;
      	
        return _result;
    }

    public void visitNode(TreeNode node) {
      	if (node.getColor() == Color.RED)
      		_result = _result * node.getValue();
    }

    public void visitLeaf(TreeLeaf leaf) {
    	if (leaf.getColor() == Color.RED)
      		_result = _result * leaf.getValue();
    }
}