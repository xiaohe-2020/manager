package com.example.test.Thread;

import com.example.test.comm.base.BaseThread;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//测试线程
public class TestThread extends BaseThread {

    private static Log log = LogFactory.getLog(TestThread.class);

    @Override
    protected THREAD_PROC_RET threadMainProc() throws Exception {

        try{
            log.info("开始执行线程任务了。。。。。。。。。。。");
        }catch(Exception e){
            log.error("|    【MessagePushThread】线程异常", e);
            return THREAD_PROC_RET.NG;
        }finally{
            try{
//                MDC.remove("LOG_THREAD_ID");
            }catch(Exception e){

            }
        }

        return THREAD_PROC_RET.OK;
    }


}
