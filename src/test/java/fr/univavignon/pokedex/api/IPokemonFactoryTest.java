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
	Pokemon p = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);

	@Mock private IPokemonFactory PokemonFactory;

	@Before
	public void setUp(){
		int index = 0;
		int cp = 613;
		int hp = 64;
		int dust = 4000;
		int candy = 4;
		
		MockitoAnnotations.initMocks(this);
		Mockito.when(PokemonFactory.createPokemon(index, cp, hp, dust, candy)).thenReturn(p);
	}

	@Test
	public void test() {
		Pokemon p = PokemonFactory.createPokemon(0, 613, 64, 4000, 4);
		assertEquals(0, p.getIndex());
		assertEquals("Bulbizarre", p.getName());
		assertEquals(126, p.getAttack());
		assertEquals(126, p.getDefense());
		assertEquals(90, p.getStamina());
		assertEquals(613, p.getCp());
		assertEquals(64, p.getHp());
		assertEquals(4000, p.getDust());
		assertEquals(56, p.getIv(),0);		
	}
}