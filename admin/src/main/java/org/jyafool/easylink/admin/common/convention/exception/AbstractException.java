package org.jyafool.easylink.admin.common.convention.exception;

import com.google.protobuf.ServiceException;
import lombok.Getter;
import org.jyafool.easylink.admin.common.convention.errorcode.IErrorCode;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * 抽象异常体系：客户端异常、服务端异常以及远程服务调用异常
 * @Author jyafool
 * @Version 1.0
 * @Since 2024/6/7
 *
 *  @see ClientException
 *  @see ServiceException
 *  @see RemoteException
 */
@Getter
public abstract class AbstractException extends RuntimeException {

    public final String errorCode;

    public final String errorMessage;

    public AbstractException(String message, Throwable throwable, IErrorCode errorCode) {
        super(message, throwable);
        this.errorCode = errorCode.code();
        this.errorMessage = Optional.ofNullable(StringUtils.hasLength(message) ? message : null).orElse(errorCode.message());
    }
}
