


public class Card{
  String suite;
  String letOrNum;
  String fullValue;
  int value;
  int scale;


  public Card(char suite,int num){
    this.suite =Character.toString(suite);
    
    this.value = num;
    
    this.letOrNum = String.valueOf(num);
    if(num==1){
      this.letOrNum = "A";
    }else if (num==11){
      this.letOrNum = "j";
    }
    else if (num==12){
      this.letOrNum = "Q";
    }else if (num==13){
      this.letOrNum = "K";
    }
    String temp = Character.toString(suite);
    fullValue = (temp+letOrNum);
    
  }
  public String toString(){
    return (suite+letOrNum);
  }
  public String suite(){
    return this.suite;
  }
  public int value(){
    return this.value;
  }
  public String letOrNum(){
    return this.letOrNum;
  }
  public String fullValue(){
    return this.fullValue;
  }

}