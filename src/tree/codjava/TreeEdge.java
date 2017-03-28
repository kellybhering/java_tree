package tree.codjava;
import java.util.ArrayList;
import java.util.Iterator;

class TreeEdge implements Iterable<Integer>  {
	
	private int key;
	private ArrayList<Integer> edges;
	
	public TreeEdge(int key) {
		this.key = key;
		edges = new ArrayList<>();
	}
	
    public int getKey() {
        return key;
    }
    
    public void addEdge(int edge) {
    	edges.add(edge);
    }
    
    public ArrayList<Integer> getEdges() {
    	return edges;
    }
    
    public void removeEdge(int edge) {
    	//edges.removeIf(a -> a == edge);
    	
    	for (Object b : edges) {
    		if ((int)b == edge) {
    			edges.remove(b);
    			return;
    		}
    	}
    }
    
    @Override
	public Iterator<Integer> iterator() {
	  return edges.iterator();
	}

}
