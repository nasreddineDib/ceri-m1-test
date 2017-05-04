/**
 * 
 */
package fr.univavignon.pokedex.imp;
import fr.univavignon.pokedex.api.IPokemonFactoryTest;
import fr.univavignon.pokedex.api.Pokemon;
import fr.univavignon.pokedex.implementation.PokemonFactory;

/**
 * @author Dib Nasreddine
 *
 */
public class PokemonFactoryTest extends IPokemonFactoryTest {
	@Override
	public void setUp() {
		this.setPokemonFactory(PokemonFactory.getInstance());
		this.setPokemon1(new Pokemon(0, "Bulbasaur", 126, 126, 90, 613, 64, 4000, 4, 56));
		this.setPokemon2(new Pokemon(134, "Jolteon", 192, 174, 130, 613, 64, 4000, 0, 56));
	}
}