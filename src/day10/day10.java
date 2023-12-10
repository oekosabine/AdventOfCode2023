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

		public PunktMitRichtung(PunktMitRichtung punktInit) {
			x = punktInit.x;
			y = punktInit.y;
			ist = punktInit.ist;
			wohin = punktInit.wohin;
			woher = punktInit.woher;

		}

		public void linksRechts(boolean welcheListe, List<PunktMitRichtung> linkeSeite,
				List<PunktMitRichtung> rechteSeite) {
			// welche Liste = true: links
			// false: rechts
			if (welcheListe)
				linkeSeite.add(this);
			else
				rechteSeite.add(this);
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
		PunktMitRichtung Startpunkt = startDinge(dataList);
		long steps = 0L;
		boolean nochUnterwegs = true;
		PunktMitRichtung meinPunkt = findeStart(Startpunkt);
		steps++;

		while (nochUnterwegs) {
			steps++;
			meinPunkt = neuerPunkt(meinPunkt);
			if (meinPunkt.ist == 'S')
				nochUnterwegs = false;
		}
		return steps / 2;
	}

	int xLaenge, yLaenge;

	private PunktMitRichtung findeStart(PunktMitRichtung Startpunkt) {
		PunktMitRichtung meinPunkt = null, punktRechts = null, punktLinks = null, punktOben = null, punktUnten = null;
		Character wertLinks = null, wertOben = null, wertRechts = null, wertUnten = null;
		if (Startpunkt.x + 1 < xLaenge) {
			wertRechts = Karte[Startpunkt.x + 1][Startpunkt.y];
			punktRechts = new PunktMitRichtung(Startpunkt.x + 1, Startpunkt.y, wertRechts, ' ', 'L');
		}
		if (Startpunkt.x > 0) {
			wertLinks = Karte[Startpunkt.x - 1][Startpunkt.y];
			punktLinks = new PunktMitRichtung(Startpunkt.x - 1, Startpunkt.y, wertLinks, ' ', 'R');
		}
		if (Startpunkt.y > 0) {
			wertOben = Karte[Startpunkt.x][Startpunkt.y - 1];
			punktOben = new PunktMitRichtung(Startpunkt.x, Startpunkt.y - 1, wertOben, ' ', 'U');
		}
		if (Startpunkt.y + 1 < yLaenge) {
			wertUnten = Karte[Startpunkt.x][Startpunkt.y + 1];
			punktUnten = new PunktMitRichtung(Startpunkt.x, Startpunkt.y + 1, wertUnten, ' ', 'O');
		}
		if (punktRechts != null && punktRechts.linksoffen()) {
			meinPunkt = new PunktMitRichtung(punktRechts);
		} else if (punktUnten != null && punktUnten.untenoffen()) {
			meinPunkt = new PunktMitRichtung(punktUnten);
		} else if (punktLinks != null && punktLinks.rechtsoffen()) {
			meinPunkt = new PunktMitRichtung(punktLinks);
		} else if (punktOben != null && punktOben.obenoffen()) {
			meinPunkt = new PunktMitRichtung(punktOben);
		}
		return meinPunkt;
	}

	public long solvePart2(List<String> dataList) {
		PunktMitRichtung Startpunkt = startDinge(dataList);
		List<PunktMitRichtung> seilPunkte = new ArrayList<PunktMitRichtung>();
		List<PunktMitRichtung> linkeSeite = new ArrayList<PunktMitRichtung>();
		List<PunktMitRichtung> rechteSeite = new ArrayList<PunktMitRichtung>();
		boolean nochUnterwegs = true;
		PunktMitRichtung meinPunkt = findeStart(Startpunkt);
		seilPunkte.add(meinPunkt);
		while (nochUnterwegs) {
			meinPunkt = neuerPunkt(meinPunkt);
			seilPunkte.add(meinPunkt);
			addLinksRechts(linkeSeite, rechteSeite, meinPunkt);
			if (meinPunkt.ist == 'S')
				nochUnterwegs = false;
		}

		Character[][] neueKarte = new Character[xLaenge][yLaenge];
		erstBefuellungNeueKarte(seilPunkte, linkeSeite, rechteSeite, neueKarte);
		auffuellen(neueKarte);
		int sumLinks = 0;
		int sumRechts = 0;
		for (int i = 0; i < xLaenge; i++) {
			for (int j = 0; j < yLaenge; j++) {
				if (neueKarte[i][j] == '*')
					sumLinks++;
				if (neueKarte[i][j] == 'R')
					sumRechts++;
			}
		}
		for (int i = 0; i < yLaenge; i++) {
			for (int j = 0; j < xLaenge; j++) {
				System.out.print(neueKarte[j][i]);
			}
			System.out.print("\n");
		}

		for (int i = 0; i < yLaenge; i++) {
			if (neueKarte[0][i] == '*') {
				return sumRechts;
			}
			if (neueKarte[xLaenge - 1][i] == '*') {
				return sumRechts;
			}
		}
		for (int i = 0; i < xLaenge; i++) {
			if (neueKarte[i][0] == '*') {
				return sumRechts;
			}
			if (neueKarte[i][yLaenge - 1] == '*') {
				return sumRechts;
			}
		}
		return sumLinks;
	}

	private void erstBefuellungNeueKarte(List<PunktMitRichtung> seilPunkte, List<PunktMitRichtung> linkeSeite,
			List<PunktMitRichtung> rechteSeite, Character[][] neueKarte) {
		for (int i = 0; i < xLaenge; i++)
			for (int j = 0; j < yLaenge; j++)
				neueKarte[i][j] = ' ';
		for (PunktMitRichtung punkt : linkeSeite) {
			if (punkt.x >= 0 && punkt.y >= 0 && punkt.x < xLaenge && punkt.y < yLaenge)
				neueKarte[punkt.x][punkt.y] = '*';
		}
		for (PunktMitRichtung punkt : rechteSeite) {
			if (punkt.x >= 0 && punkt.y >= 0 && punkt.x < xLaenge && punkt.y < yLaenge)
				neueKarte[punkt.x][punkt.y] = 'R';
		}
		for (PunktMitRichtung punkt : seilPunkte) {
			if (punkt.x >= 0 && punkt.y >= 0 && punkt.x < xLaenge && punkt.y < yLaenge)
				neueKarte[punkt.x][punkt.y] = 'Z';
		}
		for (int i = 0; i < yLaenge; i++) {
			for (int j = 0; j < xLaenge; j++) {
				System.out.print(neueKarte[j][i]);
			}
			System.out.print("\n");
		}
	}

	private PunktMitRichtung startDinge(List<String> dataList) {
		xLaenge = dataList.get(0).length();
		yLaenge = dataList.size();
		Karte = new Character[xLaenge][yLaenge];
		PunktMitRichtung Startpunkt = new PunktMitRichtung(0, 0);
		for (int y = 0; y < yLaenge; y++) {
			String line = (dataList.get(y));
			for (int x = 0; x < xLaenge; x++) {
				Character element = line.charAt(x);
				Karte[x][y] = element;
				if (element == 'S') {
					Startpunkt = new PunktMitRichtung(x, y);
				}
			}
		}
		return Startpunkt;
	}

	private void auffuellen(Character[][] neueKarte) {
		boolean beendet = false;
		while (!beendet) {
			for (int i = 0; i < xLaenge; i++) {
				for (int j = 0; j < yLaenge; j++) {
					if (neueKarte[i][j] == '*') {
						if (i > 0 && neueKarte[i - 1][j] == ' ') {
							neueKarte[i - 1][j] = '*';
						} else if (j > 0 && neueKarte[i][j - 1] == ' ') {
							neueKarte[i][j - 1] = '*';
						} else if (i < xLaenge - 1 && neueKarte[i + 1][j] == ' ') {
							neueKarte[i + 1][j] = '*';
						} else if (i < xLaenge - 1 && j < yLaenge - 1 && neueKarte[i + 1][j + 1] == ' ') {
							neueKarte[i + 1][j + 1] = '*';

						}
					}
					if (neueKarte[i][j] == 'R') {
						if (i > 0 && neueKarte[i - 1][j] == ' ') {
							neueKarte[i - 1][j] = 'R';
						} else if (j > 0 && neueKarte[i][j - 1] == ' ') {
							neueKarte[i][j - 1] = 'R';
						} else if (i < xLaenge - 1 && neueKarte[i + 1][j] == ' ') {
							neueKarte[i + 1][j] = 'R';
						} else if (i < xLaenge - 1 && j < yLaenge - 1 && neueKarte[i + 1][j + 1] == ' ') {
							neueKarte[i + 1][j + 1] = 'R';
						}
					}
				}
			}
			beendet = true;
			for (int i = 0; i < xLaenge; i++)
				for (int j = 0; j < yLaenge; j++) {
					if (neueKarte[i][j] == ' ')
						beendet = false;
				}
		}
	}

	private void addLinksRechts(List<PunktMitRichtung> linkeSeite, List<PunktMitRichtung> rechteSeite,
			PunktMitRichtung meinPunkt) {
		if (meinPunkt.ist == '|') {
			PunktMitRichtung punkt = new PunktMitRichtung(meinPunkt.x + 1, meinPunkt.y, '*', ' ', ' ');
			punkt.linksRechts(meinPunkt.woher != 'U', linkeSeite, rechteSeite);
			punkt = new PunktMitRichtung(meinPunkt.x - 1, meinPunkt.y, '*', ' ', ' ');
			punkt.linksRechts(meinPunkt.woher == 'U', linkeSeite, rechteSeite);
		} else if (meinPunkt.ist == '-') {
			PunktMitRichtung punkt = new PunktMitRichtung(meinPunkt.x, meinPunkt.y + 1, '*', ' ', ' ');
			punkt.linksRechts(meinPunkt.woher != 'L', linkeSeite, rechteSeite);
			punkt = new PunktMitRichtung(meinPunkt.x, meinPunkt.y - 1, '*', ' ', ' ');
			punkt.linksRechts(meinPunkt.woher == 'L', linkeSeite, rechteSeite);
		} else if (meinPunkt.ist == 'L') {
			PunktMitRichtung punkt = new PunktMitRichtung(meinPunkt.x - 1, meinPunkt.y, '*', ' ', ' ');
			punkt.linksRechts(meinPunkt.woher != 'O', linkeSeite, rechteSeite);
			punkt = new PunktMitRichtung(meinPunkt.x - 1, meinPunkt.y + 1, '*', ' ', ' ');
			punkt.linksRechts(meinPunkt.woher != 'O', linkeSeite, rechteSeite);
			punkt = new PunktMitRichtung(meinPunkt.x, meinPunkt.y + 1, '*', ' ', ' ');
			punkt.linksRechts(meinPunkt.woher != 'O', linkeSeite, rechteSeite);
			punkt = new PunktMitRichtung(meinPunkt.x + 1, meinPunkt.y - 1, '*', ' ', ' ');
			punkt.linksRechts(meinPunkt.woher == 'O', linkeSeite, rechteSeite);
		} else if (meinPunkt.ist == 'J') {
			PunktMitRichtung punkt = new PunktMitRichtung(meinPunkt.x - 1, meinPunkt.y - 1, '*', ' ', ' ');
			punkt.linksRechts(meinPunkt.woher != 'O', linkeSeite, rechteSeite);
			punkt = new PunktMitRichtung(meinPunkt.x + 1, meinPunkt.y, '*', ' ', ' ');
			punkt.linksRechts(meinPunkt.woher == 'O', linkeSeite, rechteSeite);
			punkt = new PunktMitRichtung(meinPunkt.x + 1, meinPunkt.y + 1, '*', ' ', ' ');
			punkt.linksRechts(meinPunkt.woher == 'O', linkeSeite, rechteSeite);
			punkt = new PunktMitRichtung(meinPunkt.x, meinPunkt.y + 1, '*', ' ', ' ');
			punkt.linksRechts(meinPunkt.woher == 'O', linkeSeite, rechteSeite);
		} else if (meinPunkt.ist == '7') {
			PunktMitRichtung punkt = new PunktMitRichtung(meinPunkt.x - 1, meinPunkt.y + 1, '*', ' ', ' ');
			punkt.linksRechts(meinPunkt.woher != 'L', linkeSeite, rechteSeite);
			punkt = new PunktMitRichtung(meinPunkt.x, meinPunkt.y - 1, '*', ' ', ' ');
			punkt.linksRechts(meinPunkt.woher == 'L', linkeSeite, rechteSeite);
			punkt = new PunktMitRichtung(meinPunkt.x + 1, meinPunkt.y - 1, '*', ' ', ' ');
			punkt.linksRechts(meinPunkt.woher == 'L', linkeSeite, rechteSeite);
			punkt = new PunktMitRichtung(meinPunkt.x + 1, meinPunkt.y, '*', ' ', ' ');
			punkt.linksRechts(meinPunkt.woher == 'L', linkeSeite, rechteSeite);
		} else if (meinPunkt.ist == 'F') {
			PunktMitRichtung punkt = new PunktMitRichtung(meinPunkt.x + 1, meinPunkt.y + 1, '*', ' ', ' ');
			punkt.linksRechts(meinPunkt.woher != 'U', linkeSeite, rechteSeite);
			punkt = new PunktMitRichtung(meinPunkt.x - 1, meinPunkt.y, '*', ' ', ' ');
			punkt.linksRechts(meinPunkt.woher == 'U', linkeSeite, rechteSeite);
			punkt = new PunktMitRichtung(meinPunkt.x - 1, meinPunkt.y - 1, '*', ' ', ' ');
			punkt.linksRechts(meinPunkt.woher == 'U', linkeSeite, rechteSeite);
			punkt = new PunktMitRichtung(meinPunkt.x, meinPunkt.y - 1, '*', ' ', ' ');
			punkt.linksRechts(meinPunkt.woher == 'U', linkeSeite, rechteSeite);
		}
	}

	private PunktMitRichtung neuerPunkt(PunktMitRichtung meinPunkt) {
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
		return meinPunkt;
	}
}
