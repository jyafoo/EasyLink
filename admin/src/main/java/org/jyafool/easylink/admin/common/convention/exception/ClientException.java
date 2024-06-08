package org.jyafool.easylink.admin.common.convention.exception;

import org.jyafool.easylink.admin.common.convention.errorcode.BaseErrorCode;
import org.jyafool.easylink.admin.common.convention.errorcode.IErrorCode;

/**
 * 客户端异常
 * @Author jyafool
 * @Version 1.0
 * @Since 2024/6/7
 */
public class ClientException extends AbstractException {

    public ClientException(IErrorCode errorCode) {
        this(null, null, errorCode);
    }

    public ClientException(String message) {
        this(message, null, BaseErrorCode.CLIENT_ERROR);
    }

    public ClientException(String message, IErrorCode errorCode) {
        this(message, null, errorCode);
    }

    public ClientException(String message, Throwable throwable, IErrorCode errorCode) {
        super(message, throwable, errorCode);
    }

    @Override
    public String toString() {
        return "ClientException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMessage + "'" +
                '}';
    }
}
