package com.egen.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = { 6, 10, 6, 9, 7, 8 };// {6, 6, 7} -->3
		int[] b = { 6, 10, 1, 2, 6, 6, 7 };// {6, 6, 6, 7} -->4
		int[] c = { 5, 5, 3, 3, 9 };// {3, 3}, {5, 5} -->2
		int[] d = { 5, 6, 1, 3, 2, 4 }; // 2
		int[] e = { 6, 7, 7, 7, 8, 8 };
		int[] f = { 5, 7, 7, 8 };

		System.out.println("Answer is: " + quasiConstant(a));
		System.out.println("Answer is: " + quasiConstant(b));
		System.out.println("Answer is: " + quasiConstant(c));
		System.out.println("Answer is: " + quasiConstant(d));
		System.out.println("Answer is: " + quasiConstant(e));
		System.out.println("Answer is: " + quasiConstant(f));

	}

	static int quasiConstant(int[] a) {
		/*
		 * Map<Integer, Integer> map = new HashMap<>(); for (int i : a)
		 * map.compute(i, (k, v) -> v == null ? 1 : v + 1); int max = 0; for
		 * (Entry<Integer, Integer> e : map.entrySet()) { int t = e.getValue() +
		 * map.getOrDefault(e.getKey() + 1, 0); if (t > max) max = t; }
		 * System.out.println(Arrays.toString(a) + " -> " + max); return max;
		 */

		Map<Integer, Integer> map = new HashMap<>();
		for (int i : a) {
			Integer count = map.get(i);
			if (count == null) {
				map.put(i, 1);
			} else {
				map.put(i, ++count);
			}
		}
		int max = 0;
		int total = 0;
		for (Entry<Integer, Integer> entry : map.entrySet()) {
			total = entry.getValue();
			if (map.containsKey(entry.getKey() + 1)) {
				total += map.get(entry.getKey() + 1);
			}
			if (total > max) {
				max = total;
			}

		}
		return max;
	}
}
