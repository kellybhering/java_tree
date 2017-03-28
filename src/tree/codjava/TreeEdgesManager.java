package tree.codjava;

import java.util.ArrayList;

class TreeEdgesManager {
	private ArrayList<TreeEdge> edgesList;
	
	public TreeEdgesManager(int listSize) {
		edgesList = new ArrayList<>(listSize);
		for (int i = 0; i < listSize; i++) {
			edgesList.add(null);
		}
	}
	
	public TreeEdge getEdgeByKey(int key) {
		for (TreeEdge b : edgesList) {
			if (b != null) {
				if (b.getKey() == key)
					return b;
			}
		}
		
		return null;
	}
	
	public TreeEdge getEdgeByIndex(int index) {		
		return edgesList.get(index);		
	}
	
	public void setEdge(TreeEdge treeEdge, int index) {
		edgesList.set(index, treeEdge);		
	}
	
	public ArrayList<TreeEdge> getEdgesList() {
		return edgesList;
	}
	
	public void addEdge(TreeEdge treeEdge) {
		edgesList.add(treeEdge);		
	}
	
}
