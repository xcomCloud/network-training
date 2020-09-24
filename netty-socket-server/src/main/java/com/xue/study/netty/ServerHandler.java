package com.xue.study.netty;

import com.xue.study.service.TestService;
import com.xue.study.utils.HexConvertUtil;
import io.netty.buffer.*;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.CharSequenceValueConverter;
import org.apache.tomcat.util.buf.HexUtils;
import org.apache.tomcat.util.http.fileupload.UploadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.codec.CharSequenceEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;

/**
 * Author: mf015
 * Date: 2020/7/16 0016
 */
@Component
public class ServerHandler extends ChannelInboundHandlerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerHandler.class);

    @Autowired
    private TestService testService;
    private static TestService testServiceUsing;

    @PostConstruct
    public void setTestServiceUsing(){
        testServiceUsing = testService;
    }

    //读取终端报文
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        // TODO: 2020/7/17 0017
        // TODO: 2020/7/17 0017
        /**
         * 预备方案： 循环字节数组
         *
         */

//        ByteBuf buf = (ByteBuf) msg;
//        int readableLen = buf.readableBytes();
//        byte[] bytes = new byte[readableLen];
//
//        buf.readBytes(bytes);
//        String msgBody = new String(bytes, StandardCharsets.UTF_8);
//        buf.release();
//
//        routineMsg(ctx, msgBody);
    }

    //进入报文处理流
    private void routineMsg(ChannelHandlerContext ctx, String msgBody) {
        try {
//            String hexMsg = HexConvertUtil.convertStrToHex(msgBody);
//            String response = testServiceUsing.test(hexMsg);
//            response = HexConvertUtil.hexToAscii(response);

            String response = testServiceUsing.test(ByteBufUtil.hexDump(msgBody.getBytes(StandardCharsets.UTF_8)));
//            String response = testServiceUsing.test(HexConvertUtil.convertStrToHex(msgBody));
            respond(ctx, HexConvertUtil.hexToAscii(response));
        } catch (Exception e) {
            LOGGER.error("routineMsg", e);
        }
    }

    //服务端应答
    private void respond(ChannelHandlerContext ctx, String response) {
        ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
//        ByteBufUtil.ensureWritableSuccess()
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //super.channelReadComplete(ctx);
        LOGGER.info("---- channelReadComplete ----");
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //super.exceptionCaught(ctx, cause);
        LOGGER.error("exceptionCaught error: ");// TODO: 2020/7/16 0016
        LOGGER.error(ctx.channel().id().asShortText() + " "
                + cause.getMessage() + "=======================", cause);
        ctx.close();
    }
}
