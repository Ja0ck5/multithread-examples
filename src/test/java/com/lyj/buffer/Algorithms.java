package com.lyj.buffer;

import java.util.Random;

import org.junit.Test;

public class Algorithms {
		@Test
		public void testSortedWillBeQuickly() throws Exception {
			// Generate data
			int arraySize = 32768;
			int data[] = new int[arraySize];
	
			Random rnd = new Random(0);
			for (int c = 0; c < arraySize; ++c)
				data[c] = rnd.nextInt() % 256;
	
			// !!! With this, the next loop runs faster
			// Arrays.sort(data);
	
			// Test
			long start = System.nanoTime();
			long sum = 0;
	
			for (int i = 0; i < 100000; ++i) {
				// Primary loop
				for (int c = 0; c < arraySize; ++c) {
					/*
					 * If the compiler isn't able to optimize the branch into a
					 * conditional move, you can try some hacks if you are willing
					 * to sacrifice readability for performance.
					 * Replace:
					 * if (data[c] >= 128) sum += data[c]; with:
					 * int t = (data[c] - 128) >> 31; sum += ~t & data[c];
					 */
					if (data[c] >= 128)
						sum += data[c];
					// int t = (data[c] - 128) >> 31;
					// sum += ~t & data[c];
				}
			}
	
			System.out.println((System.nanoTime() - start) / 1000000000.0);
			System.out.println("sum = " + sum);
		}
}

