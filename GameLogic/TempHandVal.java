package GameLogic;

import static GameLogic.CheckMultipleVal.*;
import java.lang.Math.*;

public class TempHandVal{
  short[] cardShorts;
  public TempHandVal(byte[] num, byte[] suit){
    cardShorts = new short[num.length];
    for(int i = 0; i < num.length; i++){
      cardShorts[i] += 1;
      cardShorts[i] <<= num[i];
      if (num[i] == 0){
        cardShorts[i] |= 16384;
      }
    }
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
}