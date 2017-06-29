package net.solooo.demo.other.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import net.solooo.demo.other.netty.handler.ReceiverServerHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class NioFileServerManager {

    private Logger logger = LoggerFactory.getLogger(NioFileServerManager.class);

    /**
     * @param port
     * @param handler
     * @throws InterruptedException
     */
    public void startServer(Integer port, final ChannelHandlerAdapter handler) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // 配置服务器的NIO线程租
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)//
                    .channel(NioServerSocketChannel.class)//
                    .option(ChannelOption.SO_BACKLOG, 1024 * 1024 * 10)//
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            // 以("\n")为结尾分割的 解码器
                            pipeline.addLast("framer",
                                    new DelimiterBasedFrameDecoder(512*1024, Delimiters.lineDelimiter()));
                            pipeline.addLast("decoder", new StringDecoder());
                            pipeline.addLast("encoder", new StringEncoder());
                            // 自定义处理器
                            pipeline.addLast("handler", handler);
                        }
                    });

            // tomcat容器写法
//            serverBootstrap.bind(port);
            // 测试写法，TCP服务挂起，等待请求
            ChannelFuture f = serverBootstrap.bind(port).sync();
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            logger.error("远程调用接口服务启动异常", e);
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int port = 7000;
        new NioFileServerManager().startServer(port, new ReceiverServerHandler());
    }

}
