package com.meditec.medmanagement;

public class BNodeNode<T> {
	
		private int key;
		
		private T data;
		
		public BNodeNode<T>(int key, T data){
			this.key = key;
			
			this.data = data;
		}

		/**
		 * @return the key
		 */
		public int getKey() {
			return key;
		}

		/**
		 * @param key the key to set
		 */
		public void setKey(int key) {
			this.key = key;
		}

		/**
		 * @return the data
		 */
		public T getData() {
			return data;
		}

		/**
		 * @param data the data to set
		 */
		public void setData(T data) {
			this.data = data;
		}

	}


