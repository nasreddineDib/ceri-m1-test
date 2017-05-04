/**
 * 
 */
package fr.univavignon.pokedex.imp;

import fr.univavignon.pokedex.api.IPokedexTest;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.Pokemon;
import fr.univavignon.pokedex.implementation.Pokedex;
import fr.univavignon.pokedex.implementation.PokemonFactory;
import fr.univavignon.pokedex.implementation.PokemonMetadataProvider;

/**
 * @author Dib Nasreddine
 *
 */
public class PokedexTest extends IPokedexTest{
	@Override
	public void setUp() throws PokedexException {
		this.setPokemon1(new Pokemon(0, "Bulbasaur", 126, 126, 90, 613, 64, 4000, 4, 56));
		this.setPokemon2(new Pokemon(134, "Jolteon", 192, 174, 130, 613, 64, 4000, 0, 56));
		this.setPokedex(new Pokedex(PokemonFactory.getInstance(),PokemonMetadataProvider.getInstance()));
	}
}
