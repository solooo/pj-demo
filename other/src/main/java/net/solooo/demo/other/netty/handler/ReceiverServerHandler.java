package net.solooo.demo.other.netty.handler;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.solooo.demo.other.netty.msg.req.DataMsg;
import net.solooo.demo.other.netty.msg.req.RequestMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

/**
 * Description:
 * Author:Eric
 * Date:2017/6/29
 */
public class ReceiverServerHandler extends SimpleChannelInboundHandler<String> {

    private Logger logger = LoggerFactory.getLogger(ReceiverServerHandler.class);

    private String name;
    
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + ": " + s);

        RequestMsg reqMsg = JSON.parseObject(s, RequestMsg.class);
        DataMsg data = reqMsg.getData();
        String mediaData = data.getMediaData();
        byte[] bytes = new BASE64Decoder().decodeBuffer(mediaData);
        if (data.getSeq() == 1) {
            name = data.getOther().getFileName();
        }
        try (BufferedOutputStream bo = new BufferedOutputStream(new FileOutputStream("E:/test/" + name))) {
            bo.write(bytes);
        }

        ctx.writeAndFlush("Received message!\n");
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info(ctx.channel().remoteAddress() + "接入！");
        ctx.writeAndFlush("welcome\n");
        super.channelActive(ctx);
    }
}
