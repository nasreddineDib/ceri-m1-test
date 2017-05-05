/**
 * 
 */
package fr.univavignon.pokedex.implementation;

import java.io.Serializable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.Pokemon;
import fr.univavignon.pokedex.api.PokemonMetadata;
import io.github.bonigarcia.wdm.ChromeDriverManager;

/**
 * @author Dib Nasreddine
 *
 */
public class PokemonFactory implements IPokemonFactory, Serializable {

	private static final long serialVersionUID = 21528211181680756L;
	private static PokemonFactory INSTANCE;

	private PokemonFactory() {}

	public static synchronized PokemonFactory getInstance() {
		return INSTANCE == null?new PokemonFactory():INSTANCE;
	}

	@Override
	public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
		try {
			PokemonMetadataProvider pmdtp = PokemonMetadataProvider.getInstance();		
			PokemonMetadata pmdt = pmdtp.getPokemonMetadata(index);
			return calculIV(index, pmdt.getName(),pmdt.getAttack(), pmdt.getDefense(),pmdt.getStamina(), cp,hp,dust,candy);
		} catch (PokedexException e) {
			return null;
		}
		
	}

	/**
	 * 
	 * @param name
	 * @param cp
	 * @param hp
	 * @param dust
	 * @return
	 */
	private Pokemon calculIV(//NOPMD
			int index,
			String name,
			int attack,
			int defense,
			int stamina,
			int cp,
			int hp,
			int dust,
			int candy) {
		final String url = "https://pokeassistant.com/main/ivcalculator?locale=en";
		ChromeDriverManager.getInstance().setup();
		WebDriver webDriver = new ChromeDriver();
		webDriver.get(url);

		webDriver.findElement(By.xpath("//*[@id=\"search_pokemon_name\"]")).sendKeys(name);
		webDriver.findElement(By.xpath("/html/body/div/div[3]/div[1]/span/div/div/div")).click();
		webDriver.findElement(By.xpath("//*[@id=\"search_cp\"]")).sendKeys(String.valueOf(cp));
		webDriver.findElement(By.xpath("//*[@id=\"search_hp\"]")).sendKeys(String.valueOf(hp));
		webDriver.findElement(By.xpath("//*[@id=\"search_dust\"]")).sendKeys(String.valueOf(dust));
		webDriver.findElement(By.xpath("//*[@id=\"calculatebtn\"]")).click();
		String res = webDriver.findElement(By.xpath("//*[@id=\"possibleCombinationsStringmax\"]//b")).getText();
				
		webDriver.quit();
		return new Pokemon(index,name,attack,defense,stamina,cp,hp,dust,candy,Math.round(Float.parseFloat(res.replace("%", ""))));
	}

}
