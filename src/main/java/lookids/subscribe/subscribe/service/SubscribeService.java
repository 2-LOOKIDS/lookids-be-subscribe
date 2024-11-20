package lookids.subscribe.subscribe.service;

import lookids.subscribe.subscribe.dto.out.SubscribeResponseDto;

public interface SubscribeService {
	SubscribeResponseDto getSubscribers(String authorUuid);
}
