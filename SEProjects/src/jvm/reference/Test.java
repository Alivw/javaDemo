package jvm.reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

// -Xmx20m -XX:+PrintGCDetails  -verbose:gc
public class Test {

    private static final int _4MB = 4 * 1024 * 1024;


    public static void main(String[] args) {
        //hardReference();

        //soft();

        referenceQueue();

    }

    /**
     * 应用队列的使用，需要手动释放软引用对象的内存
     */
    private static void referenceQueue() {
        ReferenceQueue<byte[]> queue = new ReferenceQueue<>();

        List<SoftReference<byte[]>> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            // 关联了引用队列，当软引用所关联的byte[]被回收时，
            SoftReference<byte[]> softR = new SoftReference<>(new byte[_4MB], queue);
            list.add(softR);
            System.out.println(softR.get());
            System.out.println(i + 1);
        }
        Reference<? extends byte[]> poll = null;

        // 从列表中获取无用的软引用对象，并将其删除
        while ((poll = queue.poll()) != null) {
            list.remove(poll);
        }
        System.out.println("=============");
        for (SoftReference<byte[]> s : list) {
            System.out.println(s.get());
        }
    }


    /**
     * 使用强引用，会导致内存溢出，报错：outOfMemory
     */
    private static void hardReference() {
        List<byte[]> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new byte[_4MB]);
            System.out.println(i + 1);
        }
    }

    /**
     * 使用软引用，在内存紧张的时候，经过一次垃圾回收之后，还是不能满足需求，将回收软引用 引用的对象（该软引用并不会被回收，因为软引用此时正被一个list 强引用）
     * 要解决软引用对象也被回收，就需要用到引用队列
     */
    private static void soft() {
        List<SoftReference<byte[]>> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            SoftReference<byte[]> softR = new SoftReference<>(new byte[_4MB]);
            list.add(softR);
            System.out.println(softR.get());
            System.out.println(i + 1);
        }

        for (SoftReference<byte[]> s : list) {
            System.out.println(s.get());
        }
    }
}
