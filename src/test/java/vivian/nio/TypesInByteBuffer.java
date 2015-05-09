package vivian.nio;

import java.nio.ByteBuffer;

/**
 * clear() 和 flip() 方法用于让缓冲区在读和写之间切换。
 * @author no1
 *
 */

public class TypesInByteBuffer {
	static public void main(String args[]) throws Exception {
		ByteBuffer buffer = ByteBuffer.allocate(64);

		buffer.putInt(30);
		buffer.putLong(7000000000000L);
		buffer.putDouble(Math.PI);

		buffer.flip();

		System.out.println(buffer.getInt());
		System.out.println(buffer.getLong());
		System.out.println(buffer.getDouble());
	}
}
