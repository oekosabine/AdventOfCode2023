package day8;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class day8Test {

	@Test
	void test_part1() {
		day8 testInstance = new day8();
		List<String> testDaten = new ArrayList<String>();
		testDaten.add("RL");
		testDaten.add("");
		testDaten.add("AAA = (BBB, CCC)");
		testDaten.add("BBB = (DDD, EEE)");
		testDaten.add("CCC = (ZZZ, GGG)");
		testDaten.add("DDD = (DDD, DDD)");
		testDaten.add("EEE = (EEE, EEE)");
		testDaten.add("GGG = (GGG, GGG)");
		testDaten.add("ZZZ = (ZZZ, ZZZ)");
		assertTrue(testInstance.solvePart1(testDaten) == 2);
	}

	@Test
	void test_part2() {
		day8 testInstance = new day8();
		List<String> testDaten = new ArrayList<String>();
		testDaten.add("LR");
		testDaten.add("");
		testDaten.add("11A = (11B, XXX)");
		testDaten.add("11B = (XXX, 11Z)");
		testDaten.add("11Z = (11B, XXX)");
		testDaten.add("22A = (22B, XXX)");
		testDaten.add("22B = (22C, 22C)");
		testDaten.add("22C = (22Z, 22Z)");
		testDaten.add("22Z = (22B, 22B)");
		testDaten.add("XXX = (XXX, XXX)");
		assertTrue(testInstance.solvePart2(testDaten) == 6);
	}

	@Test
	void test_refactor() throws IOException {
		day8 testInstance = new day8();
		List<String> testDaten = testInstance
				.readData("C:\\Users\\boehm\\eclipse-workspace\\AdventOfCode2023\\src\\day8\\input.txt");
		assertTrue((testInstance.solvePart1(testDaten)) == 19241);
		assertTrue((testInstance.solvePart2(testDaten)) == 9606140307013L);
	}
}
