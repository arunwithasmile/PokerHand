/**
 * 
 */
package com.arunsp.pokerhand.mod;

import com.arunsp.pokerhand.util.Player;

/**
 * @author arun
 *
 */
public class PlayResult {

	private long playerACount;
	private long playerBCount;
	private long drawCount;

	public void updateResult(Player winner) {
		if (winner == Player.PLAYER_A) {
			playerACount++;
		} else if (winner == Player.PLAYER_B) {
			playerBCount++;
		} else {
			drawCount++;
		}
	}

	public long getPlayerACount() {
		return playerACount;
	}

	public long getPlayerBCount() {
		return playerBCount;
	}

	public long getDrawCount() {
		return drawCount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder().append("Player 1: ").append(playerACount).append(" hands")
				.append("\nPlayer 2: ").append(playerBCount).append(" hands");
		if (drawCount > 0) {
			builder.append("\nDraws: ").append(drawCount);
		}
		return builder.toString();
	}
}
