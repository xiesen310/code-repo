package top.xiesen.security.demo.web.async;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class AsyncController {
    private Logger logger = LoggerFactory.getLogger(AsyncController.class);

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHandler deferredResultHandler;

    @RequestMapping("/order")
    public DeferredResult<String> order() throws Exception {
        logger.info("主线程开始");
        String orderNumber = RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceOrder(orderNumber);

        DeferredResult<String> result = new DeferredResult<>();
        deferredResultHandler.getMap().put(orderNumber, result);

        /*Callable<String> result = new Callable<String>() {
            @Override
            public String call() throws Exception {
                logger.info("副线程开始");
                Thread.sleep(1000);
                logger.info("副线程返回");
                return "success";
            }
        };*/

        logger.info("主线程返回");
        return result;
    }
}
