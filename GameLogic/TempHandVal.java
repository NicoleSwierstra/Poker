package GameLogic;

import java.lang.Math.*;

//00 = Spades, 01 = Hearts, 10 = Diamonds, 11 = Clubs

public class TempHandVal{
	short[] cardShorts;
	byte[] num;
	byte[] suit;
	Card[] cards;
	public TempHandVal(byte[] num, byte[] suit){
		this.num = num;
		this.suit = suit;
		cardShorts = new short[num.length];
		for(int i = 0; i < num.length; i++){
			cardShorts[i] += 1;
			cardShorts[i] <<= num[i];
			if (num[i] == 0){
				cardShorts[i] |= 16384;
			}
		}
	}

	public TempHandVal(Card[] cards) {
		this.cards = cards;
		num = new byte[cards.length];
		suit = new byte[cards.length];
		cardShorts = new short[num.length];
		for(int i = 0; i < 7; i++) {
			cardShorts[i] += 0x1 << (num[i] = cards[i].num);
			suit[i] = cards[i].suit;
			//System.out.print(num[i] + " ");
		}
	}



	public int checkForFlush() {
		int suitCount = 0;
		for(byte var : suit) {
			suitCount += (0x1 << var*4);
		}
		for(int i = 0; i < 4; i++) {
			if ((suitCount >>> 4*i & 0xF) >= 5) return i;
		}
		return 4;
	}

	public int[] checkForStraights(){
		short output = 0;
		for(short var : cardShorts){
			output |= var;
		}
		int tShifts; int consecBits = 0;
		for(tShifts = 0; tShifts < 14; tShifts++){
			if((output & 1) == 1){
				consecBits++;
			} else {
				if(consecBits >= 5){
					break;
				}
				consecBits = 0;
			}
			output >>>= 1;
		}
		return new int[]{tShifts, tShifts - consecBits};
	}
	
	public void printHand() {
		for(Card var : cards) {
			var.print();
		}
		//System.out.println("\n");
	}
}