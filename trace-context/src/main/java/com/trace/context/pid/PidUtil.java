package com.trace.context.pid;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 * 进程util
 * @author chunhua.wei
 */
public final class PidUtil {
    /**
     * process id
     */
    private static int pid;

    static {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        String name = runtimeMXBean.getName();
        try{
            pid = Integer.valueOf(name.substring(0, name.indexOf("@")));
        }catch(Throwable t){
            // ignore
        }
    }

    public static int getPid() {
        return pid;
    }

}