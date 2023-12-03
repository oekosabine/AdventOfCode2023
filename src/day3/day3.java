package day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class day3 {

	public static void main(String[] args) {
		day3 instanceOfTheDay = new day3();
		try {
			instanceOfTheDay.solve("C:\\Users\\boehm\\eclipse-workspace\\AdventOfCode2023\\src\\day3\\input.txt");
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
		solution = solvePart2(dataList);
		System.out.println("Solution for part 2: " + solution);
	}

	public class numbersAndSymbols {
		int number = 0;
		Character symbol = null;
		int start = 0;
		int end = 0;
		int zeile = 0;

		numbersAndSymbols(Character setSymbol, int setStart, int setZeile) {
			number = 0;
			symbol = setSymbol;
			start = setStart;
			end = setStart;
			zeile = setZeile;
		}

		numbersAndSymbols(String zahl, int setStart, int setEnde, int setZeile) {
			number = Integer.parseInt(zahl);
			symbol = null;
			start = setStart;
			end = setEnde;
			zeile = setZeile;
		}

		public boolean isRelevantNumber() {
			for (numbersAndSymbols entry : liste) {
				// rechts von der Zahl
				if (entry.symbol != null) {
					if (entry.zeile == this.zeile) {
						if (entry.start == this.end + 1) {
							return true;
						}
					}
				}
				// links von der Zahl
				if (entry.symbol != null) {
					if (entry.zeile == this.zeile) {
						if (entry.start == this.start - 1) {
							return true;
						}
					}
				}
				// obendrüber von der Zahl
				if (entry.symbol != null) {
					if (entry.zeile == this.zeile - 1) {
						if (entry.start >= this.start - 1) {
							if (entry.start <= this.end + 1) {
								return true;
							}
						}
					}
				}
				// unterhalb der Zahl
				if (entry.symbol != null) {
					if (entry.zeile == this.zeile + 1) {
						if (entry.start >= this.start - 1) {
							if (entry.start <= this.end + 1) {
								return true;
							}
						}
					}
				}
			}
			return false;
		}

		public int[] isStarWithNumbers() {
			List<Integer> zweiZahlen = new ArrayList<Integer>();
			for (numbersAndSymbols entry : liste) {
				if (this.symbol != null) {
					if (this.symbol == '*') {
						if (entry.number != 0) {
							// gleiche Zeile
							if (this.zeile == entry.zeile) {
								// rechts davon
								if (entry.start == this.start + 1) {
									zweiZahlen.add(entry.number);
								}
								// links davon
								if (entry.end == this.start - 1) {
									zweiZahlen.add(entry.number);
								}
							}
							// obendrüber
							if (this.zeile == entry.zeile - 1) {
								if ((this.start >= entry.start) && (this.start <= entry.end)) {
									zweiZahlen.add(entry.number);
								}
								if (this.start - 1 == entry.end) {
									zweiZahlen.add(entry.number);
								}
								if ((this.start + 1 == entry.start)) {
									zweiZahlen.add(entry.number);
								}
							}
							// untendrunter
							if (this.zeile == entry.zeile + 1) {
								if ((this.start >= entry.start) && (this.start <= entry.end)) {
									zweiZahlen.add(entry.number);
								}
								if (this.start - 1 == entry.end) {
									zweiZahlen.add(entry.number);
								}
								if ((this.start + 1 == entry.start)) {
									zweiZahlen.add(entry.number);
								}
							}
						}
					}
				}
			}
			int returnWert[] = { 0, 0 };
			if (zweiZahlen.size() == 2) {
				int i = 0;
				for (int entry : zweiZahlen) {
					returnWert[i] = entry;
					i++;
				}

			}
			return returnWert;
		}
	}

	public static boolean isSymbol(Character symbol) {
		if (symbol == '.')
			return false;
		return true;
	}

	List<numbersAndSymbols> liste;

	public int solvePart1(List<String> dataList) {
		liste = new ArrayList<numbersAndSymbols>();
		int ende;
		int zeilennummer = 0;
		for (String line : dataList) {
			char[] lineAsChar = line.toCharArray();
			int i = 0;
			while (i < line.length()) {
				ende = 0; // alle Zahlen finden
				if (Character.isDigit(lineAsChar[i])) {
					boolean zahlLaufend = true;
					int speicherJ = 0;
					for (int j = 1; i + j < line.length(); j++) {
						if (Character.isDigit(lineAsChar[i + j])) {
							speicherJ = j;
						} else {
							ende = i + j - 1;
							zahlLaufend = false;
							break;
						}
					}
					if (zahlLaufend) {
						ende = i + speicherJ;
						zahlLaufend = false;
					}
					String zahl = "";
					for (int j = i; j <= ende; j++) {
						zahl = zahl + lineAsChar[j];
					}
					numbersAndSymbols toAdd = new numbersAndSymbols(zahl, i, ende, zeilennummer);
					liste.add(toAdd);
					i = ende + 1;
				} else { // symbole finden
					if (day3.isSymbol(lineAsChar[i])) {
						numbersAndSymbols toAdd = new numbersAndSymbols(lineAsChar[i], i, zeilennummer);
						liste.add(toAdd);
					}
					i++;
				}
			}
			zeilennummer++;
		}
		int sum = 0;
		for (numbersAndSymbols entry : liste) {
			if (entry.isRelevantNumber()) {
				sum += entry.number;
			}
		}
		return sum;
	}

	public int solvePart2(List<String> dataList) {
		liste = new ArrayList<numbersAndSymbols>();
		int ende;
		int zeilennummer = 0;
		for (String line : dataList) {
			char[] lineAsChar = line.toCharArray();
			int i = 0;
			while (i < line.length()) {
				ende = 0; // alle Zahlen finden
				if (Character.isDigit(lineAsChar[i])) {
					boolean zahlLaufend = true;
					int speicherJ = 0;
					for (int j = 1; i + j < line.length(); j++) {
						if (Character.isDigit(lineAsChar[i + j])) {
							speicherJ = j;
						} else {
							ende = i + j - 1;
							zahlLaufend = false;
							break;
						}
					}
					if (zahlLaufend) {
						ende = i + speicherJ;
						zahlLaufend = false;
					}
					String zahl = "";
					for (int j = i; j <= ende; j++) {
						zahl = zahl + lineAsChar[j];
					}
					numbersAndSymbols toAdd = new numbersAndSymbols(zahl, i, ende, zeilennummer);
					liste.add(toAdd);
					i = ende + 1;
				} else { // symbole finden
					if (day3.isSymbol(lineAsChar[i])) {
						numbersAndSymbols toAdd = new numbersAndSymbols(lineAsChar[i], i, zeilennummer);
						liste.add(toAdd);
					}
					i++;
				}
			}
			zeilennummer++;
		}
		int sum = 0;
		for (numbersAndSymbols entry : liste) {
			int[] zweiZahlen = entry.isStarWithNumbers();
			sum += zweiZahlen[0] * zweiZahlen[1];
		}
		return sum;
	}

}
