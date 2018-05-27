package GameLogic;

public class GameDecoder {
    
    public static void parseMap(PlayerLogic player, String mapString) {
		
		String[] columns = mapString.split(";");
		
		int[][] map = new int[columns.length][columns.length];
		
		boolean gameOver = true;
		
		for(int i = 0; i < columns.length ; i++) {
			String[] rows = columns[i].split(",");
			for(int j = 0; j < rows.length ; j++) {
				map[i][j] = Integer.parseInt(rows[j]);
				if (Integer.parseInt(rows[j]) == 0)
					gameOver = false;
			}
		}

		player.setGameOver(gameOver);	
		player.setMap(map);
	}
}
