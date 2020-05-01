package SpringHomework.POJO;

import SpringHomework.Reader.CountryReader;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Service
public class CountryService {

    private final List<Country> countries = new ArrayList<>();

    //   name|capital|population|area|continent|neighbour1~neighbour2
    public CountryService() throws FileNotFoundException {
        countries.addAll(new CountryReader().readCountries("src\\main\\resources\\countries2"));
    }

    //- list all countries: /countries -> returns a list of country objects

    public List<Country> getAllCountries() {
        return new ArrayList<>(countries);
    }

    //- list all country names : /countries/names -> returns a list of strings

    public List<String> getCountryName() {
        return countries.stream()
                .map(Country::getName)
                .collect(toList());
    }

    public Optional<String> getCountry(int id) {
        return countries.stream()
                .filter(c -> c.getId() == id)
                .map(Country::getName)
                .findFirst();
    }

    //- get capital of a country : /countries/<countryId>/capital -> returns a string
    public Optional<String> getCapital(String country) {
        return countries.stream()
                .filter(c -> c.getName().equals(country))
                .map(Country::getCapital)
                .findFirst();
    }

    //- get population of a country : /countries/<countryId>/population -> returns a long
    public Optional<Long> populationOf(int id) {
        return countries.stream()
                .filter(country1 -> country1.getId() == id)
                .map(Country::getPopulation)
                .findFirst();
    }

    //- get countries in continent : /continents/<continentName>/countries -> returns list of Country objects
    public List<Country> showCountriesFrom(String continent) {

        return countries.stream()
                .filter(c -> c.getContinent().equalsIgnoreCase(continent))
                .collect(toList());
    }

    //- get country neighbours : /countries/<countryId>/neighbours -> returns list of Strings
    public List<List<String>> getCountryNeighbours(int id) {
        return countries.stream()
                .filter(country1 -> country1.getId() == id)
                .map(Country::getNeighbours)
                .collect(Collectors.toList());
    }

    //- get countries in <continent> with population larger than <population> : /continents/<continentName>/countries?minPopulation=<minimum population> -> returns list of Country objects
    public List<Country> getCountriesOnContinentLargerThenNumber(String continent, long largerThen) {

        return countries.stream()
                .filter(c -> c.getContinent().equals(continent))
                .filter(p -> p.getPopulation() > largerThen)
                .collect(toList());
    }

    //- get countries that neighbor X but not neighbor Y : /countries?includeNeighbour=<includedNeighbourCode>&excludeNeighbour=<excludedNeighbourCode> -> returns list of Country objects
    public List<Country> gotNeighbourXandNotY(String haveNeighbour, String notNeighbour) {

        return countries.stream()
                .filter(country -> country.getNeighbours().contains(haveNeighbour) && !country.getNeighbours().contains(notNeighbour))
                .collect(toList());
    }

    //- get map from country to population : /countries/population -> returns map from String to Long
    public Map<String, Long> mapFromCountryToPopulation() {

        return countries.stream()
                .collect(toMap(Country::getName,
                        Country::getPopulation));
    }

    //- get map from continent to list of countries : /continents/countries  -> returns Map from String to List<Country>
    public Map<String, List<Country>> mapContinentToListOfCountries() {
        return countries.stream()
                .collect(Collectors.groupingBy(Country::getContinent));
    }

}
