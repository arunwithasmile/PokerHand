/**
 * 
 */
package com.arunsp.pokerhand.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arunsp.pokerhand.mod.PlayResult;
import com.arunsp.pokerhand.service.InvalidDataException;
import com.arunsp.pokerhand.service.PokerHandGame;

/**
 * This service handles all the file parsing tasks and delegates the deals to
 * the game one by one.
 * 
 * @author Arun S P
 *
 */
@Service
public class PokerHandService {

	@Autowired
	private PokerHandGame pokerHandGame;

	public PlayResult extractDealsAndPlay(String fileName)
			throws InvalidDataException, IOException, FileNotFoundException {
		List<String> deals = convertToLines(fileName);
		return pokerHandGame.play(deals);
	}

	private List<String> convertToLines(String fileName) throws IOException, FileNotFoundException {
		System.out.println("Reading file: " + fileName + "\n");
		List<String> lines = new ArrayList<>();
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
			String line;
			do {
				line = bufferedReader.readLine();
				if (line != null) {
					lines.add(line);
				}
			} while (line != null);
		}
		return lines;
	}
}
