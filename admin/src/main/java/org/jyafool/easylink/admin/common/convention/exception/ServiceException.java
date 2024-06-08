package org.jyafool.easylink.admin.common.convention.exception;

import org.jyafool.easylink.admin.common.convention.errorcode.BaseErrorCode;
import org.jyafool.easylink.admin.common.convention.errorcode.IErrorCode;

import java.util.Optional;

/**
 * 服务端异常
 * @Author jyafool
 * @Version 1.0
 * @Since 2024/6/7
 */
public class ServiceException extends AbstractException {

    public ServiceException(String message) {
        this(message, null, BaseErrorCode.SERVICE_ERROR);
    }

    public ServiceException(IErrorCode errorCode) {
        this(null, errorCode);
    }

    public ServiceException(String message, IErrorCode errorCode) {
        this(message, null, errorCode);
    }

    public ServiceException(String message, Throwable throwable, IErrorCode errorCode) {
        super(Optional.ofNullable(message).orElse(errorCode.message()), throwable, errorCode);
    }

    @Override
    public String toString() {
        return "ServiceException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMessage + "'" +
                '}';
    }
}