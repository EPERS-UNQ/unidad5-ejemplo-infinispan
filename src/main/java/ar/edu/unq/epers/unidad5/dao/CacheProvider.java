package ar.edu.unq.epers.unidad5.dao;

import java.util.List;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;

import ar.edu.unq.epers.unidad5.model.Cancion;

public class CacheProvider {
	
	private static CacheProvider INSTANCE;
	
	public synchronized static CacheProvider getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CacheProvider();
		}
		return INSTANCE;
	}

	private RemoteCacheManager cacheManager;
	private RankingDiarioCache rankingDiarioCache;
	
	private CacheProvider() {
		ConfigurationBuilder builder = new ConfigurationBuilder();
		builder.addServer().host("127.0.0.1").port(11222);

		this.cacheManager = new RemoteCacheManager(builder.build());
		
		RemoteCache<RankingPorDia, List<Cancion>> realCache = this.cacheManager.getCache("ranking-diario");
		this.rankingDiarioCache = new RankingDiarioCache(realCache);
	}
	
	public RankingDiarioCache getRankingDiarioCache() {
		return this.rankingDiarioCache;
	}

}
