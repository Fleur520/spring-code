import java.util.Arrays;

import org.junit.Test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * @author minzhang
 * @date 2022/02/03 21:27
 **/
public class ByteBufTest {

    @Test
    public void test1(){
        io.netty.buffer.ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(16);
        System.out.println(buffer);

        io.netty.buffer.ByteBuf buffer2 = ByteBufAllocator.DEFAULT.directBuffer(16);

        io.netty.buffer.ByteBuf directBuf = Unpooled.directBuffer(100);
    }

    @Test
    public void testComposite() {
        CompositeByteBuf messageBuf = Unpooled.compositeBuffer();
        ByteBuf headerBuf = Unpooled.copiedBuffer("head===zm", CharsetUtil.UTF_8); // can be backing or direct
        ByteBuf bodyBuf = Unpooled.copiedBuffer("body===zm", CharsetUtil.UTF_8); // can be backing or direct
        messageBuf.addComponents(headerBuf, bodyBuf);
        System.out.println("Remove Head Before------------------");
        printCompositeBuffer(messageBuf);
        for (ByteBuf buf : messageBuf) {
            System.out.println(buf.toString(CharsetUtil.UTF_8));
        }
        messageBuf.removeComponent(0); // remove the header
        System.out.println("Remove Head After------------------");
        printCompositeBuffer(messageBuf);
        for (ByteBuf buf : messageBuf) {
            System.out.println(buf.toString(CharsetUtil.UTF_8));
        }
    }



    @Test
    public void test3(){
        ByteBuf directBuf = Unpooled.directBuffer(100);
        directBuf.writeBytes("direct buffer".getBytes());
        System.out.println("可写字节容量："+directBuf.writableBytes());
        System.out.println("初始化可读字节："+directBuf.readableBytes());
        System.out.println("初始化可丢弃字节："+directBuf.readerIndex()+"\n");
        directBuf.readBytes(2);
        System.out.println("读取两个字节"+"\n");
        System.out.println("读取后可写字节容量："+directBuf.writableBytes());
        System.out.println("读取后可读字节："+directBuf.readableBytes());
        System.out.println("读取后可丢弃字节："+directBuf.readerIndex()+"\n");
        directBuf.discardReadBytes();
        System.out.println("执行discardReadBytes后可写字节容量："+directBuf.writableBytes());
        System.out.println("执行discardReadBytes后可读字节："+directBuf.readableBytes());
        System.out.println("执行discardReadBytes后可丢弃字节："+directBuf.readerIndex());

    }

    @Test
    public void testSearch(){
        ByteBuf directBuf = Unpooled.directBuffer(100);
        directBuf.writeBytes("direct buffer".getBytes());
        System.out.println(directBuf.indexOf(0,directBuf.readableBytes(), (byte) 'i'));
    }


    public static void printCompositeBuffer(CompositeByteBuf compBuf){
        int length = compBuf.readableBytes();
        byte[] array = new byte[length];
        compBuf.getBytes(compBuf.readerIndex(), array);
        System.out.println (Arrays.toString(array));
        System.out.println (length);
    }

}
