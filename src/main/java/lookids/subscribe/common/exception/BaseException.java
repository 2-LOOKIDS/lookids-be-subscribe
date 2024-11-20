package lookids.subscribe.common.exception;

import lombok.Getter;
import lookids.subscribe.common.entity.BaseResponseStatus;

@Getter
public class BaseException extends RuntimeException{
	private final BaseResponseStatus status;

	public BaseException(BaseResponseStatus status) {
		this.status = status;
	}
}
