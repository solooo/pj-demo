package net.solooo.demo.other.enums;


/**
 * Description:
 * Author:Eric
 * Date:2017/7/6
 */
public class EnumTest {
    public static void main(String[] args) {
        System.out.println("UPLOAD".equals(OperationEnum.UPLOAD.name()));
        System.out.println(OperationEnum.valueOf("UPLOAD"));
        System.out.println(MsgTypeEnum.MediaCloudStore);
    }

}
