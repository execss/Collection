import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.HandlerWrapper;
import org.junit.jupiter.api.Test;


public class HandlerContainer {

    @Test
   void t1() {
        HandlerCollection handlerCollection = new HandlerCollection();
        HandlerList handlerList =new HandlerList();
        HandlerWrapper handlerWrapper = new HandlerWrapper();
        ContextHandlerCollection contextHandlerCollection = new ContextHandlerCollection();
    }


}
