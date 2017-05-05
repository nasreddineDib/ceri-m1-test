package fr.univavignon.pokedex.api;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

/**
 * 
 * @author Dib Nasreddine
 *
 */
public class IPokemonTrainerFactoryTest {

	@Mock private IPokemonTrainerFactory pokemonTrainerFactory;
	@Mock private IPokedex pokedex;
	@Mock private IPokedexFactory pokedexFactory;
	@Mock private PokemonTrainer spark;
	@Mock private PokemonTrainer blanche;
	@Mock private PokemonTrainer candela;

	public void setSpark(PokemonTrainer Spark) {this.spark = Spark;}
	public void setBlanche(PokemonTrainer blanche) {this.blanche = blanche;}
	public void setCandela(PokemonTrainer candela) {this.candela = candela;}
	public IPokemonTrainerFactory getPokemonTrainerFactory() {return pokemonTrainerFactory;}
	public void setPokemonTrainerFactory(IPokemonTrainerFactory pokemonTrainerFactory) {this.pokemonTrainerFactory = pokemonTrainerFactory;}
	public IPokedexFactory getPokedexFactory() {return pokedexFactory;}
	public void setPokedexFactory(IPokedexFactory pokedexFactory) {this.pokedexFactory = pokedexFactory;}
	public void setPokedex(IPokedex pokedex) {this.pokedex = pokedex;}

	@Before
	public void setUp() throws PokedexException {
		Pokemon pokemon = new Pokemon(0, "Bulbasaur", 126, 126, 90, 613, 64, 4000, 4, 56);
		MockitoAnnotations.initMocks(this);

		//On initialise le leader de la INSTINCT
		setSpark(new PokemonTrainer("Spark", Team.INSTINCT,  pokedex));
		Mockito.when(pokemonTrainerFactory.createTrainer("Spark", Team.INSTINCT,  getPokedexFactory())).thenReturn(spark);

		//On initialise le leader de la team MYSTIC
		setBlanche(new PokemonTrainer("Blanche", Team.MYSTIC,  pokedex));
		Mockito.when(pokemonTrainerFactory.createTrainer("Blanche", Team.MYSTIC,  getPokedexFactory())).thenReturn(blanche);

		//On initialise le leader de la VALOR
		setCandela(new PokemonTrainer("Candela", Team.VALOR,  pokedex));
		Mockito.when(pokemonTrainerFactory.createTrainer("Candela", Team.VALOR,  getPokedexFactory())).thenReturn(candela);
		
		Mockito.when(pokedex.size()).thenReturn(1);
		Mockito.when(pokedex.getPokemon(0)).thenReturn(pokemon);
	}


	@Test
	public void createTrainerTest() {
		//Team INSTINCT
		PokemonTrainer sparkTrainer = getPokemonTrainerFactory().createTrainer("Spark", Team.INSTINCT, getPokedexFactory());
		assertEquals("Spark", sparkTrainer.getName());
		assertEquals(0, sparkTrainer.getPokedex().getPokemons().size());
		assertEquals(Team.INSTINCT, sparkTrainer.getTeam());

		//Team MYSTIC
		PokemonTrainer blancheTrainer = getPokemonTrainerFactory().createTrainer("Blanche", Team.MYSTIC, getPokedexFactory());
		assertEquals("Blanche", blancheTrainer.getName());
		assertEquals(0, blancheTrainer.getPokedex().getPokemons().size());
		assertEquals(Team.MYSTIC, blancheTrainer.getTeam());

		//Team VALOR
		PokemonTrainer candelaTrainer = getPokemonTrainerFactory().createTrainer("Candela", Team.VALOR, getPokedexFactory());
		assertEquals("Candela", candelaTrainer.getName());
		assertEquals(0, candelaTrainer.getPokedex().getPokemons().size());
		assertEquals(Team.VALOR, candelaTrainer.getTeam());
	}

	@Test 
	public void testPokemonTrainer() throws PokedexException {
		Pokemon pokemon = new Pokemon(0, "Bulbasaur", 126, 126, 90, 613, 64, 4000, 4, 56);

		PokemonTrainer pokemonTrainer = getPokemonTrainerFactory().createTrainer("Spark", Team.INSTINCT, getPokedexFactory());
		assertEquals("Spark", pokemonTrainer.getName());
		assertEquals(Team.INSTINCT, pokemonTrainer.getTeam());
		int index = pokemonTrainer.getPokedex().addPokemon(pokemon);
		assertEquals(1, pokemonTrainer.getPokedex().size());

		PokemonTrainer pokemonTrainer2 = getPokemonTrainerFactory().createTrainer("Spark", Team.INSTINCT, getPokedexFactory());
		assertEquals("Spark", pokemonTrainer2.getName());
		assertEquals(Team.INSTINCT, pokemonTrainer2.getTeam());
		assertEquals(1, pokemonTrainer2.getPokedex().size());
		assertEquals(pokemon.getName(), pokemonTrainer2.getPokedex().getPokemon(index).getName());
		assertEquals(pokemon.getIndex(), pokemonTrainer2.getPokedex().getPokemon(index).getIndex());

	}

	@After
	public void clear(){
		try{
			final String file1 = "."+File.separator+"src"+File.separator+"serialized"+File.separator+"Spark.ser";
			final String file2 = "."+File.separator+"src"+File.separator+"serialized"+File.separator+"Spark.ser";
			final String file3 = "."+File.separator+"src"+File.separator+"serialized"+File.separator+"Spark.ser";
			File trainer2 = new File(file1);
			trainer2.delete();
			trainer2 = new File(file2);
			trainer2.delete();
			trainer2 = new File(file3);
			trainer2.delete();
		}catch(Exception e){}

	}

}
