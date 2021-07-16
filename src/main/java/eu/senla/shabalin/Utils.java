package eu.senla.shabalin;

import com.codeborne.selenide.ElementsCollection;
import eu.senla.shabalin.enums.SortBy;
import org.assertj.core.api.SoftAssertions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$;

public class Utils {
    public static List<List<String>> collectionSorter(SortBy sortBy, List<String> list1, List<String> list2) {
        if (sortBy == SortBy.ASC) {
            Collections.sort(list1);
            Collections.sort(list2);
        } else {
            list1.sort(Collections.reverseOrder());
            list2.sort(Collections.reverseOrder());
        }
        return new ArrayList<>(Arrays.asList(list1, list2));
    }

    public static void beforeAndAfterTwoCollectionAssertion(List<String> firstBefore, List<String> firstAfter,
                                                            List<String> secondBefore, List<String> secondAfter) {
        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(firstBefore.equals(firstAfter))
                .as("Равенство списка первой страницы и отсортированного вручную")
                .isEqualTo(true);
        assertions.assertThat(secondBefore.equals(secondAfter))
                .as("Равенство списка предпоследней страницы и отсортированного вручную")
                .isEqualTo(true);
        assertions.assertAll();
    }

    private static void headRowPrinter() {
        $$("thead>tr>th").texts().forEach(text -> System.out.print(text+" | "));
        System.out.println();
    }

    public static void rowsPrinter(ElementsCollection allRows) {
        headRowPrinter();
        allRows.forEach(element ->
        {
            element.$$("td").texts().forEach(text -> System.out.print(text+" | "));
            System.out.println();
        });
    }
}
