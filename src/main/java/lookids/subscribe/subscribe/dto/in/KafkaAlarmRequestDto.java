package lookids.subscribe.subscribe.dto.in;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KafkaAlarmRequestDto {
	private String senderUuid;
	private List<String> receiverUuidList;
	private String feedContent;
	private String mediaUrl;
	private String type;

	@Builder
	public KafkaAlarmRequestDto(
		String senderUuid,
		List<String> receiverUuidList,
		String feedContent,
		String mediaUrl,
		String type
	) {
		this.senderUuid = senderUuid;
		this.receiverUuidList = receiverUuidList;
		this.feedContent = feedContent;
		this.mediaUrl = mediaUrl;
		this.type = type;
	}
}
