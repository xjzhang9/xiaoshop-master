package com.xjzhang.pro.exception;
import com.xjzhang.base.enums.ErrorCodeEnum;
import com.xjzhang.base.exception.BusinessException;
import com.xjzhang.base.model.GlobalExceptionLogDto;
import com.xjzhang.base.wrapper.BaseWrapper;
import com.xjzhang.base.wrapper.ResWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.annotation.Resource;
import java.nio.file.AccessDeniedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 全局的的异常拦截器
 *
 * @author paascloud.net @gmail.com
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	@Resource
	private TaskExecutor taskExecutor;
	@Value("${spring.profiles.active}")
	String profile;
	@Value("${spring.application.name}")
	String applicationName;
//	@Resource
////	private MdcExceptionLogFeignApi mdcExceptionLogFeignApi;

	/**
	 * 参数非法异常.
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public BaseWrapper illegalArgumentException(IllegalArgumentException e) {
		log.error("参数非法异常={}", e.getMessage(), e);

		return ResWrapper.illegalArgument(e.getMessage());
	}

	/**
	 * 业务异常.
	 */
	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public BaseWrapper businessException(BusinessException e) {
		log.error("业务异常={}", e.getMessage(), e);
		return ResWrapper.error(e.getCode() == 0 ? BaseWrapper.ERROR_CODE : e.getCode(), e.getMessage());
	}

	/**
	 * 无权限访问.
	 */
	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ResponseBody
	public BaseWrapper unAuthorizedException(AccessDeniedException e) {
		log.error("业务异常={}", e.getMessage(), e);
		return ResWrapper.error(ErrorCodeEnum.GL99990001.code(), ErrorCodeEnum.GL99990001.msg());
	}


	/**
	 * 全局异常.
	 *
	 * @param e the e
	 *
	 * @return the wrapper
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public BaseWrapper exception(Exception e) {
		log.info("保存全局异常信息 ex={}", e.getMessage(), e);
		taskExecutor.execute(() -> {
			GlobalExceptionLogDto globalExceptionLogDto = new GlobalExceptionLogDto().getGlobalExceptionLogDto(e, profile, applicationName);
//			mdcExceptionLogFeignApi.saveAndSendExceptionLog(globalExceptionLogDto);
		});
		return ResWrapper.error();
	}
}
