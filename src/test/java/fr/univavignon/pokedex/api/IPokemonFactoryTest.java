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
	private IPokemonFactory pokemonFactory;
	private Pokemon pokemon1;
	private Pokemon pokemon2;

	public IPokemonFactory getPokemonFactory() {return pokemonFactory;}
	public void setPokemonFactory(IPokemonFactory pokemonFactory) {this.pokemonFactory = pokemonFactory;	}

	public void setPokemon1(Pokemon pokemon1) {this.pokemon1 = pokemon1;}
	public void setPokemon2(Pokemon pokemon2) {this.pokemon2 = pokemon2;}

	@Before
	public void setUp() throws PokedexException {
		MockitoAnnotations.initMocks(this);
		pokemon1 = new Pokemon(0, "Bulbasaur", 126, 126, 90, 613, 64, 4000, 4, 56);
		pokemon2 = new Pokemon(133, "Vaporeon", 186, 168, 260, 1984, 172, 3500, 4, 69);
		Mockito.when(getPokemonFactory().createPokemon(0, 613, 64, 4000, 4)).thenReturn(pokemon1);
		Mockito.when(getPokemonFactory().createPokemon(133, 1984, 172, 3500, 4)).thenReturn(pokemon2);
	}

	@Test
	public void testPokemon1() {
		Pokemon pokemon1 = pokemonFactory.createPokemon(0, 613, 64, 4000, 4);
		assertEquals("Bulbasaur", pokemon1.getName());
		assertEquals(0, pokemon1.getIndex());
		assertEquals(126, pokemon1.getAttack());
		assertEquals(126, pokemon1.getDefense());
		assertEquals(90, pokemon1.getStamina());
		assertEquals(613, pokemon1.getCp());
		assertEquals(64, pokemon1.getHp());
		assertEquals(4000, pokemon1.getDust());
		assertEquals(4, pokemon1.getCandy());
		assertEquals(56, pokemon1.getIv(), 0.001);
	}

	@Test
	public void testPokemon2() {
		Pokemon pokemon2 = pokemonFactory.createPokemon(133, 1984, 172, 3500, 4);
		assertEquals("Vaporeon", pokemon2.getName());
		assertEquals(133, pokemon2.getIndex());
		assertEquals(186, pokemon2.getAttack());
		assertEquals(168, pokemon2.getDefense());
		assertEquals(260, pokemon2.getStamina());
		assertEquals(1984, pokemon2.getCp());
		assertEquals(172, pokemon2.getHp());
		assertEquals(3500, pokemon2.getDust());
		assertEquals(4, pokemon2.getCandy());
		assertEquals(69, pokemon2.getIv(), 0.001);

	}
}