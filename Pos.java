class Pos{
  int x;
  int y;
  int scale;
  Pos(int x,int y){
    this.x=x;
    this.y=y;
  }
  Pos(int x,int y,int scale){
    this.x=x;
    this.y=y;
    this.scale=scale;
  }
  public int scale(){
    if(scale != 0){
      return this.scale;
    }else{
      return 1;
    }
  }
  public int x(){
    return this.x;

  }
  public int y(){
    return this.y;
  }
  
}