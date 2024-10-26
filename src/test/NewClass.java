/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

/**
 *
 * @author skyga
 */
public class NewClass {
    public static void main(String[] args) {
        int[] mang = {1, 3, 4, 6};
//        int length = mangCoSan.getLast() - mangCoSan.getFirst() + 1;
        int length = 6;
        int[] count_sort = new int[length];

        for(int i = 0; i < mang.length; i++) {
            count_sort[i] = mang[i];
        }
        if(count_sort[1] == 0) {
            System.out.println(mang[0]);
            return;
        }
        for(int i = 0; i < count_sort.length; i++) {

            if(count_sort[i] == 0) {
                System.out.println(count_sort[i-1] + 1 + mang[0]);
                return;
            }
        }

        System.out.println(length+1);
        return;
    }
}
