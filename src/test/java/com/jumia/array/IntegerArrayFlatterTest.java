package com.jumia.array;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class IntegerArrayFlatterTest {
    private IntegerArrayFlatter integerArrayFlatter = new IntegerArrayFlatter();

    @Test
    public void should_return_empty_array_with_not_an_array_object() {
        // Given
        Integer notAnArray = 1;

        // When
        Integer[] flat = integerArrayFlatter.flat(notAnArray);

        // Then
        assertThat(flat).isEmpty();
    }

    @Test
    public void should_return_array_of_int_with_no_nested_array() {
        // Given
        Object[] toPrint = {1, 2, 3, 4};

        // When
        Integer[] result = integerArrayFlatter.flat(toPrint);

        // Then
        Integer[] expected = {1, 2, 3, 4};
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void should_return_array_of_int_with_nested_array() {
        // Given
        Object[] nestedArray1 = {3};
        Object[] nestedArray2 = {1, 2, nestedArray1};
        Object[] array = {nestedArray2, 4};

        // When
        Integer[] result = integerArrayFlatter.flat(array);

        // Then
        Integer[] expected = {1, 2, 3, 4};
        assertThat(result).isEqualTo(expected);
    }
}