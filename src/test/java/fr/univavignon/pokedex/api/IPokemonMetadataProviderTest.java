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
	private PokemonMetadata pokemonMeta1 = new PokemonMetadata(0, "Bulbasaur", 126, 126, 90);
	private PokemonMetadata pokemonMeta2 = new PokemonMetadata(134, "Jolteon", 192, 174, 130);


	public void setIpokemonMetadataProviderMock(IPokemonMetadataProvider pokemonMetadataProvider) {
		this.pokemonMetadataProvider = pokemonMetadataProvider;
	}

	public void setPokemonMetadata1(PokemonMetadata pokemonMeta) {
		this.pokemonMeta1 = pokemonMeta;
	}
	
	public void setPokemonMetadata2(PokemonMetadata pokemonMeta) {
		this.pokemonMeta2 = pokemonMeta;
	}

	/**
	 * 
	 * @throws PokedexException
	 */
	@Before
	public void setUp() throws PokedexException{
		MockitoAnnotations.initMocks(this);

		//Valid values
		Mockito.when(pokemonMetadataProvider.getPokemonMetadata(0)).thenReturn(pokemonMeta1);
		Mockito.when(pokemonMetadataProvider.getPokemonMetadata(134)).thenReturn(pokemonMeta2);

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
		assertEquals(bulbasor.getIndex(), pokemonMeta1.getIndex());
		assertEquals(bulbasor.getName(), pokemonMeta1.getName());
		assertEquals(bulbasor.getAttack(), pokemonMeta1.getAttack());
		assertEquals(bulbasor.getDefense(), pokemonMeta1.getDefense());
		assertEquals(bulbasor.getStamina(), pokemonMeta1.getStamina());

		//Test with Jolteon
		PokemonMetadata jolteon = pokemonMetadataProvider.getPokemonMetadata(134);
		assertEquals(jolteon.getIndex(), pokemonMeta2.getIndex());
		assertEquals(jolteon.getName(), pokemonMeta2.getName());
		assertEquals(jolteon.getAttack(), pokemonMeta2.getAttack());
		assertEquals(jolteon.getDefense(), pokemonMeta2.getDefense());
		assertEquals(jolteon.getStamina(), pokemonMeta2.getStamina());
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
