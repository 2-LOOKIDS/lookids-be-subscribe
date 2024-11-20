package lookids.subscribe.subscribe.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.subscribe.subscribe.vo.in.SubscribeRequestVo;

@Getter
@NoArgsConstructor
public class SubscribeRequestDto {
	private String authorUuid;

	@Builder
	public SubscribeRequestDto(
		String authorUuid
	) {
		this.authorUuid = authorUuid;
	}

	public static SubscribeRequestDto toDto(SubscribeRequestVo subscribeRequestVo){
		return SubscribeRequestDto.builder()
			.authorUuid(subscribeRequestVo.getAuthorUuid())
			.build();
	}
}
