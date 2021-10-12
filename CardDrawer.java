import java.awt.*;
import java.awt.geom.AffineTransform;


class CardDrawer{
  Graphics g;
  
  
  
  
  CardDrawer(Graphics i){
    this.g = i;
  }

  
  public void drawThisCard(Card toDraw,int x,int y,int scale){
    if (toDraw!=null){
    int centerX = (scale*50+x)/2;
    int centerY = (scale*70+y)/2;
    String numberToShow =("N");
    if (toDraw.letOrNum()!=null){
      numberToShow = toDraw.letOrNum();
    }
    
    String suite = toDraw.suite();
    int value = toDraw.value();
    g.setColor(Color.WHITE);
    g.fillRect(x, y, scale*50, scale*70);

    
    determineColor(suite);

    g.setFont(new Font(null, Font.PLAIN, 7*scale));
    g.drawString(numberToShow, x+scale*2, y+6*scale);

    drawThisShape(x+scale, y+scale*7,scale*7 , scale*7,suite);
    Font rotatedFont = makeRotatedFont(scale,centerX,centerY);
    g.setFont(rotatedFont);
    g.drawString(numberToShow, 0, 0-6*scale);
    drawThisShape(scale*50+x-(7*scale), scale*70+y-(14*scale),scale*7, scale*7,suite);
    }
  }


  public void drawThisCard(Card toDraw,Pos pos){
    int x = pos.x();
    int y = pos.y();
    int scale = pos.scale();
    if (toDraw!=null){
    int centerX = (scale*50+x)/2;
    int centerY = (scale*70+y)/2;
    String numberToShow =("N");
    if (toDraw.letOrNum()!=null){
      numberToShow = toDraw.letOrNum();
    }
    
    String suite = toDraw.suite();
    int value = toDraw.value();
    g.setColor(Color.WHITE);
    g.fillRect(x, y, scale*50, scale*70);

    
    determineColor(suite);

    g.setFont(new Font(null, Font.PLAIN, 7*scale));
    g.drawString(numberToShow, x+scale*2, y+6*scale);

    drawThisShape(x+scale, y+scale*7,scale*7 , scale*7,suite);
    Font rotatedFont = makeRotatedFont(scale,centerX,centerY);
    g.setFont(rotatedFont);
    g.drawString(numberToShow, 0, 0-6*scale);
    drawThisShape(scale*50+x-(7*scale), scale*70+y-(14*scale),scale*7, scale*7,suite);
    }
  }


  private Font makeRotatedFont(int scale,int centerX,int centerY){
    Font font = new Font(null, Font.PLAIN, 7*scale); 
    g.setFont(font);
    AffineTransform affineTransform = new AffineTransform();
    affineTransform.rotate(Math.toRadians(180), centerX, centerY);
    Font rotatedFont = font.deriveFont(affineTransform);
    return rotatedFont;
  }
  
  public void determineColor(String suite){
    if(suite.equals("D")|| suite.equals("H")){
      g.setColor(Color.RED);
      
    }else{
      g.setColor(Color.BLACK);
    }
  }


  
  public void drawThisShape(int x,int y,int width,int height,String suite){
    
    if (suite.equals("D")){
      int[] xPoints = {x,x+width/2,x+width,x+width/2};
      int[] yPoints = {y+height/2,y+height,y+height/2,y};
      g.fillPolygon(xPoints, yPoints, 4);
    }else if (suite.equals("S")){
      g.fillRect(x, y, width, height);
    }else if (suite.equals("C")){
      g.fillOval(x,y,width,height);
    }else if (suite.equals("H")){
      int[] triangleXP = {x,x+width/2,x+width,(x+width/2)+width/4,x+width/2,x+width/4,x};
      int[] triangleYP = {y+height/2,y+height,y+height/2,y,y+height/2,y,y+height/2};
      g.fillPolygon(triangleXP, triangleYP, 7);
    }
    
  }
  public void clearBoard(){
    g.setColor(Color.GREEN);
    g.fillRect(0,0,600,600);
  }


}