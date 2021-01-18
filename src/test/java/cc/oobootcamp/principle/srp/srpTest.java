package cc.oobootcamp.principle.srp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class srpTest {

    @Test
    void should_return_date_without_slash() {
        String source = "2021/01/18";
        StringConverter stringConverter = new StringConverter();

        String convertResult = stringConverter.convertSlash(source);

        assertThat(convertResult).isEqualTo("20210118");
    }

    @Test
    void should_return_date_without_horizontal_bar() {
        String source = "2021-01-18";
        StringConverter stringConverter = new StringConverter();

        String convertResult = stringConverter.convertHorizontalBar(source);

        assertThat(convertResult).isEqualTo("20210118");
    }

    @Test
    void should_return_statistic() {
        String source = "2021/1/18";
        StringStatistic stringStatistic = new StringStatistic();

        String convertResult = stringStatistic.count(source);

        assertThat(convertResult).isEqualTo("{0=1, 1=1, 2=1, 8=1, /=1}");
    }
}