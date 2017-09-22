import java.lang.String;
import java.io.PrintStream;
import java.lang.Object;
import java.lang.Math;
import java.text.DecimalFormat;
import java.util.Scanner;
 
public class HITS{
  public static void main(String[] args) {
 
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter a number: ");
    int number = scanner.nextByte();
    double M[][]=new double [number][number];
    for(int i=0;i<number;i++)
      for(int j=0;j<number;j++)
        M[i][j]=scanner.nextByte();
    double Mt[][]=new double [number][number];
    double MMt[][]=new double [number][number];
    double MtM[][]=new double [number][number];
    double H[]=new double[number];
    double A[]=new double[number];

    for(int i=0;i<number;i++){
      for(int j=0;j<number;j++)
        Mt[j][i]=M[i][j];
    }
    for(int i=0;i<number;i++){
      H[i]=1;
      A[i]=1;
    }
    System.out.println("");
    System.out.println("HITS:");
    System.out.println("");
    String println="";
    System.out.println("Matrix:");
    for(int i=0;i<number;i++){ 
      for(int j=0;j<number;j++)
        println=println+ Integer.toString((int)(M[i][j]))+" ";//String.valueOf(M[i][j])+" ";
      System.out.println(println);
      println="";
}
 
    //M, M(transpose)
    for(int i=0;i<number;i++){
      for(int j=0;j<number;j++){
        for(int h=0;h<number;h++){
          MMt[i][j]+=M[i][h]*Mt[h][j];
          MtM[i][j]+=Mt[i][h]*M[h][j];
        }
      }
    }
    
    
    System.out.println("");
    System.out.println("HITS:");
    int x=0;
    while(x<10){
      String pri2="   ";
      int sum_A=0,sum_H=0;
      String pri3="   ";
      double sqrt_a=0,sqrt_h=0;
      double temp_A[]=new double[16];
      double temp_H[]=new double[16];
      System.out.println("Iteration "+x++);
      for(int i=0;i<number;i++) {
        pri2=pri2+ Double.toString((double)(A[i]))+" ";
        pri3=pri3+ Double.toString((double)(H[i]))+" ";
}
      
      System.out.println("Authority：");
      System.out.println(pri2);
      System.out.println("Hub：");
      System.out.println(pri3);
      for(int i=0;i<number;i++){
        for(int j=0;j<number;j++){
          temp_A[i]+=MtM[i][j]*A[j];
          sum_A+=temp_A[i]*temp_A[i];
          /*if (Math.sqrt(sum_A)-sqrt_a<.00001 || Math.sqrt(sum_H)-sqrt_h<.00001){
              break;
          }*/
          sqrt_a=Math.sqrt(sum_A);
          sqrt_h=Math.sqrt(sum_H);    
          //System.out.println("+++++++++"+temp_A[i]*temp_A[i]);
          temp_H[i]+=MMt[i][j]*H[j];
          sum_H+=temp_H[i]*temp_H[i];
          //System.out.println("+++++++++"+temp_H[i]*temp_H[i]);
        }
    }
      //System.out.println("-----------------"+sum_A+" "+sum_H);
      for(int i=0;i<number;i++){
        A[i]=(double)temp_A[i]/Math.sqrt(sum_A);
        H[i]=(double)temp_H[i]/Math.sqrt(sum_H);
      }     
}
    int max_A=0;
    double maxA=0;
    int max_H=0;
    double maxH=0;
    for(int i=0;i<number;i++){
      if(maxA<A[i]) {
        maxA=A[i];
        max_A=i;      
      }
      if(maxH<H[i]){
        maxH=H[i];
        max_H=i;
      }
}
    
    String pri4="   ";
    String pri5="   ";
    for(int i=0;i<number;i++) {
        pri4 = pri4 + String.valueOf(A[i])+" ";
        pri5 = pri5 + String.valueOf(H[i])+" ";
    }
    
      System.out.println("");
      System.out.println("Authority：");
      System.out.println(pri4);
      System.out.println("Hub：");
      System.out.println(pri5);
      System.out.println("");
      System.out.println("Page with maximum Authority Score:");
      System.out.println("Aut"+ max_A +"  "+maxA);
      System.out.println("Page with maximum Hub Score:");
      System.out.println("Hub"+ max_H +"  "+maxH);  
}
}
