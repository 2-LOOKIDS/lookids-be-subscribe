package lookids.subscribe.common.config;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {

	@Bean
	public CaffeineCacheManager cacheManager() {
		CaffeineCacheManager cacheManager = new CaffeineCacheManager();
		cacheManager.setCaffeine(com.github.benmanes.caffeine.cache.Caffeine.newBuilder()
			.initialCapacity(100) // 초기 용량
			.maximumSize(1000) // 최대 크기
			.expireAfterWrite(10, TimeUnit.MINUTES) // 쓰기 후 만료 시간
			.recordStats()); // 통계 기록
		return cacheManager;
	}
}
