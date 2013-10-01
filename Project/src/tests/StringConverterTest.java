package tests;

import se.chalmers.krogkollen.utils.StringConverter;

/**
 * Created with IntelliJ IDEA.
 * User: filipcarlen
 * Date: 2013-09-27
 * Time: 17:24
 * To change this template use File | Settings | File Templates.
 */
public class StringConverterTest {
    public static void main(String[] args){

        int x = StringConverter.convertCombinedStringto(":1111:2222:3333:4444:", 3);
        System.out.print("x should be: 3333. x is:" +x);
        if(x == 3333){
            System.out.println(" CORRECT");
        }
        else{
            System.out.println(" NOT CORRECT");
        }
    }
}
