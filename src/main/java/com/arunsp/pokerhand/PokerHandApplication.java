/**
 * 
 */
package com.arunsp.pokerhand;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.arunsp.pokerhand.exception.InvalidDataException;
import com.arunsp.pokerhand.mod.PlayResult;
import com.arunsp.pokerhand.service.PokerHandService;

/**
 * @author Arun S P
 *
 */
@SpringBootApplication
public class PokerHandApplication implements CommandLineRunner {

	private final PokerHandService pokerHandService;

	@Autowired
	public PokerHandApplication(PokerHandService pokerHandService) {
		this.pokerHandService = pokerHandService;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(PokerHandApplication.class);
		SpringApplication app = builder.logStartupInfo(false).build();
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		String fileName = null;
		PlayResult result;
		try {
			System.out.println("\n♠ ♥ ♦ ♣ ♠ ♥ ♦ ♣ ♠ ♥ ♦ ♣ ♠ ♥ ♦ ♣ Poker Hand ♥ ♦ ♣ ♠ ♥ ♦ ♣ ♠ ♥ ♦ ♣ ♠ ♥ ♦ ♠\n");

			// Deciding if we need to read from file.
			if (args.length == 0) {
				System.err.println(
						"No file name found in Arguments. Relying on the terminal input. (Feed an empty line to finish)");
			} else {
				fileName = args[0];
			}

			// Call the appropriate handler.
			if (fileName != null) {
				result = pokerHandService.extractDealsAndPlay(fileName);
			} else {
				result = pokerHandService.extractDealsAndPlay();
			}

			// Showdown
			System.out.println(result);

			System.out.println("\n♠ ♥ ♦ ♣ ♠ ♥ ♦ ♣ ♠ ♥ ♦ ♣ ♠ ♥ ♦ ♣ ♠ ♥ End ♥ ♦ ♣ ♠ ♥ ♦ ♣ ♠ ♥ ♦ ♣ ♠ ♥ ♦ ♠ ♥ ♦\n");
		} catch (InvalidDataException e) {
			System.err.println("Unfortunately, due to error at the line " + e.getLineNo()
					+ " in the contents of the file provided, the application has to end. Find the details below.");
			System.err.println(e.getMessage());
		} catch (FileNotFoundException e) {
			System.err.println(
					"It appears that you've provided a text as an argument for this program. '" + fileName + "'");
			System.err.println("Unfortunately however, we were not able to read the file as '" + fileName
					+ "'. Make sure the fle exists in the same location as this jar");
		} catch (Exception e) {
			System.out.println(
					"There was an error that I know nothing off. See the details below and share it with the developer to debug it.");
			throw e;
		}
	}
}
