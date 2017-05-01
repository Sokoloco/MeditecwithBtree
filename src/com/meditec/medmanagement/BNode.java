package com.meditec.medmanagement;

public class BNode<T> {

		private BNodeNode<T>[] data;

		private BNode<T> parent;

		private BNode<T>[] children;

		private boolean isLeaf;

		// Keeps track of how many keys a node has
		private int count;

		public BNode<T>(int order, BNode<T> parent, T data, int key) {
			this.parent = parent;

			children = new BNode[2 * order];

			this.data = new BNodeNode[order - 1];

			this.data[0] = new BNodeNode<T>(key, data);

			this.count = 0;

			isLeaf = true;
		}

		public BNode(BNode<T> child, int order) {

			children = new BNode[2 * order];

			this.data = new BNodeNode[2 * order - 1];

			this.children[0] = child;
			isLeaf = true;
		}

		public BNode(int order, boolean isLeaf) {
			children = new BNode[2 * order];
			
			this.data = new BNodeNode[2 * order - 1];

			this.isLeaf = isLeaf;
		}

		/**
		 * 
		 * @return whether the BNode has any children
		 */
		public boolean isLeaf() {
			return isLeaf;
		}

		/**
		 * @param isLeaf
		 *            the isLeaf to set
		 */
		public void setLeaf(boolean isLeaf) {
			this.isLeaf = isLeaf;
		}

		/**
		 * @return the data
		 */
		public BNodeNode<T> getData(int pos) {
			return data[pos];
		}

		/**
		 * @param data
		 *            the data to set
		 */
		public void addData(int pos, BNodeNode<T> data) {
			this.data[pos] = data;
		}

		/**
		 * @return the parent
		 */
		public BNode<T> getParent() {
			return parent;
		}

		/**
		 * @param parent
		 *            the parent to set
		 */
		public void setParent(BNode<T> parent) {
			this.parent = parent;
		}

		/**
		 * @return the child
		 */
		public BNode<T> getChild(int pos) {
			return children[pos];
		}

		/**
		 * @param children
		 *            the children to set
		 */
		public void setChild(BNode<T> child, int pos) {
			this.children[pos] = child;
		}

		/**
		 * @return the count
		 */
		public int getCount() {
			return count;
		}

		/**
		 * @param count
		 *            the count to set
		 */
		public void setCount(int count) {
			this.count = count;
		}

		/*
		 * // order of tree (how many children a node can have) private int order;
		 * 
		 * // Keeps count of how many keys a node can have private int count;
		 * 
		 * // Array of nodes(children) a node(father) contains private BNode<T>
		 * children[];
		 * 
		 * // True if the node has no children private boolean isLeaf;
		 * 
		 * // Leads to the parent private BNode<T> parent;
		 * 
		 * // Node Information private BNodeNode<T>[] data;
		 * 
		 * public BNode(int order, BNode<T> parent, T data) { this.order = order;
		 * 
		 * this.parent = parent;
		 * 
		 * keys = new int[2 * order - 1];
		 * 
		 * children = new BNode[2 * order];
		 * 
		 * isLeaf = true; // This because each node starts as a leaf
		 * 
		 * count = 0; // for now
		 * 
		 * 
		 * }
		 * 
		 *//**
			 * @return the data
			 */
		/*
		 * public T getData(int pos) { return data.get(pos); }
		 * 
		 *//**
			 * @param data
			 *            the data to set
			 */
		/*
		 * public void setData(T data, int pos) { this.data.add(data); }
		 * 
		 *//**
			 * @return the t
			 */
		/*
		 * public int getOrder() { return order; }
		 * 
		 *//**
			 * @param t
			 *            the t to set
			 */
		/*
		 * public void setOrder(int order) { this.order = order; }
		 * 
		 *//**
			 * @return the count
			 */
		/*
		 * public int getCount() { return count; }
		 * 
		 *//**
			 * @param count
			 *            the count to set
			 */
		/*
		 * public void setCount(int count) { this.count = count; }
		 * 
		 *//**
			 * @return the keys
			 */
		/*
		 * public int getKey(int i) { return keys[i]; }
		 * 
		 *//**
			 * @param keys
			 *            the keys to set
			 */
		/*
		 * public void setKey(int key,int pos) { this.keys[pos] = key; }
		 * 
		 *//**
			 * @return the children
			 */
		/*
		 * public BNode<T> getChild(int i) { return children[i]; }
		 * 
		 *//**
			 * @param children
			 *            the children to set
			 */
		/*
		 * public void setChild(BNode<T> child,int pos) { this.children[pos] =
		 * child; }
		 * 
		 *//**
			 * @return the isLeaf
			 */
		/*
		 * public boolean isLeaf() { return isLeaf; }
		 * 
		 *//**
			 * @param isLeaf
			 *            the isLeaf to set
			 */
		/*
		 * public void setLeaf(boolean isLeaf) { this.isLeaf = isLeaf; }
		 * 
		 *//**
			 * @return the parent
			 */
		/*
		 * public BNode<T> getParent() { return parent; }
		 * 
		 *//**
			 * @param parent
			 *            the parent to set
			 *//*
			 * public void setParent(BNode<T> parent) { this.parent = parent; }
			 */
	}
}
