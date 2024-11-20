package lookids.subscribe.subscribe.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lookids.subscribe.subscribe.domain.Subscribe;
import lookids.subscribe.subscribe.dto.out.SubscribeResponseDto;
import lookids.subscribe.subscribe.repository.SubscribeRepository;

@Service
@RequiredArgsConstructor
public class SubscribeServiceImpl implements SubscribeService{

	private final SubscribeRepository subscribeRepository;

	@Override
	public SubscribeResponseDto getSubscribers(String authorUuid) {
		List<Subscribe> subscribers = subscribeRepository.findByAuthorUuid(authorUuid);

		List<String> subscriberUuids = subscribers.stream()
			.map(Subscribe::getSubscriberUuid)
			.collect(Collectors.toList());

		return SubscribeResponseDto.toDto(authorUuid, subscriberUuids);
	}
}
