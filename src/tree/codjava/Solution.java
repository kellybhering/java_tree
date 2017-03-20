package tree.codjava;

import java.io.*;

public class Solution {

	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	public static Tree solve() {

		boolean dataOK = false;
		int treeSize = 0;
		int[] elements = null;
		byte[] colors = null;
		int[][] elementsEdges = null;

		while (!dataOK) {
			treeSize = getTreeSize();
			dataOK = treeSize != 0;
		}

		dataOK = false;
		while (!dataOK) {
			elements = getElements(treeSize);
			dataOK = elements != null;
		}

		dataOK = false;
		while (!dataOK) {
			colors = getElementsColor(treeSize);
			dataOK = colors != null;
		}

		dataOK = false;
		while (!dataOK) {
			elementsEdges = getelementsEdges(treeSize);
			dataOK = elementsEdges != null;
		}

		return TreeBuilder.buildTree(treeSize, elements, colors, elementsEdges);
	}

	public static void main(String[] args) {

		Tree root = solve();

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

	}

	private static int getTreeSize() {

		System.out.println("Digite o tamanho da árvore. O número deve estar entre 1 e 100000");

		try {

			String val = in.readLine();
			try {

				int treeSize = Integer.parseInt(val);
				if (TreeBuilder.validateTreeSize(treeSize))
					return treeSize;

			} catch (Exception ex) {
				System.out.println("Valor inválido!");
				return 0;
			}

		} catch (IOException ie) {
			ie.printStackTrace();
		}

		return 0;
	}

	private static int[] getElements(int treeSize) {

		System.out.println(
				"Digite os elementos da árvores separados por espaço. A quantidade deve ser igual ao tamanho da árvore e os valores devem estar entre 1 e 1000");

		try {

			String[] val = in.readLine().trim().split("\\s+");
			int[] elements = new int[val.length];

			try {

				for (int i = 0; i < val.length; i++)
					elements[i] = Integer.parseInt(val[i]);

				if (TreeBuilder.validateElements(elements, treeSize))
					return elements;

			} catch (Exception ex) {
				System.out.println("Um dos valores é inválido!");
				return null;
			}

		} catch (IOException ie) {
			ie.printStackTrace();
		}

		return null;

	}

	private static byte[] getElementsColor(int treeSize) {

		System.out.println("Digite as cores dos elementos da árvore separadas por espaço. Red: 0, Green: 1");

		try {

			String[] val = in.readLine().trim().split("\\s+");
			byte[] colors = new byte[val.length];

			try {

				for (int i = 0; i < val.length; i++)
					colors[i] = Byte.parseByte(val[i]);

				if (TreeBuilder.validateElementsColor(colors, treeSize))
					return colors;

			} catch (Exception ex) {
				System.out.println("Um dos valores é inválido!");
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
				System.out.println(String.format(
						"Digite a ligação %1$d entre os elementos da árvore, separado por espaço. Total de ligações: %2$d",
						i + 1, treeSize - 1));

				String[] val = in.readLine().trim().split("\\s+");

				try {
					elementsEdges[i] = new int[2];
					elementsEdges[i][0] = Integer.parseInt(val[0]);
					elementsEdges[i][1] = Integer.parseInt(val[1]);

				} catch (Exception ex) {
					System.out.println("Um dos valores é inválido!");
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
