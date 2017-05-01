package com.meditec.medmanagement;

public class BTree<T> {

		// order of the Tree (how many children a node can have)
		private int order;

		// root of the Tree
		private BNode<T> root;

		public BTree(int order) {

			this.order = order;

			root = null;

		}

		public BNode<T> search(int key) {
			return search(key, root);
		}

		public BNode<T> search(int key, BNode<T> current) {
			int i = 0;
			System.out.println(current.getCount());
			while(i < current.getCount() && key > current.getData(i).getKey()){
				i++;
			}
			if(current.getData(i).getKey() == key){
				return current;
			}
			if(current.isLeaf()){
				return null;
			}
			return search(key, current.getChild(i));
			
		}

		public BNode<T> traverse(int k) {
			return traverse(k, root, 0);
		}

		public BNode<T> traverse(int key, BNode<T> current, int pos) {
			if (pos < order - 1 && current.getData(pos) != null && !current.isLeaf()) {
				if (key < current.getData(pos).getKey()) {
					return traverse(key, current.getChild(pos), 0);
				} else if (key > current.getData(pos).getKey()) {
					return traverse(key, current, ++pos);
				}
			}
			return current;
		}

		public void insert(int k, T c) {
			BNodeNode<T> data = new BNodeNode<T>(k, c);
			insert(root, data);
		}

		public void insert(BNode<T> t, BNodeNode<T> k) {
			if (root == null) {
				root = new BNode<T>(order,true);
				root.addData(0, k);
			} else {
				BNode<T> r = t;
				if (r.getCount() == 2 * order - 1) {
					BNode<T> s = new BNode<T>(order, false);
					root = s;
					s.setCount(0);
					s.setChild(r, 1);
					splitchild(s, 1, r);
					insertNonFull(s, k);
				} else {
					insertNonFull(r, k);
				}
			}
		}

		public void splitchild(BNode<T> parent, int pos, BNode<T> splitted) {
			BNode<T> z = new BNode<T>(order, splitted.isLeaf());

			z.setCount(order - 1);

			for (int j = 0; j < order - 1; j++) {
				z.addData(j, splitted.getData(order + j));
			}

			if (!splitted.isLeaf()) {
				for (int j = 0; j < order; j++) {
					z.setChild(splitted.getChild(order + j), j);
				}
			}
			splitted.setCount(order - 1);
			for (int j = parent.getCount() + 1; j > pos + 1; j--) {
				parent.setChild(parent.getChild(j), j + 1);
			}
			parent.setChild(z, pos + 1);
			for (int j = parent.getCount(); j > pos; j--) {
				parent.addData(j + 1, parent.getData(j));
			}
			parent.addData(pos + 1, splitted.getData(pos));
			parent.setCount(parent.getCount() + 1);
		}

		public void insertNonFull(BNode<T> x, BNodeNode<T> k) {
			int i = x.getCount();
			System.out.println(i);
			if (x.isLeaf()) {
				while (i >= 1 && i < k.getKey()) {
					System.out.println("This is i" + i);
					x.addData(i, x.getData(i));
					i--;
				}
				x.addData(i + 1, k);
				x.setCount(x.getCount() + 1);
			} else {
				while (i >= 1 && k.getKey() < x.getData(i).getKey()) {
					i--;
				}
				i++;
				if (x.getChild(i).getCount() == 2 * order - 1) {
					splitchild(x, i, x.getChild(i));
					if (k.getKey() > x.getData(i).getKey()) {
						i++;
					}
				}
				insertNonFull(x.getChild(i), k);
			}
		}

		public int findKey(int k, BNode<T> root) {
			int idx = 0;
			while (idx < order && root.getData(idx).getKey() < k) {
				++idx;
			}
			return idx;
		}

