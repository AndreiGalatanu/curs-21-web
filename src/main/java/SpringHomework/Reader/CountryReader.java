package SpringHomework.Reader;

import SpringHomework.POJO.Country;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CountryReader {


    public List<Country> readCountries(String filename) throws FileNotFoundException {
        return new BufferedReader(new FileReader(filename))
                .lines()
                .map(this::readCountry)
                .collect(Collectors.toList());


    }


    private Country readCountry(String line) {
        final String[] token = line.split("[|]");

        return new Country(
                token[0],
                token[1],
                Long.parseLong(token[2]),
                Long.parseLong(token[3]),
                token[4],
                token.length > 5 ? parseNeighbours(token[5]) : Collections.emptyList()
        );
    }

    private List<String> parseNeighbours(String neighbours) {

        Scanner scanner = new Scanner(neighbours);
        scanner.useDelimiter("~");
        List<String> result = new ArrayList<>();
        while (scanner.hasNext()) {
            result.add(scanner.next());
        }
        return result;
    }
}
