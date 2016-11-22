package ar.edu.unq.epers.unidad5.dao;

import java.util.List;

import org.infinispan.client.hotrod.RemoteCache;

import ar.edu.unq.epers.unidad5.model.Cancion;

public class RankingDiarioCache {
	
	private RemoteCache<RankingPorDia, List<Cancion>> realCache;

	public RankingDiarioCache(RemoteCache<RankingPorDia, List<Cancion>> realCache) {
		this.realCache = realCache;
	}

	public void clear() {
		this.realCache.clear();
	}

	public List<Cancion> put(RankingPorDia key, List<Cancion> value) {
		return this.realCache.put(key, value);
	}
	
	public List<Cancion> get(Object key) {
		return this.realCache.get(key);
	}

	public void stop() {
		this.realCache.stop();
	}

	public int size() {
		return this.realCache.size();
	}


	
	
	
}
