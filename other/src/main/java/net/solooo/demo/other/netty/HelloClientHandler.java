package net.solooo.demo.other.netty;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.solooo.demo.other.netty.msg.resp.DownloadMsg;
import sun.misc.BASE64Decoder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Description:
 * Author:Eric
 * Date:2017/6/29
 */
public class HelloClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        DownloadMsg downloadMsg = JSON.parseObject(s, DownloadMsg.class);
        System.out.println(downloadMsg.getData().getLastPacket() + "\t" +downloadMsg.getData().getSeq());
        String mediaData = downloadMsg.getData().getMediaData();
        byte[] bytes = new BASE64Decoder().decodeBuffer(mediaData);
        Path path = Paths.get("E:/test/111.HIK");
        Files.write(path, bytes, StandardOpenOption.APPEND);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client active!");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client close!");
        super.channelInactive(ctx);
    }
}
