package com.jalivv.spring.a14;

import org.springframework.cglib.core.Signature;

/**
 * @Description
 * @Date 2022/4/3 08:43
 * @Created by jalivv
 */
public class TargetFastClass {

    static Signature s0 = new Signature("save", "()V");
    static Signature s1 = new Signature("save", "(I)V");
    static Signature s2 = new Signature("save", "(J)V");

    /*
        获取目标方法的编号
            Target
                save()          0
                save(int)       1
                save(long)      2
     */

    public int getIndex(Signature signature) {
        if (s0.equals(signature)) return 0;
        if (s1.equals(signature)) return 1;
        if (s2.equals(signature)) return 2;
        return -1;
    }

    public Object invoke(int idx, Object target, Object[] ags) {
        if (0 == idx) {
            ((Target) target).save();
            return null;
        }
        else if (1 == idx){
            ((Target) target).save((int) ags[0]);
            return null;
        } else if(2==idx){
            ((Target) target).save((long) ags[0]);
            return null;
        }else
            throw new RuntimeException("无此方法");

    }

}
