package tree.codjava;

import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) {

		Tree root = solveTwo();

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
	private static HashMap<Integer, HashSet<Integer>> map;

	public static Tree solveTwo() {
		Scanner scan = new Scanner(System.in);
        int numNodes = scan.nextInt();
        
        /* Handle 1-node tree */
        if (numNodes == 1) {
            return new TreeLeaf(values[0], colors[0], 0);
        }
        
        /* Read and save nodes and colors */
        values = new int[numNodes];
        colors = new Color[numNodes];
        map = new HashMap<>(numNodes);
        for (int i = 0; i < numNodes; i++) {
            values[i] = scan.nextInt();
        }
        for (int i = 0; i < numNodes; i++) {
            colors[i] = scan.nextInt() == 0 ? Color.RED : Color.GREEN;
        }
        
        /* Save edges */
        for (int i = 0; i < numNodes - 1; i++) {
            int u = scan.nextInt();
            int v = scan.nextInt();
            
            /* Edges are undirected: Add 1st direction */
            HashSet<Integer> uNeighbors = map.get(u);
            if (uNeighbors == null) {                
                uNeighbors = new HashSet<>();
                map.put(u, uNeighbors);
            }
            uNeighbors.add(v);
            
            /* Edges are undirected: Add 2nd direction */
            HashSet<Integer> vNeighbors = map.get(v);
            if (vNeighbors == null) {
                vNeighbors = new HashSet<>();
                map.put(v, vNeighbors);
            }
            vNeighbors.add(u);
        }
        scan.close();
        
        /* Create Tree */
        TreeNode root = new TreeNode(values[0], colors[0], 0);
        addChildren(root, 1);
        return root;
	}

	private static void addChildren(TreeNode parent, Integer parentNum) {
		/* Get HashSet of children and loop through them */
		for (Integer treeNum : map.get(parentNum)) {
			map.get(treeNum).remove(parentNum); // removes the opposite arrow
												// direction

			/* Add child */
			HashSet<Integer> grandChildren = map.get(treeNum);
			boolean childHasChild = (grandChildren != null && !grandChildren.isEmpty());
			Tree tree;
			if (childHasChild) {
				tree = new TreeNode(values[treeNum - 1], colors[treeNum - 1], parent.getDepth() + 1);
			} else {
				tree = new TreeLeaf(values[treeNum - 1], colors[treeNum - 1], parent.getDepth() + 1);
			}
			parent.addChild(tree);

			/* Recurse if necessary */
			if (tree instanceof TreeNode) {
				addChildren((TreeNode) tree, treeNum);
			}
		}
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

	static BufferedReader br = null;
	private static final String FILENAME = "C:\\Teste\\Input\\testCase1_Input.txt";
	private static final String FILENAMEOUTPUT = "C:\\Teste\\Output\\testCase1_Output.txt";

	public static Tree solve() {

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

}
