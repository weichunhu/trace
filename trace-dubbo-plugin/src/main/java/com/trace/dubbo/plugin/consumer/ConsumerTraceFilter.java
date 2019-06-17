package com.trace.dubbo.plugin.consumer;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;
import com.trace.dubbo.plugin.base.AbstractTraceFilter;

/**
 * Dubbo Consumer Trace Filter
 * @author 364623395@qq.com
 */
@Activate(group = Constants.CONSUMER)
public class ConsumerTraceFilter extends AbstractTraceFilter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        return doInvoke(invoker, invocation);
    }

}