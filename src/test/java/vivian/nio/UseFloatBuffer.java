package vivian.nio;

import java.nio.FloatBuffer;

/**
 * clear() 方法重设缓冲区，使它可以接受读入的数据
 * flip() 方法让缓冲区可以将新读入的数据写入另一个通道
 * 
 * clear() 和 flip() 方法用于让缓冲区在读和写之间切换。
 * 
 * @author no1
 *
 */
public class UseFloatBuffer {
	static public void main(String args[]) throws Exception {
		FloatBuffer buffer = FloatBuffer.allocate(10);

		for (int i = 0; i < buffer.capacity(); ++i) {
			float f = (float) Math.sin((((float) i) / 10) * (2 * Math.PI));
			buffer.put(f);
		}
		
		buffer.flip();
		
		while (buffer.hasRemaining()) {
			float f = buffer.get();
			System.out.println(f);
		}
	}
}
