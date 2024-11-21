package lookids.subscribe.subscribe.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lookids.subscribe.common.entity.BaseResponse;
import lookids.subscribe.subscribe.service.SubscribeService;
import lookids.subscribe.subscribe.vo.out.SubscribeResponseVo;

@RequiredArgsConstructor
@RestController
@RequestMapping("/read/subscribe")
public class SubscribeReadController {
	private final SubscribeService subscribeService;


	// Todo: api 테스트용 컨트롤러, Kafka 연동 후 삭제
	@Operation(summary = "구독자 목록 조회 API", description = "해당 게시글 글쓴이의 알림 신청인 목록을 조회합니다.", tags = {"Subscribe"})
	@GetMapping("/{authorUuid}")
	public BaseResponse<SubscribeResponseVo> getSubscribers(@RequestParam String authorUuid) {
		return new BaseResponse<>(subscribeService.readSubscribers(authorUuid).toVo());

	}



}
