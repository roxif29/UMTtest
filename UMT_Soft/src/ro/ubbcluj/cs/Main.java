package ro.ubbcluj.cs;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Utils utils=new Utils();
        int[] A=new int[]{1,2,3,4,5,6,7,8};

        if(utils.splitArraySameAverage(A))
            System.out.println("It's all good! You can split the array :D");
        else
            System.out.println(":( You can not split the array");

    }

}
