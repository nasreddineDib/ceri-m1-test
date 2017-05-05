package fr.univavignon.pokedex.api;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.mockito.Mockito;

/**
 * 
 * @author Dib Nasreddine
 *
 */
public class IPokedexFactoryTest {

	@Mock private IPokedexFactory pokedexFactory;
	@Mock private IPokemonMetadataProvider pokemonMetadataProvider;
	@Mock private IPokemonFactory pokemonFactory;
	@Mock private IPokedex pokedex = null;

	public IPokemonFactory getPokemonFactory() {return pokemonFactory;}
	public void setPokedexFactory(IPokedexFactory pokedexFactory) {this.pokedexFactory = pokedexFactory;}

	@Before
	public void setUp() throws PokedexException {
		MockitoAnnotations.initMocks(this);
		Mockito.when(pokedexFactory.createPokedex(pokemonMetadataProvider, getPokemonFactory())).thenReturn(pokedex);
	}

	@Test
	public void createPokedexTest() {
		IPokedex pokedex = pokedexFactory.createPokedex(pokemonMetadataProvider, getPokemonFactory());
		assertNotNull(pokedex);
	}


}
