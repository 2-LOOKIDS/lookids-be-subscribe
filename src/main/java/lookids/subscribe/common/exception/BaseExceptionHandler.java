package lookids.subscribe.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import lookids.subscribe.common.entity.BaseResponse;
import lookids.subscribe.common.entity.BaseResponseStatus;

@RestControllerAdvice
@Slf4j
public class BaseExceptionHandler {
	/**
	 * 발생한 예외 처리
	 */
	@ExceptionHandler(BaseException.class)
	protected ResponseEntity<BaseResponse<Void>> BaseError(BaseException e) {
		BaseResponse<Void> response = new BaseResponse<>(e.getStatus());
		log.error("BaseException -> {}({})", e.getStatus(), e.getStatus().getMessage(), e);

		return new ResponseEntity<>(response, response.httpStatus());
	}


	/**
	 * security 인증 에러
	 * 아이디가 없거나 비밀번호가 틀린 경우 AuthenticationManager 에서 발생
	 *
	 * @return FAILED_TO_LOGIN 에러 response
	 */
	// @ExceptionHandler(BadCredentialsException.class)
	// protected ResponseEntity<BaseResponse<Void>> handleBadCredentialsException(BadCredentialsException e) {
	// 	BaseResponse<Void> response = new BaseResponse<>(BaseResponseStatus.FAILED_TO_LOGIN);
	// 	log.error("BadCredentialsException: ", e);
	//
	// 	return new ResponseEntity<>(response, response.httpStatus());
	// }

	@ExceptionHandler(RuntimeException.class)
	protected ResponseEntity<BaseResponse<Void>> RuntimeError(RuntimeException e) {
		BaseResponse<Void> response = new BaseResponse<>(BaseResponseStatus.INTERNAL_SERVER_ERROR, e);
		log.error("RuntimeException: ", e);

		return new ResponseEntity<>(response, response.httpStatus());
	}

}
