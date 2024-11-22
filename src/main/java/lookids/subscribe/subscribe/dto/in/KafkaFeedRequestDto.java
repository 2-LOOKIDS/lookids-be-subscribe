package lookids.subscribe.subscribe.dto.in;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KafkaFeedRequestDto {
	private String senderUuid;
	private String receiverUuid;
	private List<String> mediaUrlList; // 첫 이미지만 알림 서비스로 전송
	private String feedContent;		   // 피드 내용 적절히 잘라서 알림 서비스로 전송

	@Builder
	public KafkaFeedRequestDto(
		String senderUuid,
		String receiverUuid,
		List<String> mediaUrlList,
		String feedContent
	) {
		this.senderUuid = senderUuid;
		this.receiverUuid = receiverUuid;
		this.mediaUrlList = mediaUrlList;
		this.feedContent = feedContent;
	}
}
