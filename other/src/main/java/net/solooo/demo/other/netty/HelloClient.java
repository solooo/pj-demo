package net.solooo.demo.other.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Description:
 * Author:Eric
 * Date:2017/6/29
 */
public class HelloClient {

    public static String host = "127.0.0.1";
    public static int port = 7000;

    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group);
            b.channel(NioSocketChannel.class);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ChannelPipeline pipeline = socketChannel.pipeline();
                    // 以("\n")为结尾分割的 解码器
//                    pipeline.addLast("framer",
//                            new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
//                    pipeline.addLast("decoder", new StringDecoder());
                    LengthFieldBasedFrameDecoder lengthFieldBasedFrameDecoder = new LengthFieldBasedFrameDecoder(
                            Integer.MAX_VALUE, 0, 4, 0, 4);
                    pipeline.addLast("decoder", lengthFieldBasedFrameDecoder);

                    pipeline.addLast("String decoder", new StringDecoder());
                    // 自定义处理器
                    pipeline.addLast("handler", new HelloClientHandler());
                }
            });

            Channel channel = b.connect(host, port).sync().channel();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.println("--------");
                String line = reader.readLine();
                if (line == null) {
                    continue;
                }
                ByteBuf response = HelloClient.response(line);
                channel.writeAndFlush(response);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    /**
     * 响应
     * @param msg
     * @return
     */
    private static ByteBuf response(String msg) {
        String delimiter = "\r\n\r\n";
        byte[] body = msg.getBytes();
        byte[] len = BytesUtil.intToByteBigEndian(body.length + 4);
        byte[] deli = delimiter.getBytes();
        byte[] respBytes = BytesUtil.concatenateByteArrays(len, body, deli);
        ByteBuf byteBuf = Unpooled.copiedBuffer(respBytes);
        return byteBuf;
    }
}
