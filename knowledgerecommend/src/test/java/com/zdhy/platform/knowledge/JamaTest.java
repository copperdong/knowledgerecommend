package com.zdhy.platform.knowledge;

import Jama.Matrix;

public class JamaTest {
	private static double[][] array1 = {{1,2,3},{2,3,4},{3,4,5}};
	private static double[][] array2 = {{1,2,6},{5,3,4},{9,4,5}};
	private static Matrix a = new Matrix(array1);
	private static Matrix b = new Matrix(array2);

	public static void main(String[] args) {
		matrixName();

	}
	
	/**
	 * 矩阵命名
	 */
	static void matrixName(){
		//double[][] arg0 = {{1,2},{1,2}};
		Matrix matrix = new Matrix(2,2,2.0);
		matrix.set(0, 0, 7);
		matrix.set(0, 1, 7);
		matrix.set(1, 0, 7);
		matrix.set(1, 1, 7);
		
		matrix.print(0, 0);
	}
	/**
	 * 矩阵相加
	 */
	static void plus(){
		Matrix c = a.plus(b);
		a.print(0, 0);
		System.out.println("------------------------------");
		b.print(0, 0);
		System.out.println("------------------------------");
		c.print(0, 0);
	}
	/**
	 * 矩阵相减
	 */
	static void minus(){
		Matrix c = a.minus(b);
		a.print(0, 0);
		System.out.println("------------------------------");
		b.print(0, 0);
		System.out.println("------------------------------");
		c.print(0, 0);
	}
	/**
	 * 矩阵乘法
	 */
	static void times(){
		Matrix c = a.times(b);
		a.print(0, 0);
		System.out.println("------------------------------");
		b.print(0, 0);
		System.out.println("------------------------------");
		c.print(0, 0);
	}

}
