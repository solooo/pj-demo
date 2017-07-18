package net.solooo.demo.other.netty;


import java.io.ByteArrayOutputStream;

/**
 * Description:
 * Author:Eric
 * Date:2017/6/30
 */
public class BytesUtil {

    /**
     * int 转 byte[] 大端模式
     * @param value
     * @return
     */
    public static byte[] intToByteBigEndian(int value) {
        byte[] src = new byte[4];
        src[0] = (byte) ((value >> 24) & 0xFF);
        src[1] = (byte) ((value >> 16) & 0xFF);
        src[2] = (byte) ((value >> 8) & 0xFF);
        src[3] = (byte) (value & 0xFF);
        return src;
    }

    /**
     * Description:byte[]转int
     * Author:殷建卫(yinjw@htdatacloud.com)
     * Date:2016年4月27日
     * History:
     * his1:
     *
     * @param bytes
     * @return
     */
    public static int byteArrayToInt(byte[] bytes) {
        int value = 0;
        value = bytes[0] & 0xFF;
        value |= ((bytes[1] << 8) & 0xFF00);
        value |= ((bytes[2] << 16) & 0xFF0000);
        value |= ((bytes[3] << 24) & 0xFF000000);
        return value;
    }

    /**
     * Description:拼接多个byte数组
     * Author:殷建卫(yinjw@htdatacloud.com)
     * Date:2016年4月28日
     * History:
     * his1:
     *
     * @param bytesList
     * @return
     */
    public static byte[] concatenateByteArrays(byte[]... bytesList) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        for (byte[] bytes : bytesList) {
            os.write(bytes, 0, bytes.length);
        }
        return os.toByteArray();
    }

    public static void main(String[] args) {
        byte[] bytes = new byte[3];
        bytes[0] = 'c';
        bytes[1] = '\0';
        bytes[2] = 'c';
        String s = new String(bytes);
        System.out.println(s);
        System.out.println(s.length());
        System.out.println(Integer.toHexString(Integer.parseInt(s)));
    }
}
