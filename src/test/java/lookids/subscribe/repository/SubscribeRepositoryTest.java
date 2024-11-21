package lookids.subscribe.repository;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lookids.subscribe.subscribe.dto.out.SubscribeResponseDto;
import lookids.subscribe.subscribe.repository.SubscribeRepository;
import lookids.subscribe.subscribe.service.SubscribeServiceImpl;

@SpringBootTest

public class SubscribeRepositoryTest {
	@Autowired
	private SubscribeRepository subscribeRepository;
	@Autowired
	private SubscribeServiceImpl subscribeService;

	// @Test
	// public void testBulkSubscribersRegistration() {
	// 	Random random = new Random();
	// 	// 주어진 authorUuid
	// 	String authorUuid = "normal-12345678";
	//
	// 	// 500명의 subscriberUuid 생성
	// 	List<String> subscriberUuids = IntStream.range(1, 9001)
	// 		.mapToObj(i -> "subscriber-" + String.format("%05d", random.nextInt(100000)))
	// 		.collect(Collectors.toList());
	//
	// 	// 각 subscriberUuid에 대해 Subscribe 엔티티 생성 및 저장
	// 	for (String subscriberUuid : subscriberUuids) {
	// 		Subscribe subscribe = Subscribe.builder()
	// 			.authorUuid(authorUuid)
	// 			.subscriberUuid(subscriberUuid)
	// 			.build();
	// 		subscribeRepository.save(subscribe);
	// 	}
	//
	// }


	@Test
	public void testQueryTimeForNormalAuthor() {
		String authorUuid = "normal-12345678";

		// 첫 번째 호출
		long startTime = System.nanoTime();
		SubscribeResponseDto response1 = subscribeService.readSubscribers(authorUuid);
		long endTime = System.nanoTime();
		long duration1 = (endTime - startTime) / 1_000_000;
		System.out.println("첫 번째 조회 시간: " + duration1 + " ms");

		// 두 번째 호출 (캐시에서 가져와야 하므로 시간이 줄어들어야 함)
		startTime = System.nanoTime();
		SubscribeResponseDto response2 = subscribeService.readSubscribers(authorUuid);
		endTime = System.nanoTime();
		long duration2 = (endTime - startTime) / 1_000_000;
		System.out.println("두 번째 조회 시간: " + duration2 + " ms");

		assertThat(response1.getSubscriberUuids()).isNotEmpty();
		assertThat(response2.getSubscriberUuids()).isEqualTo(response1.getSubscriberUuids());
	}
}
