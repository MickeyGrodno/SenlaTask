package eu.senla.shabalin;

import eu.senla.shabalin.enums.Column;
import eu.senla.shabalin.enums.SortBy;
import eu.senla.shabalin.pageobjects.ComputerDatabasePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.executeJavaScript;

public class ComputerDatabaseTest {
    private ComputerDatabasePage page = new ComputerDatabasePage();

    @BeforeEach
    public void beforeTest() {
        page.openFirstPage();
    }

    @Test
    public void printAllRowsTest() {
        Utils.rowsPrinter(page.getAllRowsFromPage());
    }

    @Test
    public void computerNameSortByAscTest() {
        page.sortAllBy(Column.NAME, SortBy.ASC);
        List<String> firstPageList = page.getComputerColumnInString(Column.NAME);

        page.openPenultimatePage();
        page.sortAllBy(Column.NAME, SortBy.ASC);
        List<String> penultimatePageList = page.getComputerColumnInString(Column.NAME);

        List<List<String>> sortedLists = Utils.collectionSorter(SortBy.ASC,
                new ArrayList<>(firstPageList), new ArrayList<>(penultimatePageList));

        Utils.beforeAndAfterTwoCollectionAssertion(firstPageList, sortedLists.get(0),
                penultimatePageList, sortedLists.get(1));
    }
        @Test
        public void computerNameSortByDescTest() {
            page.sortAllBy(Column.NAME, SortBy.DESC);
            List<String> firstPageList = page.getComputerColumnInString(Column.NAME);

            page.openPenultimatePage();
            page.sortAllBy(Column.NAME, SortBy.DESC);
            List<String> penultimatePageList = page.getComputerColumnInString(Column.NAME);

            List<List<String>> sortedLists = Utils.collectionSorter(SortBy.DESC,
                    new ArrayList<>(firstPageList), new ArrayList<>(penultimatePageList));

            Utils.beforeAndAfterTwoCollectionAssertion(firstPageList, sortedLists.get(0),
                    penultimatePageList, sortedLists.get(1));
        }

    @Test
    public void computerCompanySortByAscTest() {
        page.sortAllBy(Column.COMPANY, SortBy.ASC);
        List<String> firstPageList = page.getComputerColumnInString(Column.COMPANY);

        page.openPenultimatePage();
        page.sortAllBy(Column.COMPANY, SortBy.ASC);
        List<String> penultimatePageList = page.getComputerColumnInString(Column.COMPANY);

        List<List<String>> sortedLists = Utils.collectionSorter(SortBy.ASC,
                new ArrayList<>(firstPageList), new ArrayList<>(penultimatePageList));

        Utils.beforeAndAfterTwoCollectionAssertion(firstPageList, sortedLists.get(0),
                penultimatePageList, sortedLists.get(1));
    }

    @Test
    public void computerCompanySortByDescTest() {
        page.sortAllBy(Column.COMPANY, SortBy.DESC);
        List<String> firstPageList = page.getComputerColumnInString(Column.COMPANY);

        page.openPenultimatePage();
        page.sortAllBy(Column.COMPANY, SortBy.DESC);
        List<String> penultimatePageList = page.getComputerColumnInString(Column.COMPANY);

        List<List<String>> sortedLists = Utils.collectionSorter(SortBy.DESC,
                new ArrayList<>(firstPageList), new ArrayList<>(penultimatePageList));

        Utils.beforeAndAfterTwoCollectionAssertion(firstPageList, sortedLists.get(0),
                penultimatePageList, sortedLists.get(1));
    }

    @Test
    public void checkTenRowsInPagePositiveTest() {
        Assertions.assertEquals(10, page.getAllRowsFromPage().size());
    }

    @Test
    public void checkTenRowsInPageNegativeTest() {
        int sizeBeforeDeleteRow = page.getAllRowsFromPage().size();
        executeJavaScript("return document.querySelectorAll('tbody>tr')[0].remove();");
        int sizeAfterDeleteRow = page.getAllRowsFromPage().size();
        Assertions.assertEquals(sizeBeforeDeleteRow, sizeAfterDeleteRow);
    }
}
