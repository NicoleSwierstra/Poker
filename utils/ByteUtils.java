package utils;

import java.nio.ByteBuffer;

public class ByteUtils {
    private static ByteBuffer longBuffer  = ByteBuffer.allocate(8);
    private static ByteBuffer intBuffer   = ByteBuffer.allocate(4);
    private static ByteBuffer floatBuffer = ByteBuffer.allocate(4);
    private static ByteBuffer shortBuffer = ByteBuffer.allocate(2);

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
        longBuffer.clear();
        return longBuffer.put(bytes, 0, 8).flip().getLong();
    }

    public static int bytesToInt(byte[] bytes) {
        intBuffer.clear();
        return intBuffer.put(bytes, 0, 4).flip().getInt();
    }

    public static float bytesToFloat(byte[] bytes) {
        floatBuffer.clear();
        return floatBuffer.put(bytes, 0, 4).flip().getFloat();
    }

    public static short bytesToShort(byte[] bytes) {
        shortBuffer.clear();
        return shortBuffer.put(bytes, 0, 2).flip().getShort();
    }
}
