/**
 * 
 */
package fr.univavignon.pokedex.imp;

import fr.univavignon.pokedex.api.IPokedexFactory;
import fr.univavignon.pokedex.api.IPokedexFactoryTest;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.implementation.PokedexFactory;

/**
 * @author Dib Nasreddine
 *
 */
public class PokedexFactoryTest extends IPokedexFactoryTest {
	@Override
	public void setUp() throws PokedexException {
		IPokedexFactory factory = new PokedexFactory();
		this.setPokedexFactory(factory);
	}
}
