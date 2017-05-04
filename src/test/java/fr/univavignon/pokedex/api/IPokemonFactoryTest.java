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
public class IPokemonFactoryTest {
	@Mock 
	private IPokemonFactory PokemonFactory;
	private Pokemon pokemon = new Pokemon(1, "Bulbasaur", 126, 126, 90, 613, 64, 4000, 4, 56);
	
	@Before
	public void setUp(){
		int index = 1;
		int cp = 613;
		int hp = 64;
		int dust = 4000;
		int candy = 4;	
		MockitoAnnotations.initMocks(this);
		Mockito.when(PokemonFactory.createPokemon(index, cp, hp, dust, candy)).thenReturn(pokemon);
	}

	@Test
	public void test() {
		Pokemon p = PokemonFactory.createPokemon(1, 613, 64, 4000, 4);
		assertEquals(pokemon.getIndex(), p.getIndex());
		assertEquals(pokemon.getName(), p.getName());
		assertEquals(pokemon.getAttack(), p.getAttack());
		assertEquals(pokemon.getDefense(), p.getDefense());
		assertEquals(pokemon.getStamina(), p.getStamina());
		assertEquals(pokemon.getCandy(), p.getCandy());
		assertEquals(pokemon.getCp(), p.getCp());
		assertEquals(pokemon.getHp(), p.getHp());
		assertEquals(pokemon.getDust(), p.getDust());
		assertEquals(pokemon.getIv(), p.getIv(),0);		
	}
}