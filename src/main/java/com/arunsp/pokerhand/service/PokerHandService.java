/**
 * 
 */
package com.arunsp.pokerhand.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arunsp.pokerhand.exception.InvalidDataException;
import com.arunsp.pokerhand.mod.PlayResult;

/**
 * This service handles all the file parsing tasks and delegates the deals to
 * the game one by one.
 * 
 * @author Arun S P
 *
 */
@Service
public class PokerHandService {

	private final PokerHandGame pokerHandGame;

	@Autowired
	public PokerHandService(PokerHandGame pokerHandGame) {
		this.pokerHandGame = pokerHandGame;
	}

	public PlayResult extractDealsAndPlay() throws InvalidDataException, FileNotFoundException, IOException {
		List<String> deals = readIntoLines(new InputStreamReader(System.in));
		return pokerHandGame.play(deals);
	}

	public PlayResult extractDealsAndPlay(String fileName)
			throws InvalidDataException, FileNotFoundException, IOException {
		if (fileName != null) {
			System.out.println("Reading file: " + fileName + "\n");
		}
		List<String> deals = readIntoLines(new FileReader(fileName));
		return pokerHandGame.play(deals);
	}

	private List<String> readIntoLines(InputStreamReader reader) throws IOException, FileNotFoundException {
		List<String> lines = new ArrayList<>();
		try (BufferedReader bufferedReader = new BufferedReader(reader)) {
			String line;
			do {
				line = bufferedReader.readLine();
				if (line != null && !line.isEmpty()) {
					lines.add(line);
				}
			} while (line != null && !line.isEmpty());
		}
		return lines;
	}
}
