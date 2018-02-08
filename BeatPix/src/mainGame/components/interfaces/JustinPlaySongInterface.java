package mainGame.components.interfaces;

/**
 * These methods are what I will need to make the song work in the Game Screen
 * 
 * @author Justin Yau
 *
 */
public interface JustinPlaySongInterface {

	/**
	 * This method pauses the song.
	 */
	public void pauseSong();
	
	/**
	 * This method continues the song from the last time it was paused.
	 */
	public void resumeSong();
	
	/**
	 * This method stops the song from playing. <br> 
	 * It will be called when the game is exited out of early or when the game finishes.
	 */
	public void stopSong();
	
}
