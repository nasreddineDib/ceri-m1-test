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
	private PokemonMetadata pokemonMeta1 = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);
	private PokemonMetadata pokemonMeta2 = new PokemonMetadata(133, "Aquali", 186, 168, 260);

	/**
	 * 
	 * @throws PokedexException
	 */
	@Before
	public void setUp() throws PokedexException{
		MockitoAnnotations.initMocks(this);

		//Valid values
		Mockito.when(pokemonMetadataProvider.getPokemonMetadata(0)).thenReturn(pokemonMeta1);
		Mockito.when(pokemonMetadataProvider.getPokemonMetadata(133)).thenReturn(pokemonMeta2);

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
		assertEquals(pokemonMetadataProvider.getPokemonMetadata(0).getIndex(), pokemonMeta1.getIndex());
		assertEquals(pokemonMetadataProvider.getPokemonMetadata(0).getName(), pokemonMeta1.getName());
		assertEquals(pokemonMetadataProvider.getPokemonMetadata(0).getAttack(), pokemonMeta1.getAttack());
		assertEquals(pokemonMetadataProvider.getPokemonMetadata(0).getDefense(), pokemonMeta1.getDefense());
		assertEquals(pokemonMetadataProvider.getPokemonMetadata(0).getStamina(), pokemonMeta1.getStamina());

		//Test with Aquali
		assertEquals(pokemonMetadataProvider.getPokemonMetadata(133).getIndex(), pokemonMeta2.getIndex());
		assertEquals(pokemonMetadataProvider.getPokemonMetadata(133).getName(), pokemonMeta2.getName());
		assertEquals(pokemonMetadataProvider.getPokemonMetadata(133).getAttack(), pokemonMeta2.getAttack());
		assertEquals(pokemonMetadataProvider.getPokemonMetadata(133).getDefense(), pokemonMeta2.getDefense());
		assertEquals(pokemonMetadataProvider.getPokemonMetadata(133).getStamina(), pokemonMeta2.getStamina());
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
