package tree.codjava;

class SumInLeavesVisitor extends TreeVis {
	
	private int _result = 0;
    public int getResult() {      	
        return _result;
    }

    public void visitNode(TreeNode node) {
      	
    }

    public void visitLeaf(TreeLeaf leaf) {
    	_result += leaf.getValue();
    }
}