package models;

public class Tree {

	private Node root;
	private Node newRoot;

	public void add(Node node) {
		if (root != null) {
			add(root, node);
		}else {
			root = node;
		}
	}

	private void add(Node base, Node node) {
		if (node.getInformation() < base.getInformation()) {
			if (base.getLeft() != null) {
				add(base.getLeft(), node);
			}else {
				base.setLeft(node);
			}
		}else {
			if (base.getRight() != null) {
				add(base.getRight(), node);
			}else {
				base.setRight(node);
			}
		}
	}

	public void balanceLL() {
		if(root.getLeft() != null) {
			if(root.getLeft().getRight() != null) {
				newRoot = root.getLeft();
				root.setLeft(root.getLeft().getRight());
				root.getLeft().setLeft(newRoot);
				root.getLeft().getLeft().setRight(null);
				root.getLeft().getLeft().setLeft(null);
			}else {
				newRoot = root;
				root = root.getLeft();
				root.setRight(newRoot);
				root.getRight().setLeft(null);
			}
		}else if(root.getRight().getRight() != null) {
			newRoot = root;
			root = root.getRight();
			root.setLeft(newRoot);
			newRoot.setRight(null);
			root.getRight().setRight(null);
			root.getRight().setLeft(null);
		}
	}

	public void balanceRR() {
		if(root.getRight() != null) {
			if(root.getRight().getLeft() != null) {
				newRoot = root.getRight();
				root.setRight(root.getRight().getLeft());
				root.getRight().setRight(newRoot);
				root.getRight().getRight().setRight(null);
			}else {
				newRoot = root;
				root = root.getRight();
				root.setLeft(newRoot);
				root.getLeft().setRight(null);

			}
		}else if(root.getLeft().getLeft() != null) {
			newRoot = root;
			root = root.getLeft();
			root.setRight(newRoot);
			newRoot.setRight(null);
			root.getRight().setRight(null);
			root.getRight().setLeft(null);
		}
	}

	public void balance() {
		if(root.getLeft() != null && root.getLeft().getLeft() == null && root.getLeft().getRight() != null) {
			balanceLR();
		}else if(root.getRight() != null && root.getRight().getLeft() != null && root.getRight().getRight() == null) {
			balanceRL();
		}else if(root.getRight() != null){
			balanceRR();
		}else if(root.getLeft().getLeft() != null) {
			balanceLL();
		}
	}

	private void balanceRL() {
		balanceRR();
		balanceLL();
	}

	public void balanceLR() {
		balanceLL();
		balanceRR();
	}
	
	public void delete(int info) {
		delete(null, root, info);
	}

	public void delete(Node father, Node actual, int info) {
		if (actual.getInformation() == info) {
			if (isComplete(actual)) {
				deleteComplete(actual);
			}else if (hasOneChildren(actual)) {
				deleteOneChild(father, actual);
			}else {
				deleteLeaf(father, actual);
			}
		}else {
			if (info < actual.getInformation()) {
				delete(actual, actual.getLeft(), info);
			}else {
				delete(actual, actual.getRight(), info);
			}
		}
	}

	private void deleteComplete(Node actual) {
		Node maxLeft = getMaxNode(actual.getLeft());
		Node minRight = getMinNode(actual.getRight());
		int data = (Math.abs(maxLeft.getInformation() - actual.getInformation()) 
				< Math.abs(minRight.getInformation() - actual.getInformation())) ?
					maxLeft.getInformation(): minRight.getInformation();
		delete(data);
		actual.setInformation(data);
	}
	
	public Node getMinNode(Node base) {
		Node actual = base;
		while (actual.getLeft() != null) {
			actual = actual.getLeft();
		}
		return actual;
	}
	
	public Node getMaxNode(Node base) {
		Node actual = base;
		while (actual.getRight() != null) {
			actual = actual.getRight();
		}
		return actual;
	}

	private void deleteOneChild(Node father, Node actual) {
		if (actual == root) {
			root = getOneChild(actual);
		}else if (father.getLeft().equals(actual)) {
			father.setLeft(getOneChild(actual));
		}else {
			father.setRight(getOneChild(actual));
		}
	}

	private void deleteLeaf(Node father, Node actual) {
		if (father == null) {
			root = null;
		}else if (father.getLeft() != null && father.getLeft().equals(actual)) {
			father.setLeft(null);
		}else {
			father.setRight(null);
		}
	}
	
	private Node getOneChild(Node actual) {
		return actual.getLeft() != null ? actual.getLeft() : actual.getRight();
	}

	private boolean hasOneChildren(Node actual) {
		return actual.getLeft() != null || actual.getRight() != null;
	}

	private boolean isComplete(Node actual) {
		return actual.getLeft() != null && actual.getRight() != null;
	}

	public void print() {
		System.out.println("------------------");
		print(root);
	}

	private void print(Node node) {
		if(node != null) {
			System.out.println(node.getInformation());
			print(node.getLeft());
			print(node.getRight());
		}
	}
	
	public Node getRoot() {
		return root;
	}
}
