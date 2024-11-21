package lookids.subscribe.subscribe.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lookids.subscribe.subscribe.domain.Subscribe;
import lookids.subscribe.subscribe.dto.in.SubscribeRequestDto;
import lookids.subscribe.subscribe.dto.out.SubscribeResponseDto;
import lookids.subscribe.subscribe.repository.SubscribeRepository;

@Service
@RequiredArgsConstructor
public class SubscribeServiceImpl implements SubscribeService{

	private final SubscribeRepository subscribeRepository;

	@Override
	@Cacheable(value = "subscribers", key = "#authorUuid")
	public SubscribeResponseDto readSubscribers(String authorUuid) {
		List<Subscribe> subscribers = subscribeRepository.findByAuthorUuid(authorUuid);

		List<String> subscriberUuids = subscribers.stream()
			.map(Subscribe::getSubscriberUuid)
			.collect(Collectors.toList());

		return SubscribeResponseDto.toDto(authorUuid, subscriberUuids);
	}

	// 게시글 알림 신청 버튼이 off 상태일 때 적용 (등록)
	@Override
	public void createSubscribe(SubscribeRequestDto subscribeRequestDto) {
		subscribeRepository.save(subscribeRequestDto.toEntity());
	}

	// 게시글 알림 신청 버튼이 on 상태일 때 적용 (삭제)
	@Override
	@Transactional
	public void deleteSubscribe(SubscribeRequestDto subscribeRequestDto) {
		subscribeRepository.deleteByAuthorUuidAndSubscriberUuid(
			subscribeRequestDto.getAuthorUuid(),
			subscribeRequestDto.getSubscriberUuid()
		);
	}
}
