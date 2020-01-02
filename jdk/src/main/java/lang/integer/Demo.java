package lang.integer;


/**
 * @author liuzhipeng
 * @description
 * @create 2019-08-23 15:43
 */
public class Demo {

    public static void main(String[] args) {

        Integer i1 = 40;
        Integer i2 = 40;
        Integer i3 = 0;
        Integer i4 = new Integer(40);
        Integer i5 = new Integer(40);
        Integer i6 = new Integer(0);

        System.out.println("i1=i2   " + (i1 == i2));
        System.out.println("i1.equals(i2)   " + (i1.equals(i2)));

        System.out.println("i1=i2+i3   " + (i1 == i2 + i3));
        System.out.println("i1.equals(i2 + i3)   " + (i1.equals(i2 + i3)));

        System.out.println("i1=i4   " + (i1 == i4));// false
        System.out.println("i1.equals(i4)   " + (i1.equals(i4)));

        System.out.println("i4=i5   " + (i4 == i5));// false
        System.out.println("i4.equals(i5)   " + (i4.equals(i5)));

        System.out.println("i4=i5+i6   " + (i4 == i5 + i6));

        System.out.println("40=i5+i6   " + (40 == i5 + i6));

        Integer I1 = 200;
        Integer I2 = 200;
        Long l1 = 200L;
        Long l2 = 200L;

        System.out.println((I1 == I2)); // false
        System.out.println(I1.equals(I2));
        System.out.println((l1 == l2));// false
        System.out.println(l1.equals(l2));
    }
}
