package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import models.Node;
import models.Tree;
import views.WindowTree;

public class Controller implements ActionListener{
	
	private Tree tree;
	private WindowTree window;

	public Controller() {
		tree = new Tree();
		tree.add(new Node(10));
		tree.add(new Node(7));
		tree.add(new Node(5));
//		tree.add(new Node(2));
//		tree.add(new Node(4));
//		tree.add(new Node(8));
//		tree.add(new Node(9));
//		tree.add(new Node(1));
		
		window = new WindowTree(this);
		window.paintTree(tree.getRoot());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("ADD")) {
			tree.add(new Node(Integer.valueOf(JOptionPane.showInputDialog("id"))));			
		}else{
			tree.balance();
		}
		window.paintTree(tree.getRoot());
	}
	
	public static void main(String[] args) {
		new Controller();
	}
}