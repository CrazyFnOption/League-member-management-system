package Utils;

import java.util.UUID;

public class UUIDUtils {
    //uuid是根据自己身的操作系统和电脑硬件生成的一个32位的随机字符串
    //如果在同一台计算机上使用，仅仅使用一年都不会重复
    public static String getId(){
        return UUID.randomUUID().toString();
    }

    public static void main(String[] args) {
        System.out.println(getId());
    }
}

//  结果：49b088da-cb91-4cf7-be65-7ecd0d553a78