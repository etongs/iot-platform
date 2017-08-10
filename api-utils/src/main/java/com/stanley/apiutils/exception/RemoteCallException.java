package com.stanley.apiutils.exception;

import com.stanley.apiutils.api.Error;

/**
 * @Description hystrix会忽略这个异常, 不会触发熔断
 * @date 2017/8/4
 * @author 13346450@qq.com 童晟
 */
public class RemoteCallException extends AppBusinessException {

    private Error originError;

    public RemoteCallException(Error error, int httpStatus) {
        super(error.getCode(), httpStatus, "调用远程服务异常, cause: " + error.getMessage());
        this.originError = error;
    }

    public Error getOriginError() {
        return originError;
    }
}
