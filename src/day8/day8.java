package day8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class day8 {

	public static void main(String[] args) {
		day8 instanceOfTheDay = new day8();
		try {
			instanceOfTheDay.solve("C:\\Users\\boehm\\eclipse-workspace\\AdventOfCode2023\\src\\day8\\input.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<String> readData(String inputData) throws IOException {
		Path path = Paths.get(inputData);
		List<String> dataList = Files.readAllLines(path);
		return dataList;
	}

	private void solve(String inputData) throws IOException {
		List<String> dataList = readData(inputData);
		int solution = solvePart1(dataList);
		System.out.println("Solution for part 1: " + solution);
		long solutionLong = solvePart2(dataList);
		System.out.println("Solution for part 2: " + solutionLong);
	}

	public int solvePart1(List<String> dataList) {
		HashMap<String, String> linksrum = new HashMap<String, String>();
		HashMap<String, String> rechtsrum = new HashMap<String, String>();
		for (int i = 2; i < dataList.size(); i++) {
			String line = dataList.get(i);
			String key = line.substring(0, 3);
			String valueLinks = line.substring(7, 10);
			String valueRechts = line.substring(12, 15);
			linksrum.put(key, valueLinks);
			rechtsrum.put(key, valueRechts);
		}

		String anweisungen = dataList.get(0);
		Character richtung;
		String knoten = "AAA";
		int dauer = 0;
		boolean nichtGefunden = true;
		while (nichtGefunden) {
			for (int i = 0; i < anweisungen.length(); i++) {
				richtung = anweisungen.charAt(i);
				if (richtung == 'L') {
					knoten = linksrum.get(knoten);
					dauer++;
				} else if (richtung == 'R') {
					knoten = rechtsrum.get(knoten);
					dauer++;
				}
				if (knoten.equals("ZZZ")) {
					nichtGefunden = false;
					break;
				}

			}
		}
		return dauer;
	}

	public long solvePart2(List<String> dataList) {
		HashMap<String, String> linksrum = new HashMap<String, String>();
		HashMap<String, String> rechtsrum = new HashMap<String, String>();
		for (int i = 2; i < dataList.size(); i++) {
			String line = dataList.get(i);
			String key = line.substring(0, 3);
			String valueLinks = line.substring(7, 10);
			String valueRechts = line.substring(12, 15);
			linksrum.put(key, valueLinks);
			rechtsrum.put(key, valueRechts);
		}

		String anweisungen = dataList.get(0);
		Character richtung;
		// Suche Startknoten
		ArrayList<String> knoten = new ArrayList<String>();
		Set<String> keys = linksrum.keySet();
		for (String key : keys) {
			if (key.charAt(2) == 'A') {
				knoten.add(key);
			}
		}
		long dauer = 0;
		boolean nichtGefunden = true;
		while (nichtGefunden) {
			for (int i = 0; i < anweisungen.length(); i++) {
				richtung = anweisungen.charAt(i);
				if (richtung == 'L') {
					for (int j = 0; j < knoten.size(); j++) {

						knoten.set(j, linksrum.get(knoten.get(j)));
					}
					dauer++;
				} else if (richtung == 'R') {
					for (int j = 0; j < knoten.size(); j++) {

						knoten.set(j, rechtsrum.get(knoten.get(j)));
					}
					dauer++;
				}
				boolean allesZ = true;
				boolean einZ = false;
				for (String node : knoten) {

					if (node.charAt(2) != 'Z') {
						allesZ = false;
						break;
					} else {
						einZ = true;
					}
				}
				if (allesZ) {
					nichtGefunden = false;
					break;
				}
				if (einZ) {
					for (String node : knoten) {
						System.out.print(" " + node);
					}
					System.out.println(" " + dauer);
				}
				/*
				 * Es gab 6 Startknoten. In der Ausgabe stehen jetzt immer die 6 Knoten, wenn
				 * mindestens einmal ein Z dabei war. Diese Ausgabe in Notepad++ kopieren. Dann
				 * erkennt man, dass jeder Knoten sein eigenes End-Z hat, an dem er alle X
				 * Schritte vorbei kommt. Jetzt kann man sich einfach die Frequenz für jeden
				 * Knoten ansehen. Das heißt jeder Knoten mit End-Z hat eine eigene Dauer, bis
				 * wieder der jeweilige Z-Knoten erreicht ist. Aus diesen Dauern lässt man sich
				 * online mit einem kgV-Rechner das kleinste gemeinsame Vielfache ausgeben und
				 * erhält damit die Lösung.
				 */
			}
		}
		return dauer;
	}

}
