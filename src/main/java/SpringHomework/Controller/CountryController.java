package SpringHomework.Controller;


import SpringHomework.POJO.CountryService;
import SpringHomework.POJO.Country;
import org.springframework.web.bind.annotation.*;


import javax.print.DocFlavor;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class CountryController {

    private final CountryService service;

    public CountryController(CountryService service) {
        this.service = service;
    }

    @GetMapping("/countries")
    List<Country> gelAllCountries() {
        return service.getAllCountries();
    }

    //- list all country names : /countries/names -> returns a list of strings
    @GetMapping("/countries/names}")
    List<String> getCountryName() {
        return service.getCountryName();
    }

    //get capital of a country : /countries/<countryId>/capital -> returns a string
    @GetMapping("/countries/{id}/capital")
    Optional<String> countryCapital(@PathVariable int id) {
        return service.getCountry(id);
    }

    //- get population of a country : /countries/<countryId>/population -> returns a long
    @GetMapping("/countries/{id}/population")
    Optional<Long> countrypopulation(@PathVariable int id) {
        return service.populationOf(id);
    }

    //- get countries in continent : /continents/<continentName>/countries -> returns list of Country objects
    @GetMapping("/countries/continent/countries")
    List<Country> countiesOfContinent(@RequestParam(required = false) String continent) {

        return service.showCountriesFrom(continent);

    }

    //- get country neighbours : /countries/<countryId>/neighbours -> returns list of Strings
    @GetMapping("/countries/{id}/neighbours")
    List<List<String>> getNeighbours(@PathVariable int id) {
        return service.getCountryNeighbours(id);
    }

    //- get countries in <continent> with population larger than <population> : /continents/<continentName>/countries?minPopulation=<minimum population> -> returns list of Country objects
    @GetMapping("/continents/population")
    List<Country> getCountries(@RequestParam(required = false) String name,@RequestParam(required = false) long pop) {
        return service.getCountriesOnContinentLargerThenNumber(name, pop);
    }

    //- get countries that neighbor X but not neighbor Y : /countries?includeNeighbour=<includedNeighbourCode>&excludeNeighbour=<excludedNeighbourCode> -> returns list of Country objects
    @GetMapping("/countries/neighboursX")
    List<Country> countriesWithNei(@RequestParam(required = false) String neighbour, @RequestParam String excludedNeighbour) {
        return service.gotNeighbourXandNotY(neighbour, excludedNeighbour);
    }

    //- get map from country to population : /countries/population -> returns map from String to Long
    @GetMapping("/countries/population")
    Map<String, Long> mapCountryToPopulation() {
        return service.mapFromCountryToPopulation();
    }

    //- get map from continent to list of countries : /continents/countries  -> returns Map from String to List<Country>
    @GetMapping("/continents/countries")
    Map<String, List<Country>> continentToCountry() {
        return service.mapContinentToListOfCountries();
    }
}


