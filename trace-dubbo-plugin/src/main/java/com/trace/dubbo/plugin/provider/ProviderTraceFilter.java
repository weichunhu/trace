package com.trace.dubbo.plugin.provider;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;
import com.trace.dubbo.plugin.base.AbstractTraceFilter;

/**
 * Dubbo Provider Trace Filter
 * @author chunhua.wei
 */
@Activate(group = Constants.PROVIDER)
public class ProviderTraceFilter extends AbstractTraceFilter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        return doInvoke(invoker, invocation);
    }

}