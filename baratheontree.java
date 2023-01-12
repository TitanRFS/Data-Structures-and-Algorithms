import java.io.*;
import java.lang.*;
import java.util.*;

public class familyTree{
	//Εδώ αντιπροσωππεύει name kai parent 
	private class Node{
		public String name;
		public String parent;
		public Node(String mem, String par){
			name = mem;
			parent = par;
		}
	}//Εδω δημιουργώ μία κλάση Generation και με arraylists μπορώ να δημιουργήσω καινούργια nodes
	private class Generation{
		public ArrayList<Node> nodes;

		public Generation(){
			nodes =  new ArrayList<Node>();
		}

		public Generation(String name, String parent){
			nodes =  new ArrayList<Node>();
			nodes.add(0, new Node(name, parent));
		}

		public Generation(ArrayList<Node> children){
			nodes = new ArrayList<Node>();
			nodes.addAll(children);
		}
		public void addNode(String name, String parent){
			nodes.add(new Node(name, parent));
		}
		//Η A
		public boolean areChildren(ArrayList<Node> parent){ 
			for(Node child : nodes){
				for(Node par : parent) {
					if(child.parent.equals(par.name)){
						return true;
					}
				}
			}
			return false;
		}
	}
	private class Queue{
		private ArrayList<Node> queue;
		private ArrayList<String> parents;

		public Queue(){  
			queue = new ArrayList<Node>();
			parents = new ArrayList<String>();
		}

		public void enqueue(String name, String parent){
			queue.add(new Node(name, parent));
			parents.add(new String(parent));
		}

		public boolean containsParent(String parent){
			if(parents.contains(parent)) return true;
			else return false;
		}

		public ArrayList<Node> dequeue(String parent){ 
			ArrayList<Node> children = new ArrayList<Node>();
			for(Node node : this.queue){
				if(node.parent.equals(parent)){ 
					children.add(node);		
				}
			}
			parents.remove(parent);
			return children; 
		}

		public boolean empty(){
			if(queue.size() > 0) return false;
			else return true;
		}

	}

	private ArrayList<Generation> genTree;
	private Queue queue;	

	public familyTree() {
		genTree = new ArrayList<Generation>();
		queue = new Queue();
	}

	private void addChildren(int i, String parent){
		if(genTree.size() > i+1 && genTree.get(i+1).areChildren(genTree.get(i).nodes)) {
			genTree.get(i+1).nodes.addAll(queue.dequeue(parent));
		} else if (genTree.size() > i+1 && !genTree.get(i+1).areChildren(genTree.get(i).nodes)){
			Generation gen = new Generation(queue.dequeue(parent));
			genTree.add(i+1, gen);
		} else {
			Generation gen = new Generation(queue.dequeue(parent));
			genTree.add(gen);
		}
	}

	private BufferedReader br;
		public void fillTree(String filename){		
		String line;
		try{
			br = new BufferedReader(new FileReader(filename));
			br.readLine();
			while((line = br.readLine()) != null){
				String[] names = line.split(",");
				boolean found = false;
				if(genTree.size() == 0 || names[0].equals("NULL")){ 
					Generation gen = new Generation(names[1], names[0]);
					genTree.add(0, gen);
					found = true;
				}
				else {
					for(int i = 0; !found && i < genTree.size(); i++){
						for(Node node : genTree.get(i).nodes){ 
							f(names[1].equals(node.parent) && i == 0){ //check if it is a parent
								Generation gen = new Generation(names[1], names[0]);
								genTree.add(0, gen);
								found = true;
								break;
							} else if (names[1].equals(node.parent) && i > 0 && genTree.get(i).areChildren(genTree.get(i-1).nodes)) {
								genTree.get(i-1).addNode(names[1], names[0]);
								found = true;
								break;
							} else if (names[1].equals(node.parent) && i > 0 && !genTree.get(i).areChildren(genTree.get(i-1).nodes)) {
								Generation gen = new Generation(names[1], names[0]);
								genTree.add(i-1, gen);
								found = true;
								break;
							} else if (names[0].equals(node.name) && i+1 == genTree.size()){
								Generation gen = new Generation(names[1], names[0]);
								genTree.add(gen);
								found = true;
								break;
							}	else if (names[0].equals(node.name) && i+1 < genTree.size() && genTree.get(i+1).areChildren(genTree.get(i).nodes)) {
								genTree.get(i+1).addNode(names[1], names[0]);
								found = true;
								break;
							} else if (names[0].equals(node.name) && i+1 < genTree.size() && genTree.get(i+1).areChildren(genTree.get(i).nodes)) {
								Generation gen = new Generation(names[1], names[0]);
								genTree.add(i-1, gen);
								found = true;
								break;
							}
						}
					}
				}
				if(!found){
					queue.enqueue(names[1], names[0]);
				}
			}
		} 
		for(int i = 0; i < genTree.size() && !queue.empty(); i++){
			for(Node node : genTree.get(i).nodes){
				if(queue.containsParent(node.name)){
					this.addChildren(i, node.name);
				}
				if(queue.empty()) break;
			}
		}
	}
	
	public void search(int depth){
		int i = 0;
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<Node> nodes = genTree.get(depth).nodes;
		for(Node node : nodes){
			names.add(node.name);
		}
		Object[] names2 =  names.toArray();
		Arrays.sort(names2);
		for(Object name : names2) System.out.println(name);
	}
	} 
	   
}
