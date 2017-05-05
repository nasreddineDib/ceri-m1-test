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
public class IPokemonMetadataProviderTest {

	@Mock private IPokemonMetadataProvider pokemonMetadataProvider;
	@Mock private PokemonMetadata pokemonMetadata1;
	@Mock private PokemonMetadata pokemonMetadata2;


	/**
	 * @param pokemonMetadataProvider
	 */
	public void setIpokemonMetadataProviderMock(IPokemonMetadataProvider pokemonMetadataProvider) {
		this.pokemonMetadataProvider = pokemonMetadataProvider;
	}

	/**
	 * 
	 * @param pokemonMeta
	 */
	public void setPokemonMetadata1(PokemonMetadata pokemonMeta) {
		this.pokemonMetadata1 = pokemonMeta;
	}
	
	/**
	 * 
	 * @param pokemonMeta
	 */
	public void setPokemonMetadata2(PokemonMetadata pokemonMeta) {
		this.pokemonMetadata2 = pokemonMeta;
	}

	/**
	 * 
	 * @throws PokedexException
	 */
	@Before
	public void setUp() throws PokedexException{
		MockitoAnnotations.initMocks(this);
		pokemonMetadata1 = new PokemonMetadata(0, "Bulbasaur", 126, 126, 90);
		pokemonMetadata2 = new PokemonMetadata(134, "Jolteon", 192, 174, 130);
		//Valid values
		Mockito.when(pokemonMetadataProvider.getPokemonMetadata(0)).thenReturn(pokemonMetadata1);
		Mockito.when(pokemonMetadataProvider.getPokemonMetadata(134)).thenReturn(pokemonMetadata2);

		//Exceptions

		Mockito.when(pokemonMetadataProvider.getPokemonMetadata(190)).thenThrow(new PokedexException("Index error"));
		Mockito.when(pokemonMetadataProvider.getPokemonMetadata(151)).thenThrow(new PokedexException("Index error"));

		Mockito.when(pokemonMetadataProvider.getPokemonMetadata(-1)).thenThrow(new PokedexException("Index can't be neagtive"));
		Mockito.when(pokemonMetadataProvider.getPokemonMetadata(-154)).thenThrow(new PokedexException("Index can't be neagtive"));

	}

	/**
	 * 
	 * @throws PokedexException
	 */
	@Test
	public void getPokemonMetadataSuccessTest() throws PokedexException{
		//Test with Bulbizarre
		PokemonMetadata bulbasor = pokemonMetadataProvider.getPokemonMetadata(0);
		assertEquals(bulbasor.getIndex(), pokemonMetadata1.getIndex());
		assertEquals(bulbasor.getName(), pokemonMetadata1.getName());
		assertEquals(bulbasor.getAttack(), pokemonMetadata1.getAttack());
		assertEquals(bulbasor.getDefense(), pokemonMetadata1.getDefense());
		assertEquals(bulbasor.getStamina(), pokemonMetadata1.getStamina());

		//Test with Jolteon
		PokemonMetadata jolteon = pokemonMetadataProvider.getPokemonMetadata(134);
		assertEquals(jolteon.getIndex(), pokemonMetadata2.getIndex());
		assertEquals(jolteon.getName(), pokemonMetadata2.getName());
		assertEquals(jolteon.getAttack(), pokemonMetadata2.getAttack());
		assertEquals(jolteon.getDefense(), pokemonMetadata2.getDefense());
		assertEquals(jolteon.getStamina(), pokemonMetadata2.getStamina());
	}

	/**
	 * 
	 * @throws PokedexException
	 */
	@Test(expected = PokedexException.class)
	public void negativeIndexTest() throws PokedexException {
		pokemonMetadataProvider.getPokemonMetadata(-1);
		pokemonMetadataProvider.getPokemonMetadata(-190);	
	}

	/**
	 * 
	 * @throws PokedexException
	 */
	@Test(expected = PokedexException.class)
	public void outOfBoundExceptionTest() throws PokedexException {
		pokemonMetadataProvider.getPokemonMetadata(151);
		pokemonMetadataProvider.getPokemonMetadata(190);
	}

}
