package cn.wowspeeder.temp;

import cn.wowspeeder.ss.SSCommon;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class IdleStateHandlerImpl extends IdleStateHandler{

    public IdleStateHandlerImpl(long readerIdleTime, long writerIdleTime, long allIdleTime, TimeUnit unit) {
        super(readerIdleTime, writerIdleTime, allIdleTime, unit);

//        @Override
//        protected IdleStateEvent newIdleStateEvent(IdleState state, boolean first) {
//            ctx.close();
//            return super.newIdleStateEvent(state, first);
//        }
    }


//new IdleStateHandler(0, 0,SSCommon.TCP_PROXY_IDEL_TIME, TimeUnit.SECONDS) {
//
//
//        @Override
//        protected IdleStateEvent newIdleStateEvent(IdleState state, boolean first) {
//            ctx.close();
//            return super.newIdleStateEvent(state, first);
//        }
//    }
}
