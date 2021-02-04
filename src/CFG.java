import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CFG {
    Set<Node> nodes = new HashSet<Node>();
    Map<Node, Set<Node>> edges = new HashMap<Node, Set<Node>>();

    static class Node {
	int position;
	MethodNode method;
	ClassNode clazz;

	Node(int p, MethodNode m, ClassNode c) {
	    position = p; method = m; clazz = c;
	}

	public boolean equals(Object o) {
	    if (!(o instanceof Node)) return false;
	    Node n = (Node)o;
	    return (position == n.position) &&
		method.equals(n.method) && clazz.equals(n.clazz);
	}

	public int hashCode() {
	    return position + method.hashCode() + clazz.hashCode();
	}

	public String toString() {
	    return clazz.name + "." +
		method.name + method.signature + ": " + position;
	}
    }

    public void addNode(int p, MethodNode m, ClassNode c) {
	// ...
		// Create the new node
		Node n = new Node(p,m,c);
		// Determine if the node already exists in the nodes set
		if (nodes.contains(n)) {
			// Delete the created node
			n = null;
		} else {
			// Add the node into the nodes set and put node in edges
			// with empty set of edge
			nodes.add(n);
			Set<Node> empty = new HashSet<Node>();
			edges.put(n, empty);
		}
    }

    public void addEdge(int p1, MethodNode m1, ClassNode c1,
			int p2, MethodNode m2, ClassNode c2) {
		// Create new nodes
		Node n1 = new Node(p1, m1, c1);
		Node n2 = new Node(p2, m2, c2);
		// Check if nodes set contains n1 and n2, add them if necessary
		if (!nodes.contains(n1)) {
			addNode(p1, m1, c1);
		}
		if (!nodes.contains(n2)) {
			addNode(p2, m2, c2);
		}
		// Add n2 to hashset of n1 in edge set
		edges.get(n1).add(n2);
	}
	
	public void deleteNode(int p, MethodNode m, ClassNode c) {
		// Create node to see if it exits in nodes set
		Node target = new Node(p, m , c);
		if (nodes.contains(target)) {
			// Remove node from nodes set
			nodes.remove(target);
			// Traverse through nodes set and delete edges to target
			for (Node n : nodes) {
				edges.get(n).remove(target);
			}
			// Remove target node from edges set
			edges.remove(target);
		}
    }
	
    public void deleteEdge(int p1, MethodNode m1, ClassNode c1,
						int p2, MethodNode m2, ClassNode c2) {
		// Create nodes to check if they exist in nodes set
		Node n1 = new Node(p1,m1,c1);
		Node n2 = new Node(p2,m2,c2);
		if ((nodes.contains(n1)) && (nodes.contains(n2))) {
			edges.get(n1).remove(n2);
		}
    }
	

    public boolean isReachable(int p1, MethodNode m1, ClassNode c1,
			       int p2, MethodNode m2, ClassNode c2) {
		// Check if both nodes exists in nodes set
		Node n1 = new Node(p1,m1,c1);
		Node n2 = new Node(p2,m2,c2);

		if ((nodes.contains(n1)) && (nodes.contains(n2))) {
			Set<Node> list = edges.get(n1);
			while (list.size() != 0) {
				for (Node n: list) {
					if (edges.get(n).contains(n2)) {
						return true;
					} else {
						list = edges.get(n);
						break;
					}
				}
			}
		}
		return false;
	}
}
