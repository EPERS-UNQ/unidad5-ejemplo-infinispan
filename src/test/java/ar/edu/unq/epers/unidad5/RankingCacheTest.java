package ar.edu.unq.epers.unidad5;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unq.epers.unidad5.dao.CacheProvider;
import ar.edu.unq.epers.unidad5.dao.RankingDiarioCache;
import ar.edu.unq.epers.unidad5.dao.RankingPorDia;
import ar.edu.unq.epers.unidad5.model.Cancion;

public class RankingCacheTest {

	private Cancion cancion1;
	private Cancion cancion2;
	private Cancion cancion3;
	private Cancion cancion4;
	private Cancion cancion5;
	private Cancion cancion6;
	
	private RankingDiarioCache cache;

	@Before
	public void setup() {
		this.cache = CacheProvider.getInstance().getRankingDiarioCache();

		this.cancion1 = new Cancion("cancion1", "artista1", "album1");
		this.cancion2 = new Cancion("cancion2", "artista2", "album2");
		this.cancion3 = new Cancion("cancion3", "artista3", "album3");
		this.cancion4 = new Cancion("cancion4", "artista4", "album4");
		this.cancion5 = new Cancion("cancion5", "artista5", "album5");
		this.cancion6 = new Cancion("cancion6", "artista6", "album6");

		RankingPorDia key = new RankingPorDia("Rolling Stone", LocalDate.of(2016, 11, 10));
		this.cache.put(key, Arrays.asList(this.cancion1, this.cancion2, this.cancion4, this.cancion5));

		RankingPorDia key2 = new RankingPorDia("Rolling Stone", LocalDate.of(2016, 11, 11));
		this.cache.put(key2, Arrays.asList(this.cancion1, this.cancion2, this.cancion3, this.cancion6));
	}

	
	@Test
	public void obtenerBusqueda() {
		List<Cancion> busqueda = this.cache.get( new RankingPorDia("Rolling Stone", LocalDate.of(2016, 11, 11)) );
		Assert.assertEquals(Arrays.asList(this.cancion1, this.cancion2, this.cancion3, this.cancion6), busqueda);
	}
	@Test
	public void obtenerBusqueda2() {
		List<Cancion> busqueda = this.cache.get( new RankingPorDia("Rolling Stone", LocalDate.of(2016, 11, 10)) );
		Assert.assertEquals(Arrays.asList(this.cancion1, this.cancion2, this.cancion4, this.cancion5), busqueda);
	}
		
	@Test
	public void testEviction() {
		Assert.assertEquals(2, this.cache.size());
		
		for (int i = 0; i < 10; i++) {
			RankingPorDia key = new RankingPorDia("Test" + i, LocalDate.of(2016, 11, 10));
			this.cache.put(key, Arrays.asList(this.cancion1, this.cancion2, this.cancion4, this.cancion5));
		}
		Assert.assertEquals(5, this.cache.size());
	}
	
	@Test
	public void busquedaVacia() {
		List<Cancion> busqueda = this.cache.get( new RankingPorDia("Billboard", LocalDate.of(2016, 11, 10)) );
		Assert.assertNull(busqueda);
	}
	
	@After
	public void cleanup() {
		this.cache.clear();
		this.cache.stop();
	}
	
}
