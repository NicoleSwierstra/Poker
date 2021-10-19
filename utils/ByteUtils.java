package utils;

import java.nio.ByteBuffer;

public class ByteUtils {
    private static ByteBuffer longBuffer = ByteBuffer.allocate(Long.BYTES);
    private static ByteBuffer intBuffer = ByteBuffer.allocate(Integer.BYTES);
    private static ByteBuffer floatBuffer = ByteBuffer.allocate(Float.BYTES);
    private static ByteBuffer shortBuffer = ByteBuffer.allocate(Short.BYTES);

    public static byte[] longToBytes(long x) {
        return longBuffer.putLong(0, x).array();
    }

    public static byte[] intToBytes(int x) {
        return intBuffer.putInt(0, x).array();
    }
    
    public static byte[] floatToBytes(float x) {
        return floatBuffer.putFloat(0, x).array();
    }
    
    public static byte[] shortToBytes(short x) {
        return shortBuffer.putShort(0, x).array();
    }

    //
    //
    //

    public static long bytesToLong(byte[] bytes) {
        return longBuffer.put(bytes, 0, 8).flip().getLong();
    }

    public static int bytesToInt(byte[] bytes) {
        return intBuffer.put(bytes, 0, 4).flip().getInt();
    }

    public static float bytesToFloat(byte[] bytes) {
        return floatBuffer.put(bytes, 0, 4).flip().getFloat();
    }

    public static short bytesToShort(byte[] bytes) {
        return shortBuffer.put(bytes, 0, 2).flip().getShort();
    }
}
