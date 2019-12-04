package edu.zju.sc.aoot.model;
import java.text.DecimalFormat;
import java.util.Random;
import java.io.*;

/**<p>This class simulates a n-side dice.The class can tell you the top-value of
 * the dice.We can also change the number of the dice's side by this class.What's
 * more,this class can imitate 100000-time roll and output the result.</p>
 * @author Wu,Qihui
 * @version 1.0
 */

public class Die {

    /**
     * the number of the side
     */

    private int sides = 6;

    /**
     * the top-face number of the dice currently
     */

    private int value = 1;

    /**
     * change the value of the dice
     * @param     value  the value to be changed to
     */

    public void setValue(int value) {
        this.value = value;
    }

    /**
     * get the value currently
     * @return current value
     */

    public int getValue() {
        return value;
    }

    /**
     * Initializes a newly created {@code Die} object so that it represents a
     * six-side dice.
     */

    public Die(){
        sides = 6;
    }

    /**
     * Initializes a newly created {@code Die} object so that it represents
     * the n sides dice.
     * @param  n
     *         the number of side
     * @throws IllegalArgumentException if the initial number of side(n) is
     *          nonpositive
     */

    public Die(int n){
        super();
        if(n <= 1)
            throw new IllegalArgumentException("Illeagal argument");
        else sides = n;
    }

    /**
     * Get the number of side
     *
     * @return the number of side
     */

    public int numOfSides(){
        return sides;
    }

    /**
     * Roll the dice,and return the topface of it.
     *
     * @return the topface currently
     */

    public int roll(){
        Random r = new Random();
        int top = r.nextInt(sides)+1;
        setValue(top);
        return top;
    }

    /**
     * Get the topFace currently
     *
     * @return the topFace currently
     */

    public int topFace(){
        return this.getValue();
    }

    /**
     * Imitate a 100000-time roll,and print the result to the base
     * dictionary named "result.txt".
     *
     */
    public static void printResult(){
        int[] arr = new int[6];
        Die die1 = new Die();
        for(int i = 0 ;i < 100000 ;i++)
            arr[die1.roll()-1]++;
        try {

            /**
             * prepare file result.txt,which is empty now
              */

            File f1 = new File("./result.txt");
            String str = "value,   times of returned,  percentage\r\n";
            for(int i = 1; i <= 6; i++){
                for(int j =0; j <3 ;j++){
                    if(j == 0){
                        str += String.valueOf(i);
                        str += "        ";
                    }
                    else if(j == 1){
                        str += String.valueOf(arr[i-1]);
                        str += "                  ";
                    }
                    else{
                        DecimalFormat df = new DecimalFormat("#.00");
                        double ans = (double)arr[i-1]/1000;
                        str += df.format(ans);
                        str += "%\r\n";
                    }
                }
            }
            // creat a new file-based output stream
            FileOutputStream fos = new FileOutputStream(f1);
            // write the date to the output stream
            fos.write(str.getBytes());
            // close the output stream
            fos.close();
            System.out.println("输入完成");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
