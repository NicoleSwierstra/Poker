package GameLogic;

public class CheckMultipleVal{
  public static boolean CMV(int check, int[] checkArray){
    for(int cv : checkArray){
      if (cv != check) return false;
    }
    return true;
  }

  public static boolean CMV(int check, int[] checkArray, boolean invert){
    for(int cv : checkArray){
      if(invert ? cv == check : cv != check){
        return false;
      }
    }
    return true;
  }

    public static boolean CMV(byte check, byte[] checkArray){
    for(byte cv : checkArray){
      if (cv != check) return false;
    }
    return true;
  }

  public static boolean CMV(byte check, byte[] checkArray, boolean invert){
    for(byte cv : checkArray){
      if(invert ? cv == check : cv != check){
        return false;
      }
    }
    return true;
  }
}