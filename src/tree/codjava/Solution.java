package tree.codjava;

import java.io.*;

public class Solution {

	static BufferedReader br = null;	
	private static final String FILENAME = "D:\\Teste\\Input\\testCase14_Input.txt";
	private static final String FILENAMEOUTPUT = "D:\\Teste\\Output\\testCase14_Output.txt";
	
	public static Tree solve() {

		
		int treeSize = 0;
		int[] elements = null;
		byte[] colors = null;
		int[][] elementsEdges = null;
		
		try {	
			br = new BufferedReader(new FileReader(FILENAME));
			//br = new BufferedReader(new InputStreamReader(System.in));
		} catch (Exception e) {
            e.printStackTrace();
        }
				
		treeSize = getTreeSize();
		elements = getElements(treeSize);			
		colors = getElementsColor(treeSize);			
		elementsEdges = getelementsEdges(treeSize);			

		return TreeBuilder.buildTree(treeSize, elements, colors, elementsEdges);
	}

	public static void main(String[] args) {

		Tree root = solve();

		//DebugVisitor visDebug = new DebugVisitor();
		//root.accept(visDebug);
		
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
	
	private static void verifyResult(int res1, int res2, int res3) {
		try {
			BufferedReader ot = new BufferedReader(new FileReader(FILENAMEOUTPUT));
			
			System.out.println(res1 ==  Integer.parseInt(ot.readLine()));
			System.out.println(res2 ==  Integer.parseInt(ot.readLine()));
			System.out.println(res3 ==  Integer.parseInt(ot.readLine()));
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
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
