package lookids.subscribe.subscribe.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.subscribe.subscribe.domain.Subscribe;
import lookids.subscribe.subscribe.vo.in.SubscribeRequestVo;

@Getter
@NoArgsConstructor
public class SubscribeRequestDto {
	private String authorUuid;
	private String subscriberUuid;

	@Builder
	public SubscribeRequestDto(
		String authorUuid,
		String subscriberUuid
	) {
		this.authorUuid = authorUuid;
		this.subscriberUuid = subscriberUuid;
	}

	public static SubscribeRequestDto toDto(String subscriberUuid, SubscribeRequestVo subscribeRequestVo){
		return SubscribeRequestDto.builder()
			.authorUuid(subscriberUuid)
			.subscriberUuid(subscribeRequestVo.getSubscriberUuid())
			.build();
	}

	public Subscribe toEntity(){
		return Subscribe.builder()
			.authorUuid(authorUuid)
			.subscriberUuid(subscriberUuid)
			.build();
	}
}
