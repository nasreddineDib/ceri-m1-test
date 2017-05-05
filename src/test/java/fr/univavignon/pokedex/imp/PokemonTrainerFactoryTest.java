/**
 * 
 */
package fr.univavignon.pokedex.imp;

import fr.univavignon.pokedex.api.IPokemonTrainerFactoryTest;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.implementation.PokedexFactory;
import fr.univavignon.pokedex.implementation.PokemonTrainerFactory;

/**
 * @author Dib Nasreddine
 *
 */
public class PokemonTrainerFactoryTest extends IPokemonTrainerFactoryTest {
	@Override
	public void setUp() throws PokedexException {
		setPokemonTrainerFactory(PokemonTrainerFactory.getInstance());
		setPokedexFactory(PokedexFactory.getInstance());
	}
}
