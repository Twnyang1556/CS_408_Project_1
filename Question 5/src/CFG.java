import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.*;

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

    /* Original test cases didn't satisfy NC nor EC, since we cannot be sure that
	   the added node in the original test cases is indeed new to the CFG. I added
	   addNode_addNew() to make sure that the added node doesn't already exist in
	   the CFG.
     */
    public void addNode(int p, MethodNode m, ClassNode c) {
		// Create the new node
		Node n = new Node(p,m,c);
		// Determine if the node already exists in the nodes set
		if (!nodes.contains(n)) {
			// Add the node into the nodes set and put node in edges
			// with empty set of edge
			nodes.add(n);
			Set<Node> empty = new HashSet<Node>();
			edges.put(n, empty);
		}
    }

	/* Original test cases didn't satisfy NC nor EC, since we cannot be sure that
       the added node in the original addEdge_oneNewNode() is indeed new to the CFG and therefore
       will not cover the edges and nodes for adding the new node.
       Added addEdge_oneNewNodeImproved() to make sure that at least one of the nodes is
       definitely new to the CFG, and will satisfy TR of adding the new node if it is not in the CFG
     */
    public void addEdge(int p1, MethodNode m1, ClassNode c1,
			int p2, MethodNode m2, ClassNode c2) {
		// Create new nodes
		Node n1 = new Node(p1, m1, c1);
		Node n2 = new Node(p2, m2, c2);
		// Check if nodes set contains n1 and n2, add them if necessary
		if (!nodes.contains(n1) || !nodes.contains(n2)) {
			addNode(p1, m1, c1);
			addNode(p2, m2, c2);
		}
		// Add n2 to hashset of n1 in edge set
		// Since Sets don't add new elements if it's already in the set,
		// there is no need to check if n1 already has an edge to n2
		edges.get(n1).add(n2);
	}

	/* The test cases provided for deleteNode() satisfies NC and EC.
	   deleteNode() covers the nodes and edges of my method's CFG when the targeted
	   node is in the CFG.
	   deleteNode_missing() covers the nodes and edges of my method's CFG when the
	   targeted node is not in the CFG.
	 */
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

	/* The test cases provided for deleteEdge() satisfies NC and EC.
       deleteEdge() covers the nodes and edges of my method's CFG when both of the
       nodes are in the CFG and the edge from source to target is removed.
       deleteEdge_missing() and deleteEdge_MissingSrc() covers the nodes and edges
       of my method's CFG when either the source, the target, or both are missing
       and the CFG stays unchanged.
     */
    public void deleteEdge(int p1, MethodNode m1, ClassNode c1,
						int p2, MethodNode m2, ClassNode c2) {
		// Create nodes to check if they exist in nodes set
		Node n1 = new Node(p1,m1,c1);
		Node n2 = new Node(p2,m2,c2);
		if ((nodes.contains(n1)) && (nodes.contains(n2))) {
			edges.get(n1).remove(n2);
		}
    }
	
	// TODO: Add explanation for NC and EC
    public boolean isReachable(int p1, MethodNode m1, ClassNode c1,
			       int p2, MethodNode m2, ClassNode c2) {
		// Check if both nodes exists in nodes set
		Node n1 = new Node(p1,m1,c1);
		Node n2 = new Node(p2,m2,c2);
		Node temp;
		if ((nodes.contains(n1)) && (nodes.contains(n2))) {
			// Created a visited set to keep track of visited nodes
			Set<Node> visited = new HashSet<Node>();
			// Create a queue for BFS
			LinkedList<Node> queue = new LinkedList<Node>();
			// Mark the current node as visited and enqueue it
			visited.add(n1);
			queue.add(n1);
			while (queue.size() != 0) {
				// Dequeue a node from queue
				temp = queue.poll();
				// Iterate through each node in the set of adjacent nodes and
				// see if it is equal to the destination
				for (Node next : edges.get(temp)) {
					if (next.equals(n2)) {
						return true;
					}
					// If the node hasn't ben visited, add to the queue to visit
					if (!visited.contains(next)) {
						visited.add(next);
						queue.add(next);
					}
				}
			}
		}
		return false;
	}
}
