package lookids.subscribe.subscribe.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lookids.subscribe.common.entity.BaseResponse;
import lookids.subscribe.subscribe.dto.in.SubscribeRequestDto;
import lookids.subscribe.subscribe.service.SubscribeService;
import lookids.subscribe.subscribe.vo.in.SubscribeRequestVo;

@RequiredArgsConstructor
@RestController
@RequestMapping("/write/subscribe")
public class SubscribeWriteController {
	private final SubscribeService subscribeService;

	@Operation(summary = "게시글 알림 신청 API", description = "해당 게시글의 알림을 신청합니다.", tags = {"Subscribe"})
	@PostMapping("/{authorUuid}")
	public BaseResponse<Void> createSubscribe(
		@RequestHeader String subscriberUuid,
		@RequestBody SubscribeRequestVo subscribeRequestVo) {
		subscribeService.createSubscribe(SubscribeRequestDto.toDto(subscriberUuid, subscribeRequestVo));
		return new BaseResponse<>();
	}

	@Operation(summary = "게시글 알림 신청 취소 API", description = "해당 게시글의 알림을 취소합니다.", tags = {"Subscribe"})
	@DeleteMapping("/{authorUuid}")
	public BaseResponse<Void> deleteSubscribe(
		@RequestHeader String subscriberUuid,
		@RequestBody SubscribeRequestVo subscribeRequestVo) {
		subscribeService.deleteSubscribe(SubscribeRequestDto.toDto(subscriberUuid, subscribeRequestVo));
		return new BaseResponse<>();
	}



}
