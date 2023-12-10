package day10;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class day10Test {

	@Test
	void test_part1() {
		day10 testInstance = new day10();
		List<String> testDaten = new ArrayList<String>();
		testDaten.add("..F7.");
		testDaten.add(".FJ|.");
		testDaten.add("SJ.L7");
		testDaten.add("|F--J");
		testDaten.add("LJ...");
		assertTrue(testInstance.solvePart1(testDaten) == 8);
	}

	@Test
	void test_part2() {
		day10 testInstance = new day10();
		List<String> testDaten = new ArrayList<String>();
		testDaten.add("...........");
		testDaten.add(".S-------7.");
		testDaten.add(".|F-----7|.");
		testDaten.add(".||OOOOO||.");
		testDaten.add(".||OOOOO||.");
		testDaten.add(".|L-7OF-J|.|");
		testDaten.add(".|II|O|II|.");
		testDaten.add(".L--JOL--J.");
		testDaten.add(".....O.....");
		assertTrue(testInstance.solvePart2(testDaten) == 4);
	}

	@Test
	void test_part2_2() {
		day10 testInstance = new day10();
		List<String> testDaten = new ArrayList<String>();
		testDaten.add(".F----7F7F7F7F-7....");
		testDaten.add(".|F--7||||||||FJ....");
		testDaten.add(".||.FJ||||||||L7....");
		testDaten.add("FJL7L7LJLJ||LJ.L-7..");
		testDaten.add("L--J.L7...LJS7F-7L7.");
		testDaten.add("....F-J..F7FJ|L7L7L7");
		testDaten.add("....L7.F7||L7|.L7L7|");
		testDaten.add(".....|FJLJ|FJ|F7|.LJ");
		testDaten.add("....FJL-7.||.||||...");
		testDaten.add("....L---J.LJ.LJLJ...");
		assertTrue(testInstance.solvePart2(testDaten) == 8);
	}

	@Test
	void test_part2_3() {
		day10 testInstance = new day10();
		List<String> testDaten = new ArrayList<String>();
		testDaten.add("FF7FSF7F7F7F7F7F---7");
		testDaten.add("L|LJ||||||||||||F--J");
		testDaten.add("FL-7LJLJ||||||LJL-77");
		testDaten.add("F--JF--7||LJLJ7F7FJ-");
		testDaten.add("L---JF-JLJ.||-FJLJJ7");
		testDaten.add("|F|F-JF---7F7-L7L|7|");
		testDaten.add("|FFJF7L7F-JF7|JL---7|");
		testDaten.add("7-L-JL7||F7|L7F-7F7|");
		testDaten.add("L.L7LFJ|||||FJL7||LJ");
		testDaten.add("L7JLJL-JLJLJL--JLJ.L");
		assertTrue(testInstance.solvePart2(testDaten) == 10);
	}

	@Test
	void test_refactor() throws IOException {
		day10 testInstance = new day10();
		List<String> testDaten = testInstance
				.readData("C:\\Users\\boehm\\eclipse-workspace\\AdventOfCode2023\\src\\day10\\input.txt");
		assertTrue((testInstance.solvePart1(testDaten)) == 6907);
		assertTrue((testInstance.solvePart2(testDaten)) == 541);
	}
}
