package tree.codjava;

import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) {

		Tree root = solveAdapted();

		// DebugVisitor visDebug = new DebugVisitor();
		// root.accept(visDebug);

		SumInLeavesVisitor vis1 = new SumInLeavesVisitor();
		ProductOfRedNodesVisitor vis2 = new ProductOfRedNodesVisitor();
		FancyVisitor vis3 = new FancyVisitor();

		root.accept(vis1);
		root.accept(vis2);
		root.accept(vis3);

		int res1 = vis1.getResult();
		int res2 = vis2.getResult();
		int res3 = vis3.getResult();

		System.out.println(res1);
		System.out.println(res2);
		System.out.println(res3);

		verifyResult(res1, res2, res3);

	}

	private static int[] values;
	private static Color[] colors;
	private static TreeEdgesManager myMap;
	static BufferedReader br = null;
	private static final String FILENAME = "C:\\Teste\\Input\\testCase12_Input.txt";
	private static final String FILENAMEOUTPUT = "C:\\Teste\\Output\\testCase12_Output.txt";

	public static Tree solveAdapted() {
		
		try {
			Scanner scan = new Scanner(new File(FILENAME));

			int numNodes = scan.nextInt();

			if (numNodes == 1) {
				return new TreeLeaf(values[0], colors[0], 0);
			}

			values = new int[numNodes];
			colors = new Color[numNodes];
			myMap = new TreeEdgesManager(numNodes);

			for (int i = 0; i < numNodes; i++) {
				values[i] = scan.nextInt();
			}
			for (int i = 0; i < numNodes; i++) {
				colors[i] = scan.nextInt() == 0 ? Color.RED : Color.GREEN;
			}

			for (int i = 0; i < numNodes - 1; i++) {
				int u = scan.nextInt();
				int v = scan.nextInt();

				TreeEdge uNeighbors = myMap.getEdgeByIndex(u - 1);
				if (uNeighbors == null) {
					uNeighbors = new TreeEdge(u);
				}
				uNeighbors.addEdge(v);
				myMap.setEdge(uNeighbors, u - 1);
				//myMap.addEdge(uNeighbors);

				TreeEdge vNeighbors = myMap.getEdgeByIndex(v - 1);
				if (vNeighbors == null) {
					vNeighbors = new TreeEdge(v);
				}
				vNeighbors.addEdge(u);
				myMap.setEdge(vNeighbors, v - 1);
				//myMap.addEdge(vNeighbors);
			}
			scan.close();
			
			TreeNode root = new TreeNode(values[0], colors[0], 0);
			addChildrenAdapted(root, 1);
			return root;
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		}
		
		return null;

	}

	private static void addChildrenAdapted(TreeNode parent, Integer parentNum) {

		TreeEdge edgesByParent = myMap.getEdgeByKey(parentNum);
		TreeEdge childEdge;
		for (Integer treeNum : edgesByParent) {
			childEdge = myMap.getEdgeByKey(treeNum);
			childEdge.removeEdge(parentNum);

			ArrayList<Integer> grandChildren = childEdge.getEdges();
			boolean childHasChild = (grandChildren != null && !grandChildren.isEmpty());
			Tree tree;
			if (childHasChild) {
				tree = new TreeNode(values[treeNum - 1], colors[treeNum - 1], parent.getDepth() + 1);
			} else {
				tree = new TreeLeaf(values[treeNum - 1], colors[treeNum - 1], parent.getDepth() + 1);
			}
			parent.addChild(tree);

			if (tree instanceof TreeNode) {
				addChildrenAdapted((TreeNode) tree, treeNum);
			}
		}
	}

	public static Tree solveOriginal() {

		int treeSize = 0;
		int[] elements = null;
		byte[] colors = null;
		int[][] elementsEdges = null;

		try {
			br = new BufferedReader(new FileReader(FILENAME));
			// br = new BufferedReader(new InputStreamReader(System.in));
		} catch (Exception e) {
			e.printStackTrace();
		}

		treeSize = getTreeSize();
		elements = getElements(treeSize);
		colors = getElementsColor(treeSize);
		elementsEdges = getelementsEdges(treeSize);

		return TreeBuilder.buildTree(treeSize, elements, colors, elementsEdges);
	}

	private static int getTreeSize() {

		try {
			String val = br.readLine();
			try {

				int treeSize = Integer.parseInt(val);
				return treeSize;

			} catch (Exception ex) {
				return 0;
			}

		} catch (IOException ie) {
			ie.printStackTrace();
		}

		return 0;
	}

	private static int[] getElements(int treeSize) {

		try {

			String[] val = br.readLine().trim().split("\\s+");
			int[] elements = new int[val.length];

			try {

				for (int i = 0; i < val.length; i++)
					elements[i] = Integer.parseInt(val[i]);

				return elements;

			} catch (Exception ex) {
				return null;
			}

		} catch (IOException ie) {
			ie.printStackTrace();
		}

		return null;

	}

	private static byte[] getElementsColor(int treeSize) {

		try {

			String[] val = br.readLine().trim().split("\\s+");
			byte[] colors = new byte[val.length];

			try {

				for (int i = 0; i < val.length; i++)
					colors[i] = Byte.parseByte(val[i]);

				return colors;

			} catch (Exception ex) {
				return null;
			}

		} catch (IOException ie) {
			ie.printStackTrace();
		}

		return null;
	}

	private static int[][] getelementsEdges(int treeSize) {

		int[][] elementsEdges = new int[treeSize - 1][];

		try {

			for (int i = 0; i < treeSize - 1; i++) {

				String[] val = br.readLine().trim().split("\\s+");

				try {
					elementsEdges[i] = new int[2];
					elementsEdges[i][0] = Integer.parseInt(val[0]);
					elementsEdges[i][1] = Integer.parseInt(val[1]);

				} catch (Exception ex) {
					return null;
				}
			}

			return elementsEdges;

		} catch (IOException ie) {
			ie.printStackTrace();
		}

		return null;
	}

	private static void verifyResult(int res1, int res2, int res3) {
		try {
			BufferedReader ot = new BufferedReader(new FileReader(FILENAMEOUTPUT));

			int valor1 = Integer.parseInt(ot.readLine());
			int valor2 = Integer.parseInt(ot.readLine());
			int valor3 = Integer.parseInt(ot.readLine());

			System.out.println(valor1 + " : " + (res1 == valor1) + "  Diferença: " + (res1 - valor1));
			System.out.println(valor2 + " : " + (res2 == valor2) + "  Diferença: " + (res2 - valor2));
			System.out.println(valor3 + " : " + (res3 == valor3) + "  Diferença: " + (res3 - valor3));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
