package com.vinz.taxman.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testing Receipt ID generator")
public class IdGeneratorServiceTests
{

    @InjectMocks
    IdGeneratorService testedIdGeneratorService;

    @Test
    void testIdGeneration()
    {

        /* Prepare */

        /* Execute */
        Set<String> ids = new HashSet<>(10);

        for (int i = 0; i < 10; i++) {

            ids.add(testedIdGeneratorService.generate());
        }

        /* Verify */

        // We have 10 different Ids?
        assertThat(ids.size()).isEqualTo(10);

        // Verify not null id
        assertThat(ids.contains(null)).isFalse();
    }
}
