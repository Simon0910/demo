package util.aqs;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-09-20 13:04
 */
public class UnsafeDemo {

    private static Unsafe unsafe;

    static {
        try {
            Class<Unsafe> unsafeClass = Unsafe.class;
            Field theUnsafe = unsafeClass.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);
        } catch (Exception ex) {
            ex.printStackTrace();
            // return null;
        }
    }

    private volatile int state;

    public int get() {
        return state;
    }

    private static final long stateOffset;

    static {
        try {
            stateOffset = unsafe.objectFieldOffset(UnsafeDemo.class.getDeclaredField("state"));
        } catch (Exception ex) {
            throw new Error(ex);
        }
    }

    protected final boolean compareAndSetState(int expect, int update) {
        // See below for intrinsics setup to support this
        return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
    }

    public static void main(String[] args) {
        UnsafeDemo unsafeDemo = new UnsafeDemo();
        System.out.println(unsafeDemo.get());

        boolean b = unsafeDemo.compareAndSetState(0, 1);
        System.out.println(b);
        System.out.println(unsafeDemo.get());

        boolean b2 = unsafeDemo.compareAndSetState(0, 1);
        System.out.println(b2);
        System.out.println(unsafeDemo.get());

    }
}
