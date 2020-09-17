package com.devil.han.juc.c_018_01_Unsafe;

//import sun.misc.*;

import sun.misc.Unsafe;
/**
 * 直接操纵jvm 中的对象
 */

public class HelloUnsafe {
    static class M {
        private M() {}

        int i =0;
    }

   public static void main(String[] args) throws InstantiationException {
        Unsafe unsafe = Unsafe.getUnsafe();
        M m = (M)unsafe.allocateInstance(M.class);
        m.i = 9;
        System.out.println(m.i);
    }
}


