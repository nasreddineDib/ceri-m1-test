package fr.univavignon.pokedex.api;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.mockito.*;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Dib Nasreddine
 *
 */
public class IPokedexTest {

	@Mock private IPokedex pokedex;
	private int index;
	private int nbrPokemon;
	private List<Pokemon> pokemons = new ArrayList<Pokemon>(151);
	private Pokemon pokemon1 = new Pokemon(0,"Bulbasaur",126,126,90,613,64,4000,4,56);
	private Pokemon pokemon2 = new Pokemon(134, "Jolteon", 192, 174, 130, 613, 64, 4000, 0, 56);

	public IPokedex getPokedex() {return pokedex;}
	public void setPokedex(IPokedex pokedex) {this.pokedex = pokedex;}

	public int getIndex() {return index;}
	public void setIndex(int index) {this.index = index;}

	public int getNbrPokemon() {return nbrPokemon;}
	public void setNbrPokemon(int nbrPokemon) {this.nbrPokemon = nbrPokemon;}

	public List<Pokemon> getPokemons() {return pokemons;}
	public void setPokemons(List<Pokemon> pokemons) {this.pokemons = pokemons;}

	public Pokemon getPokemon1() {return pokemon1;}
	public void setPokemon1(Pokemon pokemon1) {this.pokemon1 = pokemon1;}

	public Pokemon getPokemon2() {return pokemon2;}
	public void setPokemon2(Pokemon pokemon2) {this.pokemon2 = pokemon2;}

	@Before
	public void setUp() throws PokedexException{
		MockitoAnnotations.initMocks(this);
		index = 0;
		nbrPokemon = 0;

		//Mock de getPokemon
		Mockito.when(this.getPokedex().getPokemon(0)).thenReturn(this.getPokemon1());
		Mockito.when(this.getPokedex().getPokemon(1)).thenReturn(this.getPokemon2());
		Mockito.when(this.getPokedex().getPokemon(-1)).thenThrow(new PokedexException("Aucun pokemon de cet id n'est pas dans le pokedex!"));
		Mockito.when(this.getPokedex().getPokemon(300)).thenThrow(new PokedexException("Aucun pokemon de cet id n'est pas dans le pokedex!"));

		//Mock pour tester la taille avec size()
		Mockito.when(this.getPokedex().size()).thenAnswer(new Answer<Object>() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				return nbrPokemon;
			}
		});

		//Mock pour tester l'index apres l'ajout du pokemon1
		Mockito.when(this.getPokedex().addPokemon(this.getPokemon1())).thenAnswer(new Answer<Object>() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				return index;
			}
		});

		//Mock pour tester l'index apres l'ajout du pokemon2
		Mockito.when(this.getPokedex().addPokemon(this.getPokemon2())).thenAnswer(new Answer<Object>() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				return index;
			}
		});

		//Mock pour tester getPokemonMetadata
		Mockito.when(this.getPokedex().getPokemonMetadata(0)).thenReturn(new PokemonMetadata(0, "Bulbasaur", 126, 126, 90));
		Mockito.when(this.getPokedex().getPokemonMetadata(134)).thenReturn(new PokemonMetadata(134, "Jolteon", 192, 174, 130));

		//On les ajoute par order croissant d'index
		ArrayList<Pokemon> byIndex = new ArrayList<Pokemon>();
		byIndex.add(this.getPokedex().getPokemon(0));
		byIndex.add(this.getPokedex().getPokemon(1));
		
		//On les ajoute par order croissant de nom
		List<Pokemon> byName = new ArrayList<Pokemon>();
		byName.add(this.getPokedex().getPokemon(0));
		byName.add(this.getPokedex().getPokemon(1));

		//On les ajoute par order croissant de CP
		ArrayList<Pokemon> byCp = new ArrayList<Pokemon>();
		byCp.add(this.getPokedex().getPokemon(0));
		byCp.add(this.getPokedex().getPokemon(1));

		//Mock pour tester getPokemons
		Mockito.when(this.getPokedex().getPokemons()).thenReturn(pokemons);
		Mockito.when(this.getPokedex().getPokemons(PokemonComparators.NAME)).thenReturn(byName);
		Mockito.when(this.getPokedex().getPokemons(PokemonComparators.INDEX)).thenReturn(byIndex);
		Mockito.when(this.getPokedex().getPokemons(PokemonComparators.CP)).thenReturn(byCp);

	}

	@Test(expected=PokedexException.class)
	public void testPokemonException1() throws PokedexException{
		this.getPokedex().getPokemon(-1);
	}

	@Test(expected=PokedexException.class)
	public void testPokemonException2() throws PokedexException{
		this.getPokedex().getPokemon(300);
	}

	@Test
	public void addPokemonTest() {
		assertEquals(0, this.mockAddModifier(this.getPokemon1()));
		assertEquals(1, this.mockAddModifier(this.getPokemon2()));
	}
	@Test
	public void getPokemonTest() throws PokedexException {
		this.getPokedex().addPokemon(pokemon1);
		assertEquals(this.getPokedex().getPokemon(0), this.getPokemon1());
		this.getPokedex().addPokemon(pokemon2);
		assertEquals(this.getPokedex().getPokemon(1), this.getPokemon2());
	}

	@Test
	public void getPokemonsTest(){
		List<Pokemon> pokemons = this.getPokedex().getPokemons();
		assertEquals(this.getPokemons(), pokemons);
	}

	@Test
	public void getPokemonMetadataTest() throws PokedexException{

		/**
		 * Test pokemon 1
		 */
		PokemonMetadata p1 = this.getPokedex().getPokemonMetadata(0);
		assertEquals(this.getPokemon1().getAttack(), p1.getAttack());
		assertEquals(this.getPokemon1().getDefense(), p1.getDefense());
		assertEquals(this.getPokemon1().getIndex(), p1.getIndex());
		assertEquals(this.getPokemon1().getName(), p1.getName());
		assertEquals(this.getPokemon1().getStamina(), p1.getStamina());

		/**
		 * Test pokemon 2
		 */
		PokemonMetadata p2 = this.getPokedex().getPokemonMetadata(134);
		assertEquals(this.getPokemon2().getAttack(), p2.getAttack());
		assertEquals(this.getPokemon2().getDefense(), p2.getDefense());
		assertEquals(this.getPokemon2().getIndex(), p2.getIndex());
		assertEquals(this.getPokemon2().getName(), p2.getName());
		assertEquals(this.getPokemon2().getStamina(), p2.getStamina());
	}

	@Test
	public void sizeTest() {
		assertEquals(0,this.getPokedex().size());
		assertEquals(0, this.mockAddModifier(this.getPokemon1()));
		assertEquals(1,this.getPokedex().size());
		assertEquals(1, this.mockAddModifier(this.getPokemon2()));
	}

	@Test
	public void getPokemonsComparatorsTest(){
		this.mockAddModifier(getPokemon2());
		this.mockAddModifier(getPokemon1());
		List<Pokemon> byIndex = this.getPokedex().getPokemons(PokemonComparators.INDEX);
		List<Pokemon> byName = this.getPokedex().getPokemons(PokemonComparators.NAME);
		List<Pokemon> byCp = this.getPokedex().getPokemons(PokemonComparators.CP);
		assertEquals(byIndex.size(), byIndex.size());
		assertEquals(byName.size(), byName.size());
		assertEquals(byCp.size(), byCp.size());
		assertEquals(this.getPokemon1().getName(), byName.get(0).getName());
	}

	public int mockAddModifier(Pokemon pokemon){
		int res = this.getPokedex().addPokemon(pokemon);
		index++;
		nbrPokemon++;
		return res;
	}
}
