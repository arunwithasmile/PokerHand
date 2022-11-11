/**
 * 
 */
package com.arunsp.pokerhand;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.arunsp.pokerhand.mod.PlayResult;
import com.arunsp.pokerhand.service.InvalidDataException;
import com.arunsp.pokerhand.service.PokerHandService;

/**
 * @author arun
 *
 */
@SpringBootApplication
public class PokerHandApplication implements CommandLineRunner {

	@Autowired
	private PokerHandService pokerHandService;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(PokerHandApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String fileName = null;
		try {
			System.out.println("\n♠ ♥ ♦ ♣ ♠ ♥ ♦ ♣ ♠ ♥ ♦ ♣ ♠ ♥ ♦ ♣ Poker Hand ♥ ♦ ♣ ♠ ♥ ♦ ♣ ♠ ♥ ♦ ♣ ♠ ♥ ♦ ♠\n");
			if (args.length == 0) {
				System.err.println("No file name found in Arguements. Use the below format to run the application.");
				System.err.println("\njava -jar poker-hand-1.0.0.jar poker-hands.txt\n");
				return;
			}
			fileName = args[0];
			PlayResult result = pokerHandService.extractDealsAndPlay(fileName);
			System.out.println(result);
			System.out.println("\n♠ ♥ ♦ ♣ ♠ ♥ ♦ ♣ ♠ ♥ ♦ ♣ ♠ ♥ ♦ ♣ ♠ ♥ End ♥ ♦ ♣ ♠ ♥ ♦ ♣ ♠ ♥ ♦ ♣ ♠ ♥ ♦ ♠ ♥ ♦\n");
		} catch (InvalidDataException e) {
			System.err.println("Unfortunately, due to error at the line " + e.getLineNo()
					+ " in the contents of the file provided, the application has to end. Find the details below.");
			System.err.println(e.getMessage());
		} catch (FileNotFoundException e) {
			System.err.println("Unable to read the file '" + fileName
					+ "'. Make sure the fle exists in the same location as this jar");
		} catch (Exception e) {
			System.out.println(
					"There was an error that I know nothing off. See the details below and share it with the developer to debug it.");
			throw e;
		}
	}
}
