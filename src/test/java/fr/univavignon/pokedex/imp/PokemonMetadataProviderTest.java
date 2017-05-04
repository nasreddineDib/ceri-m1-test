/**
 * 
 */
package fr.univavignon.pokedex.imp;

import org.junit.Before;
import fr.univavignon.pokedex.api.IPokemonMetadataProviderTest;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.PokemonMetadata;
import fr.univavignon.pokedex.implementation.PokemonMetadataProvider;

/**
 * @author Dib Nasreddine
 *
 */
public class PokemonMetadataProviderTest extends IPokemonMetadataProviderTest{
	@Before
	public void setUp() throws PokedexException {
		/**
		 * On recupere une instance du PokemonMetadataProvider
		 */
		this.setIpokemonMetadataProviderMock(PokemonMetadataProvider.getInstance());

		/**
		 * On cree un pokemon
		 */
		this.setPokemonMetadata1(new PokemonMetadata(0, "Bulbasaur", 126, 126, 90));
		this.setPokemonMetadata2(new PokemonMetadata(134, "Jolteon", 192, 174, 130));
	}
}
