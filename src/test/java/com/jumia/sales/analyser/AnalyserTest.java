package com.jumia.sales.analyser;

import org.junit.Test;

import java.io.IOException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

public class AnalyserTest {

    private Analyser analyser = new Analyser();

    @Test
    public void should_analyse_real_file() throws IOException {
        // Given
        String fileLocation = getFileLocationFromClasspath("2015-07-31_sales.json");

        // When
        analyser.analyse(fileLocation);

        // Then
        String topCategoriesByWeekDay = analyser.report();
        assertThat(topCategoriesByWeekDay).isEqualTo("MONDAY=>Home & Office\n" +
                "TUESDAY=>Cameras & Electronics\n" +
                "WEDNESDAY=>Computing\n" +
                "THURSDAY=>Cameras & Electronics\n" +
                "FRIDAY=>Cameras & Electronics\n" +
                "SATURDAY=>Cameras & Electronics\n" +
                "SUNDAY=>Phones & Tablets\n");
    }

    @Test
    public void should_analyse_one_sale_file() throws IOException {
        // Given
        String fileLocation = getFileLocationFromClasspath("one_sale.json");

        // When
        analyser.analyse(fileLocation);

        // Then
        String topCategoriesByWeekDay = analyser.report();
        assertThat(topCategoriesByWeekDay).isEqualTo("MONDAY=>\n" +
                "TUESDAY=>\n" +
                "WEDNESDAY=>Home & Office\n" +
                "THURSDAY=>\n" +
                "FRIDAY=>\n" +
                "SATURDAY=>\n" +
                "SUNDAY=>\n");
    }

    private String getFileLocationFromClasspath(String fileName) {
        URL resource = this.getClass().getClassLoader().getResource(fileName);
        if (resource != null) {
            return resource.getFile();
        }
        throw new IllegalArgumentException("File not found in classpath " + fileName);
    }
}