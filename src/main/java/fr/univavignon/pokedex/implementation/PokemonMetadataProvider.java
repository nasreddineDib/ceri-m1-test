package fr.univavignon.pokedex.implementation;
/**
 * @author Dib Nasreddine
 *
 */
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import fr.univavignon.pokedex.api.IPokemonMetadataProvider;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.PokemonMetadata;

public class PokemonMetadataProvider implements IPokemonMetadataProvider, Serializable {

	private static final long serialVersionUID = -1331137594371941987L;
	private static PokemonMetadataProvider INSTANCE = null;
	private List<PokemonMetadata> PokemonMetadataList;
	private static String URL_STRING = "https://raw.githubusercontent.com/PokemonGo-Enhanced/node-pokemongo-data/master/data.json";

	/**
	 * Retourne le singleton
	 * @return PokemonMetadataProvider
	 */
	public static synchronized PokemonMetadataProvider getInstance() throws PokedexException{
		try {
			return INSTANCE == null?new PokemonMetadataProvider():INSTANCE;
		} catch (JSONException e) {
			throw new PokedexException(e.getMessage());
		}
	}

	/**
	 * Contructeur privee qui initialise la liste et la rempli a partir du fichier json
	 * @throws PokedexException 
	 * @throws JSONException
	 * @throws IOException
	 */
	private PokemonMetadataProvider() throws PokedexException{
		this.PokemonMetadataList = new ArrayList<PokemonMetadata>();
		try {
			this.fillPokemonMetadataList();
		} catch (JSONException | PokedexException | IOException e) {
			throw new  PokedexException(e.getMessage());
		}
	}

	/**
	 * Rempli la liste PokemonMetadataList avec les PokemonMetadata recupere et generes a partir du fichier json
	 * @throws JSONException
	 * @throws IOException
	 */
	private void fillPokemonMetadataList() throws PokedexException, JSONException, IOException {
		URL url = new URL(URL_STRING);
		JSONArray jsonArray = new JSONArray(IOUtils.toString(url));
		for(int i = 0; i < jsonArray.length(); i++){
			JSONObject obj = jsonArray.optJSONObject(i);
			PokemonMetadata pmdt = jsonToPMDT(obj);
			PokemonMetadataList.add(pmdt);
		}
	}

	/**
	 * Retourne un objet PokemonMetadata correspondant a l''index passe en parametre
	 * @param data
	 * @return PokemonMetadata
	 * @throws JSONException
	 */
	@Override
	public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
		if(index < 0 || index > 150)
			throw new PokedexException("L'index doit etre compris entre 0 et 150");
		
		return this.PokemonMetadataList.get(index);
	}

	/**
	 * Cree un PokemonMetadata a partir d'un objet json passe en parametre
	 * @param data
	 * @return PokemonMetadata
	 * @throws JSONException
	 */
	public PokemonMetadata jsonToPMDT(JSONObject data) throws JSONException{
		if(data == null) throw new JSONException("data ne peut etre null");
		PokemonMetadata pokemonMetadata;
		pokemonMetadata = new PokemonMetadata(data.getInt("PkMn")-1,
				data.getString("Identifier"), 
				data.getInt("BaseAttack"), 
				data.getInt("BaseDefense"), 
				data.getInt("BaseStamina"));
		return pokemonMetadata;
	}
}