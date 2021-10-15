package Networking.test;
import java.net.URL;
import java.net.MalformedURLException;
// i did some things:
class NetworkTest{
  NetworkTest(){
    try{
      URL test = new URL("https://www.youtube.com/");
      int t = test.getDefaultPort();
      System.out.println(t);

    }catch(MalformedURLException error){
      System.out.println("error");
    }
    
  }
}