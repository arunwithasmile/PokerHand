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

	public long getPlayerACount() {
		return playerACount;
	}

	public void setPlayerACount(long playerACount) {
		this.playerACount = playerACount;
	}

	public long getPlayerBCount() {
		return playerBCount;
	}

	public void setPlayerBCount(long playerBCount) {
		this.playerBCount = playerBCount;
	}

	public long getDrawCount() {
		return drawCount;
	}

	public void setDrawCount(long drawCount) {
		this.drawCount = drawCount;
	}

	public void updateResult(Player winner) {
		if (winner == Player.PLAYER_A) {
			playerACount++;
		} else if (winner == Player.PLAYER_B) {
			playerBCount++;
		} else {
			drawCount++;
		}
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
