package fr.univavignon.pokedex.api;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.mockito.Mockito;


public class IPokedexFactoryTest {

	@Mock private IPokedexFactory pokedexFactory;
	private IPokemonMetadataProvider pokemonMetadataProvider;
	private IPokemonFactory pokemonFactory;
	private IPokedex pokedex;

	@Before
	public void setUp() throws PokedexException {
		MockitoAnnotations.initMocks(this);
		Mockito.when(pokedexFactory.createPokedex(pokemonMetadataProvider, pokemonFactory)).thenReturn(pokedex);
	}
	
	@Test
	public void createPokedexTest() {
		assertEquals(pokedexFactory.createPokedex(pokemonMetadataProvider, pokemonFactory), pokedex);
	}

	

}
