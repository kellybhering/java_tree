package tree.codjava;

import java.util.ArrayList;
import java.util.Collections;

class TreeBuilder {

	public static boolean validateTreeSize(int size) {
		return size >= 2 && size <= Math.pow(10, 5);
	}

	public static boolean validateElements(int[] elements, int treeSize) {

		for (int b : elements) {
			if (b < 1 || b > Math.pow(10, 3))
				return false;
		}

		return elements.length == treeSize; // Constraint: 1 <= x[i] <=
											// math.pow(10,3)

	}

	public static boolean validateElementsColor(byte[] colors, int treeSize) {

		for (byte b : colors) {
			if (b != 0 && b != 1)
				return false;
		}

		return colors.length == treeSize;

	}

	public static boolean validateelementsEdges(int[][] elementsEdges, int treeSize) {
		// Constraint: 1 <= x[i] <= math.pow(10,3)
		for (int i = 0; i < elementsEdges.length; i++) {
			for (int j = 0; j < elementsEdges[i].length; j++) {
				if(elementsEdges[i][j] < 1 || elementsEdges[i][j] > treeSize)
					return false;
			}			
		}

		return true;
	}

	public static Tree buildTree(int treeSize, int[] elements, byte[] colors, int[][] elementsEdges) {

		ArrayList<Integer> treeFatherNodesNumber = new ArrayList<>();
		ArrayList<Tree> treeElements = new ArrayList<>();
		Tree element = null;
		int depth = 0;
		int fatherElementNumber = 0;
		TreeNode fatherElementNode = null;
		int treeElement = 0;

		// Find TreeNodes
		for (int i = 0; i < elementsEdges.length; i++) {
			if (!treeFatherNodesNumber.contains(elementsEdges[i][0]))
				treeFatherNodesNumber.add(elementsEdges[i][0]);

		}

		// Prepare treeElements
		for (int i = 0; i < elements.length; i++)
			treeElements.add(null);

		Collections.sort(treeFatherNodesNumber);

		// Insert TreeNodes
		for (int i = 0; i < treeFatherNodesNumber.size(); i++) {
			treeElement = treeFatherNodesNumber.get(i);

			fatherElementNumber = getElementFather(elementsEdges, treeElement);

			if (fatherElementNumber > 0)				
				fatherElementNode = (TreeNode) treeElements.get(fatherElementNumber - 1);

			depth = fatherElementNumber == 0 ? 0 : fatherElementNode.getDepth() + 1;

			element = new TreeNode(elements[treeElement - 1], colors[treeElement - 1] == 0 ? Color.RED : Color.GREEN, depth);
			treeElements.set(treeElement - 1, element);

			// Add child
			if (fatherElementNode != null)
				fatherElementNode.addChild(element);

		}

		// Insert TreeLeaves
		for (int i = 0; i < elements.length; i++) {
			if (!treeFatherNodesNumber.contains(i + 1)) {
				fatherElementNumber = getElementFather(elementsEdges, i + 1);
				fatherElementNode = (TreeNode) treeElements.get(fatherElementNumber - 1);
				depth = treeElements.get(fatherElementNumber - 1).getDepth() + 1;
				element = new TreeLeaf(elements[i], colors[i] == 0 ? Color.RED : Color.GREEN, depth);
				treeElements.set(i, element);
				fatherElementNode.addChild(element);
			}
		}

		
		return treeElements.get(0);
	}

	private static int getElementFather(int[][] elementsEdges, int elementNumber) {

		for (int i = 0; i < elementsEdges.length; i++) {

			if (elementsEdges[i][1] == elementNumber) {
				return elementsEdges[i][0];
			}
		}

		return 0;
	}

}
