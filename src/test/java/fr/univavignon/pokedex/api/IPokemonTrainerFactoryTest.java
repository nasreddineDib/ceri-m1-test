package fr.univavignon.pokedex.api;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

/**
 * 
 * @author Dib Nasreddine
 *
 */
public class IPokemonTrainerFactoryTest {

	@Mock private IPokemonTrainerFactory pokemonTrainerFactory;
	private IPokedex pokedex;
	private IPokedexFactory pokedexFactory;
	private PokemonTrainer pokemoTrainer = new PokemonTrainer("Nasreddine", Team.MYSTIC,  pokedex);

	@Before
	public void setUp() throws PokedexException {
		MockitoAnnotations.initMocks(this);
		Mockito.when(pokemonTrainerFactory.createTrainer("Nasreddine", Team.MYSTIC,  pokedexFactory)).thenReturn(pokemoTrainer);
	}
	
	@Test
	public void createTrainerTest() {
		assertEquals(pokemoTrainer, pokemonTrainerFactory.createTrainer("Nasreddine", Team.MYSTIC,  pokedexFactory));
	}

}
