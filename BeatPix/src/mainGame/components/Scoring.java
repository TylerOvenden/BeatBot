package mainGame.components;

public class Scoring {
	int score = 0;
	int offset;
	int health = 100;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public void scoring() {
		if(offset<16) {
			if(score<500000) {
				score += 10000;
			}	
					score += 15000;
		}
		if(offset<40 && offset>16) {
			score += 5000;
		}
		if(offset>40 && offset<73) {
			score += 1000;
		}
		if(offset>73 && offset<103) {
			score += 100;
		}
		if(offset>103 && offset<127) {
			score -= 200;
			health -= 1;
		}
		if(offset>127) {
			score -= 2000;
			health -= 3;
		}
			
	}

}