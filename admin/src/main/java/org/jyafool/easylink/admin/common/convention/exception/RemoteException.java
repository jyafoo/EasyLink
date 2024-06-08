package org.jyafool.easylink.admin.common.convention.exception;

import org.jyafool.easylink.admin.common.convention.errorcode.BaseErrorCode;
import org.jyafool.easylink.admin.common.convention.errorcode.IErrorCode;

/**
 * 远程服务调用异常
 * @Author jyafool
 * @Version 1.0
 * @Since 2024/6/7
 */
public class RemoteException extends AbstractException {

    public RemoteException(String message) {
        this(message, null, BaseErrorCode.REMOTE_ERROR);
    }

    public RemoteException(String message, IErrorCode errorCode) {
        this(message, null, errorCode);
    }

    public RemoteException(String message, Throwable throwable, IErrorCode errorCode) {
        super(message, throwable, errorCode);
    }

    @Override
    public String toString() {
        return "RemoteException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMessage + "'" +
                '}';
    }
}
