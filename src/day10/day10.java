package day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class day10 {

	public static void main(String[] args) {
		day10 instanceOfTheDay = new day10();
		try {
			instanceOfTheDay.solve("C:\\Users\\boehm\\eclipse-workspace\\AdventOfCode2023\\src\\day10\\input.txt");
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
		long solution = solvePart1(dataList);
		System.out.println("Solution for part 1: " + solution);
		solution = solvePart2(dataList);
		System.out.println("Solution for part 2: " + solution);
	}

	public class Kartenpunkte {
		PunktMitRichtung punkt;
		Character linksRechtsSeilLeer;

		public Kartenpunkte(PunktMitRichtung punktInit, char ort) {
			punkt = punktInit;
			linksRechtsSeilLeer = ort;

		}
	}

	public class PunktMitRichtung {
		int x;
		int y;
		Character ist = null;
		Character wohin = null;
		Character woher = null;

		public PunktMitRichtung(int xInit, int yInit) {
			x = xInit;
			y = yInit;
			ist = null;
			wohin = null;
			woher = null;
		}

		public PunktMitRichtung(int xInit, int yInit, Character istInit, char wohinInit, char woherInit) {
			x = xInit;
			y = yInit;
			ist = istInit;
			wohin = wohinInit;
			woher = woherInit;
		}

		public boolean linksoffen() {
			if (ist == 'J')
				return true;
			if (ist == '-')
				return true;
			if (ist == '7')
				return true;
			return false;
		}

		public boolean rechtsoffen() {
			if (ist == '-')
				return true;
			if (ist == 'L')
				return true;
			if (ist == 'F')
				return true;
			return false;
		}

		public boolean obenoffen() {
			if (ist == '|')
				return true;
			if (ist == 'L')
				return true;
			if (ist == 'J')
				return true;
			return false;
		}

		public boolean untenoffen() {
			if (ist == '|')
				return true;
			if (ist == '7')
				return true;
			if (ist == 'F')
				return true;
			return false;
		}
	}

	public PunktMitRichtung rechts(PunktMitRichtung einPunkt) {
		PunktMitRichtung einneuerPunkt = new PunktMitRichtung(einPunkt.x + 1, einPunkt.y,
				Karte[einPunkt.x + 1][einPunkt.y], ' ', 'L');
		return einneuerPunkt;
	}

	public PunktMitRichtung links(PunktMitRichtung einPunkt) {
		PunktMitRichtung einneuerPunkt = new PunktMitRichtung(einPunkt.x - 1, einPunkt.y,
				Karte[einPunkt.x - 1][einPunkt.y], ' ', 'R');
		return einneuerPunkt;
	}

	public PunktMitRichtung unten(PunktMitRichtung einPunkt) {
		PunktMitRichtung einneuerPunkt = new PunktMitRichtung(einPunkt.x, einPunkt.y + 1,
				Karte[einPunkt.x][einPunkt.y + 1], ' ', 'O');
		return einneuerPunkt;
	}

	public PunktMitRichtung oben(PunktMitRichtung einPunkt) {
		PunktMitRichtung einneuerPunkt = new PunktMitRichtung(einPunkt.x, einPunkt.y - 1,
				Karte[einPunkt.x][einPunkt.y - 1], ' ', 'U');
		return einneuerPunkt;

	}

	Character[][] Karte;

	public long solvePart1(List<String> dataList) {
		Karte = new Character[dataList.get(0).length()][dataList.size()];
		List<Integer> Startpunkt = new ArrayList<Integer>();
		for (int y = 0; y < dataList.size(); y++) {
			String line = (dataList.get(y));
			for (int x = 0; x < dataList.get(0).length(); x++) {
				Character element = line.charAt(x);
				Karte[x][y] = element;
				if (element == 'S') {
					Startpunkt.add(0, x);
					Startpunkt.add(1, y);
				}
			}
		}
		long steps = 0L;
		List<Integer> woBinIch = Startpunkt;
		Character wohinGehts = null; // R, U, L, O
		int x = Startpunkt.get(0);
		int y = Startpunkt.get(1);
		PunktMitRichtung meinPunkt = new PunktMitRichtung(x, y, Karte[x][y], ' ', ' ');
		boolean nochUnterwegs = true;

		if ((Karte[meinPunkt.x + 1][meinPunkt.y] == '-' || (Karte[meinPunkt.x + 1][meinPunkt.y] == 'J')
				|| (Karte[meinPunkt.x + 1][meinPunkt.y] == '7'))) {
			meinPunkt.x = x + 1;
			meinPunkt.ist = Karte[x + 1][y];
			meinPunkt.woher = 'L';
		} else if ((Karte[meinPunkt.x][meinPunkt.y + 1] == '|' || (Karte[meinPunkt.x][meinPunkt.y + 1] == 'J')
				|| (Karte[meinPunkt.x][meinPunkt.y + 1] == 'L'))) {
			meinPunkt.y = y + 1;
			meinPunkt.ist = Karte[x][y + 1];
			meinPunkt.woher = 'O';
		} else if ((Karte[meinPunkt.x - 1][meinPunkt.y] == '-' || (Karte[meinPunkt.x - 1][meinPunkt.y] == 'F')
				|| (Karte[meinPunkt.x - 1][meinPunkt.y] == 'L'))) {
			meinPunkt.x = x - 1;
			meinPunkt.ist = Karte[x - 1][y];
			meinPunkt.woher = 'R';
		} else if ((Karte[meinPunkt.x][meinPunkt.y - 1] == '|' || (Karte[meinPunkt.x][meinPunkt.y - 1] == 'F')
				|| (Karte[meinPunkt.x][meinPunkt.y - 1] == '7'))) {
			meinPunkt.y = y - 1;
			meinPunkt.ist = Karte[x][y - 1];
			meinPunkt.woher = 'U';
		}
		steps++;

		while (nochUnterwegs) {
			steps++;
			Character elementKarte = meinPunkt.ist;
			if ((meinPunkt.woher == 'L') && (meinPunkt.linksoffen())) {
				if (meinPunkt.rechtsoffen())
					meinPunkt = rechts(meinPunkt);
				else if (meinPunkt.obenoffen())
					meinPunkt = oben(meinPunkt);
				else if (meinPunkt.untenoffen())
					meinPunkt = unten(meinPunkt);
			} else if ((meinPunkt.woher == 'U') && (meinPunkt.untenoffen())) {
				if (meinPunkt.rechtsoffen())
					meinPunkt = rechts(meinPunkt);
				else if (meinPunkt.obenoffen())
					meinPunkt = oben(meinPunkt);
				else if (meinPunkt.linksoffen())
					meinPunkt = links(meinPunkt);
			} else if ((meinPunkt.woher == 'R') && (meinPunkt.rechtsoffen())) {
				if (meinPunkt.untenoffen())
					meinPunkt = unten(meinPunkt);
				else if (meinPunkt.obenoffen())
					meinPunkt = oben(meinPunkt);
				else if (meinPunkt.linksoffen())
					meinPunkt = links(meinPunkt);
			} else if ((meinPunkt.woher == 'O') && (meinPunkt.obenoffen())) {
				if (meinPunkt.untenoffen())
					meinPunkt = unten(meinPunkt);
				else if (meinPunkt.rechtsoffen())
					meinPunkt = rechts(meinPunkt);
				else if (meinPunkt.linksoffen())
					meinPunkt = links(meinPunkt);
			}
			if (meinPunkt.ist == 'S')
				nochUnterwegs = false;
		}
		return steps / 2;
	}

	public long solvePart2(List<String> dataList) {
		Karte = new Character[dataList.get(0).length()][dataList.size()];
		List<Integer> Startpunkt = new ArrayList<Integer>();
		for (int y = 0; y < dataList.size(); y++) {
			String line = (dataList.get(y));
			for (int x = 0; x < dataList.get(0).length(); x++) {
				Character element = line.charAt(x);
				Karte[x][y] = element;
				if (element == 'S') {
					Startpunkt.add(0, x);
					Startpunkt.add(1, y);
				}
			}
		}
		List<PunktMitRichtung> steps = new ArrayList<PunktMitRichtung>();
		List<PunktMitRichtung> seilPunkte = new ArrayList<PunktMitRichtung>();
		List<PunktMitRichtung> linkeSeite = new ArrayList<PunktMitRichtung>();
		List<PunktMitRichtung> rechteSeite = new ArrayList<PunktMitRichtung>();
		List<Integer> woBinIch = Startpunkt;
		Character wohinGehts = null; // R, U, L, O
		int x = Startpunkt.get(0);
		int y = Startpunkt.get(1);
		PunktMitRichtung meinPunkt = new PunktMitRichtung(x, y, Karte[x][y], ' ', ' ');
		boolean nochUnterwegs = true;

		if ((Karte[meinPunkt.x + 1][meinPunkt.y] == '-' || (Karte[meinPunkt.x + 1][meinPunkt.y] == 'J')
				|| (Karte[meinPunkt.x + 1][meinPunkt.y] == '7'))) {
			meinPunkt.x = x + 1;
			meinPunkt.ist = Karte[x + 1][y];
			meinPunkt.woher = 'L';
		} else if ((Karte[meinPunkt.x][meinPunkt.y + 1] == '|' || (Karte[meinPunkt.x][meinPunkt.y + 1] == 'J')
				|| (Karte[meinPunkt.x][meinPunkt.y + 1] == 'L'))) {
			meinPunkt.y = y + 1;
			meinPunkt.ist = Karte[x][y + 1];
			meinPunkt.woher = 'O';
		} else if ((Karte[meinPunkt.x - 1][meinPunkt.y] == '-' || (Karte[meinPunkt.x - 1][meinPunkt.y] == 'F')
				|| (Karte[meinPunkt.x - 1][meinPunkt.y] == 'L'))) {
			meinPunkt.x = x - 1;
			meinPunkt.ist = Karte[x - 1][y];
			meinPunkt.woher = 'R';
		} else if ((Karte[meinPunkt.x][meinPunkt.y - 1] == '|' || (Karte[meinPunkt.x][meinPunkt.y - 1] == 'F')
				|| (Karte[meinPunkt.x][meinPunkt.y - 1] == '7'))) {
			meinPunkt.y = y - 1;
			meinPunkt.ist = Karte[x][y - 1];
			meinPunkt.woher = 'U';
		}
		steps.add(meinPunkt);
		seilPunkte.add(meinPunkt);

		while (nochUnterwegs) {
			Character elementKarte = meinPunkt.ist;
			if ((meinPunkt.woher == 'L') && (meinPunkt.linksoffen())) {
				if (meinPunkt.rechtsoffen())
					meinPunkt = rechts(meinPunkt);
				else if (meinPunkt.obenoffen())
					meinPunkt = oben(meinPunkt);
				else if (meinPunkt.untenoffen())
					meinPunkt = unten(meinPunkt);
			} else if ((meinPunkt.woher == 'U') && (meinPunkt.untenoffen())) {
				if (meinPunkt.rechtsoffen())
					meinPunkt = rechts(meinPunkt);
				else if (meinPunkt.obenoffen())
					meinPunkt = oben(meinPunkt);
				else if (meinPunkt.linksoffen())
					meinPunkt = links(meinPunkt);
			} else if ((meinPunkt.woher == 'R') && (meinPunkt.rechtsoffen())) {
				if (meinPunkt.untenoffen())
					meinPunkt = unten(meinPunkt);
				else if (meinPunkt.obenoffen())
					meinPunkt = oben(meinPunkt);
				else if (meinPunkt.linksoffen())
					meinPunkt = links(meinPunkt);
			} else if ((meinPunkt.woher == 'O') && (meinPunkt.obenoffen())) {
				if (meinPunkt.untenoffen())
					meinPunkt = unten(meinPunkt);
				else if (meinPunkt.rechtsoffen())
					meinPunkt = rechts(meinPunkt);
				else if (meinPunkt.linksoffen())
					meinPunkt = links(meinPunkt);
			}
			steps.add(meinPunkt);
			seilPunkte.add(meinPunkt);
			if (meinPunkt.ist == '|') {
				if (meinPunkt.woher == 'U') {
					PunktMitRichtung punkt = new PunktMitRichtung(meinPunkt.x + 1, meinPunkt.y, '*', ' ', ' ');
					rechteSeite.add(punkt);
					punkt = new PunktMitRichtung(meinPunkt.x - 1, meinPunkt.y, '*', ' ', ' ');
					linkeSeite.add(punkt);
				} else if (meinPunkt.woher == 'O') {
					PunktMitRichtung rechterPunkt = new PunktMitRichtung(meinPunkt.x - 1, meinPunkt.y, '*', ' ', ' ');
					rechteSeite.add(rechterPunkt);
					PunktMitRichtung linkerPunkt = new PunktMitRichtung(meinPunkt.x + 1, meinPunkt.y, '*', ' ', ' ');
					linkeSeite.add(linkerPunkt);
				}

			} else if (meinPunkt.ist == '-') {
				if (meinPunkt.woher == 'L') {
					PunktMitRichtung rechterPunkt = new PunktMitRichtung(meinPunkt.x, meinPunkt.y + 1, '*', ' ', ' ');
					rechteSeite.add(rechterPunkt);
					PunktMitRichtung linkerPunkt = new PunktMitRichtung(meinPunkt.x, meinPunkt.y - 1, '*', ' ', ' ');
					linkeSeite.add(linkerPunkt);
				} else if (meinPunkt.woher == 'R') {
					PunktMitRichtung rechterPunkt = new PunktMitRichtung(meinPunkt.x, meinPunkt.y - 1, '*', ' ', ' ');
					rechteSeite.add(rechterPunkt);
					PunktMitRichtung linkerPunkt = new PunktMitRichtung(meinPunkt.x, meinPunkt.y + 1, '*', ' ', ' ');
					linkeSeite.add(linkerPunkt);
				}

			} else if (meinPunkt.ist == 'L') {
				if (meinPunkt.woher == 'O') {
					PunktMitRichtung rechterPunkt = new PunktMitRichtung(meinPunkt.x - 1, meinPunkt.y, '*', ' ', ' ');
					rechteSeite.add(rechterPunkt);
					rechterPunkt = new PunktMitRichtung(meinPunkt.x - 1, meinPunkt.y + 1, '*', ' ', ' ');
					rechteSeite.add(rechterPunkt);
					rechterPunkt = new PunktMitRichtung(meinPunkt.x, meinPunkt.y + 1, '*', ' ', ' ');
					rechteSeite.add(rechterPunkt);
					PunktMitRichtung linkerPunkt = new PunktMitRichtung(meinPunkt.x - 1, meinPunkt.y - 1, '*', ' ',
							' ');
					linkeSeite.add(linkerPunkt);
				} else if (meinPunkt.woher == 'R') {
					PunktMitRichtung rechterPunkt = new PunktMitRichtung(meinPunkt.x + 1, meinPunkt.y - 1, '*', ' ',
							' ');
					rechteSeite.add(rechterPunkt);
					PunktMitRichtung linkerPunkt = new PunktMitRichtung(meinPunkt.x, meinPunkt.y + 1, '*', ' ', ' ');
					linkeSeite.add(linkerPunkt);
					linkerPunkt = new PunktMitRichtung(meinPunkt.x - 1, meinPunkt.y + 1, '*', ' ', ' ');
					linkeSeite.add(linkerPunkt);
					linkerPunkt = new PunktMitRichtung(meinPunkt.x - 1, meinPunkt.y, '*', ' ', ' ');
					linkeSeite.add(linkerPunkt);
				}
			} else if (meinPunkt.ist == 'J') {
				if (meinPunkt.woher == 'O') {
					PunktMitRichtung rechterPunkt = new PunktMitRichtung(meinPunkt.x - 1, meinPunkt.y - 1, '*', ' ',
							' ');
					rechteSeite.add(rechterPunkt);
					PunktMitRichtung linkerPunkt = new PunktMitRichtung(meinPunkt.x + 1, meinPunkt.y, '*', ' ', ' ');
					linkeSeite.add(linkerPunkt);
					linkerPunkt = new PunktMitRichtung(meinPunkt.x + 1, meinPunkt.y + 1, '*', ' ', ' ');
					linkeSeite.add(linkerPunkt);
					linkerPunkt = new PunktMitRichtung(meinPunkt.x, meinPunkt.y + 1, '*', ' ', ' ');
					linkeSeite.add(linkerPunkt);
				} else if (meinPunkt.woher == 'L') {
					PunktMitRichtung punkt = new PunktMitRichtung(meinPunkt.x - 1, meinPunkt.y - 1, '*', ' ', ' ');
					linkeSeite.add(punkt);
					punkt = new PunktMitRichtung(meinPunkt.x, meinPunkt.y + 1, '*', ' ', ' ');
					rechteSeite.add(punkt);
					punkt = new PunktMitRichtung(meinPunkt.x + 1, meinPunkt.y + 1, '*', ' ', ' ');
					rechteSeite.add(punkt);
					punkt = new PunktMitRichtung(meinPunkt.x + 1, meinPunkt.y, '*', ' ', ' ');
					rechteSeite.add(punkt);
				}
			} else if (meinPunkt.ist == '7') {
				if (meinPunkt.woher == 'L') {
					PunktMitRichtung punkt = new PunktMitRichtung(meinPunkt.x - 1, meinPunkt.y + 1, '*', ' ', ' ');
					rechteSeite.add(punkt);
					punkt = new PunktMitRichtung(meinPunkt.x, meinPunkt.y - 1, '*', ' ', ' ');
					linkeSeite.add(punkt);
					punkt = new PunktMitRichtung(meinPunkt.x + 1, meinPunkt.y - 1, '*', ' ', ' ');
					linkeSeite.add(punkt);
					punkt = new PunktMitRichtung(meinPunkt.x + 1, meinPunkt.y, '*', ' ', ' ');
					linkeSeite.add(punkt);

				} else if (meinPunkt.woher == 'U') {
					PunktMitRichtung punkt = new PunktMitRichtung(meinPunkt.x - 1, meinPunkt.y + 1, '*', ' ', ' ');
					linkeSeite.add(punkt);
					punkt = new PunktMitRichtung(meinPunkt.x, meinPunkt.y - 1, '*', ' ', ' ');
					rechteSeite.add(punkt);
					punkt = new PunktMitRichtung(meinPunkt.x + 1, meinPunkt.y - 1, '*', ' ', ' ');
					rechteSeite.add(punkt);
					punkt = new PunktMitRichtung(meinPunkt.x + 1, meinPunkt.y, '*', ' ', ' ');
					rechteSeite.add(punkt);
				}
			} else if (meinPunkt.ist == 'F') {
				if (meinPunkt.woher == 'U') {
					PunktMitRichtung punkt = new PunktMitRichtung(meinPunkt.x + 1, meinPunkt.y + 1, '*', ' ', ' ');
					rechteSeite.add(punkt);
					punkt = new PunktMitRichtung(meinPunkt.x - 1, meinPunkt.y, '*', ' ', ' ');
					linkeSeite.add(punkt);
					punkt = new PunktMitRichtung(meinPunkt.x - 1, meinPunkt.y - 1, '*', ' ', ' ');
					linkeSeite.add(punkt);
					punkt = new PunktMitRichtung(meinPunkt.x, meinPunkt.y, '*', ' ', ' ');
					linkeSeite.add(punkt);

				} else if (meinPunkt.woher == 'R') {
					PunktMitRichtung punkt = new PunktMitRichtung(meinPunkt.x + 1, meinPunkt.y + 1, '*', ' ', ' ');
					linkeSeite.add(punkt);
					punkt = new PunktMitRichtung(meinPunkt.x - 1, meinPunkt.y, '*', ' ', ' ');
					rechteSeite.add(punkt);
					punkt = new PunktMitRichtung(meinPunkt.x - 1, meinPunkt.y - 1, '*', ' ', ' ');
					rechteSeite.add(punkt);
					punkt = new PunktMitRichtung(meinPunkt.x, meinPunkt.y, '*', ' ', ' ');
					rechteSeite.add(punkt);
				}
			}
			if (meinPunkt.ist == 'S')
				nochUnterwegs = false;
		}

		Character[][] neueKarte = new Character[dataList.get(0).length()][dataList.size()];

		for (int i = 0; i < dataList.get(0).length(); i++)
			for (int j = 0; j < dataList.size(); j++)
				neueKarte[i][j] = ' ';

		for (PunktMitRichtung punkt : linkeSeite) {
			if (punkt.x >= 0 && punkt.y >= 0 && punkt.x < dataList.get(0).length() && punkt.y < dataList.size())
				neueKarte[punkt.x][punkt.y] = '*';
		}
		for (PunktMitRichtung punkt : rechteSeite) {
			if (punkt.x >= 0 && punkt.y >= 0 && punkt.x < dataList.get(0).length() && punkt.y < dataList.size())

				neueKarte[punkt.x][punkt.y] = 'R';
		}
		for (PunktMitRichtung punkt : seilPunkte) {
			if (punkt.x >= 0 && punkt.y >= 0 && punkt.x < dataList.get(0).length() && punkt.y < dataList.size())

				neueKarte[punkt.x][punkt.y] = 'Z';
		}

		for (int i = 0; i < dataList.size(); i++) {
			for (int j = 0; j < dataList.get(0).length(); j++) {
				System.out.print(neueKarte[j][i]);
			}
			System.out.print("\n");

		}

		boolean beendet = false;
		while (!beendet) {
			for (int i = 0; i < dataList.get(0).length(); i++) {
				for (int j = 0; j < dataList.size(); j++) {
					if (neueKarte[i][j] == '*') {
						if (i > 0 && neueKarte[i - 1][j] == ' ') {
							neueKarte[i - 1][j] = '*';
						} else if (j > 0 && neueKarte[i][j - 1] == ' ') {
							neueKarte[i][j - 1] = '*';
						} else if (i < dataList.get(0).length() - 1 && neueKarte[i + 1][j] == ' ') {
							neueKarte[i + 1][j] = '*';
						} else if (i < dataList.get(0).length() - 1 && j < dataList.size() - 1
								&& neueKarte[i + 1][j + 1] == ' ') {
							neueKarte[i + 1][j + 1] = '*';

						}
					}
					if (neueKarte[i][j] == 'R') {
						if (i > 0 && neueKarte[i - 1][j] == ' ') {
							neueKarte[i - 1][j] = 'R';
						} else if (j > 0 && neueKarte[i][j - 1] == ' ') {
							neueKarte[i][j - 1] = 'R';
						} else if (i < dataList.get(0).length() - 1 && neueKarte[i + 1][j] == ' ') {
							neueKarte[i + 1][j] = 'R';
						} else if (i < dataList.get(0).length() - 1 && j < dataList.size() - 1
								&& neueKarte[i + 1][j + 1] == ' ') {
							neueKarte[i + 1][j + 1] = 'R';
						}
					}
				}
			}
			beendet = true;
			for (int i = 0; i < dataList.get(0).length(); i++)
				for (int j = 0; j < dataList.size(); j++) {
					if (neueKarte[i][j] == ' ')
						beendet = false;
				}
		}
		int sumLinks = 0;
		int sumRechts = 0;
		for (int i = 0; i < dataList.get(0).length(); i++) {
			for (int j = 0; j < dataList.size(); j++) {
				if (neueKarte[i][j] == '*')
					sumLinks++;
				if (neueKarte[i][j] == 'R')
					sumRechts++;
			}

		}
		for (int i = 0; i < dataList.size(); i++) {
			for (int j = 0; j < dataList.get(0).length(); j++) {
				System.out.print(neueKarte[j][i]);
			}
			System.out.print("\n");

		}
		for (int i = 0; i < dataList.size(); i++) {
			if (neueKarte[0][i] == '*') {
				return sumRechts;
			}
			if (neueKarte[dataList.get(0).length() - 1][i] == '*') {
				return sumRechts;
			}
		}
		for (int i = 0; i < dataList.get(0).length(); i++) {
			if (neueKarte[i][0] == '*') {
				return sumRechts;
			}
			if (neueKarte[i][dataList.size() - 1] == '*') {
				return sumRechts;
			}
		}

		return sumLinks;
	}
}
