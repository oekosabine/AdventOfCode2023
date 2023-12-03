package day3;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class day3Test {

	@Test
	void test_part1() {
		day3 testInstance = new day3();
		List<String> testDaten = new ArrayList<String>();
		testDaten.add("467..114..");
		testDaten.add("...*......");
		testDaten.add("..35..633.");
		testDaten.add("......#...");
		testDaten.add("617*......");
		testDaten.add(".....+.58.");
		testDaten.add("..592.....");
		testDaten.add("......755.");
		testDaten.add("...$.*....");
		testDaten.add(".664.598..");
		assertTrue(testInstance.solvePart1(testDaten) == 4361);
	}

	@Test
	void test_part2() {
		day3 testInstance = new day3();
		List<String> testDaten = new ArrayList<String>();
		testDaten.add("467..114..");
		testDaten.add("...*......");
		testDaten.add("..35..633.");
		testDaten.add("......#...");
		testDaten.add("617*......");
		testDaten.add(".....+.58.");
		testDaten.add("..592.....");
		testDaten.add("......755.");
		testDaten.add("...$.*....");
		testDaten.add(".664.598..");
		assertTrue(testInstance.solvePart2(testDaten) == 467835);
	}

	@Test
	void test_refactor() throws IOException {
		day3 testInstance = new day3();
		List<String> testDaten = testInstance
				.readData("C:\\Users\\boehm\\eclipse-workspace\\AdventOfCode2023\\src\\day3\\input.txt");
		assertTrue((testInstance.solvePart1(testDaten)) == 527144);
		assertTrue((testInstance.solvePart2(testDaten)) == 81463996);
	}
}
