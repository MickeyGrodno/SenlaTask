package eu.senla.shabalin;

import eu.senla.shabalin.enums.Column;
import eu.senla.shabalin.enums.SortBy;
import eu.senla.shabalin.pageobjects.ComputerDatabasePage;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComputerDatabaseTest {
    private ComputerDatabasePage page = new ComputerDatabasePage();
    private SoftAssertions assertions = new SoftAssertions();

    @BeforeEach
    public void beforeTest() {
        page.openFirstPage();
    }

    @Test
    public void computerNameSortByAscTest() {
        page.sortAllBy(Column.NAME, SortBy.ASC);
        List<String> firstPageList = page.getAllComputerNameInString(Column.NAME);

        page.openPenultimatePage();
        page.sortAllBy(Column.NAME, SortBy.ASC);
        List<String> penultimatePageList = page.getAllComputerNameInString(Column.NAME);

        List<String> sortedFirstPageList = new ArrayList<>(firstPageList);
        List<String> sortedPenultimatePageList = new ArrayList<>(penultimatePageList);

        Collections.sort(sortedFirstPageList);
        Collections.sort(sortedPenultimatePageList);

        assertions.assertThat(firstPageList.equals(sortedFirstPageList))
                .as("Равенство списка первой страницы и отсортированного вручную")
                .isEqualTo(true);
        assertions.assertThat(penultimatePageList.equals(sortedPenultimatePageList))
                .as("Равенство списка предпоследней страницы и отсортированного вручную")
                .isEqualTo(true);
        assertions.assertAll();
    }
        @Test
        public void computerNameSortByDescTest() {
            page.sortAllBy(Column.NAME, SortBy.DESC);
            List<String> firstPageList = page.getAllComputerNameInString(Column.NAME);

            page.openPenultimatePage();
            page.sortAllBy(Column.NAME, SortBy.DESC);
            List<String> penultimatePageList = page.getAllComputerNameInString(Column.NAME);

            List<String> sortedFirstPageList = new ArrayList<>(firstPageList);
            List<String> sortedPenultimatePageList = new ArrayList<>(penultimatePageList);

            Collections.sort(sortedFirstPageList);
            Collections.reverse(sortedFirstPageList);
            Collections.sort(sortedPenultimatePageList);
            Collections.reverse(sortedPenultimatePageList);

            assertions.assertThat(firstPageList.equals(sortedFirstPageList))
                    .as("Равенство списка первой страницы и отсортированного вручную")
                    .isEqualTo(true);
            assertions.assertThat(penultimatePageList.equals(sortedPenultimatePageList))
                    .as("Равенство списка предпоследней страницы и отсортированного вручную")
                    .isEqualTo(true);
            assertions.assertAll();
        }

    @Test
    public void computerCompanySortByAscTest() {
        page.sortAllBy(Column.COMPANY, SortBy.ASC);
        List<String> firstPageList = page.getAllComputerNameInString(Column.COMPANY);

        page.openPenultimatePage();
        page.sortAllBy(Column.COMPANY, SortBy.ASC);
        List<String> penultimatePageList = page.getAllComputerNameInString(Column.COMPANY);

        List<String> sortedFirstPageList = new ArrayList<>(firstPageList);
        List<String> sortedPenultimatePageList = new ArrayList<>(penultimatePageList);

        Collections.sort(sortedFirstPageList);
        Collections.sort(sortedPenultimatePageList);

        assertions.assertThat(firstPageList.equals(sortedFirstPageList))
                .as("Равенство списка первой страницы и отсортированного вручную")
                .isEqualTo(true);
        assertions.assertThat(penultimatePageList.equals(sortedPenultimatePageList))
                .as("Равенство списка предпоследней страницы и отсортированного вручную")
                .isEqualTo(true);
        assertions.assertAll();
    }

    @Test
    public void computerCompanySortByDescTest() {
        page.sortAllBy(Column.COMPANY, SortBy.DESC);
        List<String> firstPageList = page.getAllComputerNameInString(Column.COMPANY);

        page.openPenultimatePage();
        page.sortAllBy(Column.COMPANY, SortBy.DESC);
        List<String> penultimatePageList = page.getAllComputerNameInString(Column.COMPANY);

        List<String> sortedFirstPageList = new ArrayList<>(firstPageList);
        List<String> sortedPenultimatePageList = new ArrayList<>(penultimatePageList);

        Collections.sort(sortedFirstPageList);
        Collections.reverse(sortedFirstPageList);
        Collections.sort(sortedPenultimatePageList);
        Collections.reverse(sortedPenultimatePageList);

        assertions.assertThat(firstPageList.equals(sortedFirstPageList))
                .as("Равенство списка первой страницы и отсортированного вручную")
                .isEqualTo(true);
        assertions.assertThat(penultimatePageList.equals(sortedPenultimatePageList))
                .as("Равенство списка предпоследней страницы и отсортированного вручную")
                .isEqualTo(true);
        assertions.assertAll();
    }

    @Test
    public void checkTenRowsInPage() {
        Assertions.assertEquals(10, page.getAllRowsFromPage().size());
    }
    }