		/*
		 * public void delete(int k) { remove(k, root); }
		 * 
		 * public void remove(int k, BNode<T> removing) {
		 * 
		 * int idx = findKey(k, removing);
		 * 
		 * if (idx < order && removing.getData(idx).getKey() == k) { if
		 * (removing.isLeaf()) removeFromLeaf(idx, removing); else
		 * removeFromNonLeaf(idx, removing); } else { if (removing.isLeaf()) {
		 * System.out.println("That key does not exist"); }
		 * 
		 * // The key to be removed is present in the sub-tree rooted with this //
		 * node // The flag indicates whether the key is present in the sub-tree //
		 * rooted // with the last child of this node boolean flag = (idx == order);
		 * 
		 * if(root.getChild(idx).getCount() < order){ fill(idx); }
		 * 
		 * if (flag && idx > removing.getCount()) remove(k, removing.getChild(idx -
		 * 1)); else remove(k, removing.getChild(idx)); } return; }
		 * 
		 * public void removeFromLeaf(int idx, BNode<T> root) { for (int i=idx+1; i
		 * < root.getCount(); ++i) root.addData(i-1, root.getData(i));
		 * 
		 * root.setCount(root.getCount()-1); }
		 * 
		 * public void removeFromNonLeaf(int idx, BNode<T> root) {
		 * 
		 * int k = root.getData(idx).getKey();
		 * 
		 * // If the child that precedes k (C[idx]) has atleast t keys, // find the
		 * predecessor 'pred' of k in the subtree rooted at // C[idx]. Replace k by
		 * pred. Recursively delete pred // in C[idx] if
		 * (root.getChild(idx).getCount() >= order){
		 * 
		 * BNodeNode<T> pred = getPred(k , root); root.addData(idx, pred);
		 * root.setChild(, idx); }
		 * 
		 * // If the child C[idx] has less that t keys, examine C[idx+1]. // If
		 * C[idx+1] has atleast t keys, find the successor 'succ' of k in // the
		 * subtree rooted at C[idx+1] // Replace k by succ // Recursively delete
		 * succ in C[idx+1] else if (C[idx+1]->n >= t) { int succ = getSucc(idx);
		 * keys[idx] = succ; C[idx+1]->remove(succ); }
		 * 
		 * // If both C[idx] and C[idx+1] has less that t keys,merge k and all of
		 * C[idx+1] // into C[idx] // Now C[idx] contains 2t-1 keys // Free C[idx+1]
		 * and recursively delete k from C[idx] else { merge(idx);
		 * C[idx]->remove(k); } }
		 * 
		 * // A function to get predecessor of keys[idx] public BNodeNode<T>
		 * getPred(int idx, BNode<T> root){ // Keep moving to the right most node
		 * until we reach a leaf BNode<T> cur = root.getChild(idx); while
		 * (!cur.isLeaf()){ cur = cur.getChild(cur.getCount()); } // Return the last
		 * key of the leaf return cur.getData(cur.getCount()-1); } int
		 * BTreeNode::getSucc(int idx) {
		 * 
		 * // Keep moving the left most node starting from C[idx+1] until we reach a
		 * leaf BTreeNode *cur = C[idx+1]; while (!cur->leaf) cur = cur->C[0];
		 * 
		 * // Return the first key of the leaf return cur->keys[0]; }
		 * 
		 * // A function to fill child C[idx] which has less than t-1 keys void
		 * BTreeNode::fill(int idx) {
		 * 
		 * // If the previous child(C[idx-1]) has more than t-1 keys, borrow a key
		 * // from that child if (idx!=0 && C[idx-1]->n>=t) borrowFromPrev(idx);
		 * 
		 * // If the next child(C[idx+1]) has more than t-1 keys, borrow a key //
		 * from that child else if (idx!=n && C[idx+1]->n>=t) borrowFromNext(idx);
		 * 
		 * // Merge C[idx] with its sibling // If C[idx] is the last child, merge it
		 * with with its previous sibling // Otherwise merge it with its next
		 * sibling else { if (idx != n) merge(idx); else merge(idx-1); } return; }
		 */
	}


}
